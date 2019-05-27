package chris.costas.teo.Business.NewAccount;

import android.view.View;

public interface CreateNewAccountContract {
    interface MvpView{
        void CreateAccountButtonClicked();
    }
    interface Presenter{
        void handleCreateAccountButtonClick(View view);
    }
}
