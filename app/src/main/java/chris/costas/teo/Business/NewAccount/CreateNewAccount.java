package chris.costas.teo.Business.NewAccount;

import androidx.appcompat.app.AppCompatActivity;

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

public class CreateNewAccount extends AppCompatActivity {

    EditText CompName, CompEmail, Password, CompAddress, IBAN, RentalRange, Policy, Description, TIN;
    Button CreateAccountBtn;
    List<Address> addressList;
    double latitude, longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_account);

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


        addressList = new ArrayList<Address>();

        CreateAccountBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if(checkDataEntered()) {

                    try {
                        if(SetCoordinates()){

                            BankAccount newBA = new BankAccount(CompName.getText().toString(), IBAN.getText().toString(), 0);
                            //TODO ADD new User to dataset here
                            AccountService.register(CompName.getText().toString(), Policy.getText().toString(), Description.getText().toString(), Float.parseFloat(RentalRange.getText().toString()), latitude, longitude, CompEmail.getText().toString(), Password.getText().toString(), TIN.getText().toString(),newBA);
                        }
                    } catch (IOException e) {

                    }
                }
                Intent backTosign= new Intent(CreateNewAccount.this, SignIn_UpOption.class);
                startActivity(backTosign);
            }
        });



    }

    boolean checkDataEntered() {
        boolean flag = true;
        if(isEmail(CompEmail) == false){
            CompEmail.setError("Enter a valid email");


            flag = false;
        }
        if(infoExists(CompName, "name") == true){
            CompName.setError("The company name you entered already exists!");
            flag = false;
        }
        if(infoExists(CompEmail, "email") == true){
            CompEmail.setError("The email you entered already exists!");
            flag = false;
        }
        if(!AccountService.passwordIsValid(Password.getText().toString())){
            Password.setError("Password must be 8-32 characters long and contain a number, an Uppercase letter, a lowercase letter, and !@#$%^&+=");
            flag = false;
        }


        if(isEmpty(CompName)){
            Toast t = Toast.makeText(this, "You must enter a company name", Toast.LENGTH_SHORT);
            t.show();
            flag = false;
        }
        if(isEmpty(CompEmail)){
            Toast t = Toast.makeText(this, "You must enter a company email", Toast.LENGTH_SHORT);
            t.show();
            flag = false;
        }
        if(isEmpty(Password)){
            Toast t = Toast.makeText(this, "You must enter a password", Toast.LENGTH_SHORT);
            t.show();
            flag = false;
        }
        if(isEmpty(CompAddress)){
            Toast t = Toast.makeText(this, "You must enter a company address", Toast.LENGTH_SHORT);
            t.show();
            flag = false;
        }
        if(isEmpty(IBAN)){
            Toast t = Toast.makeText(this, "You must enter your company IBAN number", Toast.LENGTH_SHORT);
            t.show();
            flag = false;
        }
        if(isEmpty(RentalRange)){
            Toast t = Toast.makeText(this, "You must enter your rental range", Toast.LENGTH_SHORT);
            t.show();
            flag = false;
        }
        if(isEmpty(Policy)){
            Toast t = Toast.makeText(this, "You must enter your company policy", Toast.LENGTH_SHORT);
            t.show();
            flag = false;
        }
//        Uncomment if you want description to be necessary
//        if(isEmpty(Description)){
//            Toast t = Toast.makeText(this, "You must enter a company description", Toast.LENGTH_SHORT);
//            t.show();
//        }
        return flag;
    }

    boolean isEmpty(EditText text){
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }

    boolean isEmail(EditText text){
        CharSequence email = text.getText().toString();
        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }

    boolean infoExists(EditText text, String dataType){
        boolean nameFlag = false;
        boolean emailFlag = false;
        boolean addressFlag = false;
        CharSequence info = text.getText().toString();
        if(dataType.equals("name")){
            List<CompanyAccount> companies = Service.getCompanies();
            for (CompanyAccount c : companies){
                if(c.getCompanyName().equals(info)){
                    nameFlag = true;
                    break;
                }
            }
            return nameFlag;
        }else if(dataType.equals("email")){
            List<CompanyAccount> companies = Service.getCompanies();
            for (CompanyAccount c : companies){
                if(c.getEmail().equals(info)){
                    emailFlag = true;
                    break;
                }
            }
            return emailFlag;
       }return false;
    }

    public boolean SetCoordinates() throws IOException {
        boolean coordflag = false;
        Geocoder gc = new Geocoder(this);
        addressList = gc.getFromLocationName(CompAddress.getText().toString(), 1);

        if(addressList.size() != 0){
            Address add = addressList.get(0);

            latitude = add.getLatitude();
            longitude = add.getLongitude();
            coordflag = true;
        }else{
            Toast t = Toast.makeText(this, "We couldn't find the address. Try adding more info(City, Postal no., etc) and check your spelling", Toast.LENGTH_SHORT);
            t.show();
        }
        return  coordflag;
    }
}
