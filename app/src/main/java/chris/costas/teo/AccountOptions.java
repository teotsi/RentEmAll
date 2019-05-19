package chris.costas.teo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import model.services.*;

public class AccountOptions extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_options);
        TextView companyName = (TextView) findViewById(R.id.CompanyNameText);
        String name = AccountService.getName();
        companyName.setText(name);
    }
}
