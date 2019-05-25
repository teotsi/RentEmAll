package chris.costas.teo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.io.IOException;

import model.services.Service;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button BusinessButton;
    private Button RentButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            AssetManager assets = this.getAssets();
            Service.companyReader(assets.open("dataset/Companies.txt"));
            Service.vehicleReader(assets.open("dataset/Vehicles.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        BusinessButton = findViewById(R.id.BusinessButton);
        RentButton = findViewById(R.id.RentAvecButton);
        BusinessButton.setOnClickListener(this);
        RentButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.BusinessButton:
                System.out.println("Businesssssssssss");
                Intent intentIn_Up = new Intent(MainActivity.this, SignIn_UpOption.class);
                startActivity(intentIn_Up);
                break;
            case R.id.RentAvecButton:
                Intent intent = new Intent(MainActivity.this,SearchActivity.class);
                startActivity(intent);
                break;
        }
    }
}
