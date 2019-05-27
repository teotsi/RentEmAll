package chris.costas.teo.Client.PickVehicle;

import android.content.Context;
import android.content.res.AssetManager;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.FragmentManager;

import java.time.LocalDate;
import java.util.ArrayList;

import model.classes.Vehicle;

public class PickVehiclePresenter implements PickVehicleContract.AdapterPresenter {

    private final ArrayList<Vehicle> mVehicles;
    private AssetManager assetManager;

    public PickVehiclePresenter(ArrayList<Vehicle> mVehicles, AssetManager assetManager) {
        this.mVehicles = mVehicles;
        this.assetManager = assetManager;
    }

    @Override
    public void onBindRowViewAtPosition(PickVehicleContract.MvpView holder, int position) {
        Vehicle vehicle = mVehicles.get(position);
        holder.setID(vehicle.getId());
        holder.setVehicleData(vehicle.getBrand() + " " + vehicle.getModel());
        holder.setPicture(vehicle.getPic(), assetManager, vehicle.getBrand(), vehicle.getModel());
    }

    @Override
    public int getRows() {
        return mVehicles.size();
    }

    @Override
    public void handleInfoClick(int position, Context mContext) {
        Vehicle vehicle = mVehicles.get(position);
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setMessage("Info:\n" +
                "Brand:" + vehicle.getBrand() + "\n" +
                "Model:" + vehicle.getModel() + "\n" +
                "Type:" + vehicle.getType() + "\n" +
                "Seats:" + vehicle.getSeats() + "\n" +
                "Fuel type:" + vehicle.getFuelType() + "\n" +
                "PCE:" + vehicle.isPce() + "\n" +
                "Rate:" + vehicle.getRate() + "\n" +
                "Extra:" + vehicle.getExtra() + "\n" +
                "Transmission type:" + vehicle.getTransmissionType() + "\n")
                .setCancelable(true);
        builder.create().show();
    }

    @Override
    public void handleRentClick(FragmentManager fragmentManager, LocalDate startDate, LocalDate endDate, PickVehicle activity, int position) {
        ClientRentDialog dialog = ClientRentDialog.display(fragmentManager, mVehicles.get(position), startDate, endDate, activity);
    }

}
