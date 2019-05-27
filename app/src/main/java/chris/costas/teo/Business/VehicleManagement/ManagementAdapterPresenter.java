package chris.costas.teo.Business.VehicleManagement;

import android.app.FragmentManager;
import android.content.Context;
import android.content.res.AssetManager;
import android.widget.Toast;

import androidx.fragment.app.FragmentManager;

import java.util.ArrayList;

import chris.costas.teo.Client.PickVehicle.PickVehicleContract;
import model.classes.Vehicle;
import model.services.AccountService;

class ManagementAdapterPresenter implements VehicleManagementContract.AdapterPresenter {
    private final ArrayList<Vehicle> mVehicles;
    private AssetManager assetManager;

    public ManagementAdapterPresenter(ArrayList<Vehicle> mVehicles, AssetManager assetManager) {
        this.mVehicles = mVehicles;
        this.assetManager = assetManager;
    }

    @Override
    public void onBindRowViewAtPosition(VehicleManagementContract.MvpView holder, int position) {
        Vehicle vehicle = mVehicles.get(position);
        holder.setID(vehicle.getId());
        holder.setVehicleData(vehicle.getBrand()+" "+vehicle.getModel());
        holder.setPicture(vehicle.getPic(),assetManager,vehicle.getBrand(),vehicle.getModel());
    }

    @Override
    public int getRows() {
        return mVehicles.size();
    }

    @Override
    public void handleDelete(int position, FragmentManager fragmentManager) {
        AccountService.removeVehicle(position);
    }

    @Override
    public void handleEdit(int position, FragmentManager fragmentManager) {
        EditVehicleDialog dialog = EditVehicleDialog.display(fragmentManager, AccountService.getVehicles().get(position),position);
    }
}
