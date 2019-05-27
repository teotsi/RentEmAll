package chris.costas.teo.Business.NewAccount;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import chris.costas.teo.R;
import chris.costas.teo.Business.SignIn_Up.SignIn_UpOption;
import model.classes.BankAccount;
import model.classes.CompanyAccount;
import model.services.AccountService;
import model.services.Service;

public class CreateNewAccount extends AppCompatActivity implements CreateNewAccountContract.MvpView, View.OnClickListener{


    EditText CompName, CompEmail, Password, CompAddress, IBAN, RentalRange, Policy, Description, TIN;
    Button CreateAccountBtn;
    List<Address> addressList;
    double latitude, longitude;

    CreateNewAccountContract.Presenter newAccountPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_account);

        newAccountPresenter = new CreateNewAccountPresenter(this, latitude, longitude);

        CompName = findViewById(R.id.CompanyNameTextField);
        CompEmail = findViewById(R.id.EmailTextField);
        Password = findViewById(R.id.PasswordTextField);
        CompAddress = findViewById(R.id.CompanyAddressTextField);
        IBAN = findViewById(R.id.IBANTextField);
        RentalRange = findViewById(R.id.RentalRangeTextField);
        Policy = findViewById(R.id.PolicyTextField);
        Description = findViewById(R.id.DescriptionTextField);
        TIN = findViewById(R.id.afm);
        CreateAccountBtn = findViewById(R.id.CreateAccountButton);
        CreateAccountBtn.setOnClickListener(this);

        addressList = new ArrayList<>();
    }

    @Override
    public void onClick(View v) {
        newAccountPresenter.handleCreateAccountButtonClick();
    }


    public void CreateAccountButtonClicked(){
        if(newAccountPresenter.DataValidation(CompAddress, CompName, Policy, Description, IBAN, RentalRange, latitude, longitude, CompEmail, Password, TIN)) {
            try {
                if(newAccountPresenter.LongLat(this, addressList, CompAddress)){
                    latitude = newAccountPresenter.getLatitude();
                    longitude = newAccountPresenter.getLongitude();
                    newAccountPresenter.newAccount(CompName, Policy, Description, IBAN, RentalRange, latitude, longitude, CompEmail, Password, TIN);
                }
            } catch (IOException e) {}
        }
        Intent backTosign= new Intent(CreateNewAccount.this, SignIn_UpOption.class);
        startActivity(backTosign);
    }

    @Override
    public boolean checkDataEntered(EditText CompAddress, EditText CompName, EditText Policy, EditText Description, EditText IBAN, EditText RentalRange, double latitude, double longitude , EditText CompEmail, EditText Password, EditText TIN) {
        return newAccountPresenter.DataValidation(CompAddress, CompName, Policy, Description, IBAN, RentalRange, latitude, longitude, CompEmail, Password, TIN);
    }

    @Override
    public boolean isEmpty(EditText text){
        return newAccountPresenter.CheckEmpty(text);
    }

    @Override
    public boolean isEmail(EditText text){
        return newAccountPresenter.CheckEmail(text);
    }

    @Override
    public boolean infoExists(EditText text, String dataType){
        return newAccountPresenter.InfoExistence(text, dataType);
    }

    @Override
    public boolean SetCoordinates(Context con, List<Address> addressList, EditText CompAddress, double latitude, double longitude) throws IOException {
        return newAccountPresenter.LongLat(con, addressList, CompAddress);
    }

    public void ToastMessages(String id){
        Toast t = new Toast(this);
        switch (id){
            case("CompName"):
                t = Toast.makeText(this, "You must enter a company name", Toast.LENGTH_SHORT);
            case("CompEmail"):
                t = Toast.makeText(this, "You must enter a company email", Toast.LENGTH_SHORT);
            case("Password"):
                t = Toast.makeText(this, "You must enter a password", Toast.LENGTH_SHORT);
            case("CompAddress"):
                t = Toast.makeText(this, "You must enter a company address", Toast.LENGTH_SHORT);
            case("IBAN"):
                t = Toast.makeText(this, "You must enter your company IBAN number", Toast.LENGTH_SHORT);
            case("RentalRange"):
                t = Toast.makeText(this, "You must enter your rental range", Toast.LENGTH_SHORT);
            case("Policy"):
                t = Toast.makeText(this, "You must enter your company policy", Toast.LENGTH_SHORT);
            case("LongLat"):
                t = Toast.makeText(this, "We couldn't find the address. Try adding more info(City, Postal no., etc) and check your spelling", Toast.LENGTH_SHORT);
        }
        t.show();
    }
}
