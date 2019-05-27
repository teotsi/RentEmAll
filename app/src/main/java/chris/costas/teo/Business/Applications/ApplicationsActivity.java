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

/**
 * This activity is for displaying company's pending applications and when one of them is clicked it
 * calls a dialog to edit it.
 */
public class ApplicationsActivity extends AppCompatActivity implements ApplicationAdapter.OnNoteListener, DialogInterface.OnDismissListener, ApplicationContract.MvpView {

    private RecyclerView myrecyclerview;
    private ApplicationAdapter appAdapter;
    private ApplicationDialog appDialog;

    public static ApplicationContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_applications);
        mPresenter=new ApplicationPresenter(this);

        ((ApplicationPresenter) mPresenter).loadApplications();

        ApplicationContract.PresenterAdapter adPresenter=new ApplicationAdapterPresenter(mPresenter.getApplications());
        myrecyclerview = findViewById(R.id.ApplicationRecyclerView);
        appAdapter=new ApplicationAdapter(getApplicationContext(), this, getSupportFragmentManager(), (ApplicationAdapterPresenter) adPresenter);
        myrecyclerview.setLayoutManager(new LinearLayoutManager(this));
        myrecyclerview.setAdapter(appAdapter);

    }

    @Override
    public void onNoteClick(int position) {
        appDialog=ApplicationDialog.display(getSupportFragmentManager(), position);
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        if(appDialog.Dpresenter.getRemove()){
            int position=appDialog.Dpresenter.getPosition();
            appAdapter.adPresenter.remove(position);
            appAdapter.notifyItemRemoved(position);
            appAdapter.notifyItemRangeChanged(position,appAdapter.adPresenter.getApplications().size());
            appAdapter.applications=mPresenter.getApplications();
        }
    }
}
