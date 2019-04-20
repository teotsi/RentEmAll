package classes;

import java.time.LocalDate;


public class Advice {
    private String id;
    private LocalDate date;
    private String text;

    public Advice(String id, LocalDate date, String text) {
        this.id = id;
        this.date = date;
        this.text = text;
    }
    public void exportAdvice(CompanyAccount C, String timeframe){
        //Advice for accepted applications
        for(RentingApplication r : C.getAcceptedApplications()){
            if(timeframe.equals("1 week")){
                if(r.getReplyDate().isAfter(LocalDate.now().minusWeeks(1))){

                }
            }
            else if(timeframe.equals("1 month")){
                if(r.getReplyDate().isAfter(LocalDate.now().minusMonths(1))){

                }
            }
            else if(timeframe.equals("6 months")){
                if(r.getReplyDate().isAfter(LocalDate.now().minusMonths(6))){

                }
            }
            else if(timeframe.equals("1 year")){
                if(r.getReplyDate().isAfter(LocalDate.now().minusYears(1))){

                }
            }

        }


        //Advice for rejected applications
        for(RentingApplication r : C.getRejectApplications()){
            if(timeframe.equals("1 week")){
                if(r.getReplyDate().isAfter(LocalDate.now().minusWeeks(1))){

                }
            }
            else if(timeframe.equals("1 month")){
                if(r.getReplyDate().isAfter(LocalDate.now().minusMonths(1))){

                }
            }
            else if(timeframe.equals("6 months")){
                if(r.getReplyDate().isAfter(LocalDate.now().minusMonths(6))){

                }
            }
            else if(timeframe.equals("1 year")){
                if(r.getReplyDate().isAfter(LocalDate.now().minusYears(1))){

                }
            }

        }
    }
}
