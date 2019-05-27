package chris.costas.teo.Business.VehicleManagement;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import chris.costas.teo.R;
import io.github.yavski.fabspeeddial.FabSpeedDial;
import io.github.yavski.fabspeeddial.SimpleMenuListenerAdapter;
import model.classes.Vehicle;
import model.services.AccountService;


/**
 * Handles the process of viewing, editing,
 * deleting and adding new vehicles
 * to an existing account
 */
public class VehicleManagement extends AppCompatActivity implements DialogInterface.OnDismissListener {

    public static Context context;
    private RecyclerView recyclerView;
    private ArrayList<Vehicle> vehicles;
    private VehicleManagementAdapter vehicleManagementAdapter;
    private ManagementPresenter mPresenter;

    public static Context getAppContext() {
        return context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_management);
        context = getApplicationContext();
        vehicles = (ArrayList<Vehicle>) AccountService.getVehicles();
        mPresenter = new ManagementPresenter();
        recyclerView = findViewById(R.id.vehicle_list);
        vehicleManagementAdapter = new VehicleManagementAdapter(getApplicationContext(), vehicles, getSupportFragmentManager(), new ManagementAdapterPresenter(vehicles,getAssets()));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(vehicleManagementAdapter);
        FabSpeedDial fabSpeedDial = findViewById(R.id.fab_speed_dial);
        fabSpeedDial.setMenuListener(new SimpleMenuListenerAdapter() {
            @Override
            public boolean onMenuItemSelected(MenuItem menuItem) {
                mPresenter.handleMenuClick(getSupportFragmentManager());
                return true;
            }
        });

        ImageView delete_view = findViewById(R.id.delete_vehicle);
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        vehicleManagementAdapter.notifyDataSetChanged();
        mPresenter.save();
    }
}
