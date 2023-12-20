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
                    int[] coordinates = outOfBounds(xWhereCustomerIs,yWhereCustomerIs);

                    testVehicleAppMethods(vehicleApp, coordinates[0], coordinates[1]);


                    vehicleApp.visualizeGrid(5, xWhereCustomerIs, yWhereCustomerIs);


                    String taxiToBeMoved;
                    while (true) {
                        System.out.println("Which taxi ID to move? (AB12, Tony, Mark)");
                        taxiToBeMoved = scanner.next();

                        if (vehicleApp.vehicleExists(taxiToBeMoved)) {
                            break;
                        } else {
                            System.out.println("Invalid taxi ID. Please enter a valid taxi ID.");
                        }
                    }



                    System.out.println("Which x co-ordinate to go to?");
                    int xToGo = scanner.nextInt();

                    System.out.println("Which y co-ordinate to go to?");
                    int yToGo = scanner.nextInt();



                    // This is to move the taxi about
                    System.out.println(" ");
                    vehicleApp.testMoveVehicle(taxiToBeMoved, new Location(xToGo, yToGo));



                    vehicleApp.visualizeGrid(5, xToGo,yToGo);
                    String fare = vehicleApp.calculateFare(xWhereCustomerIs, yWhereCustomerIs, xToGo, yToGo);




                    System.out.println("Your fare is €" + fare);


                    System.out.println("Rate the taxi driver out of 5");
                    int userRatingofDriver = scanner.nextInt();

                    if(userRatingofDriver > 5){
                        System.out.println("Your rating has been discarded");
                    } else {
                        System.out.println("You rated the driver " + userRatingofDriver + " out of 5");
                    }


                    break;
                } catch (InputMismatchException e) {
                    System.out.println("error - enter a valid input");
                    scanner.nextLine();
                }
            }
            System.out.println("Would you like another taxi? - Type Y for Yes, or N for No");
            String repeatLoop = scanner.next();
            if(repeatLoop.equals("Y")){
                System.out.println("Repeating now");
            }else{
                System.out.println("Thank you for using Fabcabs");
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

//if they are out of bounds



        Location testLocation = new Location(xWhereCustomerIs, yWhereCustomerIs);
        int range = 1;

        CustomArrayList<String> vehiclesInRange = vehicleApp.testGetVehiclesInRange(testLocation, range);
        System.out.println("Vehicles in range of " + testLocation + " with range " + range + ": " + vehiclesInRange);

    }

    private static int[] outOfBounds(int x, int y) {

        while(x > 5 || y > 5 || x < 0 || y < 0){
            Scanner sc = new Scanner(System.in);
            System.out.println("Out of bounds, input coordinates within (4,4)");
            System.out.println("Input your x co-ordinates (must be less than 5)");
            x = sc.nextInt();
            System.out.println("Input your y co-ordinates (must be less than 5)");
            y = sc.nextInt();




        }
        return new int[]{x, y};

    }
}
