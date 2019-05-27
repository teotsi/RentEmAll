package chris.costas.teo.Business.Main;

import android.view.View;

public interface MainContract {
    interface MvpView{
        void navigateToBusiness();

        void navigateToRentCar();
    }

    interface Presenter{
        void handleBusinessButtonClick();

        void handleRentCarButtonClick();
    }
}
