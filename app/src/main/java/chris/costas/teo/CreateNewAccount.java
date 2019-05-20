package chris.costas.teo;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class CreateNewAccount extends AppCompatActivity {

    EditText CompName, CompEmail, Password, CompAddress, IBAN, RentalRange;
    Button CreateAccountBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_account);

        CompName = (EditText)findViewById(R.id.CompanyNameTextField);
        CompEmail = (EditText)findViewById(R.id.EmailTextField);
        Password = (EditText)findViewById(R.id.PasswordTextField);
        CompAddress = (EditText)findViewById(R.id.CompanyAddressTextField);
        IBAN = (EditText)findViewById(R.id.IBANTextField);
        RentalRange = (EditText)findViewById(R.id.RentalRangeTextField);
    }
}
