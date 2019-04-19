import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class CompanyAccount {
    public List<Rental> rentals;
    private String companyName;
    private int id;
    private String policy;
    private String description;
    private String logs;
    private float range;
    private List<Vehicle> vehicles;
    private List<RentingApplication> applications;
    private String email;
    private String password;
    private Address address;

    public CompanyAccount(String companyName, String policy, String description, float range, String email, String password) {
        this.companyName = companyName;
        this.id = this.hashCode();
        this.policy = policy;
        this.description = description;
        this.range = range;
        this.vehicles = new ArrayList<>();
        this.email = email;
        this.password = calculateHash(password);
        this.logs = "Account created\n";
    }

    public int authorizeLogin(String password) { //checking email/password credentials
        if (this.password.equals(calculateHash(password))) {
            return this.id;
        } else return -1;
    }
    public void addApplication(RentingApplication r) { //adding application
        applications.add(r);
        r.getVehicle().addRental(r);
    }

    public void logSession(String message) {
        this.logs += message + "\n";
    }

    public String getCompanyName(){
        return companyName;
    }
    public int getId() {
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

    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    public List<RentingApplication> getApplications() {
        return applications;
    }

    public void setApplications(List<RentingApplication> applications) {
        this.applications = applications;
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
