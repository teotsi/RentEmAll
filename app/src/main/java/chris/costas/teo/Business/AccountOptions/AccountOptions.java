package chris.costas.teo.Business.AccountOptions;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import chris.costas.teo.Business.Applications.ApplicationsActivity;
import chris.costas.teo.Business.EditAccount.EditAccount;
import chris.costas.teo.Business.Main.MainActivity;
import chris.costas.teo.R;
import chris.costas.teo.Business.Rentals.RentalsActivity;
import chris.costas.teo.Business.Statistics.StatisticsActivity;
import chris.costas.teo.Business.VehicleManagement.VehicleManagement;
import model.services.AccountService;

/**
 * These are the actions employee can do with the account.
**/

public class AccountOptions extends AppCompatActivity implements View.OnClickListener, AccountOptionsContract.MvpView {

    private Button AccountInfo;
    private Button Statistics;
    private Button Rentals;
    private Button Vehicles;
    private Button Applications;
    private Button signOut;

    AccountOptionsContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_options);

        mPresenter=new AccountOptionsPresenter(this);


        TextView companyName = findViewById(R.id.CompanyNameText);
        String name = mPresenter.getCompanyName();
        companyName.setText(name);
        signOut = findViewById(R.id.signOutButton);
        AccountInfo= findViewById(R.id.InfoSettingsButton);
        Statistics= findViewById(R.id.StatisticsButton);
        Rentals= findViewById(R.id.RentalsButton);
        Vehicles= findViewById(R.id.VehiclesButton);
        Applications= findViewById(R.id.ApplicationsButton);
        AccountInfo.setOnClickListener(this);
        Statistics.setOnClickListener(this);
        Rentals.setOnClickListener(this);
        Vehicles.setOnClickListener(this);
        Applications.setOnClickListener(this);
        signOut.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.signOutButton:
               mPresenter.signOut();
                break;
            case R.id.InfoSettingsButton:
                mPresenter.handleInfoSettingsButtonClick();
                break;
            case R.id.StatisticsButton:
                mPresenter.handleStatisticsButtonClick();
                break;
            case R.id.RentalsButton:
                mPresenter.handleRentalsButtonClick();
                break;
            case R.id.VehiclesButton:
                mPresenter.handleVehiclesButtonClick();
                break;
            case R.id.ApplicationsButton:
                mPresenter.handleApplicationsButtonClick();
                break;
        }

    }

    @Override
    public void navigateToInfoSettings() {
        Intent intentInfo=new Intent(AccountOptions.this, EditAccount.class);
        startActivity(intentInfo);
    }

    @Override
    public void navigateToStatistics() {
        Intent intentStatistics=new Intent(AccountOptions.this, StatisticsActivity.class);
        startActivity(intentStatistics);
    }

    @Override
    public void navigateToRentals() {
        Intent intentRentals=new Intent(AccountOptions.this, RentalsActivity.class);
        startActivity(intentRentals);
    }

    @Override
    public void navigateToVehicles() {
        Intent intentVehicles=new Intent(AccountOptions.this, VehicleManagement.class);
        startActivity(intentVehicles);
    }

    @Override
    public void navigateToApplications() {
        Intent intentApplications=new Intent(AccountOptions.this, ApplicationsActivity.class);
        startActivity(intentApplications);
    }

    @Override
    public void navigateToSignOut() {
        AlertDialog.Builder builder = new AlertDialog.Builder(AccountOptions.this);
        builder.setMessage("Are you sure you want to sign out?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mPresenter.save();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mPresenter.cancel(dialog);
                    }
                });
        builder.create().show();
    }

    @Override
    public void ActOut() {
        Intent returnIntent = new Intent(AccountOptions.this, MainActivity.class);
        startActivity(returnIntent);
        finish();
    }

    @Override
    public void ActCancel(DialogInterface dialog) {
        dialog.cancel();
    }
}
