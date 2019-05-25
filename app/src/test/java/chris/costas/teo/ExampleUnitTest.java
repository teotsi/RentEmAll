package chris.costas.teo;

import android.content.res.AssetManager;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import model.classes.BankAccount;
import model.classes.Customer;
import model.classes.RentingApplication;
import model.classes.Vehicle;
import model.services.AccountService;
import model.services.SearchService;
import model.services.Service;

import static java.util.Objects.isNull;

public class ExampleUnitTest {

    @Before
    public void companyReader(){
        Assert.assertEquals(Service.companyReader(""),1);
        Assert.assertEquals(Service.companyReader("Companies.txt"),0);
    }

    @Before
    public void vehicleReader(){
        Assert.assertEquals(Service.vehicleReader(""),1);
        Assert.assertEquals(Service.vehicleReader("Vehicles.txt"),0);
    }

    @Test
    public void successLoginTest() {
        Assert.assertEquals(true,AccountService.login("teotsi@gmail.com", "Qwerty!2"));
    }

    @Test
    public void wrongMailLoginTest(){
        Assert.assertEquals(false,AccountService.login("christos1charma@gmail.com", "Qwerty!2"));
    }

    @Test
    public void wrongPassLoginTest(){
        Assert.assertEquals(false,AccountService.login("christos1charma@gmail.com", "Qweerrty!2"));
    }

    @Test
    public void emailIsAvailable(){
        Assert.assertEquals(true, AccountService.emailIsAvailable("teotsi2@gmail.com"));
        Assert.assertEquals(true, AccountService.emailIsAvailable("teotsi2@yahoo.com"));
        Assert.assertEquals(true, AccountService.emailIsAvailable("teotsi@yahoo.com"));
        Assert.assertEquals(true, AccountService.emailIsAvailable("teotsi@gmail.gr"));
    }

    @Test
    public void emailIsUnavailable(){
        Assert.assertEquals(false,AccountService.emailIsAvailable("teotsi@gmail.com"));
        Assert.assertEquals(false,AccountService.emailIsAvailable("chrischarma@gmail.com"));
    }

    @Test
    public void validPassword(){
        Assert.assertEquals("Must return true because password is valid",true,AccountService.passwordIsValid("Make&489"));
        Assert.assertEquals("Must return true because password is valid",true,AccountService.passwordIsValid("ghjkTRO&489"));
        Assert.assertEquals("Must return true because password is valid",true,AccountService.passwordIsValid("78$#kjhdasDASKJ"));
        Assert.assertEquals("Must return true because password is valid",true,AccountService.passwordIsValid("1234a56!T"));
    }

    @Test
    public void  invalidPassword() {
        Assert.assertEquals("Must return false because password is invalid",false,AccountService.passwordIsValid("g"));
        Assert.assertEquals("Must return false because password is invalid",false,AccountService.passwordIsValid("4g#9879"));
        Assert.assertEquals("Must return false because password is invalid",false,AccountService.passwordIsValid("98534!jhsdkfhTYlksadkhkjshdaksjhksajdhaslkjdhaslkdjashdlkjasdhlkjashdflkjdhfskjfhdsf"));
        Assert.assertEquals("Must return false because password is invalid",false,AccountService.passwordIsValid("gT%45gr"));
        Assert.assertEquals("Must return false because password is invalid",false,AccountService.passwordIsValid("7"));
    }

    @Test
    public void checkCC(){
        Assert.assertEquals("Should return true", true, SearchService.cc("1254235678548965", "06/22", "563"));
        Assert.assertEquals("Should return true", true, SearchService.cc("1254235678575126", "05/20", "754"));
        Assert.assertEquals("Should return true", true, SearchService.cc("1254235678575126", "05/19", "754"));
        Assert.assertEquals("Should return true", true, SearchService.cc("1254235678575126", "07/19", "754"));
        Assert.assertEquals("Should return true", true, SearchService.cc("1254235678575126", "04/20", "754"));
        Assert.assertEquals("Should return false with early month", false, SearchService.cc("1254235678575126", "04/19", "754"));
        Assert.assertEquals("Should return false with invalid date", false, SearchService.cc("1254235678575126", "09/18", "754"));
        Assert.assertEquals("Should return false with invalid date and higher cnumber", false, SearchService.cc("12542356758575126", "04/20", "754"));
        Assert.assertEquals("Should return false with higher cnumber", false, SearchService.cc("1254235678575122136", "07/20", "754"));
        Assert.assertEquals("Should return false with lower cnumber", false, SearchService.cc("125423567126", "07/22", "754"));
        Assert.assertEquals("Should return false with higher cvv", false, SearchService.cc("1254235678548965", "06/22", "5653"));
        Assert.assertEquals("Should return false with lower cvv", false, SearchService.cc("1254235678548965", "06/22", "53"));

    }

    @Test
    public void calculateCostRight(){
        String startDate="2018-05-03";
        String endDate="2018-05-05";
        System.out.println(AccountService.getCompany().getCompanyName());
        System.out.println("before calculate cost");
        double expected=AccountService.getVehicles().get(0).getRate()*2;
        double actual=SearchService.calculateCost(AccountService.getVehicles().get(1),LocalDate.parse(startDate),LocalDate.parse(endDate));
        System.out.println(expected);
        System.out.println(actual);
        Assert.assertEquals(expected, actual);
        System.out.println("calculate cost");
    }

    @Test
    public void checkDistanceCalculation(){
        double result=SearchService.calculateDistance(38.0713509,23.7734172,38.0627927,23.7775256);
        System.out.println(result);
        Assert.assertTrue("Should return about 1.02", (result>1.01) && (result<1.03));
    }

    @Test
    public void checksIfTheEmailIsValidOrNot(){
        Assert.assertFalse(AccountService.emailIsValid("teotsi@mailcom"));
    }

    @Test
    public void registerCompanyFunctionality(){
        Assert.assertEquals(AccountService.register("Makis Rentals", "Makis policy",
                "Makis description", 50, 38.080641, 23.687001,
                "teotsi@mailcom", "Makis12", new BankAccount("Makis", "43", 65)),3);
        Assert.assertEquals(AccountService.register("Makis Rentals", "Makis policy",
                "Makis description", 50, 38.080641, 23.687001,
                "teotsi@gmail.com", "Makis12", new BankAccount("Makis", "43", 65)),2);
        Assert.assertEquals(AccountService.register("Makis Rentals", "Makis policy",
                "Makis description", 50, 38.080641, 23.687001,
                "makaros@gmail.com", "Makis12", new BankAccount("Makis", "43", 65)),1);
        Assert.assertEquals(AccountService.register("Makis Rentals", "Makis policy",
                "Makis description", 50, 38.080641, 23.687001,
                "makaros@gmail.com", "Makaros!2", new BankAccount("Makis", "43", 65)),0);
    }

    @Test
    public void vehicleAddandRemove(){
        int numberOfVehicles = AccountService.getNumberOfVehicles();
        Vehicle astra = new Vehicle("Opel", "Astra","Car", 5, "Diesel", false, 20,
                "extra", "Automatic", LocalDate.parse("2018-06-06"), true);
        AccountService.addVehicle(astra,2);
        int expected = numberOfVehicles+2;
        Assert.assertEquals(expected,AccountService.getNumberOfVehicles());
        expected -=1;
        AccountService.removeVehicle(AccountService.getVehicles().get(0).getId());
        Assert.assertEquals(expected,AccountService.getNumberOfVehicles());
    }



//    @Test
//    public void totalTest(){
//        try {
//            int status;
//            status = Service.companyReader("");
//            Assert.assertEquals(status,1);
//            status = Service.companyReader("Companies.txt");
//            Assert.assertEquals(status,0);
//
//            status=Service.vehicleReader("");
//            Assert.assertEquals(status,1);
//            status=Service.vehicleReader("Vehicles.txt");
//            Assert.assertEquals(status,0);
//
//            int registerStatus = AccountService.register("Makis Rentals", "Makis policy",
//                    "Makis description", 50, 38.080641, 23.687001,
//                    "teotsi@mailcom", "Makis12", new BankAccount("Makis", "43", 65));
//            Assert.assertEquals(registerStatus,3);
//
//            registerStatus =AccountService.register("Makis Rentals", "Makis policy",
//                    "Makis description", 50, 38.080641, 23.687001,
//                    "teotsi@gmail.com", "Makis12", new BankAccount("Makis", "43", 65));
//            Assert.assertEquals(registerStatus,2);
//
//            registerStatus =AccountService.register("Makis Rentals", "Makis policy",
//                    "Makis description", 50, 38.080641, 23.687001,
//                    "makaros@gmail.com", "Makis12", new BankAccount("Makis", "43", 65));
//            Assert.assertEquals(registerStatus,1);
//
//            registerStatus =AccountService.register("Makis Rentals", "Makis policy",
//                    "Makis description", 50, 38.080641, 23.687001,
//                    "makaros@gmail.com", "Makaros!2", new BankAccount("Makis", "43", 65));
//            Assert.assertEquals(registerStatus,0);
//
//
//            boolean loginStatus = AccountService.login("makaros@mailcom", "Makaros!2");
//            Assert.assertEquals(loginStatus,false);
//
//            loginStatus=AccountService.login("makaros@gmail.com","Makaros!2");
//            Assert.assertEquals(loginStatus,true);
//
//            int numberOfVehicles = AccountService.getNumberOfVehicles();
//            Vehicle astra = new Vehicle("Opel", "Astra","Car", 5, "Diesel", false, 20,
//                    "extra", "Automatic", LocalDate.parse("2018-06-06"), true);
//            AccountService.addVehicle(astra,2);
//            int expected = numberOfVehicles+2;
//            Assert.assertEquals(expected,AccountService.getNumberOfVehicles());
//
//
//            expected -=1;
//            AccountService.removeVehicle(AccountService.getVehicles().get(0).getId());
//            Assert.assertEquals(expected,AccountService.getNumberOfVehicles());
//            //
//            List<RentingApplication> list = AccountService.getPendingApplications();
//            Assert.assertEquals(true,list.isEmpty());
//            AccountService.signOut();
//
//            AccountService.login("teotsi@gmail.com","Qwerty!2");
//
//            int size = AccountService.getPendingApplications().size();
//            List<Vehicle> vehicles = SearchService.getUnfilteredVehicleList(LocalDate.parse("2018-08-08"),LocalDate.parse("2018-08-09"),
//                    38.080650,23.687501 );
//            RentingApplication application = SearchService.createApplication(vehicles.get(0).getCompanyId(),vehicles.get(4),LocalDate.parse("2018-08-08"),LocalDate.parse("2018-08-09"),
//                    LocalDate.parse("2018-07-07"),"0","Athens","salonika",
//                    new Customer("john","papajohn","5235235","john@email.com"));
//            SearchService.submitApplication(application);
//            expected = size+1;
//            Assert.assertEquals(expected,AccountService.getPendingApplications().size());
//
//            list = AccountService.getPendingApplications();
//            expected=list.size()-1;
//            AccountService.acceptApplication(list.get(0).getId());
//            expected = 1;
//            AccountService.save();
//            Assert.assertEquals(expected,Service.getRentals().size());
//
//            vehicles = SearchService.getFilteredVehicleList(LocalDate.parse("2018-08-08"),LocalDate.parse("2018-08-09"),"brand,Opel,seats,5",
//                    38.080650,23.687501 );
//            Assert.assertEquals("Opel",vehicles.get(0).getBrand());
//
//
//            Service.CompanyWriter("CompaniesOut.txt");
//            Service.VehicleWriter("testVehicleOut.txt");
//        } catch (Exception e) {
//            System.err.println("Hi");
//        }
//    }
}
