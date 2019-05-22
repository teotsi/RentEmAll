package model.services;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.StringTokenizer;

import model.classes.BankAccount;
import model.classes.CompanyAccount;
import model.classes.Customer;
import model.classes.Rental;
import model.classes.RentingApplication;
import model.classes.Vehicle;

public class Service {
    private static final String PATH = "src/main/java/model/dataset/";
    private static final double COMMISSION = 15 / 100;
    //    protected static File vehiclesFile= new File("");
    protected static DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    protected static List<CompanyAccount> companies = new ArrayList<>(); //all companies
    protected static List<RentingApplication> Applications = new ArrayList<>();//all renting application
    protected static List<Rental> Rentals = new ArrayList<>();//all rentals
    private static boolean companyRead = false;
    private static boolean vehicleRead = false;
    private static double AppBalance = 0;

    public static void printAllJavaFiles(String directory) {
        File f = new File(directory);
        if (f.isDirectory()) {
            File[] subDirectories = f.listFiles();
            System.out.println(subDirectories);
        } else {
            printFile(f);
        }
    }

    private static void printFile(File file) {
        // Get file extension
        String fileExtension = "";
        int i = file.getName().lastIndexOf('.');
        if (i >= 0) {
            fileExtension = file.getName().substring(i + 1);
        }

        if (fileExtension.equals("java")) {
            System.out.println("File: " + file.getName() + " Size: " + file.length());
        }
    }

    public static List<CompanyAccount> getCompanies() {
        return companies;
    }

    public static int companyReader(InputStream stream) {
        if (!companyRead) {
            try {
                companyRead = true;
                List<CompanyAccount> companies = new ArrayList<>();
                Scanner s = new Scanner(stream);
                while (s.hasNext()) {
                    String line = s.nextLine();

                    StringTokenizer st = new StringTokenizer(line, "/");
                    companies.add(new CompanyAccount(st.nextToken(), st.nextToken(), st.nextToken(), Float.parseFloat(st.nextToken()), Double.parseDouble(st.nextToken()), Double.parseDouble(st.nextToken()), st.nextToken(), st.nextToken(), false, new BankAccount(st.nextToken(), st.nextToken(), Double.parseDouble(st.nextToken()))));
                }
                Service.companies = companies;
                for (CompanyAccount c : companies) {
                    System.out.println(c.toString());
                }
                s.close();
                return 0;
            } catch (NoSuchElementException e) {
                return 2;
            }
        }
        return 1;
    }


    public static int companyReader(String file) {
        try {
            List<CompanyAccount> companies = new ArrayList<>();
            Scanner s = new Scanner(new File(PATH + file));
            while (s.hasNext()) {
                String line = s.nextLine();

                StringTokenizer st = new StringTokenizer(line, "/");
                companies.add(new CompanyAccount(st.nextToken(), st.nextToken(), st.nextToken(), Float.parseFloat(st.nextToken()), Double.parseDouble(st.nextToken()), Double.parseDouble(st.nextToken()), st.nextToken(), st.nextToken(), false, new BankAccount(st.nextToken(), st.nextToken(), Double.parseDouble(st.nextToken()))));
            }
            Service.companies = companies;
            for (CompanyAccount c : companies) {
                System.out.println(c.toString());
            }
            s.close();
            return 0;
        } catch (FileNotFoundException e) {
            return 1;
        } catch (NoSuchElementException e) {
            return 2;
        }
    }

    public static void increaseAppBalance(double commission) {
        AppBalance += commission;

    }

    public static double calculateCost(Vehicle vehicle, LocalDate startDate, LocalDate endDate) {
        long elapsedDays = ChronoUnit.DAYS.between(startDate, endDate);
        return elapsedDays * vehicle.getRate();
    }

    public static void CompanyWriter(String file) throws IOException {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(PATH + file))));
        } catch (FileNotFoundException e) {
            System.err.println("Hi");
        }
        try {
            for (CompanyAccount c : companies) {
                writer.write(c.toString() + "\n");
            }
            writer.close();
        } catch (IOException t) {
            System.err.println("Hi1");
        }
    }

    public static void VehicleWriter(String file) throws IOException {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(PATH + file))));
        } catch (FileNotFoundException e) {
            System.err.println("Hi");
        }
        try {
            for (CompanyAccount c : companies) {
                writer.write(c.getCompanyName() + "{\n");
                for (Vehicle v : c.getVehicles()) {
                    writer.write(v.toString() + "\n{\n");
                    for (RentingApplication application : v.getUpcomingRentals()) {
                        writer.write(application.toString() + "\n");
                    }
                    writer.write("}\n");
                }
                writer.write("}\n");
            }
            writer.close();
        } catch (IOException t) {
            System.err.println("Hi1");
        }
    }

    public static int vehicleReader(String file) {
        try {
            Scanner s = new Scanner(new File(PATH + file));
            while (s.hasNext()) {
                String owner = s.nextLine();
                owner = owner.substring(0, owner.length() - 1);
                String line = s.nextLine();
                List<Vehicle> cars = new ArrayList<>();
                List<RentingApplication> rentals = new ArrayList<>();
                int counter = -1;


                while (!line.contains("}")) {
                    StringTokenizer st = new StringTokenizer(line, "/");
                    Vehicle vehicle = new Vehicle(st.nextToken(), st.nextToken(), st.nextToken(), Integer.parseInt(st.nextToken()), st.nextToken(), Boolean.parseBoolean(st.nextToken()), Float.parseFloat(st.nextToken()), st.nextToken(), st.nextToken(), LocalDate.parse(st.nextToken()), Boolean.parseBoolean(st.nextToken()));
                    s.nextLine();
                    line = s.nextLine();
                    while (!line.trim().equals("}")) {
                        StringTokenizer st1 = new StringTokenizer(line, "/");
                        RentingApplication application = new RentingApplication(owner.hashCode(), vehicle, LocalDate.parse(st1.nextToken()), LocalDate.parse(st1.nextToken()), LocalDate.parse(st1.nextToken()), st1.nextToken(), st1.nextToken(), st1.nextToken(), new Customer(st1.nextToken(), st1.nextToken(), st1.nextToken(), st1.nextToken()), true);
                        vehicle.addUpcomingRentals(application);
                        rentals.add(application);
                        line = s.nextLine();
                    }
                    cars.add(vehicle);
                    line = s.nextLine();
                }
                for (CompanyAccount c : companies) {
                    if (c.getCompanyName().equals(owner)) {
                        c.addMultipleVehicles(cars);
                        c.addMultipleApplications(rentals);
                        for (Vehicle v : c.getVehicles()) {
                            v.setCompanyId(c.getId());
                        }
                    }
                }
            }
            for (CompanyAccount c : companies) {
                System.out.println(c.getCompanyName() + "{\n");
                for (Vehicle v : c.getVehicles()) {
                    System.out.println(v.toString());
                }
                System.out.println("\n}");
            }
            s.close();
            return 0;
        } catch (FileNotFoundException e) {
            return 1;
        } catch (NoSuchElementException e) {
            return 2;
        }
    }

    public static int vehicleReader(InputStream file) {
        if(!vehicleRead) {
            try {
                vehicleRead=true;
                Scanner s = new Scanner(file);
                while (s.hasNext()) {
                    String owner = s.nextLine();
                    owner = owner.substring(0, owner.length() - 1);
                    String line = s.nextLine();
                    List<Vehicle> cars = new ArrayList<>();
                    List<RentingApplication> rentals = new ArrayList<>();
                    int counter = -1;


                    while (!line.contains("}")) {
                        StringTokenizer st = new StringTokenizer(line, "/");
                        Vehicle vehicle = new Vehicle(st.nextToken(), st.nextToken(), st.nextToken(), Integer.parseInt(st.nextToken()), st.nextToken(), Boolean.parseBoolean(st.nextToken()), Float.parseFloat(st.nextToken()), st.nextToken(), st.nextToken(), LocalDate.parse(st.nextToken()), Boolean.parseBoolean(st.nextToken()));
                        s.nextLine();
                        line = s.nextLine();
                        while (!line.trim().equals("}")) {
                            StringTokenizer st1 = new StringTokenizer(line, "/");
                            RentingApplication application = new RentingApplication(owner.hashCode(), vehicle, LocalDate.parse(st1.nextToken()), LocalDate.parse(st1.nextToken()), LocalDate.parse(st1.nextToken()), st1.nextToken(), st1.nextToken(), st1.nextToken(), new Customer(st1.nextToken(), st1.nextToken(), st1.nextToken(), st1.nextToken()), true);
                            vehicle.addUpcomingRentals(application);
                            rentals.add(application);
                            line = s.nextLine();
                        }
                        cars.add(vehicle);
                        line = s.nextLine();
                    }
                    for (CompanyAccount c : companies) {
                        if (c.getCompanyName().equals(owner)) {
                            c.addMultipleVehicles(cars);
                            c.addMultipleApplications(rentals);
                            for (Vehicle v : c.getVehicles()) {
                                v.setCompanyId(c.getId());
                            }
                        }
                    }
                }
                for (CompanyAccount c : companies) {
                    System.out.println(c.getCompanyName() + "{\n");
                    for (Vehicle v : c.getVehicles()) {
                        System.out.println(v.toString());
                    }
                    System.out.println("\n}");
                }
                s.close();
                return 0;
            } catch (NoSuchElementException e) {
                return 2;
            }
        }
        return 1;
    }

    public static List<RentingApplication> CarApplicationHandler() {
        List<RentingApplication> l = new ArrayList<>();


        return l;
    }

    protected static void completePayment(RentingApplication application) {
        double cost = calculateCost(application.getVehicle(), application.getStartDate(), application.getEndDate());
        double earnings = COMMISSION * cost;
        increaseAppBalance(earnings);
        for (CompanyAccount companyAccount : companies) {
            if (application.getCompanyId() == companyAccount.getId()) {
                companyAccount.getBankAccount().addBalance(cost - earnings);
                return;
            }
        }
    }

    protected static void mergeLists(List<RentingApplication> updatedApplications, List<Rental> updatedRentals) {
        for (RentingApplication application : updatedApplications) {
            Applications.removeIf(old_application -> old_application.getId().equals(application.getId()));

        }
        Applications.addAll(updatedApplications);
        for (Rental rental : updatedRentals) {
            Rentals.removeIf(old_rental -> old_rental.getId().equals(rental.getId()));
        }
        Rentals.addAll((updatedRentals));

    }

    public static void main(String[] args) {
        System.out.println("Hey");
    }

    public static List<Rental> getRentals() {
        return Rentals;
    }
}

