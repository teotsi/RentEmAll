import java.text.DateFormat;

public class Advice {
    private String id;
    private DateFormat date;
    private String text;

    public Advice(String id, DateFormat date, String text){
        this.id = id;
        this.date = date;
        this.text = text;
    }
    public void exportAdvice(){}
}
