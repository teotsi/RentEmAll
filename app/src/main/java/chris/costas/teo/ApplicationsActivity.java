package chris.costas.teo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;

import java.io.IOException;
import java.util.List;

import model.classes.RentingApplication;
import model.services.AccountService;
import model.services.Service;

public class ApplicationsActivity extends AppCompatActivity implements ApplicationAdapter.OnNoteListener {

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
        applications = AccountService.getPendingApplications();
        myrecyclerview = (RecyclerView) findViewById(R.id.ApplicationRecyclerView);
        ApplicationAdapter appAdapter=new ApplicationAdapter(getApplicationContext(), applications, this, getSupportFragmentManager());
        myrecyclerview.setLayoutManager(new LinearLayoutManager(this));
        myrecyclerview.setAdapter(appAdapter);

    }

    @Override
    public void onNoteClick(int position) {
        // TODO: display application info in the text area, also display is not the on that calls the other methods, decline doesn't close
        System.out.println("its ok");
        ApplicationDialog.display(getSupportFragmentManager());
        AccountService.getApplications().get(position).setAccepted(ApplicationDialog.Accepted());
        //AccountService.getApplications().get(position).setPending(false);
        System.out.println("It works!!!!!!!");
    }
}
