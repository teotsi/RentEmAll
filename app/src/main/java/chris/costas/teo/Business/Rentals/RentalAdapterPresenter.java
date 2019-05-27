package chris.costas.teo.Business.Rentals;

import java.util.List;

import model.classes.Rental;

public class RentalAdapterPresenter implements RentalsContract.RentalsAdapterPresenter {

    private List<Rental> rentals;

    public RentalAdapterPresenter(List<Rental> rentals) {
        this.rentals = rentals;
    }

    @Override
    public void onBindRepositoryRowViewAtPosition(int position, RentalsContract.RentalsAdapterView rowView) {
        Rental rent= rentals.get(position);
        rowView.setId(rent.getId());
        rowView.setStartDate(rent.getStartDate());
        rowView.setEndDate(rent.getEndDate());
    }

    @Override
    public List<Rental> getRentals() {
        return rentals;
    }
}
