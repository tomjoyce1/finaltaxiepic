package org.example;

import java.util.*;

public interface VehicleHiringTest {

    public static boolean testAddToMap(String reg, Location loc){return false;}


    public static boolean testMoveVehicle(String reg, Location loc) {
        return false;
    }



    public static boolean testRemoveVehicle(String reg) {
        return false;
    }



    public static Location testGetVehicleLoc(String reg) {
        return null;
    }

    // Return a list of all vehicles registration numbers located within a square of side 2*r centered at location loc (inclusive
//of the boundaries).
    public static List<String> testGetVehiclesInRange(Location loc, int r) {
        return null;
    }


}
