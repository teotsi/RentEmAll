package chris.costas.teo;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.Switch;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import model.classes.Vehicle;
import model.services.AccountService;

public class EditVehicleDialog extends DialogFragment {

    public static final String TAG = "edit_vehicle_dialog";
    private static Vehicle vehicle;
    private Toolbar toolbar;
    private Spinner seats;
    private Spinner transmission;
    private Spinner fuel;
    private Spinner type;
    private EditText brand;
    private EditText model;
    private Switch pce;
    private Switch available;
    private EditText rate;
    private EditText extra;
    private Button save;

    public static EditVehicleDialog display(FragmentManager fragmentManager, Vehicle vehicle) {
        EditVehicleDialog exampleDialog = new EditVehicleDialog();
        exampleDialog.show(fragmentManager, TAG);
        EditVehicleDialog.vehicle = vehicle;
        System.out.println("Swapped");
        return exampleDialog;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.AppTheme_FullScreenDialog);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.edit_vehicle_dialog, container, false);

        toolbar = view.findViewById(R.id.toolbar);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        toolbar.setNavigationOnClickListener(v -> dismiss());
        toolbar.setTitle("Some Title");
        toolbar.inflateMenu(R.menu.edit_vehicle_dialog);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if(item.getItemId()==R.id.action_save){
                    //TODO
                }return false;
            }
        });
        seats=(Spinner)view.findViewById(R.id.edit_seats);
        transmission = (Spinner) view.findViewById(R.id.edit_transmission_type);
        fuel = (Spinner) view.findViewById(R.id.edit_fuel_type);
        type = (Spinner) view.findViewById(R.id.edit_type);
        brand = (EditText) view.findViewById(R.id.edit_brand);
        model = (EditText) view.findViewById(R.id.edit_model);
        pce = (Switch) view.findViewById(R.id.edit_pce);
        available=(Switch) view.findViewById(R.id.edit_available);
        rate = (EditText) view.findViewById(R.id.edit_rate);
        extra = (EditText) view.findViewById(R.id.edit_extra);
        seats.setSelection(((ArrayAdapter)seats.getAdapter()).getPosition(String.valueOf(vehicle.getSeats())));
        transmission.setSelection(((ArrayAdapter)transmission.getAdapter()).getPosition(vehicle.getTransmissionType()));
        fuel.setSelection(((ArrayAdapter)fuel.getAdapter()).getPosition(vehicle.getFuelType()));
        type.setSelection(((ArrayAdapter)type.getAdapter()).getPosition(vehicle.getType()));
        brand.setText(vehicle.getBrand());
        model.setText(vehicle.getModel());
        pce.setChecked(vehicle.isPce());
        available.setChecked(vehicle.isAvailable());
        rate.setText(String.valueOf(vehicle.getRate()));
        extra.setText(vehicle.getExtra());
        toolbar.setOnMenuItemClickListener(item -> {
            dismiss();
            return true;
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            dialog.getWindow().setLayout(width, height);
            dialog.getWindow().setWindowAnimations(R.style.AppTheme_Slide);
        }
    }

}
