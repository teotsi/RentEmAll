package chris.costas.teo.Business.AccountOptions;

import android.content.DialogInterface;

public interface AccountOptionsContract {
    interface MvpView{
        void navigateToInfoSettings();

        void navigateToStatistics();

        void navigateToRentals();

        void navigateToVehicles();

        void navigateToApplications();

        void navigateToSignOut();

        void ActOut();

        void ActCancel(DialogInterface dialog);
    }

    interface Presenter{

        String getCompanyName();

        void handleInfoSettingsButtonClick();

        void handleRentalsButtonClick();

        void handleApplicationsButtonClick();

        void handleStatisticsButtonClick();

        void handleVehiclesButtonClick();

        void signOut();

        void save();

        void cancel(DialogInterface dialog);
    }
}