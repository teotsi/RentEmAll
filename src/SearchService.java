import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

public class SearchService extends Service{
    private static List<CompanyAccount> companies;

    public static List<Vehicle> getUnfilteredVehicleList(LocalDate startDate, LocalDate endDate) {
        List<Vehicle> availableVehicles = new ArrayList<>();
        for (CompanyAccount companyAccount : companies) {
            List<Vehicle> vehicles = companyAccount.getVehicles();
            for (Vehicle vehicle : vehicles) {
                if (vehicle.isAvailable() && vehicle.isFree(startDate, endDate)) {
                    availableVehicles.add(vehicle);
                }
            }
        }
        return availableVehicles;
    }

    public List<Vehicle> getFilteredVehicleList(LocalDate startDate, LocalDate endDate, String filters) {
        List<Vehicle> availableVehicles = getUnfilteredVehicleList(startDate, endDate);
        StringTokenizer filterTokenizer = new StringTokenizer(filters, ",");
        while(filterTokenizer.hasMoreTokens()){
            String filter = filterTokenizer.nextToken();
            String filterChoice = filterTokenizer.nextToken();
            if(filter.equals("brand") ){
                for (Iterator<Vehicle> it = availableVehicles.iterator(); it.hasNext(); ) {
                    Vehicle vehicle = it.next();
                    if(!vehicle.getBrand().equals(filterChoice)){
                        it.remove();
                    }
                }
            }else if(filter.equals("type")){
                for (Iterator<Vehicle> it = availableVehicles.iterator(); it.hasNext(); ) {
                    Vehicle vehicle = it.next();
                    if(!vehicle.getType().equals(filterChoice)){
                        it.remove();
                    }
                }
            }else if(filter.equals("seats")){
                for (Iterator<Vehicle> it = availableVehicles.iterator(); it.hasNext(); ) {
                    Vehicle vehicle = it.next();
                    if(vehicle.getSeats()!=Integer.parseInt(filterChoice)){
                        it.remove();
                    }
                }
            }else if(filter.equals("fuelType")){
                for (Iterator<Vehicle> it = availableVehicles.iterator(); it.hasNext(); ) {
                    Vehicle vehicle = it.next();
                    if(!vehicle.getFuelType().equals(filterChoice)){
                        it.remove();
                    }
                }
            }else if(filter.equals("pce")){
                for (Iterator<Vehicle> it = availableVehicles.iterator(); it.hasNext(); ) {
                    Vehicle vehicle = it.next();
                    if(vehicle.isPce()!=Boolean.parseBoolean(filterChoice)){
                        it.remove();
                    }
                }
            }else if(filter.equals("transmissionType")){
                for (Iterator<Vehicle> it = availableVehicles.iterator(); it.hasNext(); ) {
                    Vehicle vehicle = it.next();
                    if(!vehicle.getTransmissionType().equals(filterChoice)){
                        it.remove();
                    }
                }
            }else if(filter.equals("rate")){
                for (Iterator<Vehicle> it = availableVehicles.iterator(); it.hasNext(); ) {
                    Vehicle vehicle = it.next();
                    if(vehicle.getRate()>Integer.parseInt(filterChoice)){
                        it.remove();
                    }
                }
            }
        }
        return availableVehicles;

    }
}