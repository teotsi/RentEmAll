package chris.costas.teo.Client.PickVehicle;

import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;

import androidx.appcompat.app.AlertDialog;

import java.time.LocalDate;

import model.classes.Customer;
import model.classes.Vehicle;
import model.services.AccountService;
import model.services.SearchService;

public class RentDialogPresenter implements PickVehicleContract.RentDialogPresenter {
    private PickVehicleContract.RentDialogMvpView mView;

    public RentDialogPresenter(PickVehicleContract.RentDialogMvpView mView) {
        this.mView = mView;
    }

    @Override
    public void saveRentingApp(String name, String surname, String telephone, String email, Context context, Vehicle vehicle, LocalDate startDate, LocalDate endDate) {
        Customer customer = new Customer(name, surname, telephone, email);
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage("Choose payment method.")
                .setCancelable(false)
                .setPositiveButton("Credit Card", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SearchService.submitApplication(SearchService.createApplication(vehicle.getCompanyId(), vehicle, startDate, endDate, LocalDate.now(),
                                String.valueOf(email.hashCode()), "customer location", "company location", customer));
                        mView.toMain(context);
                    }
                })
                .setNeutralButton("PayPal", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SearchService.submitApplication(SearchService.createApplication(vehicle.getCompanyId(), vehicle, startDate, endDate, LocalDate.now(),
                                String.valueOf(email.hashCode()), "customer location", "company location", customer));
                        mView.toMain(context);
                    }
                });
        builder.create().show();

    }

    @Override
    public boolean isEmpty(String text){
        return TextUtils.isEmpty(text);
    }


    @Override
    public boolean emailIsValid(String text) {
        return AccountService.emailIsValid(text);
    }
}
