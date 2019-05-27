package chris.costas.teo.Business.NewAccount;

import android.location.Address;
import android.location.Geocoder;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

import model.classes.CompanyAccount;
import model.services.AccountService;
import model.services.Service;

public class CreateNewAccountPresenter implements CreateNewAccountContract.Presenter {

    private CreateNewAccountContract.MvpView mView;

    CreateNewAccountPresenter(CreateNewAccountContract.MvpView view){
        mView = view;
    }

    @Override
    public void handleCreateAccountButtonClick(){
        mView.CreateAccountButtonClicked();
    }

    @Override
    public boolean DataValidation(){
        return mView.checkDataEntered();
    }

    @Override
    public boolean CheckEmpty(EditText txt){
        return mView.isEmpty(txt);
    }

    @Override
    public boolean CheckEmail(EditText txt){
        return mView.isEmail(txt);
    }

    @Override
    public boolean InfoExistence(EditText text, String dataType){
        return mView.infoExists(text, dataType);
    }

    @Override
    public boolean LongLat() throws IOException{
        return mView.SetCoordinates();
    }



}


