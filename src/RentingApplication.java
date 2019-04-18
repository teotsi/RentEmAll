import java.text.DateFormat;

public class RentingApplication {
    private DateFormat startDate;
    private DateFormat endDate;
    private String id;
    private String customerLocation;
    private String companyLocation;
    private boolean accepted = false;
    private boolean pending = true;
    private String comments;

    public RentingApplication(DateFormat startDate, DateFormat endDate, String id, String customerLocation, String companyLocation) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.id = id;
        this.customerLocation = customerLocation;
        this.companyLocation = companyLocation;
        comments = "";
    }

    public String getId() {
        return id;
    }

    public void setPending(boolean pending) {
        this.pending = pending;
    }

    public boolean isPending() {
        return pending;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }

    public void setComments(String comments){
        this.comments = comments;
    }
}
