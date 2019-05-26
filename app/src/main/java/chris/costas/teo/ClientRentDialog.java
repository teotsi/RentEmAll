package chris.costas.teo;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import java.time.LocalDate;

import model.classes.Customer;
import model.classes.Vehicle;
import model.services.AccountService;
import model.services.SearchService;

public class ClientRentDialog extends DialogFragment {

    public static final String TAG = "client_rent_dialog";
    private static int position;
    private static boolean status = false;
    private static Vehicle vehicle;
    private static LocalDate startDate;
    private static LocalDate endDate;
    private Toolbar toolbar;
    private EditText name;
    private EditText surname;
    private EditText telephone;
    private EditText email;

    public static ClientRentDialog display(FragmentManager fragmentManager, Vehicle vehicle, LocalDate startDate, LocalDate endDate) {
        ClientRentDialog.vehicle = vehicle;
        ClientRentDialog.startDate = startDate;
        ClientRentDialog.endDate = endDate;
        ClientRentDialog exampleDialog = new ClientRentDialog();
        exampleDialog.show(fragmentManager, TAG);
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
        View view = inflater.inflate(R.layout.client_rent_dialog, container, false);

        toolbar = view.findViewById(R.id.toolbar);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        toolbar.setNavigationOnClickListener(v -> dismiss());
        toolbar.setTitle("Add vehicle(s)");
        toolbar.inflateMenu(R.menu.client_rent_dialog);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                System.out.println(item.getItemId());
                if (item.getItemId() == R.id.action_save) {
                    //TODO
                }
                return false;
            }
        });
        toolbar.findViewById(R.id.action_save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attemptSave();
            }
        });
        name = view.findViewById(R.id.customer_name);
        surname = view.findViewById(R.id.customer_surname);
        email = view.findViewById(R.id.customer_email);
        telephone = view.findViewById(R.id.customer_telephone);
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


    public void attemptSave() {
        View errorView = null;
        boolean error = false;
        name.setError(null);
        surname.setError(null);
        email.setError(null);
        telephone.setError(null);
        if (TextUtils.isEmpty(name.getText().toString())) {
            name.setError("This field is required");
            errorView = name;
            error = true;
        }
        if (TextUtils.isEmpty(surname.getText().toString())) {
            surname.setError("This field is required");
            errorView = surname;
            error = true;
        }
        if (TextUtils.isEmpty(telephone.getText().toString())) {
            telephone.setError("This field is required");
            errorView = telephone;
            error = true;
        }
        if (TextUtils.isEmpty(email.getText().toString())) {
            email.setError("This field is required");
            errorView = email;
            error = true;
        } else if (!AccountService.emailIsValid(email.getText().toString())) {
            email.setError("Invalid email");
            errorView = email;
            error = true;
        }

        if (error) {
            errorView.requestFocus();
        } else {
            Customer customer = new Customer(name.getText().toString(), surname.getText().toString(), telephone.getText().toString(), email.getText().toString());
            SearchService.submitApplication(SearchService.createApplication(vehicle.getCompanyId(), vehicle, startDate, endDate, LocalDate.now(),
                    String.valueOf(email.hashCode()), "customer location", "company location", customer));
            dismiss();
        }
    }
}
