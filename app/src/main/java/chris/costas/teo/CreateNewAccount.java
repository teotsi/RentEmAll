package chris.costas.teo;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import model.classes.CompanyAccount;
import model.services.Service;

public class CreateNewAccount extends AppCompatActivity {

    EditText CompName, CompEmail, Password, CompAddress, IBAN, RentalRange;
    Button CreateAccountBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_account);

        CompName = (EditText)findViewById(R.id.CompanyNameTextField);
        CompEmail = (EditText)findViewById(R.id.EmailTextField);
        Password = (EditText)findViewById(R.id.PasswordTextField);
        CompAddress = (EditText)findViewById(R.id.CompanyAddressTextField);
        IBAN = (EditText)findViewById(R.id.IBANTextField);
        RentalRange = (EditText)findViewById(R.id.RentalRangeTextField);

        CreateAccountBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                checkDataEntered();
            }
        });



    }

    void checkDataEntered() {
        if(isEmail(CompEmail) == false){
            CompEmail.setError("Enter a valid email");
        }


        if(infoExists(CompName, "name") == true){
            CompName.setError("The company name you entered already exists!");
        }
        if(infoExists(CompEmail, "email") == true){
            CompEmail.setError("The email you entered already exists!");
        }
//        if(infoExists(CompAddress, "address") == true){
//
//        }


        if(isEmpty(CompName)){
            Toast t = Toast.makeText(this, "You must enter a company name", Toast.LENGTH_SHORT);
            t.show();
        }
        if(isEmpty(CompEmail)){
            Toast t = Toast.makeText(this, "You must enter a company email", Toast.LENGTH_SHORT);
            t.show();
        }
        if(isEmpty(Password)){
            Toast t = Toast.makeText(this, "You must enter a password", Toast.LENGTH_SHORT);
            t.show();
        }
        if(isEmpty(CompAddress)){
            Toast t = Toast.makeText(this, "You must enter a company address", Toast.LENGTH_SHORT);
            t.show();
        }
        if(isEmpty(IBAN)){
            Toast t = Toast.makeText(this, "You must enter your company IBAN number", Toast.LENGTH_SHORT);
            t.show();
        }
        if(isEmpty(RentalRange)){
            Toast t = Toast.makeText(this, "You must enter your rental range", Toast.LENGTH_SHORT);
            t.show();
        }
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
//       Για να μπει το address εδω πρεπει να φτιαξουμε το maps api
//
//        else if(dataType.equals("email")){
//            List<CompanyAccount> companies = Service.getCompanies();
//            for (CompanyAccount c : companies){
//                if(c.getEmail().equals(info)){
//                    emailFlag = true;
//                    break;
//                }
//            }
//            return emailFlag;
//        }else if(dataType.equals("address")){
//            List<CompanyAccount> companies = Service.getCompanies();
//            for (CompanyAccount c : companies){
//                if(c.get().equals(info)){
//                    addressFlag = true;
//                    break;
//                }
//            }
//            return addressFlag;
       }return false;
    }
}
