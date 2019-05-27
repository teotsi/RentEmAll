package chris.costas.teo.Business.VehicleManagement;

import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;

import androidx.fragment.app.FragmentManager;

import java.time.LocalDate;

public interface VehicleManagementContract {
    interface MvpView {
        void setID(String id);

        void setVehicleData(String vehicleData);

        void setPicture(Drawable picture, AssetManager assetManager, String brand, String model);

    }

    interface VehicleManagementPresenter {
        void handleMenuClick(FragmentManager fragmentManager);

        void save();
    }

    interface EditDialogPresenter {
        void handleSave(int position, String brand, String model, String type, int seats, String fuelType, boolean pce, float rate, String extra, String transmissionType, boolean available, Drawable pic);

        boolean isEmpty(String text);

        float parseFloat(String text);
    }

    interface NewDialogPresenter {
        void handleSave(String brand, String model, String type, int seats, String fuelType, boolean pce, float rate, String extra, String transmissionType, LocalDate date, boolean available, int value);

        boolean isEmpty(String text);

        float parseFloat(String text);

    }

    interface AdapterPresenter {
        void onBindRowViewAtPosition(VehicleManagementContract.MvpView holder, int position);

        int getRows();

        void handleDelete(int position, FragmentManager fragmentManager);

        void handleEdit(int position, FragmentManager fragmentManager);
    }
}
