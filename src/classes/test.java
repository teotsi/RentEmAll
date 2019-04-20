package classes;

import services.AccountService;
import services.Service;

import java.io.File;
import java.io.IOException;

public class test {
    public static void main(String[] args) {
       /* System.out.println(AccountService.passwordIsValid("Qwerty!2"));
        System.out.println(AccountService.emailIsValid("kati@gmail.com"));
        AccountService.register("","",(float) 0.0,"kati@aueb.com","Qwerty!2");
        
        AccountService.save();*/
        try{
            Service.CompanyReader("Companies.txt");
            Service.CarReader("Vehicles.txt");
            Service.CompanyWriter("Companies.txt");
            Service.VehicleWriter("testVehicle.txt");
            System.out.println(AccountService.login("costasxusa@gmail.com","Qwerty!2"));
        }catch(IOException e ){
            System.err.println("Hi");
        }    
    }
}
