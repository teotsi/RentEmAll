package chris.costas.teo.Business.Applications;

import model.services.AccountService;

public class ApplicationDialogPresenter implements ApplicationContract.DialogPresenter {

    private ApplicationContract.DialogView mView;
    private static int position;
    private boolean remove=false;
    private String m_Text = "";

    public ApplicationDialogPresenter(ApplicationContract.DialogView mView) {
        this.mView = mView;
    }


    @Override
    public void setPosition(int position) {
        this.position=position;

    }

    @Override
    public int getPosition() {
        return this.position;
    }

    @Override
    public String getApplicationText() {
        return AccountService.getPendingApplications().get(position).toString();
    }

    @Override
    public void setRemove(boolean rem) {
        this.remove=rem;
    }

    @Override
    public boolean getRemove() {
        return this.remove;
    }

    @Override
    public void setText(String text) {
        this.m_Text=text;
    }

    @Override
    public String getText() {
        return m_Text;
    }

    @Override
    public void accept() {
        AccountService.acceptApplication(ApplicationsActivity.mPresenter.getApplications().get(position).getId());
        this.remove=true;
    }

    @Override
    public void reject() {
        AccountService.rejectApplication(ApplicationsActivity.mPresenter.getApplications().get(position).getId(),m_Text);
        this.remove=true;
    }
}
