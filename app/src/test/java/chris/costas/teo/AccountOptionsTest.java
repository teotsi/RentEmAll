package chris.costas.teo;

import android.content.res.AssetManager;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import chris.costas.teo.Business.AccountOptions.AccountOptions;
import chris.costas.teo.Business.AccountOptions.AccountOptionsPresenter;
import model.services.AccountService;
import model.services.Service;

public class AccountOptionsTest {

    private AccountOptions optView;
    private AccountOptionsPresenter presenter;

    @Before
    public void setUp(){
        optView=new AccountOptions();
        presenter=new AccountOptionsPresenter(optView);
        Service.companyReader("Companies.txt");
        Service.vehicleReader("Vehicles.txt");
        AccountService.login("teotsi@gmail.com", "Qwerty!2");
    }

    @Test
    public void getTheCompanyName(){
        Assert.assertEquals("Theodore's Car Rental", presenter.getCompanyName());
    }

    @Test
    public void testTheSave(){
        presenter.saveTest();
        Assert.assertEquals(null,AccountService.getVehicles());
        Assert.assertEquals(null,AccountService.getApplications());

    }

}
