package chris.costas.teo.Business.VehicleManagement;

import android.graphics.drawable.Drawable;
import android.text.TextUtils;

import model.services.AccountService;

public class EditVehiclePresenter implements VehicleManagementContract.EditDialogPresenter {
    @Override
    public void handleSave(int position, String brand, String model, String type, int seats, String fuelType, boolean pce, float rate, String extra, String transmissionType, boolean available, Drawable pic) {
        AccountService.editVehicle(position,brand,model,type,seats,fuelType,pce,rate,extra,transmissionType,available,pic);
        AccountService.save();
    }

    @Override
    public boolean isEmpty(String text){
        return TextUtils.isEmpty(text);
    }

    @Override
    public float parseFloat(String text) throws NumberFormatException{
        return Float.parseFloat(text);
    }
}
