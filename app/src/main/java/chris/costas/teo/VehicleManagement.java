package chris.costas.teo;

import android.content.Context;
import android.content.DialogInterface;
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


/**
 * Handles the process of viewing, editing,
 * deleting and adding new vehicles
 * to an existing account
 */
public class VehicleManagement extends AppCompatActivity implements DialogInterface.OnDismissListener{

    private RecyclerView recyclerView;
    private List<Vehicle> vehicles;
    private VehicleManagementAdapter vehicleManagementAdapter;
    public static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_management);
        context= getApplicationContext();
//        AssetManager assets = this.getAssets();
//        try {
//        model.services.Service.companyReader(assets.open("dataset/Companies.txt"));
//            Service.vehicleReader(assets.open("dataset/Vehicles.txt"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        AccountService.login("teotsi@gmail.com","Qwerty!2");
        vehicles = AccountService.getVehicles();

        recyclerView = (RecyclerView) findViewById(R.id.vehicle_list);
        vehicleManagementAdapter = new VehicleManagementAdapter(getApplicationContext(),vehicles, getSupportFragmentManager());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(vehicleManagementAdapter);
        FabSpeedDial fabSpeedDial = (FabSpeedDial) findViewById(R.id.fab_speed_dial);
        fabSpeedDial.setMenuListener(new SimpleMenuListenerAdapter() {
            @Override
            public boolean onMenuItemSelected(MenuItem menuItem) {
                NewVehicleDialog.display(getSupportFragmentManager());
                return true;
            }
        });

        ImageView delete_view = (ImageView) findViewById(R.id.delete_vehicle);
    }
    @Override
    public void onDismiss(DialogInterface dialog) {
        vehicleManagementAdapter.notifyDataSetChanged();
        AccountService.save();
    }
    public static Context getAppContext(){
        return context;
    }
}
