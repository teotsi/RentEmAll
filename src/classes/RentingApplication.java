package classes;

import java.time.LocalDate;

public class RentingApplication {
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate replyDate;
    private String id;
    private String customerLocation;
    private String companyLocation;
    private boolean accepted = false;
    private boolean pending = true;
    private String comments;
    private Vehicle vehicle;
    private int companyId;
    private Customer customer;

    public RentingApplication() {
    }

    public RentingApplication(int companyId, Vehicle vehicle, LocalDate startDate, LocalDate endDate, LocalDate replyDate, String id, String customerLocation, String companyLocation, Customer customer) {
        this.companyId = companyId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.replyDate = replyDate;
        this.id = id;
        this.vehicle = vehicle;
        this.customerLocation = customerLocation;
        this.companyLocation = companyLocation;
        this.customer = customer;
        comments = "";
    }

    public String getId() {
        return id;
    }

    public boolean isPending() {
        return pending;
    }

    public void setPending(boolean pending) {
        this.pending = pending;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public LocalDate getReplyDate() {
        return replyDate;
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

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public int getCompanyId() {
        return companyId;
    }
    public Customer getCustomer(){ return this.customer;}

    public String toString() {
        return startDate + "/" + endDate + "/" + id + "/" + customerLocation + "/" + companyLocation +
                "/" + accepted + "/" + pending + "/" + comments + "/" + "Vehicle: "+ vehicle.toString() + "/" + companyId + "/" +"Customer: " +customer.toString();
    }
}
