package chris.costas.teo.Client;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import chris.costas.teo.R;
import model.classes.Vehicle;

public class FilterDialog extends DialogFragment {

    public static final String TAG = "filter_dialog";
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
    private EditText rate;
    private CheckBox seatsCheck;
    private CheckBox transmissionCheck;
    private CheckBox fuelCheck;
    private CheckBox typeCheck;
    private CheckBox brandCheck;
    private CheckBox modelCheck;
    private CheckBox pceCheck;
    private CheckBox rateCheck;

    public static FilterDialog display(FragmentManager fragmentManager) {
        FilterDialog exampleDialog = new FilterDialog();
        exampleDialog.show(fragmentManager, TAG);
        System.out.println("Swapped");
        return exampleDialog;
    }

    public static boolean getStatus() {
        return status;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.AppTheme_FullScreenDialog);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.filter_dialog, container, false);

        toolbar = view.findViewById(R.id.toolbar);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        toolbar.setNavigationOnClickListener(v -> dismiss());
        toolbar.setTitle("Filter Vehicle");
        toolbar.inflateMenu(R.menu.filter_menu);
        seats = view.findViewById(R.id.filter_seats);
        seats.setEnabled(false);
        seatsCheck = view.findViewById(R.id.seats_checkbox);

        seatsCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
               seats.setEnabled(isChecked);
            }
        });

        transmission = view.findViewById(R.id.filter_transmission_type);
        transmission.setEnabled(false);
        transmissionCheck = view.findViewById(R.id.transmission_checkbox);

        transmissionCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                transmission.setEnabled(isChecked);
            }
        });

        fuel = view.findViewById(R.id.filter_fuel_type);
        fuel.setEnabled(false);
        fuelCheck = view.findViewById(R.id.fuel_type_checkbox);

        fuelCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                fuel.setEnabled(isChecked);
            }
        });

        type = view.findViewById(R.id.filter_type);
        type.setEnabled(false);
        typeCheck = view.findViewById(R.id.type_checkbox);

        typeCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                type.setEnabled(isChecked);
            }
        });

        brand = view.findViewById(R.id.filter_brand);
        brandCheck = view.findViewById(R.id.brand_checkbox);

        brandCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                brand.setEnabled(isChecked);
            }
        });

        model = view.findViewById(R.id.filter_model);
        modelCheck = view.findViewById(R.id.model_checkbox);

        modelCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                model.setEnabled(isChecked);
            }
        });

        pce = view.findViewById(R.id.filter_pce);
        pceCheck = view.findViewById(R.id.pce_checkbox);

        pceCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                pce.setEnabled(isChecked);
            }
        });

        rate = view.findViewById(R.id.filter_rate);
        rateCheck = view.findViewById(R.id.rate_checkbox);

        rateCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                rate.setEnabled(isChecked);
            }
        });
        toolbar.findViewById(R.id.action_save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(seatsCheck.isChecked()){
                    SearchActivity.addFilter("seats",seats.getSelectedItem().toString());
                }
                if(brandCheck.isChecked()){
                    SearchActivity.addFilter("brand",brand.getText().toString());
                }
                if(modelCheck.isChecked()){
                    SearchActivity.addFilter("model",model.getText().toString());
                }
                if(typeCheck.isChecked()){
                    SearchActivity.addFilter("type",type.getSelectedItem().toString());
                }
                if(transmissionCheck.isChecked()){
                    SearchActivity.addFilter("transmissionType",transmission.getSelectedItem().toString());
                }
                if(fuelCheck.isChecked()){
                    SearchActivity.addFilter("fuelType",fuel.getSelectedItem().toString());
                }
                if(pceCheck.isChecked()){
                    SearchActivity.addFilter("pce", String.valueOf(pce.isChecked()));
                }
                if(rateCheck.isChecked()){
                    SearchActivity.addFilter("rate",rate.getText().toString());
                }
                dismiss();
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
        if (activity instanceof DialogInterface.OnDismissListener) {
            ((DialogInterface.OnDismissListener) activity).onDismiss(dialog);
        }

    }

    public void attemptSave() {
        View errorView = null;
        boolean error = false;
        brand.setError(null);
        model.setError(null);
        rate.setError(null);
        if (brandCheck.isChecked()&&TextUtils.isEmpty(brand.getText().toString())) {
            brand.setError("This field is required");
            errorView = brand;
            error = true;
        }
        if (modelCheck.isChecked() && TextUtils.isEmpty(model.getText().toString())) {
            model.setError("This field is required");
            errorView = model;
            error = true;
        }
        if (rateCheck.isChecked() && TextUtils.isEmpty(rate.getText().toString())) {
            rate.setError("This field is required");
            errorView = rate;
            error = true;
        } else if(rateCheck.isChecked()){
            try {
                Float.parseFloat(rate.getText().toString());
            } catch (NumberFormatException e) {
                rate.setError("Enter a valid number");
                errorView = rate;
                error = true;
            }
        }
        if (error) {
            errorView.requestFocus();
        } else {
        }
    }
}
