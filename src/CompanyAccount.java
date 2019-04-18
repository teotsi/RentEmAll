

import javafx.application.Application;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class CompanyAccount {
    private String id;
    private String policy;
    private String description;
    private String logs;
    private float range;
    private List<Vehicle> vehicles;
    private List<RentingApplication> applications;
    private String email;
    private String password;

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

    public boolean authorizeLogin(String email, String password){
        if(this.email.equals(email) && this.password.equals(calculateHash(password))) return true;
        return false;
    }

    public void addVehicle(Vehicle vehicle, int amount){
        for (int i = 0; i < amount; i++) {
            vehicles.add(vehicle);
        }
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

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public List<RentingApplication> getApplications() {
        return applications;
    }

    public List<RentingApplication> getPendingApplications(){
        List<RentingApplication> pendingApplications = new ArrayList<>();
        for(RentingApplication application: applications){
            if(application.isPending()){
                pendingApplications.add(application);
            }
        }
        return pendingApplications;
    }

    public List<RentingApplication> getAcceptedApplications(){
        List<RentingApplication> acceptedApplications = new ArrayList<>();
            for(RentingApplication application: applications){
                 if(application.isAccepted()){
                     acceptedApplications.add(application);
                 }
            }
        return acceptedApplications;
    }

    public List<RentingApplication> getRejectApplications(){
        List<RentingApplication> rejectedApplications = new ArrayList<>();
        for(RentingApplication application: applications){
            if(application.isAccepted()==false && application.isPending()==false){
                rejectedApplications.add(application);
            }
        }
        return rejectedApplications;
    }

    public void acceptApplication(String id){
        for(RentingApplication application: applications){
            if( application.getId().equals(id)){
                application.setPending(false);
                application.setAccepted(true);
                return;
            }
        }
    }

    public void rejectApplication(String id, String reasons){
        for(RentingApplication application:applications){
            if(application.getId().equals(id)){
                application.setPending(false);
                application.setComments(reasons);
                String vehicleID = application.getVehicle().getId();
                for(Vehicle vehicle:vehicles){
                    if(vehicle.getId().equals(vehicleID)){
                        vehicle.setAvailable(true);
                        break;
                    }
                }
                break;
            }
        }

    }

    public void allocateVehicle(String id){
        for(Vehicle vehicle:vehicles){
            if(vehicle.getId().equals(id)){
                vehicle.setAvailable(false);
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
        while(hash.length()<32){
            hash+="0";
        }
        return big.toString();
    }
}
