package chris.costas.teo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.res.AssetManager;
import android.os.Bundle;

import java.io.IOException;
import java.util.List;

import model.classes.Rental;
import model.services.AccountService;
import model.services.Service;

public class RentalsActivity extends AppCompatActivity implements RentalAdapter.OnNoteListener{

    private RecyclerView myrecyclerview;
    private static List<Rental> rentals;
    private RentalAdapter rAdapter;
    private ApplicationDialog appDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rentals);
        AssetManager assets = this.getAssets();
        try {
            model.services.Service.companyReader(assets.open("dataset/Companies.txt"));
            Service.vehicleReader(assets.open("dataset/Vehicles.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        AccountService.login("teotsi@gmail.com","Qwerty!2");
        rentals = AccountService.getRentals();// TODO: 21/5/2019 einai kenh, na ftiaxtei kai to popup me ta info toy rental
        myrecyclerview = (RecyclerView) findViewById(R.id.RentalRecyclerView);
        rAdapter=new RentalAdapter(getApplicationContext(), rentals, this, getSupportFragmentManager());
        myrecyclerview.setLayoutManager(new LinearLayoutManager(this));
        myrecyclerview.setAdapter(rAdapter);
    }

    public static List<Rental> getApplications(){
        return rentals;
    }

    @Override
    public void onNoteClick(int position) {
        appDialog=ApplicationDialog.display(getSupportFragmentManager(), position);
    }

//    @Override
//    public void onDismiss(DialogInterface dialog) {
//        if(appDialog.getRemove()){
//            int position=appDialog.getPosition();
//            rAdapter.notifyItemRemoved(position);
//            rAdapter.notifyItemRangeChanged(position,AccountService.getPendingApplications().size());
//            rentals = AccountService.getRentals();
//            rAdapter.rentals=rentals;
//            System.out.println(AccountService.getPendingApplications().size());
//        }
//    }
}
