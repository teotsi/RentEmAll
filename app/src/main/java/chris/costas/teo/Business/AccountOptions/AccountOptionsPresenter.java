package chris.costas.teo.Business.AccountOptions;

import android.content.DialogInterface;

import model.services.AccountService;

public class AccountOptionsPresenter implements AccountOptionsContract.Presenter {

    private AccountOptionsContract.MvpView mView;

    public AccountOptionsPresenter(AccountOptionsContract.MvpView mView) {
        this.mView = mView;
    }

    @Override
    public String getCompanyName() {
        return AccountService.getName();
    }

    @Override
    public void handleInfoSettingsButtonClick() {
        mView.navigateToInfoSettings();
    }

    @Override
    public void handleRentalsButtonClick() {
        mView.navigateToRentals();
    }

    @Override
    public void handleApplicationsButtonClick() {
        mView.navigateToApplications();
    }

    @Override
    public void handleStatisticsButtonClick() {
        mView.navigateToStatistics();
    }

    @Override
    public void handleVehiclesButtonClick() {
        mView.navigateToVehicles();
    }

    @Override
    public void signOut() {
        mView.navigateToSignOut();
    }

    @Override
    public void save() {
        AccountService.save();
        AccountService.signOut();
        mView.ActOut();
    }

    public void saveTest(){
        AccountService.save();
        AccountService.signOut();
    }

    @Override
    public void cancel(DialogInterface dialog) {
        mView.ActCancel(dialog);
    }
}
