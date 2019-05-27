package chris.costas.teo.Client.PickVehicle;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDate;
import java.util.ArrayList;

import chris.costas.teo.Business.Main.MainActivity;
import chris.costas.teo.R;
import model.classes.Vehicle;

public class PickVehicle extends AppCompatActivity {
    public static Context context;
    private RecyclerView recyclerView;
    private ArrayList<Vehicle> vehicles;
    private RentVehicleAdapter rentVehicleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_vehicle);
        vehicles = (ArrayList<Vehicle>) getIntent().getSerializableExtra("vehicles");
        LocalDate startDate = (LocalDate) getIntent().getSerializableExtra("startDate");
        LocalDate endDate = (LocalDate) getIntent().getSerializableExtra("endDate");
        recyclerView = findViewById(R.id.filtered_vehicle_list);
        PickVehiclePresenter presenter = new PickVehiclePresenter(vehicles, getAssets());
        rentVehicleAdapter = new RentVehicleAdapter(this, vehicles, getSupportFragmentManager(), startDate, endDate, this, presenter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(rentVehicleAdapter);
    }

    public void backToMain() {
        Intent intent = new Intent(PickVehicle.this, MainActivity.class);
        startActivity(intent);
    }
}
