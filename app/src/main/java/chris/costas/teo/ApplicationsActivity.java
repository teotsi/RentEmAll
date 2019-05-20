package chris.costas.teo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.widget.AbsListView;

import java.io.IOException;
import java.util.List;

import model.classes.RentingApplication;
import model.services.AccountService;
import model.services.Service;

public class ApplicationsActivity extends AppCompatActivity {

    private RecyclerView myrecyclerview;
    private List<RentingApplication> applications;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_applications);
        AssetManager assets = this.getAssets();
        try {
            model.services.Service.companyReader(assets.open("dataset/Companies.txt"));
            Service.vehicleReader(assets.open("dataset/Vehicles.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        AccountService.login("teotsi@gmail.com","Qwerty!2");
        applications = AccountService.getApplications();
        myrecyclerview = (RecyclerView) findViewById(R.id.ApplicationRecyclerView);
    }
}
