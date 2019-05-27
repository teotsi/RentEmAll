package chris.costas.teo.Client.PickVehicle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import chris.costas.teo.Business.Main.MainActivity;
import chris.costas.teo.R;
import model.classes.Vehicle;

public class PickVehicle extends AppCompatActivity implements PickVehicleContract.PickView {
    private RecyclerView recyclerView;
    private ArrayList<Vehicle> vehicles;
    private RentVehicleAdapter rentVehicleAdapter;
    public static Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_vehicle);
        vehicles = (ArrayList<Vehicle>) getIntent().getSerializableExtra("vehicles");
        LocalDate startDate = (LocalDate) getIntent().getSerializableExtra("startDate");
        LocalDate endDate= (LocalDate) getIntent().getSerializableExtra("endDate");
        recyclerView = findViewById(R.id.filtered_vehicle_list);
        PickVehiclePresenter presenter = new PickVehiclePresenter(vehicles,getAssets(), this);
        rentVehicleAdapter = new RentVehicleAdapter(this,vehicles, getSupportFragmentManager(),startDate,endDate, this,presenter );
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(rentVehicleAdapter);
    }
    public void backToMain(){
        Intent intent = new Intent(PickVehicle.this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void handleInfoClick(int position, Context mContext) {

    }

    @Override
    public void handleRentClick(FragmentManager fragmentManager, LocalDate startDate, LocalDate endDate, PickVehicle activity, int position) {

    }
}
