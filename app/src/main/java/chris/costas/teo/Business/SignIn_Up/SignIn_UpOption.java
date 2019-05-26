package chris.costas.teo.Business.SignIn_Up;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import chris.costas.teo.Business.Login.LoginActivity;
import chris.costas.teo.Business.NewAccount.CreateNewAccount;
import chris.costas.teo.R;

/**
 * Make the user to choose if he is already registered, if he is then the app goes to the login activity,
 * else it goes to the creation of a new account.
 */
public class SignIn_UpOption extends AppCompatActivity implements View.OnClickListener {

    private Button SignIn;
    private Button SignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in__up_option);

        SignIn= findViewById(R.id.SigninButton);
        SignUp= findViewById(R.id.SignUpButton);
        SignIn.setOnClickListener(this);
        SignUp.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch ((v.getId())){
            case R.id.SigninButton:
                Intent intentSignIn=new Intent(SignIn_UpOption.this, LoginActivity.class);
                startActivity(intentSignIn);
                break;
            case R.id.SignUpButton:
                Intent intentSignUp=new Intent(SignIn_UpOption.this, CreateNewAccount.class);
                startActivity(intentSignUp);
                break;
        }
    }
}
