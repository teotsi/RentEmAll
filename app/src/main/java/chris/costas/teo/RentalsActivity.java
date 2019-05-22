package chris.costas.teo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.widget.TextView;

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
        rentals = AccountService.getRentals();//
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
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Rental Info");
        TextView text=new TextView(this);
        text.setText(AccountService.getRentals().get(position).toSrting());
        builder.setView(text);
        builder.show();
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
