package chris.costas.teo.Business.Main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

import chris.costas.teo.R;
import chris.costas.teo.Client.SearchActivity;
import chris.costas.teo.Business.SignIn_Up.SignIn_UpOption;
import model.services.Service;

public class MainActivity extends AppCompatActivity implements MainContract.MvpView, View.OnClickListener {

    private Button BusinessButton;
    private Button RentButton;

    MainContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPresenter=new MainPresenter(MainActivity.this);
        ((MainPresenter) mPresenter).load();
        BusinessButton = findViewById(R.id.BusinessButton);
        RentButton = findViewById(R.id.RentAvecButton);
        BusinessButton.setOnClickListener(this);
        RentButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.BusinessButton:
                mPresenter.handleBusinessButtonClick();
                break;
            case R.id.RentAvecButton:
                mPresenter.handleRentCarButtonClick();
                break;
        }
    }

    @Override
    public void navigateToBusiness() {
        Intent intentIn_Up = new Intent(MainActivity.this, SignIn_UpOption.class);
        startActivity(intentIn_Up);
    }

    @Override
    public void navigateToRentCar() {
        Intent intent = new Intent(MainActivity.this, SearchActivity.class);
        startActivity(intent);
    }
}
