package services;

import classes.CompanyAccount;
import classes.RentingApplication;
import classes.Vehicle;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class SearchService extends Service {

    public static List<Vehicle> getUnfilteredVehicleList(LocalDate startDate, LocalDate endDate) {
        List<Vehicle> availableVehicles = new ArrayList<>();
        for (CompanyAccount companyAccount : companies) {
            List<Vehicle> vehicles = companyAccount.getVehicles();
            for (Vehicle vehicle : vehicles) {
                if (vehicle.isAvailable() && vehicle.isFree(startDate, endDate)) {
                    availableVehicles.add(vehicle);
                }
            }
        }
        return availableVehicles;
    }

    public static void submitApplication(RentingApplication application) {
        for (CompanyAccount companyAccount : companies) {
            if (application.getCompanyId() == companyAccount.getId()) {
                companyAccount.addApplication(application);
            }
        }
    }

    public RentingApplication createApplication(int companyId, Vehicle vehicle, LocalDate startDate, LocalDate endDate, LocalDate replyDate, String id, String customerLocation, String companyLocation) {
        return new RentingApplication(companyId, vehicle, startDate, endDate, replyDate, id, customerLocation, companyLocation);
    }

    public List<Vehicle> getFilteredVehicleList(LocalDate startDate, LocalDate endDate, String filters) {
        List<Vehicle> availableVehicles = getUnfilteredVehicleList(startDate, endDate);
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