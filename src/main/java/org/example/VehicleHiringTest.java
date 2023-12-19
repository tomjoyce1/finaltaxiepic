package org.example;

import java.util.*;

public interface VehicleHiringTest {


    boolean testAddToMap(String registrationNumber, Location loc);

    boolean testMoveVehicle(String reg, Location loc);


 boolean testRemoveVehicle(String registrationNumber);

    Location testGetVehicleLoc(String registrationNumber);

    // Return a list of all vehicles registration numbers located within a square of side 2*r centered at location loc (inclusive
//of the boundaries).
    CustomArrayList<String> testGetVehiclesInRange(Location loc, int r);
}
