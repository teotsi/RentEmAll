import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class CompanyAccount {
    public List<Rental> rentals;
    private String id;
    private String policy;
    private String description;
    private String logs;
    private float range;
    private List<Vehicle> vehicles;
    private List<RentingApplication> applications;
    private String email;
    private String password;
    private Address address;

    public CompanyAccount(String id, String policy, String description, String logs, float range, String email, String password) {
        this.id = id;
        this.policy = policy;
        this.description = description;
        this.logs = logs;
        this.range = range;
        this.vehicles = new ArrayList<>();
        this.email = email;
        this.password = calculateHash(password);
    }

    public boolean authorizeLogin(String password) { //checking email/password credentials
        return this.password.equals(calculateHash(password));
    }

    public void addVehicle(Vehicle vehicle, int amount) { //adding an x amount of a Vehicle
        for (int i = 0; i < amount; i++) {
            vehicles.add(vehicle);
        }
    }

    public void addApplication(RentingApplication r) {
        applications.add(r);
    }

    public String getId() {
        return id;
    }

    public String getPolicy() {
        return policy;
    }

    public void setPolicy(String policy) {
        this.policy = policy;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLogs() {
        return logs;
    }

    public float getRange() {
        return range;
    }

    public void setRange(float range) {
        this.range = range;
    }

    public String getEmail() {
        return email;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public List<RentingApplication> getApplications() {
        return applications;
    }

    public List<RentingApplication> getPendingApplications() { //returns all pending applications
        List<RentingApplication> pendingApplications = new ArrayList<>();
        for (RentingApplication application : applications) {
            if (application.isPending()) {
                pendingApplications.add(application);
            }
        }
        return pendingApplications;
    }

    public List<RentingApplication> getAcceptedApplications() { //returns all accepted Applications
        List<RentingApplication> acceptedApplications = new ArrayList<>();
        for (RentingApplication application : applications) {
            if (application.isAccepted()) {
                acceptedApplications.add(application);
            }
        }
        return acceptedApplications;
    }

    public List<RentingApplication> getRejectApplications() { //returns all rejected applications
        List<RentingApplication> rejectedApplications = new ArrayList<>();
        for (RentingApplication application : applications) {
            if (application.isAccepted() == false && application.isPending() == false) {
                rejectedApplications.add(application);
            }
        }
        return rejectedApplications;
    }

    public void acceptApplication(String id) { //method to accept application with ID id
        for (RentingApplication application : applications) {
            if (application.getId().equals(id)) {
                application.setPending(false);
                application.setAccepted(true);
                return;
            }
        }
    }

    public void rejectApplication(String id, String reasons) { //method to reject application with ID id, with a message
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

    public void allocateVehicle(Rental rental, int id) { //allocate Vehicle to client, save dates
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getId().equals(id)) {
                vehicle.addRental(rental);
                return;
            }
        }
    }

    private String calculateHash(String message) {//hashing function for safe password
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5"); //using MD5 algorithm
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        assert md5 != null;
        md5.update((message).getBytes());
        byte[] md = md5.digest();
        BigInteger big = new BigInteger(1, md);
        String hash = big.toString(16);
        while (hash.length() < 32) {
            hash += "0";
        }
        return big.toString();
    }
}
