package chris.costas.teo.Business.Rentals;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

import chris.costas.teo.Business.Applications.ApplicationDialog;
import chris.costas.teo.R;
import model.classes.Rental;
import model.services.AccountService;

public class RentalsActivity extends AppCompatActivity implements RentalAdapter.OnNoteListener, RentalsContract.MvpView {

    private RecyclerView myrecyclerview;
    private RentalAdapter rAdapter;

    RentalsContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rentals);
        mPresenter = new RentalsPresenter(this);
        mPresenter.loadRentals();
        RentalsContract.RentalsAdapterPresenter adapterPresenter=new RentalAdapterPresenter(mPresenter.getRentals());
        myrecyclerview = findViewById(R.id.RentalRecyclerView);
        rAdapter = new RentalAdapter(getApplicationContext(), mPresenter.getRentals(), this, getSupportFragmentManager(), (RentalAdapterPresenter) adapterPresenter);
        myrecyclerview.setLayoutManager(new LinearLayoutManager(this));
        myrecyclerview.setAdapter(rAdapter);
    }

    @Override
    public void onNoteClick(int position) {
       mPresenter.infoDialog(position,this);
    }
}
