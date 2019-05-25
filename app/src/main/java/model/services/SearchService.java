package model.services;


import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.StringTokenizer;

import model.classes.CompanyAccount;
import model.classes.Customer;
import model.classes.RentingApplication;
import model.classes.Vehicle;

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

    public static void submitApplication(RentingApplication application) {
        Applications.add(application);//add it to the list with all renting applications
        for (CompanyAccount companyAccount : companies) {// add it to the company applications
            if (application.getCompanyId() == companyAccount.getId()) {
                companyAccount.addApplication(application);
                completePayment(application);
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
        String cname;
        String expDate;
        String cvv;
        if (method.equals("cc")) {
            //cc(cname, expDate, cvv);
        } else if (method.equals("PayPal")) {
            payPal(vehicle, startDate, endDate);
        }
        double cost = calculateCost(vehicle, startDate, endDate);

        return cost;
    }

    public static boolean cc( String Cnumber, String expDate, String cvv) {//check if the numbers of the credit card have a valid length and if the date is in the future
        if( Cnumber.length()!=16){
            return false;
        }
        if(cvv.length()!=3){
            return false;
        }
        String[] date=expDate.split("(?!^)");
        StringBuilder monthBuilder = new StringBuilder();
        for (int i = 0; i < 2; i++) {
            monthBuilder.append(date[i]);
        }
        String month=monthBuilder.toString();
        if (!(Integer.parseInt(month)>0 && Integer.parseInt(month)<13)){
            return false;
        }
        StringBuilder yearBuilder = new StringBuilder();
        yearBuilder.append("20");
        for(int i=3; i< date.length; i++){
            yearBuilder.append(date[i]);
        }
        String year=yearBuilder.toString();
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        System.out.println(currentYear);
        int currentMonth = Calendar.getInstance().get(Calendar.MONTH)+1;
        if(Integer.parseInt(year)<currentYear){
            System.out.println("less");
            return false;
        }else if(Integer.parseInt(year)==currentYear){
            System.out.println("equals");
            System.out.println(currentMonth);
            System.out.println(Integer.parseInt(month));
            return Integer.parseInt(month) >= currentMonth;
        }
        return true;
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
        return new RentingApplication(companyId, vehicle, startDate, endDate, replyDate, id, customerLocation, companyLocation, customer, true);
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

    public static List<Vehicle> getFilteredVehicleList(LocalDate startDate, LocalDate endDate, String filters, double latitude, double longitude) {
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
                availableVehicles.removeIf(vehicle -> vehicle.getRate() > Float.parseFloat(filterChoice));
            }else if (filter.equals("model")){
                availableVehicles.removeIf(vehicle -> !vehicle.getModel().equals(filterChoice));
            }
        }
        return availableVehicles;

    }

}