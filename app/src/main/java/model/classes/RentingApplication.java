package model.classes;

import java.io.Serializable;
import java.time.LocalDate;

public class RentingApplication implements Serializable {
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

    public RentingApplication(int companyId, Vehicle vehicle, LocalDate startDate, LocalDate endDate, LocalDate replyDate, String id, String customerLocation, String companyLocation, Customer customer, boolean isNewApplication) {
        this.companyId = companyId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.replyDate = replyDate;
        this.vehicle = vehicle;
        this.customerLocation = customerLocation;
        this.companyLocation = companyLocation;
        this.customer = customer;
        comments = "";
        if(isNewApplication){
            this.id = String.valueOf(String.valueOf(this.toString().hashCode()).hashCode());
        }else{
            this.id = id;
        }
    }


    public RentingApplication(RentingApplication rentingApplication) {
        this.companyId = rentingApplication.companyId;
        this.startDate = rentingApplication.startDate;
        this.endDate = rentingApplication.endDate;
        this.replyDate = rentingApplication.replyDate;
        this.vehicle = rentingApplication.vehicle;
        this.customerLocation = rentingApplication.customerLocation;
        this.companyLocation = rentingApplication.companyLocation;
        this.customer = rentingApplication.customer;
        this.comments = rentingApplication.comments;
        this.id = rentingApplication.id;
    }

    public String getId() {
        return id;
    }

    public void setReplyDate(LocalDate date){this.replyDate=date;}

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

    public Customer getCustomer() {
        return this.customer;
    }

    @Override
    public String toString() {
        return "Id: "+id+"\n"+"Start date: "+startDate + "\n"+"End date: " + endDate + "\n" +"Vehicle Id: "+ vehicle.getId()+ "\n" + "Vehicle: " + vehicle.getBrand()+" "+ vehicle.getModel() +"\n"+ "Customer's location: " +customerLocation+"\n"+"Customer: "+customer.toString();
    }
}
