package chris.costas.teo.Business.VehicleManagement;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import chris.costas.teo.R;
import model.classes.Vehicle;
import model.services.AccountService;

import static android.app.Activity.RESULT_OK;

/*Creates a dialog that allows the user to edit previous settings*/
public class EditVehicleDialog extends DialogFragment {

    public static final String TAG = "edit_vehicle_dialog";
    private static Vehicle vehicle;
    private static int position;
    private static boolean status = false;
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
    private ImageView pic;
    private Uri picUri;
    private Drawable picDrawable;
    private EditVehiclePresenter mPresenter;

    public static EditVehicleDialog display(FragmentManager fragmentManager, Vehicle vehicle, int position) {
        EditVehicleDialog exampleDialog = new EditVehicleDialog();
        exampleDialog.show(fragmentManager, TAG);
        EditVehicleDialog.vehicle = vehicle;
        EditVehicleDialog.position = position;
        return exampleDialog;
    }

    public static boolean getStatus() {
        return status;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        mPresenter= new EditVehiclePresenter();
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
        toolbar.setTitle("Edit Vehicle");
        toolbar.inflateMenu(R.menu.edit_vehicle_dialog);
        toolbar.findViewById(R.id.action_save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attemptSave();
            }
        });
        seats = view.findViewById(R.id.edit_seats);
        transmission = view.findViewById(R.id.edit_transmission_type);
        fuel = view.findViewById(R.id.edit_fuel_type);
        type = view.findViewById(R.id.edit_type);
        brand = view.findViewById(R.id.edit_brand);
        model = view.findViewById(R.id.edit_model);
        pce = view.findViewById(R.id.edit_pce);
        available = view.findViewById(R.id.edit_available);
        rate = view.findViewById(R.id.edit_rate);
        extra = view.findViewById(R.id.edit_extra);
        pic = view.findViewById((R.id.edit_image));
        seats.setSelection(((ArrayAdapter) seats.getAdapter()).getPosition(String.valueOf(vehicle.getSeats())));
        transmission.setSelection(((ArrayAdapter) transmission.getAdapter()).getPosition(vehicle.getTransmissionType()));
        fuel.setSelection(((ArrayAdapter) fuel.getAdapter()).getPosition(vehicle.getFuelType()));
        type.setSelection(((ArrayAdapter) type.getAdapter()).getPosition(vehicle.getType()));
        brand.setText(vehicle.getBrand());
        model.setText(vehicle.getModel());
        pce.setChecked(vehicle.isPce());
        available.setChecked(vehicle.isAvailable());
        rate.setText(String.valueOf(vehicle.getRate()));
        extra.setText(vehicle.getExtra());
        AssetManager assetManager = getContext().getAssets();
        try {
            InputStream pictureStream = assetManager.open("vehiclePics/" + vehicle.getBrand() + vehicle.getModel() + ".png");
            Drawable pictureDrawable = Drawable.createFromStream(pictureStream, "");
            pic.setImageDrawable(pictureDrawable);
        } catch (IOException e) {
            e.printStackTrace();
        }
        pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                startActivityForResult(gallery, 100);
            }
        });
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

    @Override
    public void onDismiss(final DialogInterface dialog) {
        super.onDismiss(dialog);
        Activity activity = getActivity();
        System.out.println(activity.getClass());
        if (activity instanceof DialogInterface.OnDismissListener) {
            ((DialogInterface.OnDismissListener) activity).onDismiss(dialog);
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 100) {
            picUri = data.getData();
            pic.setImageURI(picUri);
            try {
                InputStream inputStream = VehicleManagement.getAppContext().getContentResolver().openInputStream(picUri);
                picDrawable = Drawable.createFromStream(inputStream, picUri.toString());
            } catch (FileNotFoundException e) {
                picDrawable = getResources().getDrawable(R.drawable.ic_add_black_48dp_02);
            }
        }
    }

    public void attemptSave() {
        View errorView = null;
        boolean error = false;
        brand.setError(null);
        model.setError(null);
        rate.setError(null);
        extra.setError(null);
        if (mPresenter.isEmpty(brand.getText().toString())) {
            brand.setError("This field is required");
            errorView = brand;
            error = true;
        }
        if (mPresenter.isEmpty(model.getText().toString())) {
            model.setError("This field is required");
            errorView = model;
            error = true;
        }
        if (mPresenter.isEmpty(rate.getText().toString())) {
            rate.setError("This field is required");
            errorView = rate;
            error = true;
        } else {
            try {
                mPresenter.parseFloat(rate.getText().toString());
            } catch (NumberFormatException e) {
                rate.setError("Enter a valid number");
                errorView = rate;
                error = true;
            }
        }
        if (mPresenter.isEmpty(extra.getText().toString())) {
            extra.setError("This field is required");
            errorView = extra;
            error = true;
        }

        if (error) {
            errorView.requestFocus();
        } else {
            mPresenter.handleSave(position, brand.getText().toString(), model.getText().toString(), type.getSelectedItem().toString(), Integer.parseInt(seats.getSelectedItem().toString()),
                    fuel.getSelectedItem().toString(), pce.isChecked(), Float.parseFloat(String.valueOf(rate.getText())), extra.getText().toString(), transmission.getSelectedItem().toString(), available.isChecked(), picDrawable);
            dismiss();
        }
    }
}
