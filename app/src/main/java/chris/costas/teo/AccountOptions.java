package chris.costas.teo;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import model.classes.Rental;
import model.classes.Vehicle;
import model.services.AccountService;
import model.services.Service;

public class AccountOptions extends AppCompatActivity implements View.OnClickListener {

    private Button AccountInfo;
    private Button Statistics;
    private Button Rentals;
    private Button Vehicles;
    private Button Applications;
    private Button signOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_options);
        //no need for real
//        AssetManager assets = this.getAssets();
//        try {
//            model.services.Service.companyReader(assets.open("dataset/Companies.txt"));
//            Service.vehicleReader(assets.open("dataset/Vehicles.txt"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        AccountService.login("teotsi@gmail.com", "Qwerty!2");
        ///
        TextView companyName = (TextView) findViewById(R.id.CompanyNameText);
        String name = AccountService.getName();
        companyName.setText(name);
        signOut = (Button) findViewById(R.id.signOutButton);
        AccountInfo=(Button) findViewById(R.id.InfoSettingsButton);
        Statistics=(Button) findViewById(R.id.StatisticsButton);
        Rentals=(Button) findViewById(R.id.RentalsButton);
        Vehicles=(Button) findViewById(R.id.VehiclesButton);
        Applications=(Button) findViewById(R.id.ApplicationsButton);
        AccountInfo.setOnClickListener(this);
        Statistics.setOnClickListener(this);
        Rentals.setOnClickListener(this);
        Vehicles.setOnClickListener(this);
        Applications.setOnClickListener(this);
        signOut.setOnClickListener(this);
        System.out.println("Heeeeeeeeyyyyyyyyyyyyyyyyyyyyyyyyyyy");

    }

    @Override
    public void onClick(View v) {
        System.out.println("Inside OnClick!!!!!!!!!!!!!!!!!");
        switch (v.getId()){
            case R.id.signOutButton:
                AlertDialog.Builder builder = new AlertDialog.Builder(AccountOptions.this);
                builder.setMessage("Are you sure you want to sign out?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                AccountService.save();
                                AccountService.signOut();
                                Intent returnIntent = new Intent(AccountOptions.this, MainActivity.class);
                                startActivity(returnIntent);
                                finish();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                builder.create().show();
                break;
            case R.id.InfoSettingsButton:
                System.out.println("not ready yet");
//                Intent intentInfo=new Intent(AccountOptions.this, )
                break;
            case R.id.StatisticsButton:
                Intent intentStatistics=new Intent(AccountOptions.this,StatisticsActivity.class);
                startActivity(intentStatistics);
                break;
            case R.id.RentalsButton:
                Intent intentRentals=new Intent(AccountOptions.this, RentalsActivity.class);
                startActivity(intentRentals);
                break;
            case R.id.VehiclesButton:
                Intent intentVehicles=new Intent(AccountOptions.this, VehicleManagement.class);
                startActivity(intentVehicles);
                break;
            case R.id.ApplicationsButton:
                Intent intentApplications=new Intent(AccountOptions.this, ApplicationsActivity.class);
                startActivity(intentApplications);
                break;
        }

    }
}
