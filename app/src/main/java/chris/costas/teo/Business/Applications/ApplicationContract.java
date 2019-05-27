package chris.costas.teo.Business.Applications;

import java.time.LocalDate;
import java.util.List;

import model.classes.RentingApplication;

public interface ApplicationContract {
    interface MvpView{

    }

    interface Presenter{

        void loadApplications();

        List<RentingApplication> getApplications();

    }

    interface RepositoryRowView{

        void setId(String id);

        void setStartDate(LocalDate startDate);

        void  setEndDate (LocalDate endDate);


    }

    interface PresenterAdapter{

        void onBindRepositoryRowViewAtPosition(int position, RepositoryRowView rowView);

    }

    interface DialogView{

    }

    interface DialogPresenter{

        void setPosition(int position);

        int getPosition();

        String getApplicationText();

        void setRemove(boolean rem);

        boolean getRemove();

        void setText(String text);

        String getText();

        void accept();

        void reject();

    }
}
