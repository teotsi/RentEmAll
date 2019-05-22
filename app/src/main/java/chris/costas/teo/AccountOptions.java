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
import model.services.AccountService;
import model.services.Service;

public class AccountOptions extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_options);
        //no need for real
        AssetManager assets = this.getAssets();
        try {
            model.services.Service.companyReader(assets.open("dataset/Companies.txt"));
            Service.vehicleReader(assets.open("dataset/Vehicles.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        AccountService.login("teotsi@gmail.com", "Qwerty!2");
        ///
        TextView companyName = (TextView) findViewById(R.id.CompanyNameText);
        String name = AccountService.getName();
        companyName.setText(name);
        Button signOut = (Button) findViewById(R.id.signOutButton);
        Context context = this;
        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
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
            }
        });

    }
}
