package org.example;

import java.util.*;

public interface VehicleHiringTest {


    boolean testAddToMap(String registrationNumber, Location loc);

    boolean testMoveVehicle(String reg, Location loc);

 boolean testRemoveVehicle(String registrationNumber);

    Location testGetVehicleLoc(String registrationNumber);

    // Return a list of all vehicles registration numbers located within a square of side 2*r centered at loc.
    CustomArrayList<String> testGetVehiclesInRange(Location loc, int r);



    double calculateFare(int x1, int y1, int x2, int y2, Location startingDriverLocation);
}
