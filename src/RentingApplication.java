import java.time.LocalDate;

public class RentingApplication {
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate acceptanceDate;
    private String id;
    private String customerLocation;
    private String companyLocation;
    private boolean accepted = false;
    private boolean pending = true;
    private String comments;

    public RentingApplication(LocalDate startDate, LocalDate endDate, LocalDate acceptanceDate, String id, String customerLocation, String companyLocation) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.acceptanceDate = acceptanceDate;
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

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public LocalDate getAcceptanceDate() {
        return acceptanceDate;
    }

    public String getCustomerLocation() {
        return customerLocation;
    }

    public String getCompanyLocation() {
        return companyLocation;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public String getComments() {
        return comments;
    }
}
