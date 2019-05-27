package chris.costas.teo.Business.EditAccount;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.widget.EditText;

import java.io.IOException;
import java.util.List;

import model.classes.CompanyAccount;

public interface EditAccountContract {
    interface MvpView{

        boolean isEmpty(EditText text);

        void EditAccountButtonClicked();

        void SetCoordinates() throws IOException;

        void ToastMessages(String id);

    }
    interface Presenter{

        void reverseGeocoding(List<Address> reverseAddress, Geocoder geo, CompanyAccount currentUser, EditText newAddress_Text);

        void handleEditAccountButtonClick();

        void DataUpdate(CompanyAccount currentUser, String name, float rentalRange, String newAddress, String policy, String description, String afm, EditText newAddress_Text);

        boolean CheckEmpty(EditText text);

        void LongLat(Context con, List<Address> addressList, String newAddress, CompanyAccount currentUser) throws IOException;
    }

}
