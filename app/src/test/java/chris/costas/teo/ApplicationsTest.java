package chris.costas.teo;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import chris.costas.teo.Business.AccountOptions.AccountOptions;
import chris.costas.teo.Business.AccountOptions.AccountOptionsPresenter;
import chris.costas.teo.Business.Applications.ApplicationPresenter;
import chris.costas.teo.Business.Applications.ApplicationsActivity;
import model.services.AccountService;
import model.services.Service;

public class ApplicationsTest {

    private ApplicationsActivity optView;
    private ApplicationPresenter presenter;

    @Before
    public void setUp(){
        optView=new ApplicationsActivity();
        presenter=new ApplicationPresenter(optView);
        Service.companyReader("Companies.txt");
        Service.vehicleReader("Vehicles.txt");
        AccountService.login("teotsi@gmail.com", "Qwerty!2");
    }

    @Test
    public void checkApplicationsLoading(){
        presenter.loadApplications();
        Assert.assertEquals(AccountService.getPendingApplications(),presenter.getApplications());
    }
}
