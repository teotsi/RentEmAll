package model.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import model.classes.CompanyAccount;
import model.classes.RentingApplication;


public class AdviceService extends Service{
    private String id;
    private LocalDate date;
    private String text;

    public AdviceService(String id, LocalDate date, String text) {
        this.id = id;
        this.date = date;
        this.text = text;
    }

    public void vehicle_advice(){
        List<Map.Entry<RentingApplication,Integer>> countVecs=new ArrayList<Map.Entry<RentingApplication,Integer>>();
        for(RentingApplication application: Applications){

        }
        for(CompanyAccount company: companies){

        }
    }


//    public void exportAdvice(CompanyAccount C, String timeframe){
//        //AdviceService for accepted applications
//        for(RentingApplication r : C.getAcceptedApplications()){
//            if(timeframe.equals("1 week")){
//                if(r.getReplyDate().isAfter(LocalDate.now().minusWeeks(1))){
//
//                }
//            }
//            else if(timeframe.equals("1 month")){
//                if(r.getReplyDate().isAfter(LocalDate.now().minusMonths(1))){
//
//                }
//            }
//            else if(timeframe.equals("6 months")){
//                if(r.getReplyDate().isAfter(LocalDate.now().minusMonths(6))){
//
//                }
//            }
//            else if(timeframe.equals("1 year")){
//                if(r.getReplyDate().isAfter(LocalDate.now().minusYears(1))){
//
//                }
//            }
//
//        }
//
//
//        //AdviceService for rejected applications
//        for(RentingApplication r : C.getRejectApplications()){
//            if(timeframe.equals("1 week")){
//                if(r.getReplyDate().isAfter(LocalDate.now().minusWeeks(1))){
//
//                }
//            }
//            else if(timeframe.equals("1 month")){
//                if(r.getReplyDate().isAfter(LocalDate.now().minusMonths(1))){
//
//                }
//            }
//            else if(timeframe.equals("6 months")){
//                if(r.getReplyDate().isAfter(LocalDate.now().minusMonths(6))){
//
//                }
//            }
//            else if(timeframe.equals("1 year")){
//                if(r.getReplyDate().isAfter(LocalDate.now().minusYears(1))){
//
//                }
//            }
//
//        }
//    }
}
