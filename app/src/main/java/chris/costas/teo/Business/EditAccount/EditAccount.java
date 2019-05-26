package chris.costas.teo.Business.EditAccount;

import androidx.appcompat.app.AppCompatActivity;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import chris.costas.teo.R;
import model.classes.CompanyAccount;
import model.services.AccountService;

public class EditAccount extends AppCompatActivity {

    //TODO Find a way to get currentUser from login activity

    CompanyAccount currentUser = AccountService.getCompany();
    EditText newName_Text = findViewById(R.id.NewNameTextField);
    EditText newRentalRange_Text = findViewById(R.id.NewRentalRangeTextField);
    EditText newAddress_Text = findViewById(R.id.NewAddressTextField);
    EditText newPolicy_Text = findViewById(R.id.NewPolicyTextField);
    EditText newDescription_Text = findViewById(R.id.NewDescriptionTextField);
    Button saveChangesBtn;
    String newName, newAddress, newPolicy, newDescription;
    float newRentalRange;

    List<Address> addressList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_account);

        newName_Text.setText(currentUser.getCompanyName());
        newRentalRange_Text.setText(String.valueOf(currentUser.getRange()));
        newPolicy_Text.setText(currentUser.getPolicy());
        newDescription_Text.setText(currentUser.getDescription());

        saveChangesBtn = findViewById(R.id.SaveChangesButton);

        addressList = new ArrayList<Address>();

        saveChangesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newName = findViewById(R.id.NewNameTextField).toString();
                newRentalRange = (float)(R.id.NewRentalRangeTextField);
                newAddress = findViewById(R.id.NewAddressTextField).toString();
                newPolicy = findViewById(R.id.NewPolicyTextField).toString();
                newDescription = findViewById(R.id.NewDescriptionTextField).toString();
                UpdateAccountData(currentUser, newName, newRentalRange, newAddress, newPolicy, newDescription);
            }
        });
    }


    void UpdateAccountData(CompanyAccount currentUser, String name, float rentalRange, String newAddress, String policy, String description){
        currentUser.setCompanyName(name);
        currentUser.setRange(rentalRange);
        currentUser.setPolicy(policy);
        currentUser.setDescription(description);
        AccountService.updateAccount(currentUser);
        try {
            SetCoordinates();
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

}
