package model.classes;

import java.time.LocalDate;
import java.time.LocalDateTime;
import model.services.*;

public class Rental extends RentingApplication {
    private String id;
    private LocalDate receiptDate;
    private LocalDate deliveryDate;
    public Rental(RentingApplication rentingApplication) {
        super(rentingApplication);
        this.receiptDate=super.getStartDate();
        this.deliveryDate=super.getEndDate();
    }
    public void confirmReceipt(LocalDate date){
        receiptDate = date;
    }

    public String getId(){
        return this.id;
    }

    public void confirmDelivery(LocalDate date){
        deliveryDate = date;
    }

    public LocalDate getReceiptDate(){
        return receiptDate;
    }

    public LocalDate getDeliveryDate(){
        return deliveryDate;
    }

    public double profit(Vehicle v, LocalDate receiptDate, LocalDate deliveryDate){
        return Service.calculateCost(v, receiptDate, deliveryDate);
    }

    public String toSrting(){
        return "Id: "+ this.id+"\n"+ "Receipt date: "+this.receiptDate+"\n"+"Delivery date: "+this.deliveryDate+"\n"+super.toString();
    }
}
