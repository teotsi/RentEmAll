package model.classes;

import java.time.LocalDate;

public class Rental extends RentingApplication {
    private String id;
    private LocalDate receiptDate;
    private LocalDate deliveryDate;
    public Rental(RentingApplication rentingApplication) {
        super(rentingApplication);
    }
    public void confirmReceipt(LocalDate date){
        receiptDate = date;
    }

    public void confirmDelivery(LocalDate date){
        deliveryDate = date;
    }
}
