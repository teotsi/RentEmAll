package model.classes;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import model.services.*;

public class Rental extends RentingApplication implements Serializable {
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
        return "Receipt date: "+this.receiptDate+"\n"+"Delivery date: "+this.deliveryDate+"\n"+super.toString();
    }
}
