package chris.costas.teo.Business.Rentals;

import java.util.List;

import model.classes.Rental;

/**
 * This is the presenter of the RentalsActivity.java
 */
public class RentalsPresenter implements RentalsContract.Presenter {

    private RentalsContract.MvpView mView;
    private static List<Rental> rentals;

    public RentalsPresenter(RentalsContract.MvpView mView) {
        this.mView = mView;
    }
}
