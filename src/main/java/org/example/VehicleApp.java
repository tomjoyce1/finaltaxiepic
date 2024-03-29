package org.example;

import java.util.HashMap;
import java.util.Map;
import java.text.DecimalFormat;

public class VehicleApp implements VehicleHiringTest {

    int arbitraryConstantToCalculateFarePrice = 2;

    private Map<String, Vehicle> vehicles;
    private Map<Location, CustomArrayList<Vehicle>> vehicleMap;

    public VehicleApp() {
        vehicles = new HashMap<>();
        vehicleMap = new HashMap<>();
    }

    @Override
    public boolean testAddToMap(String registrationNumber, Location loc) {
        if (vehicles.containsKey(registrationNumber)) {
            return false;
        }

        Vehicle newVehicle = new Vehicle(registrationNumber, loc);
        vehicles.put(registrationNumber, newVehicle);
        vehicleMap.computeIfAbsent(loc, k -> new CustomArrayList<>()).add(newVehicle);

        return true;
    }

    @Override
    public boolean testMoveVehicle(String reg, Location loc) {
        Vehicle vehicle = vehicles.get(reg);

        if (vehicle == null) {
            System.out.println("Please input a valid Taxi ID");
            return false;
        }

        CustomArrayList<Vehicle> vehiclesAtDestination = vehicleMap.computeIfAbsent(loc, k -> new CustomArrayList<>());

        CustomArrayList<Vehicle> currentLocationVehicles = vehicleMap.get(vehicle.getLocation());
        if (currentLocationVehicles != null) {
            currentLocationVehicles.remove(vehicle);
        }

        vehicle.setLocation(loc);

        vehicleMap.computeIfAbsent(loc, k -> new CustomArrayList<>()).add(vehicle);

        return true;
    }

    static int count = 0;
    private static double totalSum = 0;
    public static double getAverageRating(int Rating){
        count++;

        totalSum += Rating;
        return totalSum/count;

    }



    public boolean vehicleExists(String taxiInputtedByUser) {
        return vehicles.containsKey(taxiInputtedByUser);
    }





    @Override
    public boolean testRemoveVehicle(String registrationNumber) {
        Vehicle vehicle = vehicles.remove(registrationNumber);

        if (vehicle != null) {
            Location loc = vehicle.getLocation();
            vehicleMap.get(loc).remove(vehicle);
            return true;
        } else {
            System.out.println("Vehicle with ID " + registrationNumber + " not found");
            return false;
        }
    }

    @Override
    public Location testGetVehicleLoc(String registrationNumber) {
        Vehicle vehicle = vehicles.get(registrationNumber);
        return (vehicle != null) ? vehicle.getLocation() : null;
    }

@Override
    public CustomArrayList<String> testGetVehiclesInRange(Location loc, int r) {
        CustomArrayList<String> vehiclesInRange = new CustomArrayList<>();

        for (Map.Entry<Location, CustomArrayList<Vehicle>> entry : vehicleMap.entrySet()) {
            Location currentLoc = entry.getKey();
            CustomArrayList<Vehicle> vehiclesAtLoc = entry.getValue();

            int distanceX = Math.abs(currentLoc.getX() - loc.getX());
            int distanceY = Math.abs(currentLoc.getY() - loc.getY());

            if (distanceX <= r && distanceY <= r) {
                for (Vehicle vehicle : vehiclesAtLoc) {
                    vehiclesInRange.add(vehicle.getRegistrationNumber());
                }
            }
        }

        return vehiclesInRange;
    }


@Override
    public double calculateFare(int x1, int y1, int x2, int y2, Location startingDriverLocation) {

        int startingDriverX = startingDriverLocation.getX();
    int startingDriverY = startingDriverLocation.getY();


    double distancefromTaxiToCustomer = Math.sqrt(Math.pow(startingDriverX - x1, 2) + Math.pow(startingDriverY - y1, 2));
    double distance = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
        double fare = (distancefromTaxiToCustomer + distance) * arbitraryConstantToCalculateFarePrice;


    DecimalFormat df = new DecimalFormat("#.##");
    String formattedNumber = df.format(fare);
    try {
        return Double.parseDouble(formattedNumber);
    } catch (NumberFormatException e) {

        System.err.println("Error parsing fare amount: " + e.getMessage());
        return 0.0;
    }
    }

    private void printCellWithVehicle(Vehicle vehicle) {
        System.out.print("[" + vehicle.getRegistrationNumber() + "]");
    }

    private void printEmptyCell() {
        System.out.print("[    ]");
    }

    public void visualizeGrid(int gridSize, int xWhereCustomerIsNow, int yWhereCustomerIsNow) {
        for (int y = 0; y < gridSize; y++) {
            for (int x = 0; x < gridSize; x++) {
                Location currentLoc = new Location(x, y);
                CustomArrayList<Vehicle> vehiclesAtLoc = vehicleMap.getOrDefault(currentLoc, new CustomArrayList<>());

                if (!vehiclesAtLoc.isEmpty() || (x == xWhereCustomerIsNow && y == yWhereCustomerIsNow)) {
                    if (x == xWhereCustomerIsNow && y == yWhereCustomerIsNow) {
                        System.out.print("[Cust]");

                        for (Vehicle vehicle : vehiclesAtLoc) {
                            printCellWithVehicle(vehicle);
                        }
                    } else {
                        for (Vehicle vehicle : vehiclesAtLoc) {
                            printCellWithVehicle(vehicle);
                        }
                    }
                } else {
                    printEmptyCell();
                }
            }
            System.out.println();
        }
    }
}
