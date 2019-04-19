import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class Service {
    protected static DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    protected static List<CompanyAccount> companies = new ArrayList<>(); //all companies

    public static void CompanyReader(File f) throws IOException{
        List<CompanyAccount> companies = new ArrayList<>();
        
        Scanner s = new Scanner(f);
        while(s.hasNext()){
            String line = s.nextLine();
    
            StringTokenizer st = new StringTokenizer(line, "/");
            companies.add(new CompanyAccount(st.nextToken(), st.nextToken(), st.nextToken(), Float.parseFloat(st.nextToken()), st.nextToken(), st.nextToken(), true));
        }
        Service.companies = companies;
        for(CompanyAccount c : companies){
            System.out.println(c.toString());
        }
        s.close();
    }

    public static void CompanyWriter(File f) throws IOException{
        BufferedWriter writer = null;
        try{
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f)));
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

    public static void VehicleWriter(File f) throws IOException{
        
    }

    public static void CarReader(File f) throws IOException{

        Scanner s = new Scanner(f);
        while(s.hasNext()){
            String owner = s.nextLine();
            owner = owner.substring(0, owner.length() - 1);
            String line = s.nextLine();
            List<Vehicle> cars = new ArrayList<>();
            while(!line.contains("}")){
                StringTokenizer st = new StringTokenizer(line, "/");
                cars.add(new Vehicle(st.nextToken(), st.nextToken(), st.nextToken(), Integer.parseInt(st.nextToken()), st.nextToken(), Boolean.parseBoolean(st.nextToken()), Float.parseFloat(st.nextToken()), st.nextToken(), st.nextToken(), LocalDate.parse(st.nextToken()), Boolean.parseBoolean(st.nextToken())));
                line = s.nextLine();
            }   
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
}
