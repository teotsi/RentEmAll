package chris.costas.teo.Business.Rentals;

import android.app.AlertDialog;
import android.content.Context;
import android.widget.TextView;

import java.util.List;

import model.classes.Rental;
import model.services.AccountService;

/**
 * This is the presenter of the RentalsActivity.java
 */
public class RentalsPresenter implements RentalsContract.Presenter {

    private RentalsContract.MvpView mView;
    private static List<Rental> rentals;

    public RentalsPresenter(RentalsContract.MvpView mView) {
        this.mView = mView;
    }


    @Override
    public void loadRentals() {
        rentals=AccountService.getRentals();
    }

    @Override
    public List<Rental> getRentals() {
        return rentals;
    }

    @Override
    public String getRentalInfo(int position) {
        return AccountService.getRentals().get(position).toSrting();
    }

    @Override
    public void infoDialog(int position, Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Rental Info");
        TextView text = new TextView(context);
        text.setText(getRentalInfo(position));
        builder.setView(text);
        builder.show();
    }
}
