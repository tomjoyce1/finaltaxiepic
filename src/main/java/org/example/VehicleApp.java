package org.example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VehicleApp implements VehicleHiringTest {

    private Map<String, Vehicle> vehicles;
    private Map<Location, CustomArrayList<Vehicle>> vehicleMap;

    public VehicleApp() {
        vehicles = new HashMap<>();
        vehicleMap = new HashMap<>();
    }

    public boolean testAddToMap(String registrationNumber, Location loc) {
        if (vehicles.containsKey(registrationNumber)) {
            return false;
        }

        Vehicle newVehicle = new Vehicle(registrationNumber, loc);
        vehicles.put(registrationNumber, newVehicle);
        vehicleMap.computeIfAbsent(loc, k -> new CustomArrayList<>()).add(newVehicle);

        return true;
    }


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
    public Location testGetVehicleLoc(String registrationNumber) {
        Vehicle vehicle = vehicles.get(registrationNumber);
        return (vehicle != null) ? vehicle.getLocation() : null;
    }

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
