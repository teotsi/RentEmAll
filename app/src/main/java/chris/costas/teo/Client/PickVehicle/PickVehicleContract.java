package chris.costas.teo.Client.PickVehicle;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;

import androidx.fragment.app.FragmentManager;

import java.time.LocalDate;

import model.classes.Vehicle;

public interface PickVehicleContract {
    interface MvpView{
        void setID(String id);
        void setVehicleData(String vehicleData);
        void setPicture(Drawable picture, AssetManager assetManager, String brand, String model);
    }
    interface Presenter{
        void onBindRowViewAtPosition(PickVehicleContract.MvpView holder, int position);
        int getRows();
        void handleInfoClick(int position, Context mContext);
        void handleRentClick(FragmentManager fragmentManager, LocalDate startDate, LocalDate endDate, PickVehicle activity, int position);
    }
    interface PickView{
        void handleInfoClick(int position, Context mContext);
        void handleRentClick(FragmentManager fragmentManager, LocalDate startDate, LocalDate endDate, PickVehicle activity, int position);
    }
}
