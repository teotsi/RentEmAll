package chris.costas.teo;

import android.content.res.AssetManager;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.MenuItem;
import android.widget.ImageView;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import io.github.yavski.fabspeeddial.FabSpeedDial;
import io.github.yavski.fabspeeddial.SimpleMenuListenerAdapter;
import model.classes.Vehicle;
import model.services.AccountService;
import model.services.Service;


public class vehicle_management extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Vehicle> vehicles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_management);
        AssetManager assets = this.getAssets();
        try {
        model.services.Service.companyReader(assets.open("dataset/Companies.txt"));
            Service.vehicleReader(assets.open("dataset/Vehicles.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        AccountService.login("teotsi@gmail.com","Qwerty!2");
        vehicles = AccountService.getVehicles();

        recyclerView = (RecyclerView) findViewById(R.id.vehicle_list);
        VehicleAdapter vehicleAdapter = new VehicleAdapter(getApplicationContext(),vehicles, getSupportFragmentManager());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(vehicleAdapter);
        FabSpeedDial fabSpeedDial = (FabSpeedDial) findViewById(R.id.fab_speed_dial);
        fabSpeedDial.setMenuListener(new SimpleMenuListenerAdapter() {
            @Override
            public boolean onMenuItemSelected(MenuItem menuItem) {
                Vehicle vehicle = new Vehicle("Nisssan","NIzmo","great",5,"diezzel",true,532,"awesome",
                        "he", LocalDate.parse("2016-05-03"),true);
                vehicles.add(vehicle);
                vehicleAdapter.notifyItemInserted(vehicles.size()-1);
                vehicleAdapter.notifyItemRangeChanged(vehicles.size()-1,vehicles.size());
                return false;
            }
        });

        ImageView delete_view = (ImageView) findViewById(R.id.delete_vehicle);
    }
}
