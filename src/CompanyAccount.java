public class CompanyAccount {
    private String id;
    private String policy;
    private String description;
    private String logs;
    private float range;

    public CompanyAccount(String id, String policy, String description, String logs, float range) {
        this.id = id;
        this.policy = policy;
        this.description = description;
        this.logs = logs;
        this.range = range;
    }

    public void addVehicle(){

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
    public boolean manageApplication(){
        return true;
    }
}
