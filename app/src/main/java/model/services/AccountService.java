package model.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import model.classes.BankAccount;
import model.classes.CompanyAccount;
import model.classes.Rental;
import model.classes.RentingApplication;
import model.classes.Vehicle;
import model.services.AdviceService;

public class AccountService extends Service {
    private static int currentAccountID;
    private static CompanyAccount Company;
    private static List<Vehicle> vehicles = new ArrayList<>();
    private static List<RentingApplication> applications = new ArrayList<>();
    private static List<Rental> rentals = new ArrayList<>();
    private static String logs = "";
    private static BankAccount bankAccount;

    public static boolean emailIsValid(String email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public static String incomeStats(LocalDate date){
        return  AdviceService.income_stats(Company, date);

    }

    public static String getName(){// returns the name of the company
        return Company.getCompanyName();
    }

    public static boolean emailIsAvailable(String email) {
        for (CompanyAccount companyAccount : companies) {
            if (companyAccount.getEmail().equals(email)) {
                return false;
            }
        }
        return true;
    }

    public static boolean login(String email, String password) {
        for (CompanyAccount companyAccount : companies) {
            if (companyAccount.getEmail().equals(email)) { //checking email first
                int auth = companyAccount.authorizeLogin(password);
                if (auth != -1) {//authorizeLogin compares a hash to another hash, a
                    currentAccountID = auth;//waste of time if the emails don't match
                    Company=companyAccount;
                    vehicles = companyAccount.getVehicles();
                    applications = companyAccount.getApplications();
                    bankAccount = companyAccount.getBankAccount();
                    logEvent("Logged in at " + dateFormat.format(new Date()));
                    return true;
                } else {
                    companyAccount.logSession("Invalid login attempt at " + dateFormat.format(new Date()));
                    return false;
                }
            } else {
                continue;
            }
        }
        return false;
    }

    public static boolean passwordIsValid(String password) {
        //password must contain: a digit, a lower case character, an upper case character,
        //a special character, no whitespace, and its length must be between 8 and 32 chars.
        String passwordPattern = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&+=])(?=\\S+$).{8,32}";
        return password.matches(passwordPattern);
    }

    public static int register(String companyName, String policy, String description, float range, double latitude, double longitude, String email, String password, BankAccount bankAccount) {
        if (emailIsValid(email)) {
            if (emailIsAvailable(email)) {
                if (passwordIsValid(password)) {
                    companies.add(new CompanyAccount(companyName, policy, description, range, latitude, longitude, email, password, true, bankAccount));
                    return 0;
                } else {
                    return 1;
                }
            } else {
                return 2;
            }
        } else {
            return 3;
        }
    }

    public static void save() {
        for (CompanyAccount companyAccount : companies) {
            if (companyAccount.getId() == currentAccountID) {
                companyAccount.logSession(logs + "Saving at " + dateFormat.format(new Date()) + "\n");
                System.out.println(companyAccount.getLogs());
                companyAccount.setVehicles(vehicles);
                companyAccount.setApplications(applications);
                companyAccount.setRentals(rentals);
                Service.mergeLists(applications, rentals);
                break;
            }
        }

    }

    public static void addVehicle(Vehicle vehicle, int amount) { //adding an x amount of a Vehicle
        for (int i = 0; i < amount; i++) {
            vehicles.add(new Vehicle(vehicle));
        }
        logEvent("Added " + amount + " " + vehicle.getName());

    }

    public static void removeVehicle(String id) {
        for (Iterator<Vehicle> it = vehicles.iterator(); it.hasNext(); ) {
            Vehicle vehicle = it.next();
            if (vehicle.getId().equals(id)) {
                it.remove();
                logEvent("Removed " + vehicle.getName() + ", id " + vehicle.getId());
                return;
            }
        }
    }

    public static void signOut() { //resetting object
        currentAccountID = -1;
        vehicles = null;
        applications = null;
        logs = "";
    }

    public static void addApplication(RentingApplication application) { //adding application
        applications.add(application);
        allocateVehicle(application, application.getVehicle().getId());
        logEvent("Added application for " + application.getVehicle().getName());
    }

    public static void acceptApplication(String id) { //method to accept application with ID id
        for (RentingApplication application : applications) {
            if (application.getId().equals(id)) {
                application.setPending(false);
                application.setAccepted(true);
                Rental rental = new Rental(application);
                rentals.add(new Rental(application));
                Rentals.add(new Rental(application));
                logEvent("Accepted application #" + application.getId());
                return;
            }
        }
    }

    public static void confirmReceipt(String id) {
        for (Rental rental : rentals) {
            if (rental.getId().equals(id)) {
                LocalDate date = LocalDate.now();
                rental.confirmReceipt(date);
                logEvent("Rental #" + rental.getId() + ": Customer received at" + dateFormat.format(date));
                return;
            }
        }
    }

    public static void confirmDelivery(String id) {
        for (Rental rental : rentals) {
            if (rental.getId().equals(id)) {
                LocalDate date = LocalDate.now();
                rental.confirmDelivery(date);
                logEvent("Rental #" + rental.getId() + ": Customer delivered at " + dateFormat.format(date));
                return;
            }
        }
    }


    private static void logEvent(String event) {
        logs += event + "\n";
    }

    public static void rejectApplication(String id, String reasons) { //method to reject application with ID id, with a message
        for (RentingApplication application : applications) {
            if (application.getId().equals(id)) {
                application.setPending(false); //application has been reviewed now
                application.setComments(reasons); //grounds for denial
                String vehicleID = application.getVehicle().getId();
                for (Vehicle vehicle : vehicles) {
                    if (vehicle.getId().equals(vehicleID)) {
                        vehicle.removeRental(application); //removing rental to free allocated days
                        break;
                    }
                }
                break;
            }
        }

    }

    public static void allocateVehicle(RentingApplication rental, String id) { //allocate Vehicle to client, save dates
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getId().equals(id)) {
                vehicle.addRental(rental);
                return;
            }
        }
    }

    public static void editVehicle(int position, String brand, String model, String type, int seats, String fuelType, boolean pce, float rate, String extra, String transmissionType, boolean available ){
        Vehicle editedVehicle=vehicles.get(position);
        editedVehicle.setBrand(brand);
        editedVehicle.setModel(model);
        editedVehicle.setType(type);
        editedVehicle.setSeats(seats);
        editedVehicle.setFuelType(fuelType);
        editedVehicle.setPce(pce);
        editedVehicle.setRate(rate);
        editedVehicle.setExtra(extra);
        editedVehicle.setTransmissionType(transmissionType);
        editedVehicle.setAvailable(available);
        vehicles.set(position,editedVehicle);
    }

    public static List<RentingApplication> getApplications() {
        return applications;
    }

    public static List<RentingApplication> getPendingApplications() { //returns all pending applications
        List<RentingApplication> pendingApplications = new ArrayList<>();
        if (applications == null) return null;
        for (RentingApplication application : applications) {
            if (application.isPending()) {
                pendingApplications.add(application);
            }
        }
        return pendingApplications;
    }

    public static List<RentingApplication> getAcceptedApplications() { //returns all accepted Applications
        List<RentingApplication> acceptedApplications = new ArrayList<>();
        for (RentingApplication application : applications) {
            if (application.isAccepted()) {
                acceptedApplications.add(application);
            }
        }
        return acceptedApplications;
    }

    public static List<RentingApplication> getRejectedApplications() { //returns all rejected applications
        List<RentingApplication> rejectedApplications = new ArrayList<>();
        for (RentingApplication application : applications) {
            if (!application.isAccepted() && !application.isPending()) {
                rejectedApplications.add(application);
            }
        }
        return rejectedApplications;
    }

    public static List<Vehicle> getVehicles() {
        System.out.println("hollllaa");
        return vehicles;
    }


    public static int getNumberOfVehicles() {
        return vehicles.size();
    }

    public static void removeVehicle(int adapterPosition) {
        vehicles.remove(adapterPosition);
    }

    public void addToBalance(double moneyyy) {
        bankAccount.addBalance(moneyyy);
    }

}
