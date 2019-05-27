package chris.costas.teo.Business.EditAccount;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import chris.costas.teo.Business.AccountOptions.AccountOptions;
import chris.costas.teo.R;
import model.classes.CompanyAccount;
import model.services.AccountService;

public class EditAccount extends AppCompatActivity {

    //TODO Find a way to get currentUser from login activity

    CompanyAccount currentUser ;
    EditText newName_Text;
    EditText newRentalRange_Text;
    EditText newAddress_Text;
    EditText newPolicy_Text;
    EditText newDescription_Text;
    EditText TIN;
    Button saveChangesBtn;
    String newName, newAddress, newPolicy, newDescription, newAfm;
    float newRentalRange;
    Geocoder geo=new Geocoder(this);
    List<Address> reverseAddress;
    List<Address> addressList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_account);

        currentUser = AccountService.getCompany();
        newName_Text = findViewById(R.id.NewNameTextField);
        newRentalRange_Text = findViewById(R.id.NewRentalRangeTextField);
        newAddress_Text = findViewById(R.id.NewAddressTextField);
        newPolicy_Text = findViewById(R.id.NewPolicyTextField);
        newDescription_Text = findViewById(R.id.NewDescriptionTextField);
        TIN = findViewById(R.id.afm);

        newName_Text.setText(currentUser.getCompanyName());
        newRentalRange_Text.setText(String.valueOf(currentUser.getRange()));
        newPolicy_Text.setText(currentUser.getPolicy());
        newDescription_Text.setText(currentUser.getDescription());
        TIN.setText(currentUser.getAfm());
        try {
            reverseAddress = geo.getFromLocation(currentUser.getLatitude(),currentUser.getLongitude(),1);
            String addressName = reverseAddress.get(0).getAddressLine(0);
            if(addressName != null) {
                newAddress_Text.setText(addressName);
            }else{
                newAddress_Text.setText("Not valid ");

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        saveChangesBtn = findViewById(R.id.SaveChangesButton);

        addressList = new ArrayList<Address>();

        saveChangesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newName = ((EditText)findViewById(R.id.NewNameTextField)).getText().toString();
                newRentalRange = Float.parseFloat(((EditText)findViewById(R.id.NewRentalRangeTextField)).getText().toString());
                newAddress = ((EditText)findViewById(R.id.NewAddressTextField)).getText().toString();
                newPolicy = ((EditText)findViewById(R.id.NewPolicyTextField)).getText().toString();
                newDescription =((EditText)findViewById(R.id.NewDescriptionTextField)).getText().toString();
                newAfm = ((EditText)findViewById(R.id.afm)).getText().toString();

                UpdateAccountData(currentUser, newName, newRentalRange, newAddress, newPolicy, newDescription, newAfm);
                Intent intent = new Intent(EditAccount.this, AccountOptions.class);
                startActivity(intent);
            }
        });
    }


    void UpdateAccountData(CompanyAccount currentUser, String name, float rentalRange, String newAddress, String policy, String description, String afm){
        currentUser.setCompanyName(name);
        currentUser.setRange(rentalRange);
        currentUser.setPolicy(policy);
        currentUser.setDescription(description);
        currentUser.setAfm(afm);
        AccountService.updateAccount(currentUser);
        try {
            if(!isEmpty(newAddress_Text)) {
                SetCoordinates();
            }
        }catch (IOException e){
            Toast t = Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT);
            t.show();
        }
    }

    public void SetCoordinates() throws IOException{
        Geocoder gc = new Geocoder(this);
        addressList = gc.getFromLocationName(newAddress, 1);

        if(addressList.size() != 0){
            Address add = addressList.get(0);

            currentUser.setLatitude(add.getLatitude());
            currentUser.setLongitude(add.getLongitude());
        }else{
            Toast t = Toast.makeText(this, "We couldn't find the address. Try adding more info(City, Postal no., etc) and check your spelling", Toast.LENGTH_SHORT);
            t.show();
        }
    }

    boolean isEmpty(EditText text){
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }

}
