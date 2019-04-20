package services;
import classes.CompanyAccount;
import classes.RentingApplication;
import classes.Vehicle;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Service {
//    protected static File vehiclesFile= new File("");
    protected static DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    protected static List<CompanyAccount> companies = new ArrayList<>(); //all companies
    private static final String PATH = "../dataset/";
    public static void CompanyReader(String file) throws IOException{
        List<CompanyAccount> companies = new ArrayList<>();
        
        Scanner s = new Scanner(new File(PATH+file));
        while(s.hasNext()){
            String line = s.nextLine();
    
            StringTokenizer st = new StringTokenizer(line, "/");
            companies.add(new CompanyAccount(st.nextToken(), st.nextToken(), st.nextToken(), Float.parseFloat(st.nextToken()), Double.parseDouble(st.nextToken()), Double.parseDouble(st.nextToken()), st.nextToken(), st.nextToken(), false));
        }
        Service.companies = companies;
        for(CompanyAccount c : companies){
            System.out.println(c.toString());
        }
        s.close();
    }

    public static void CompanyWriter(String file) throws IOException{
        BufferedWriter writer = null;
        try{
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(PATH+file))));
        }catch(FileNotFoundException e){
            System.err.println("Hi");
        }
        try{
            for(CompanyAccount c : companies){
                writer.write(c.toString() + "\n");
            }
            writer.close();
        }catch(IOException t){
            System.err.println("Hi1");
        }
    }

    public static void VehicleWriter(String file) throws IOException{
        BufferedWriter writer = null;
        try{
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(PATH+file))));
        }catch(FileNotFoundException e){
            System.err.println("Hi");
        }
        try{
            for(CompanyAccount c : companies){
                writer.write(c.getCompanyName() + "{\n");
                for(Vehicle v : c.getVehicles()){
                    writer.write(v.toString() + "\n");
                }
                writer.write("}\n");
            }
            writer.close();
        }catch(IOException t){
            System.err.println("Hi1");
        }
    }

    public static void CarReader(String file) throws IOException{

        Scanner s = new Scanner(new File(PATH+file));
        while(s.hasNext()){
            String owner = s.nextLine();
            owner = owner.substring(0, owner.length() - 1);
            String line = s.nextLine();
            List<Vehicle> cars = new ArrayList<>();
            int counter = -1;


            while(!line.contains("}")){
                StringTokenizer st = new StringTokenizer(line, "/");
                cars.add(new Vehicle(st.nextToken(), st.nextToken(), st.nextToken(), Integer.parseInt(st.nextToken()), st.nextToken(), Boolean.parseBoolean(st.nextToken()), Float.parseFloat(st.nextToken()), st.nextToken(), st.nextToken(), LocalDate.parse(st.nextToken()), Boolean.parseBoolean(st.nextToken())));
                counter++;
                cars.get(counter).addUpcomingRentals(new RentingApplication(owner.hashCode(), LocalDate.parse(st.nextToken()), LocalDate.parse(st.nextToken()), LocalDate.parse(st.nextToken()), st.nextToken(), cars.get(counter), st.nextToken(), st.nextToken(), new Customer(st.nextToken(), st.nextToken(), st.nextToken(), st.nextToken())));
                
                line = s.nextLine();
            }   
            counter = -1;
            for(CompanyAccount c : companies){
                if(c.getCompanyName().equals(owner)){
                    c.addMultipleVehicles(cars);
                    for(Vehicle v : c.getVehicles()){
                       v.setCompanyId(c.getId());
                    }
                }
            }
        }
        for(CompanyAccount c : companies){
            System.out.println(c.getCompanyName() + "{\n");
            for(Vehicle v : c.getVehicles()){
                System.out.println(v.toString());
            }
            System.out.println("\n}");
        }
        s.close();
    }

    public static List<RentingApplication> CarApplicationHandler(){
        List<RentingApplication> l = new ArrayList<>();


        return l;
    }

}
