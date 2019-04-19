import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class AccountService extends Service {
    private static int currentAccountID;
    private static List<Vehicle> vehicles;
    private static List<RentingApplication> applications;
    private static String logs = "";

    public static boolean emailIsValid(String email) {
        String emailPattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        return email.matches(emailPattern);
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
                    vehicles = companyAccount.getVehicles();
                    applications = companyAccount.getApplications();
                    logs += "Logged in at " + dateFormat.format(new Date()) + "\n";
                    return true;
                } else {
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

    public static void register(String policy, String description, float range, String email, String password) {
        companies.add(new CompanyAccount(policy, description, range, email, password));
    }

    public static void save() {
        for (CompanyAccount companyAccount : companies) {
            if (companyAccount.getId() == currentAccountID) {
                companyAccount.logSession(logs + "Saving at " + dateFormat.format(new Date()) + "\n");
                System.out.println(companyAccount.getLogs());
                companyAccount.setVehicles(vehicles);
                companyAccount.setApplications(applications);
                break;
            }
        }
    }

    public static void addVehicle(Vehicle vehicle, int amount) { //adding an x amount of a Vehicle
        for (int i = 0; i < amount; i++) {
            vehicles.add(vehicle);
        }
    }

    public static void removeVehicle(Vehicle vehicleToRemove) {
        for (Iterator<Vehicle> it = vehicles.iterator(); it.hasNext(); ) {
            Vehicle vehicle = it.next();
            if (vehicle.equals(vehicleToRemove)) {
                it.remove();
                return;
            }
        }
    }

    public static void signOut() { //reseting object
        currentAccountID = -1;
        vehicles = null;
        applications = null;
        logs = "";
    }

    public static void addApplication(RentingApplication application) { //adding application
        applications.add(application);
        application.getVehicle().addRental(application);
    }

    public static void acceptApplication(String id) { //method to accept application with ID id
        for (RentingApplication application : applications) {
            if (application.getId().equals(id)) {
                application.setPending(false);
                application.setAccepted(true);
                return;
            }
        }
    }

    public static void rejectApplication(String id, String reasons) { //method to reject application with ID id, with a message
        for (RentingApplication application : applications) {
            if (application.getId().equals(id)) {
                application.setPending(false); //application has been reviewed now
                application.setComments(reasons); //grounds for denial
                String vehicleID = application.getVehicle().getId();
                for (Vehicle vehicle : vehicles) {
                    if (vehicle.equals(vehicleID)) {
                        vehicle.removeRental(application); //removing rental to free allocated days
                        break;
                    }
                }
                break;
            }
        }

    }

    public static void allocateVehicle(Rental rental, int id) { //allocate Vehicle to client, save dates
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getId().equals(id)) {
                vehicle.addRental(rental);
                return;
            }
        }
    }

    public static List<RentingApplication> getPendingApplications() { //returns all pending applications
        List<RentingApplication> pendingApplications = new ArrayList<>();
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
            if (application.isAccepted() == false && application.isPending() == false) {
                rejectedApplications.add(application);
            }
        }
        return rejectedApplications;
    }

}
