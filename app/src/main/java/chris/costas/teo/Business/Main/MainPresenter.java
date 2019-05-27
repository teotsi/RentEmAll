package chris.costas.teo.Business.Main;

import android.content.res.AssetManager;
import android.view.View;

import java.io.IOException;

import model.services.Service;

public class MainPresenter implements MainContract.Presenter {

    private MainActivity mView;

    MainPresenter(MainActivity view){
        mView=view;
    }

    @Override
    public void load (){
        try {
            AssetManager assets = mView.getAssets();
            Service.companyReader(assets.open("dataset/Companies.txt"));
            Service.vehicleReader(assets.open("dataset/Vehicles.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    ////////// Presenter Methods ///////////////

    @Override
    public void handleBusinessButtonClick() {
        mView.navigateToBusiness();
    }

    @Override
    public void handleRentCarButtonClick() {
        mView.navigateToRentCar();
    }
}
