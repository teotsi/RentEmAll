import java.time.LocalDate;

public class Customer {
    private String name;
    private String surname;
    private String telephone;
    private String email;


    public Customer(String name, String surname, String telephone, String email){
        this.name = name;
        this.surname = surname;
        this.telephone = telephone;
        this.email = email;
    }
    public String getTelephone() {
        return telephone;
    }

    public String getFullName(){
        return name+" "+surname;
    }

    public RentingApplication createApplication(LocalDate startDate, LocalDate endDate, LocalDate replyDate, String id, String customerLocation, String companyLocation){
        RentingApplication R = new RentingApplication(startDate, endDate, replyDate, id, customerLocation, companyLocation);
        return R;
    }


}
