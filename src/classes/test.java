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
        File f1 = new File("../dataset/Companies.txt");
        File f2 = new File("../dataset/Vehicles.txt");
        File f3 = new File("../dataset/Companies.txt");
        try{
            Service.CompanyReader(f1);
            Service.CarReader(f2);
            Service.CompanyWriter(f3);
            System.out.println(AccountService.login("costasxusa@gmail.com","Qwerty!2"));
        }catch(IOException e ){
            System.err.println("Hi");
        }    
    }
}
