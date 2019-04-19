package classes;

public class Address {
    private String street;
    private String number;
    private String zipCode;
    private String city;


    public Address(String street, String number, String zipCode, String city){
        this.street = street;
        this.number = number;
        this.zipCode = zipCode;
        this.city = city;
    }


    public String getAddress(){
        return street+" "+number+", "+zipCode+", "+city;
    }
}
