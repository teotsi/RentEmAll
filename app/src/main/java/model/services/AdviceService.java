package model.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import model.classes.CompanyAccount;
import model.classes.Rental;
import model.classes.RentingApplication;
import model.classes.Vehicle;


public class AdviceService extends Service {
    private String id;
    private LocalDate date;
//    private String text;
//
//    public AdviceService(String id, LocalDate date, String text) {
//        this.id = id;
//        this.date = date;
//        this.text = text;
//    }

    public String vehicle_advice() {
        String advice_text="something went wrong for vehicle advice";
        List<Integer> vecs = new ArrayList<Integer>();
        List<Integer> countvalues = new ArrayList<Integer>();
        for (RentingApplication application : Applications) {
            boolean flag = false;
            int position = 0;
            for (Integer vec : vecs) {
                if (vec.intValue() == application.getVehicle().getGlobalId()) {
                    flag = true;
                    countvalues.add(position, (new Integer(vec.intValue() + 1)));
                }
                if (flag) {
                    break;
                }
                position++;
            }
            if (!flag) {
                vecs.add(application.getVehicle().getGlobalId());
            }
        }
        Integer BestSellerNum; //the rentals of the best seller
        BestSellerNum = Collections.max(countvalues);
        Integer BestSeller = vecs.get(countvalues.indexOf(BestSellerNum));
        for (CompanyAccount company : companies) {//search for the companies that do not have the bestseller
            boolean flag = false;
            for (Vehicle vec : company.getVehicles()) {
                if (vec.getGlobalId() == BestSeller.intValue()) {
                    flag = true;
                }
            }
            if (!flag) {
                advice_text = "It seems that you do not have the bestseller in the app, perhaps it would help your rentals if you could add it in your vehicle list. The vehicle with global id " + (BestSeller.intValue()) + " is the bestseller";
            } else {
                advice_text = "Well done, you have in your vehicle list the app's bestseller. The vehicle with global id " + (BestSeller.intValue()) + " is the bestseller";
            }
        }
        return advice_text;
    }

    public static String income_stats(CompanyAccount company, LocalDate dateOfStats){//company's income based on rentals and no on applications
            double income= 0;
            try {
                for (Rental rental : company.getRentals()) {
                    if (dateOfStats.isBefore(rental.getStartDate())) {
                        income += rental.profit(rental.getVehicle(), rental.getReceiptDate(), rental.getDeliveryDate());
                    }
                }
            }catch (Exception e){

            }
            return "Your income from the date you chose till today is: "+ income;
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
