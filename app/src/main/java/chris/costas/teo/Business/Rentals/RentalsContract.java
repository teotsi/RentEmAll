package chris.costas.teo.Business.Rentals;

import android.content.Context;

import java.time.LocalDate;
import java.util.List;

import chris.costas.teo.Business.Applications.ApplicationContract;
import model.classes.Rental;

public interface RentalsContract {
    interface MvpView{

    }

    interface Presenter{

        void loadRentals();

        List<Rental> getRentals();

        String getRentalInfo(int position);

        void infoDialog(int position, Context context);

    }

    interface RentalsAdapterView{

        void setId(String id);

        void setStartDate(LocalDate startDate);

        void  setEndDate (LocalDate endDate);

    }

    interface RentalsAdapterPresenter{

        void onBindRepositoryRowViewAtPosition(int position, RentalsAdapterView rowView);

        List<Rental> getRentals();

    }
}
