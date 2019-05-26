package chris.costas.teo.Business.Applications;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;

import java.util.List;

import chris.costas.teo.R;
import model.classes.RentingApplication;
import model.services.AccountService;

public class ApplicationsActivity extends AppCompatActivity implements ApplicationAdapter.OnNoteListener, DialogInterface.OnDismissListener {

    private RecyclerView myrecyclerview;
    private static List<RentingApplication> applications;
    private ApplicationAdapter appAdapter;
    private ApplicationDialog appDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_applications);
//        AssetManager assets = this.getAssets();
////        try {
////            model.services.Service.companyReader(assets.open("dataset/Companies.txt"));
////            Service.vehicleReader(assets.open("dataset/Vehicles.txt"));
////        } catch (IOException e) {
////            e.printStackTrace();
////        }
////        AccountService.login("teotsi@gmail.com","Qwerty!2");
        applications = AccountService.getPendingApplications();
        myrecyclerview = findViewById(R.id.ApplicationRecyclerView);
        appAdapter=new ApplicationAdapter(getApplicationContext(), applications, this, getSupportFragmentManager());
        myrecyclerview.setLayoutManager(new LinearLayoutManager(this));
        myrecyclerview.setAdapter(appAdapter);

    }

    public static List<RentingApplication> getApplications(){
        return applications;
    }

    @Override
    public void onNoteClick(int position) {
        appDialog=ApplicationDialog.display(getSupportFragmentManager(), position);
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        if(appDialog.getRemove()){
            int position=appDialog.getPosition();
            appAdapter.notifyItemRemoved(position);
            appAdapter.notifyItemRangeChanged(position,AccountService.getPendingApplications().size());
            applications = AccountService.getPendingApplications();
            appAdapter.applications=applications;
            System.out.println(AccountService.getPendingApplications().size());
        }
    }
}
