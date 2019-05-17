package chris.costas.teo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import chris.costas.teo.ui.accountmanagement.AccountManagementFragment;

public class AccountManagement extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_management_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, AccountManagementFragment.newInstance())
                    .commitNow();
        }
    }
}
