package org.example;

import java.util.*;
import java.util.Scanner;
import java.util.InputMismatchException;
public class Main {
    public static void main(String[] args) {

        VehicleApp vehicleApp = new VehicleApp();

        while(true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Input your x co-ordinates");


            while (true) {
                try {
                    int xWhereCustomerIs = scanner.nextInt();
                    System.out.println("Input your y co-ordinates");
                    int yWhereCustomerIs = scanner.nextInt();
                    testVehicleAppMethods(vehicleApp, xWhereCustomerIs, yWhereCustomerIs);


                    vehicleApp.visualizeGrid(5);


                    System.out.println("Which taxi ID to move?");
                    String taxiToBeMoved = scanner.next();

                    System.out.println("Which x co-ordinate to go to?");
                    int xToGo = scanner.nextInt();

                    System.out.println("Which y co-ordinate to go to?");
                    int yToGo = scanner.nextInt();


                    // This is to move the taxi about
                    System.out.println(" ");
                    vehicleApp.testMoveVehicle(taxiToBeMoved, new Location(xToGo, yToGo));

                    vehicleApp.visualizeGrid(5);

                    System.out.println("Rate the taxi driver out of 5");
                    int userRatingofDriver = scanner.nextInt();
                    System.out.println("You rated the driver " + userRatingofDriver + " out of 5");


                    break;
                } catch (InputMismatchException e) {
                    System.out.println("error - enter a valid input");
                    scanner.nextLine();
                }
            }
            System.out.println("Would you like another taxi? - Type Y for Yes, or N for No");
            String repeatLoop = scanner.next();
            if(repeatLoop.equals("Y")){
                System.out.println("repeetin");
            }else{
                break;
            }

        }

    }

    private static void testVehicleAppMethods(VehicleApp vehicleApp, int xWhereCustomerIs, int yWhereCustomerIs) {


        vehicleApp.testAddToMap("AB12", new Location(1, 1));
        vehicleApp.testAddToMap("Tony", new Location(3, 3));
        vehicleApp.testAddToMap("Mark", new Location(3, 1));


        //  vehicleApp.testMoveVehicle("ABC123", new Location(4, 4));


        //   vehicleApp.testRemoveVehicle("test");


        //  Location locationABC123 = vehicleApp.testGetVehicleLoc("AB12");
        //   System.out.println("Location of ABC123: " + locationAB12);


        Location testLocation = new Location(xWhereCustomerIs, yWhereCustomerIs);
        int range = 1;

        List<String> vehiclesInRange = vehicleApp.testGetVehiclesInRange(testLocation, range);
        System.out.println("Vehicles in range of " + testLocation + " with range " + range + ": " + vehiclesInRange);
    }
}
