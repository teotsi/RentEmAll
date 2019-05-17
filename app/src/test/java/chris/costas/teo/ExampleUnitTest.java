package chris.costas.teo;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import model.classes.BankAccount;
import model.classes.Customer;
import model.classes.RentingApplication;
import model.classes.Vehicle;
import model.services.AccountService;
import model.services.SearchService;
import model.services.Service;

public class ExampleUnitTest {

    @Test
    public void totalTest(){
        try {
            int status;
            status = Service.CompanyReader("");
            Assert.assertEquals(status,1);
            status = Service.CompanyReader("Companies.txt");
            Assert.assertEquals(status,0);

            status=Service.vehicleReader("");
            Assert.assertEquals(status,1);
            status=Service.vehicleReader("Vehicles.txt");
            Assert.assertEquals(status,0);

            int registerStatus = AccountService.register("Makis Rentals", "Makis policy",
                    "Makis description", 50, 38.080641, 23.687001,
                    "teotsi@mailcom", "Makis12", new BankAccount("Makis", "43", 65));
            Assert.assertEquals(registerStatus,3);

            registerStatus =AccountService.register("Makis Rentals", "Makis policy",
                    "Makis description", 50, 38.080641, 23.687001,
                    "teotsi@gmail.com", "Makis12", new BankAccount("Makis", "43", 65));
            Assert.assertEquals(registerStatus,2);

            registerStatus =AccountService.register("Makis Rentals", "Makis policy",
                    "Makis description", 50, 38.080641, 23.687001,
                    "makaros@gmail.com", "Makis12", new BankAccount("Makis", "43", 65));
            Assert.assertEquals(registerStatus,1);

            registerStatus =AccountService.register("Makis Rentals", "Makis policy",
                    "Makis description", 50, 38.080641, 23.687001,
                    "makaros@gmail.com", "Makaros!2", new BankAccount("Makis", "43", 65));
            Assert.assertEquals(registerStatus,0);


            boolean loginStatus = AccountService.login("makaros@mailcom", "Makaros!2");
            Assert.assertEquals(loginStatus,false);

            loginStatus=AccountService.login("makaros@gmail.com","Makaros!2");
            Assert.assertEquals(loginStatus,true);

            int numberOfVehicles = AccountService.getNumberOfVehicles();
            Vehicle astra = new Vehicle("Opel", "Astra","Car", 5, "Diesel", false, 20,
                    "extra", "Automatic", LocalDate.parse("2018-06-06"), true);
            AccountService.addVehicle(astra,2);
            int expected = numberOfVehicles+2;
            Assert.assertEquals(expected,AccountService.getNumberOfVehicles());


            expected -=1;
            AccountService.removeVehicle(AccountService.getVehicles().get(0).getId());
            Assert.assertEquals(expected,AccountService.getNumberOfVehicles());
            List<RentingApplication> list = AccountService.getPendingApplications();
            Assert.assertEquals(true,list.isEmpty());

            AccountService.signOut();

            AccountService.login("teotsi@gmail.com","Qwerty!2");

            int size = AccountService.getPendingApplications().size();
            List<Vehicle> vehicles = SearchService.getUnfilteredVehicleList(LocalDate.parse("2018-08-08"),LocalDate.parse("2018-08-09"),
                    38.080650,23.687501 );
            RentingApplication application = SearchService.createApplication(vehicles.get(0).getCompanyId(),vehicles.get(4),LocalDate.parse("2018-08-08"),LocalDate.parse("2018-08-09"),
                    LocalDate.parse("2018-07-07"),"0","Athens","salonika",
                    new Customer("john","papajohn","5235235","john@email.com"));
            SearchService.submitApplication(application);
            expected = size+1;
            Assert.assertEquals(expected,AccountService.getPendingApplications().size());

            list = AccountService.getPendingApplications();
            expected=list.size()-1;
            AccountService.rejectApplication(list.get(0).getId(),"Client asked for cancel");
            Assert.assertEquals(expected,AccountService.getPendingApplications().size());

            vehicles = SearchService.getFilteredVehicleList(LocalDate.parse("2018-08-08"),LocalDate.parse("2018-08-09"),"brand,Opel,seats,5",
                    38.080650,23.687501 );
            Assert.assertEquals("Opel",vehicles.get(0).getBrand());


            Service.CompanyWriter("CompaniesOut.txt");
            Service.VehicleWriter("testVehicleOut.txt");
        } catch (IOException e) {
            System.err.println("Hi");
        }
    }
}
