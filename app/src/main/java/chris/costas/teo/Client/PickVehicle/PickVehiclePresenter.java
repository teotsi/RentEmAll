package chris.costas.teo.Client.PickVehicle;

import android.content.res.AssetManager;

import java.util.ArrayList;

import model.classes.Vehicle;

public class PickVehiclePresenter {

    private final ArrayList<Vehicle> mVehicles;
    private AssetManager assetManager;
    public PickVehiclePresenter(ArrayList<Vehicle> mVehicles, AssetManager assetManager) {
        this.mVehicles = mVehicles;
        this.assetManager = assetManager;
    }

    public void onBindRowViewAtPosition(RentVehicleAdapter.CustomViewHolder holder, int position) {
        Vehicle vehicle = mVehicles.get(position);
        holder.setID(vehicle.getId());
        holder.setVehicleData(vehicle.getBrand()+" "+vehicle.getModel());
        holder.setPicture(vehicle.getPic(),assetManager, vehicle.getBrand(),vehicle.getModel());
    }

    public int getRows(){
        return mVehicles.size();
    }

}
