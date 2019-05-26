package chris.costas.teo.Client;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;

import java.util.List;

import chris.costas.teo.R;
import model.classes.Vehicle;

public class PickVehicle extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<Vehicle> vehicles;
    private RentVehicleAdapter rentVehicleAdapter;
    public static Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_vehicle);
        vehicles = (List<Vehicle>) getIntent().getSerializableExtra("vehicles");

        recyclerView = findViewById(R.id.filtered_vehicle_list);
        rentVehicleAdapter = new RentVehicleAdapter(this,vehicles, getSupportFragmentManager());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(rentVehicleAdapter);
    }
}
