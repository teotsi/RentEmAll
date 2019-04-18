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
    public void exportAdvice(CompanyAccount C, String timeframe){
        if(timeframe.equals("1 week")){
            for(RentingApplication r : C.getAcceptedApplications()){
                if(r.getAcceptanceDate().)
            }
        }else if(timeframe.equals("1 month")){
            for(RentingApplication r : C.getAcceptedApplications()){
                if()
            }
        }else if(timeframe.equals("6 months")){
            for(RentingApplication r : C.getAcceptedApplications()){
                if()
            }
        }else if(timeframe.equals("1 year")){
            for(RentingApplication r : C.getAcceptedApplications()){
                if()
            }
        }
    }
}
