package chris.costas.teo.Business.NewAccount;

import android.view.View;

public class CreateNewAccountPresenter implements CreateNewAccountContract.Presenter {

    private CreateNewAccountContract.MvpView mView;

    CreateNewAccountPresenter(CreateNewAccountContract.MvpView view){
        mView = view;
    }


    public void handleCreateAccountButtonClick(View view){
        mView.CreateAccountButtonClicked();
    }
}


