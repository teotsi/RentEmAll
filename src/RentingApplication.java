import java.text.DateFormat;

public class RentingApplication {
    private DateFormat startDate;
    private DateFormat endDate;
    private String id;
    private String customerLocation;
    private String companyLocation;
    private boolean accepted=false;

    public RentingApplication(DateFormat startDate, DateFormat endDate, String id, String customerLocation, String companyLocation) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.id = id;
        this.customerLocation = customerLocation;
        this.companyLocation = companyLocation;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }
}
