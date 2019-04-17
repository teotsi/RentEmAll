import java.text.DateFormat;
import java.util.Date;

public class Vehicle {

    private String id;
    private String brand;
    private String type;

       private int seats;
    private String fuelType;
    private boolean pce;
    private float rate;
    private String extra;
    private String transmissionType;
   private DateFormat date;
    public String getTransmissionType() {
        return transmissionType;
    }
    private boolean available;


    public String getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public String getType() {
        return type;
    }

    public int getSeats() {
        return seats;
    }

    public String getFuelType() {
        return fuelType;
    }

    public boolean isPce() {
        return pce;
    }

    public float getRate() {
        return rate;
    }

    public String getExtra() {
        return extra;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public void setPce(boolean pce) {
        this.pce = pce;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public void setTransmissionType(String transmissionType) {
        this.transmissionType = transmissionType;
    }

    public void setDate(DateFormat date) {
        this.date = date;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }


}
