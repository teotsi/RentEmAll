package chris.costas.teo.Business.NewAccount;

import android.view.View;
import android.widget.EditText;

import java.io.IOException;

public interface CreateNewAccountContract {
    interface MvpView{
        void CreateAccountButtonClicked();

        boolean checkDataEntered();

        boolean isEmpty(EditText text);

        boolean isEmail(EditText text);

        boolean infoExists(EditText text, String dataType);

        boolean SetCoordinates() throws IOException;
    }
    interface Presenter{
        void handleCreateAccountButtonClick();

        boolean DataValidation();

        boolean CheckEmpty(EditText text);

        boolean CheckEmail(EditText text);

        boolean InfoExistence(EditText text, String dataType);

        boolean LongLat() throws IOException;
    }
}
