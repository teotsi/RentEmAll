public class Address {
    private String street;
    private String number;
    private String zipCode;
    private String city;

    public String getAddress(){
        return street+" "+number+", "+zipCode+", "+city;
    }
}
