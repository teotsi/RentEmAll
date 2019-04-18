import java.time.LocalDate;


public class Advice {
    private String id;
    private LocalDate date;
    private String text;

    public Advice(String id, LocalDate date, String text){
        this.id = id;
        this.date = date;
        this.text = text;
    }
    public void exportAdvice(CompanyAccount C, String timeframe){
        if(timeframe.equals("1 week")){
            for(RentingApplication r : C.getAcceptedApplications()){
                if(r.getAcceptanceDate().isAfter(LocalDate.now().minusWeeks(1))){

                }
            }
        }else if(timeframe.equals("1 month")){
            for(RentingApplication r : C.getAcceptedApplications()){
                if(r.getAcceptanceDate().isAfter(LocalDate.now().minusMonths(1))){

                }
            }
        }else if(timeframe.equals("6 months")){
            for(RentingApplication r : C.getAcceptedApplications()){
                if(r.getAcceptanceDate().isAfter(LocalDate.now().minusMonths(6))){

                }
            }
        }else if(timeframe.equals("1 year")){
            for(RentingApplication r : C.getAcceptedApplications()){
                if(r.getAcceptanceDate().isAfter(LocalDate.now().minusYears(1))){

                }
            }
        }
    }
}
