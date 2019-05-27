package chris.costas.teo.Business.Applications;

import java.util.List;

import model.classes.RentingApplication;
import model.services.AccountService;

/**
 * This is the presenter of the ApplicationsActivity.java
 */
public class ApplicationPresenter implements ApplicationContract.Presenter {

    private ApplicationContract.MvpView mView;
    private static List<RentingApplication> applications;

    public ApplicationPresenter(ApplicationContract.MvpView mView) {
        this.mView = mView;
    }

    @Override
    public void loadApplications(){
        applications = AccountService.getPendingApplications();
    }

    @Override
    public List<RentingApplication> getApplications(){
        return applications;
    }


}
