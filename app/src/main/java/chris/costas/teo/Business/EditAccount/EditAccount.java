package chris.costas.teo.Business.EditAccount;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
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

public class EditAccount extends AppCompatActivity implements EditAccountContract.MvpView, View.OnClickListener{

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
    Geocoder geo = new Geocoder(this);
    List<Address> reverseAddress;
    List<Address> addressList;

    EditAccountContract.Presenter mPres;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_account);

        mPres = new EditAccountPresenter(this);

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


        saveChangesBtn = findViewById(R.id.SaveChangesButton);

        addressList = new ArrayList<>();
        reverseAddress = new ArrayList<>();

        saveChangesBtn.setOnClickListener(this);

        mPres.reverseGeocoding(reverseAddress, geo, currentUser, newAddress_Text);
    }

    @Override
    public void onClick(View v) {
        mPres.handleEditAccountButtonClick();
    }

    @Override
    public void EditAccountButtonClicked(){
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

    void UpdateAccountData(CompanyAccount currentUser, String name, float rentalRange, String newAddress, String policy, String description, String afm){
        mPres.DataUpdate(currentUser, name, rentalRange, newAddress, policy, description, afm, newAddress_Text);
    }

    public void SetCoordinates() {
        try {
            mPres.LongLat(this, addressList, newAddress, currentUser);
        } catch (IOException e) {

        }
    }

    @Override
    public boolean isEmpty(EditText text){
        return mPres.CheckEmpty(text);
    }

    public void ToastMessages(String id){
        Toast t = new Toast(this);
        switch (id){
            case("DataUpdate"):
                t = Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT);
            case("LongLat"):
                t = Toast.makeText(this, "We couldn't find the address. Try adding more info(City, Postal no., etc) and check your spelling", Toast.LENGTH_SHORT);
        }
    }
}
