package chris.costas.teo.Client.PickVehicle;

import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;

public interface PickVehicleContract {
    interface MvpView{
        void setID(String id);
        void setVehicleData(String vehicleData);
        void setPicture(Drawable picture, AssetManager assetManager, String brand, String model);
    }
    interface Presenter{
        void onBindRowViewAtPosition(RentVehicleAdapter.CustomViewHolder holder, int position);
        int getRows();
    }
}
