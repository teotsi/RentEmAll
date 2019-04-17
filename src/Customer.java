public class Customer {
    private String name;
    private String surname;
    private String telephone;

    public String getTelephone() {
        return telephone;
    }

    public String getFullName(){
        return name+" "+surname;
    }
    public void createApplication(){}
}
