package chris.costas.teo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button BusinessButton;
    private Button RentButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BusinessButton = (Button) findViewById(R.id.BusinessButton);
        RentButton = (Button) findViewById(R.id.RentAvecButton);
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
            case R.id.RentAvecButton:
                System.out.println("Not ready yet");

        }
    }
}
