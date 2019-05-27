package chris.costas.teo.Business.EditAccount;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

import model.classes.CompanyAccount;
import model.services.AccountService;

public class EditAccountPresenter implements EditAccountContract.Presenter{

    private EditAccountContract.MvpView mView;

    EditAccountPresenter(EditAccountContract.MvpView v){
        this.mView = v;
    }


    public void reverseGeocoding(List<Address> reverseAddress, Geocoder geo, CompanyAccount currentUser, EditText newAddress_Text) {
        try {
            reverseAddress = geo.getFromLocation(currentUser.getLatitude(),currentUser.getLongitude(),1);
            String addressName = reverseAddress.get(0).getAddressLine(0);
            if(addressName != null) {
                newAddress_Text.setText(addressName);
            }else{
                newAddress_Text.setText("Not valid ");

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleEditAccountButtonClick(){
        mView.EditAccountButtonClicked();
    }

    public void DataUpdate(CompanyAccount currentUser, String name, float rentalRange, String newAddress, String policy, String description, String afm, EditText newAddress_Text){
        currentUser.setCompanyName(name);
        currentUser.setRange(rentalRange);
        currentUser.setPolicy(policy);
        currentUser.setDescription(description);
        currentUser.setAfm(afm);
        AccountService.updateAccount(currentUser);
        try {
            if(!mView.isEmpty(newAddress_Text)) {
                mView.SetCoordinates();
            }
        }catch (IOException e){
            mView.ToastMessages("DataUpdate");
        }
    }

    @Override
    public boolean CheckEmpty(EditText text){
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }

    @Override
    public void LongLat(Context con, List<Address> addressList, String newAddress, CompanyAccount currentUser) throws IOException {
        Geocoder gc = new Geocoder(con);
        addressList = gc.getFromLocationName(newAddress, 1);

        if(addressList.size() != 0){
            Address add = addressList.get(0);

            currentUser.setLatitude(add.getLatitude());
            currentUser.setLongitude(add.getLongitude());
        }else{
            mView.ToastMessages("LongLat");

        }
    }
}
