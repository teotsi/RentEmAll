package services;

import classes.CompanyAccount;
import classes.Customer;
import classes.RentingApplication;
import classes.Vehicle;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class SearchService extends Service {

    public static List<Vehicle> getUnfilteredVehicleList(LocalDate startDate, LocalDate endDate, double latitude, double longitude) {
        List<Vehicle> availableVehicles = new ArrayList<>(); //this list will be filled with valid vehicles
        for (CompanyAccount companyAccount : companies) { //iterating over all companies
            if (calculateDistance(latitude, longitude, companyAccount.getLatitude(), companyAccount.getLongitude()) <= companyAccount.getRange()) {
                //if company is within our range
                List<Vehicle> vehicles = companyAccount.getVehicles();
                for (Vehicle vehicle : vehicles) {
                    if (vehicle.isAvailable() && vehicle.isFree(startDate, endDate)) {
                        availableVehicles.add(vehicle);
                    }
                }
            }
        }
        return availableVehicles;
    }

    public static void submitApplication(RentingApplication application, double cost) {
        for (CompanyAccount companyAccount : companies) {
            if (application.getCompanyId() == companyAccount.getId()) {
                companyAccount.addApplication(application);
                Service.completePayment(application);
                sendRentInfo(application);
                break;
            }
        }
    }

    public static void sendRentInfo(RentingApplication application) {
        String email = application.toString();
    }

    public static Customer createCustomer(String name, String surname, String telephone, String email) {
        return new Customer(name, surname, telephone, email);
    }

    public static double paymentMethod(String method, Vehicle vehicle, LocalDate startDate, LocalDate endDate) {
        if (method.equals("cc")) {
            cc(vehicle, startDate, endDate);
        } else if (method.equals("PayPal")) {
            payPal(vehicle, startDate, endDate);
        }
        double cost = calculateCost(vehicle, startDate, endDate);

        return cost;
    }

    public static void cc(Vehicle vehicle, LocalDate startDate, LocalDate endDate) {
        //TODO chack these string arrays for their size to avoid invalid info and buffer overflow
        String Cnumber = null;
        String Cname = null;
        String expDate = null;
        String cvv = null;
    }

    public static void payPal(Vehicle vehicle, LocalDate startDate, LocalDate endDate) {
        // basically paypal should open in a different browser window
        String PayPalemail = null;
        String PayPalpassword = null;
    }

    public static double calculateCost(Vehicle vehicle, LocalDate startDate, LocalDate endDate) {
        long elapsedDays = ChronoUnit.DAYS.between(startDate, endDate);
        return elapsedDays * vehicle.getRate();
    }

    public static RentingApplication createApplication(int companyId, Vehicle vehicle, LocalDate startDate, LocalDate endDate, LocalDate replyDate, String id, String customerLocation, String companyLocation, Customer customer) {
        return new RentingApplication(companyId, vehicle, startDate, endDate, replyDate, id, customerLocation, companyLocation, customer);
    }

    public static double calculateDistance(double lat1, double long1, double lat2, double long2) {
        double earthRadius = 6371;
        double latDistance = Math.toRadians(lat1 - lat2);
        double longDistance = Math.toRadians(long1 - long2);
        double a = (Math.sin(latDistance / 2) * Math.sin(latDistance / 2)) +
                (Math.cos(Math.toRadians(lat1))) * (Math.cos(Math.toRadians(lat2))) *
                        (Math.sin(longDistance / 2)) * Math.sin(longDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return earthRadius * c;
    }

    public List<Vehicle> getFilteredVehicleList(LocalDate startDate, LocalDate endDate, String filters, double latitude, double longitude) {
        List<Vehicle> availableVehicles = getUnfilteredVehicleList(startDate, endDate, latitude, longitude);
        StringTokenizer filterTokenizer = new StringTokenizer(filters, ",");
        while (filterTokenizer.hasMoreTokens()) {
            String filter = filterTokenizer.nextToken();
            String filterChoice = filterTokenizer.nextToken();
            if (filter.equals("brand")) {
                availableVehicles.removeIf(vehicle -> !vehicle.getBrand().equals(filterChoice));
            } else if (filter.equals("type")) {
                availableVehicles.removeIf(vehicle -> !vehicle.getType().equals(filterChoice));
            } else if (filter.equals("seats")) {
                availableVehicles.removeIf(vehicle -> vehicle.getSeats() != Integer.parseInt(filterChoice));
            } else if (filter.equals("fuelType")) {
                availableVehicles.removeIf(vehicle -> !vehicle.getFuelType().equals(filterChoice));
            } else if (filter.equals("pce")) {
                availableVehicles.removeIf(vehicle -> vehicle.isPce() != Boolean.parseBoolean(filterChoice));
            } else if (filter.equals("transmissionType")) {
                availableVehicles.removeIf(vehicle -> !vehicle.getTransmissionType().equals(filterChoice));
            } else if (filter.equals("rate")) {
                availableVehicles.removeIf(vehicle -> vehicle.getRate() > Integer.parseInt(filterChoice));
            }
        }
        return availableVehicles;

    }

}