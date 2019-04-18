import java.util.Map;

public class Employee {
    private String name;
    private String surname;
    private String id;

    public Employee(String name, String surname, String id){
        this.name = name;
        this.surname = surname;
        this.id = id;
    }

    public String getId() {
        return id;
    }
    public String getFullName() {
        return name+" "+surname;
    }
    public void login(Map.Entry<String, String> credentials){

    }
}
