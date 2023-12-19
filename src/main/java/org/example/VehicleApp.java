package org.example;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

public class VehicleApp implements VehicleHiringTest{

    private Map<String,Vehicle> vehicles;
    private Map<Location,List<Vehicle>> vehicleMap;


    public VehicleApp(){
        vehicles = new HashMap<>();
        vehicleMap = new HashMap<>();
    }



    public boolean testAddToMap(String registrationNumber, Location loc){

        if(vehicles.containsKey(registrationNumber)){
            return false;
        }

        Vehicle newVehicle = new Vehicle(registrationNumber,loc);
        vehicles.put(registrationNumber,newVehicle);
        vehicleMap.computeIfAbsent(loc, k -> new ArrayList<>()).add(newVehicle);

        return true;


    }

    public boolean testMoveVehicle(String reg, Location loc) {

        Vehicle vehicle = vehicles.get(reg);
        if (vehicle == null) {
            return false;
        }


        List<Vehicle> currentLocationVehicles = vehicleMap.get(vehicle.getLocation());
        if (currentLocationVehicles != null) {
            currentLocationVehicles.remove(vehicle);
        }


        vehicle.setLocation(loc);


        vehicleMap.computeIfAbsent(loc, k -> new ArrayList<>()).add(vehicle);

        return true;
    }


    public boolean testRemoveVehicle(String reg) {

        Vehicle vehicle = vehicles.remove(reg);
        if (vehicle == null) {
            return false;
        }


        List<Vehicle> currentLocationVehicles = vehicleMap.get(vehicle.getLocation());
        if (currentLocationVehicles != null) {
            currentLocationVehicles.remove(vehicle);
        }

        return true;
    }


    public Location testGetVehicleLoc(String reg) {

        Vehicle vehicle = vehicles.get(reg);
        return (vehicle != null) ? vehicle.getLocation() : null;
    }


    public List<String> testGetVehiclesInRange(Location loc, int r) {
        List<String> vehiclesInRange = new ArrayList<>();

        for (Map.Entry<Location, List<Vehicle>> entry : vehicleMap.entrySet()) {
            Location currentLoc = entry.getKey();
            List<Vehicle> vehiclesAtLoc = entry.getValue();

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



    private void printCellWithVehicle(Vehicle vehicle){

        System.out.print("[" + vehicle.getRegistrationNumber()+ "]");
    }

    private void printEmptyCell(){
        System.out.print("[    ]");
    }


    public void visualizeGrid(int gridSize) {
        for (int y = 0; y < gridSize; y++) {
            for (int x = 0; x < gridSize; x++) {
                Location currentLoc = new Location(x, y);
                List<Vehicle> vehiclesAtLoc = vehicleMap.getOrDefault(currentLoc, new ArrayList<>());


                if (!vehiclesAtLoc.isEmpty()) {

                    printCellWithVehicle(vehiclesAtLoc.get(0));
                } else {
                    printEmptyCell();
                }
            }
            System.out.println();
        }
    }
}


