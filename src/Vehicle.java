import java.text.DateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class Vehicle {
    private String id = String.valueOf(this.hashCode());
    private int companyId;
    private String brand;
    private String model;
    private String type;
    private int seats;
    private String fuelType;
    private boolean pce;
    private float rate;
    private String extra;
    private String transmissionType;
    private DateFormat date;
    private List<RentingApplication> upcomingRentals;
    private boolean available = true;

    public Vehicle(int id, String brand, String model, String type, int seats, String fuelType, boolean pce, float rate, String extra, String transmissionType, DateFormat date, boolean available) {
        this.companyId = id;
        this.brand = brand;
        this.model = model;
        this.type = type;
        this.seats = seats;
        this.fuelType = fuelType;
        this.pce = pce;
        this.rate = rate;
        this.extra = extra;
        this.transmissionType = transmissionType;
        this.date = date;
        this.upcomingRentals = new ArrayList<>();
    }

    public String getTransmissionType() {
        return transmissionType;
    }

    public void setTransmissionType(String transmissionType) {
        this.transmissionType = transmissionType;
    }

    public String getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public boolean isPce() {
        return pce;
    }

    public void setPce(boolean pce) {
        this.pce = pce;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }


    public void addRental(RentingApplication newApplication) {
        if (this.upcomingRentals.isEmpty()) {
            this.upcomingRentals.add(newApplication);
        } else {
            int index = upcomingRentals.size() - 1;
            for (RentingApplication application : upcomingRentals) {
                if (application.getStartDate().isAfter(newApplication.getEndDate())) {
                    index = upcomingRentals.indexOf(application);
                    break;
                }
            }
            upcomingRentals.add(index, newApplication);
        }
    }

    public void removeRental(RentingApplication applicationToRemove) {

        for (Iterator<RentingApplication> it = upcomingRentals.iterator(); it.hasNext(); ) {
            RentingApplication application = it.next();
            if (application.getId().equals(applicationToRemove.getId())) {
                it.remove();
            }
        }
    }

    public boolean isFree(LocalDate startDate, LocalDate endDate) {
        if (upcomingRentals.isEmpty()) return true;
        RentingApplication first = upcomingRentals.get(0);
        for (RentingApplication application : upcomingRentals) {
            if (application.getStartDate().isAfter(startDate)) {
                return application.getStartDate().isAfter(endDate);
            } else {
                if (application.getEndDate().isBefore(startDate)) {
                    continue;
                } else
                    return false;
            }
        }
        //RentingApplication last = upcomingRentals.get(upcomingRentals.size() - 1);
        return true;
    }

    public void setDate(DateFormat date) {
        this.date = date;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) { //if a vehicle needs servicing or repairs it is marked unavailable
        this.available = available;
    }

    @Override
    public boolean equals(Object obj) { //overriding equals class to improve code readability
        if (obj == null) {
            return false;
        }

        if (!Vehicle.class.isAssignableFrom(obj.getClass())) {
            return false;
        }

        final Vehicle other = (Vehicle) obj;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        return brand + " " + model + ", seats: " + seats + ", type: " + type + ", fuel: " + fuelType + ", rate: " + rate +
                "/day, PCE: " + pce + ", transimission: " + transmissionType + ", extras: " + extra;
    }
}
