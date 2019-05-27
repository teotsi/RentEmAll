package chris.costas.teo.Business.NewAccount;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.provider.Telephony;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

import chris.costas.teo.R;
import model.classes.BankAccount;
import model.classes.CompanyAccount;
import model.services.AccountService;
import model.services.Service;

public class CreateNewAccountPresenter implements CreateNewAccountContract.Presenter {

    private CreateNewAccountContract.MvpView mView;
    public double latitude, longitude;

    CreateNewAccountPresenter(CreateNewAccountContract.MvpView view, double latitude, double longitude){
        this.mView = view;
        this.latitude = latitude;
        this.longitude =  longitude;
    }

    @Override
    public void handleCreateAccountButtonClick(){
        mView.CreateAccountButtonClicked();
    }

    @Override
    public boolean DataValidation(EditText CompAddress, EditText CompName, EditText Policy, EditText Description, EditText IBAN, EditText RentalRange, double latitude, double longitude , EditText CompEmail, EditText Password, EditText TIN){
        boolean flag = true;
        if(mView.isEmail(CompEmail) == false){
            CompEmail.setError("Enter a valid email");
            flag = false;
        }
        if(mView.infoExists(CompName, "name") == true){
            CompName.setError("The company name you entered already exists!");
            flag = false;
        }
        if(mView.infoExists(CompEmail, "email") == true){
            CompEmail.setError("The email you entered already exists!");
            flag = false;
        }
        if(!AccountService.passwordIsValid(Password.getText().toString())){
            Password.setError("Password must be 8-32 characters long and contain a number, an Uppercase letter, a lowercase letter, and !@#$%^&+=");
            flag = false;
        }


        if(mView.isEmpty(CompName)){
            mView.ToastMessages("CompName");
            flag = false;
        }
        if(mView.isEmpty(CompEmail)){
            mView.ToastMessages("CompEmail");
            flag = false;
        }
        if(mView.isEmpty(Password)){
            mView.ToastMessages("Password");
            flag = false;
        }
        if(mView.isEmpty(CompAddress)){
            mView.ToastMessages("CompAddress");
            flag = false;
        }
        if(mView.isEmpty(IBAN)){
            mView.ToastMessages("IBAN");
            flag = false;
        }
        if(mView.isEmpty(RentalRange)){
            mView.ToastMessages("RentalRange");
            flag = false;
        }
        if(mView.isEmpty(Policy)){
            mView.ToastMessages("Policy");
            flag = false;
        }
//        Uncomment if you want description to be necessary
//        if(isEmpty(Description)){
//            Toast t = Toast.makeText(this, "You must enter a company description", Toast.LENGTH_SHORT);
//            t.show();
//        }
        return flag;
    }

    @Override
    public boolean CheckEmpty(EditText text){
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }

    @Override
    public boolean CheckEmail(EditText text){
        CharSequence email = text.getText().toString();
        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }

    @Override
    public boolean InfoExistence(EditText text, String dataType){
        boolean nameFlag = false;
        boolean emailFlag = false;
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

    @Override
    public boolean LongLat(Context con, List<Address> addressList, EditText CompAddress) throws IOException{
        boolean coordflag = false;
        Geocoder gc = new Geocoder(con);
        addressList = gc.getFromLocationName(CompAddress.getText().toString(), 1);

        if(addressList.size() != 0){
            Address add = addressList.get(0);

            latitude = add.getLatitude();
            longitude = add.getLongitude();
            coordflag = true;
        }else{
            mView.ToastMessages("LongLat");
        }
        return  coordflag;
    }

    @Override
    public void newAccount(EditText CompName, EditText Policy, EditText Description, EditText IBAN, EditText RentalRange, double latitude, double longitude , EditText CompEmail, EditText Password, EditText TIN){
        BankAccount newBA = new BankAccount(CompName.getText().toString(), IBAN.getText().toString(), 0);
        //TODO ADD new User to dataset here
        AccountService.register(CompName.getText().toString(), Policy.getText().toString(), Description.getText().toString(), Float.parseFloat(RentalRange.getText().toString()), latitude, longitude, CompEmail.getText().toString(), Password.getText().toString(), TIN.getText().toString(),newBA);
    }

    public double getLatitude(){
        return this.latitude;
    }

    public double getLongitude(){
        return this.longitude;
    }
}


