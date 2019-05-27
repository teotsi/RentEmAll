package chris.costas.teo.Business.VehicleManagement;

import android.text.TextUtils;

import java.time.LocalDate;
import java.time.temporal.Temporal;

import model.classes.Vehicle;
import model.services.AccountService;

public class NewVehiclePresenter implements VehicleManagementContract.NewDialogPresenter {
    @Override
    public void handleSave(String brand, String model, String type, int seats, String fuelType, boolean pce, float rate, String extra, String transmissionType, LocalDate date, boolean available, int value) {
        Vehicle vehicle = new Vehicle(brand, model, type, seats, fuelType,pce,rate,extra,transmissionType,date,available);
        AccountService.addVehicle(vehicle,value);
        AccountService.save();
    }

    @Override
    public boolean isEmpty(String text) {
        return TextUtils.isEmpty(text);
    }

    @Override
    public float parseFloat(String text) throws NumberFormatException {
        return Float.parseFloat(text);
    }
}
