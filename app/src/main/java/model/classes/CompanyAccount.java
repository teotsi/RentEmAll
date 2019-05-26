package model.classes;

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
    private double latitude;
    private double longitude;
    private List<Vehicle> vehicles;
    private List<RentingApplication> applications;
    private String email;
    private String password;
    private BankAccount bankAccount;
    private Address address;
    private String afm;
    public CompanyAccount(String companyName, String policy, String description, float range, double latitude, double longitude, String email, String password, boolean isNewAccount,String afm, BankAccount bankAccount) {
        this.companyName = companyName;
        this.id = this.companyName.hashCode();
        this.policy = policy;
        this.description = description;
        this.range = range;
        this.latitude = latitude;
        this.longitude = longitude;
        this.vehicles = new ArrayList<>();
        this.applications = new ArrayList<>();
        this.email = email;
        this.bankAccount = bankAccount;
        this.afm=afm;
        if (isNewAccount) { //if we are reading created accounts we don't have to hash the passwords again
            this.password = calculateHash(password.trim());
        } else {
            this.password = password;
        }
        this.logs = "Account created\n";
    }

    public String toString() {
        return (this.companyName + "/" + this.policy + "/" + this.description + "/" + this.range + "/" + this.latitude + "/" + this.longitude + "/" + this.email + "/" + this.password + "/" + bankAccount.toString());
    }

    public void addMultipleVehicles(List<Vehicle> cars) {
        vehicles.addAll(cars);
    }

    public void addMultipleApplications(List<RentingApplication> l) {
        applications.addAll(l);
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

    public List<Rental> getRentals(){
        return rentals;
    }

    public void logSession(String message) {
        this.logs += message + "\n";
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String name){this.companyName = name;}

    public int getId() {
        return id;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {this.latitude = latitude;}

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude){this.longitude = longitude;}

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

    public BankAccount getBankAccount() {
        return this.bankAccount;
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

    public void setRentals(List<Rental> rentals) {
        this.rentals=rentals;
    }

    public String getAfm() {
        return afm;
    }

    public void setAfm(String afm) {
        this.afm = afm;
    }
}
