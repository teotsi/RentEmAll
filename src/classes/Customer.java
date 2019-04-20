package classes;

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

    public String toString(){
        return name + "/" + surname + "/" + telephone + "/" + email;
    }
}
