package chris.costas.teo.Business.Applications;

import java.util.List;

import model.classes.RentingApplication;

public class ApplicationAdapterPresenter implements ApplicationContract.PresenterAdapter {

    private final List<RentingApplication> applications;

    public ApplicationAdapterPresenter(List<RentingApplication> applications) {
        this.applications = applications;
    }

    @Override
    public void onBindRepositoryRowViewAtPosition(int position, ApplicationContract.RepositoryRowView rowView) {
        RentingApplication app= applications.get(position);
        rowView.setId(app.getId());
        rowView.setStartDate(app.getStartDate());
        rowView.setEndDate(app.getEndDate());
    }
}
