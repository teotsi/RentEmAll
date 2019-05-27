package chris.costas.teo.Business.NewAccount;

import android.content.Context;
import android.location.Address;
import android.view.View;
import android.widget.EditText;

import java.io.IOException;
import java.util.List;

public interface CreateNewAccountContract {
    interface MvpView{
        void CreateAccountButtonClicked();

        boolean checkDataEntered(EditText CompAddress, EditText CompName, EditText Policy, EditText Description, EditText IBAN, EditText RentalRange, double latitude, double longitude , EditText CompEmail, EditText Password, EditText TIN);

        boolean isEmpty(EditText text);

        boolean isEmail(EditText text);

        boolean infoExists(EditText text, String dataType);

        boolean SetCoordinates(Context con, List<Address> addressList, EditText CompAddress, double latitude, double longitude) throws IOException;

        void ToastMessages(String id);
    }
    interface Presenter{
        void handleCreateAccountButtonClick();

        boolean DataValidation(EditText CompAddress, EditText CompName, EditText Policy, EditText Description, EditText IBAN, EditText RentalRange, double latitude, double longitude , EditText CompEmail, EditText Password, EditText TIN);

        boolean CheckEmpty(EditText text);

        boolean CheckEmail(EditText text);

        boolean InfoExistence(EditText text, String dataType);

        boolean LongLat(Context con, List<Address> addressList, EditText CompAddress, double latitude, double longitude) throws IOException;

        void newAccount(EditText CompName, EditText Policy, EditText Description, EditText IBAN, EditText RentalRange, double latitude, double longitude , EditText CompEmail, EditText Password, EditText TIN);
    }
}
