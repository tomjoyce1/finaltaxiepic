package org.example;

import java.util.*;
import java.util.Scanner;
import java.util.InputMismatchException;


public class Main {
    private static Location startingDriverLocation = null;
    public static void main(String[] args) {

        VehicleApp vehicleApp = new VehicleApp();
        printTaxi();
        newMethod(vehicleApp);

    }

    private static void testVehicleAppMethods(VehicleApp vehicleApp, int xWhereCustomerIs, int yWhereCustomerIs) {


        vehicleApp.testAddToMap("AB12", new Location(1, 1));
        vehicleApp.testAddToMap("Tony", new Location(3, 3));
        vehicleApp.testAddToMap("Mark", new Location(3, 1));

        Location testLocation = new Location(xWhereCustomerIs, yWhereCustomerIs);
        int range = 1;

        CustomArrayList<String> vehiclesInRange = vehicleApp.testGetVehiclesInRange(testLocation, range);
        System.out.println("------------------------------------------------------------------------");
        System.out.println("------------------------------------------------------------------------");
        System.out.println("VEHICLES IN RANGE OF " + testLocation + " WITH RANGE " + range + ": " + vehiclesInRange);

        System.out.println("\n" + vehiclesInRange + " WILL HAVE A CHEAPER FARE");
        System.out.println("------------------------------------------------------------------------");
        System.out.println("------------------------------------------------------------------------");
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
    public static void printTaxi(){
        System.out.println("                                     88  \n" +
                "  ,d                                 \"\"  \n" +
                "  88                               \n" +
                "MM88MMM   ,adPPYYba,   8b,     ,d8   88  \n" +
                "  88      \"\"     `Y8    `Y8, ,8P'    88  \n" +
                "  88      ,adPPPPP88      )888(      88  \n" +
                "  88,     88,    ,88    ,d8\" \"8b,    88  \n" +
                "  \"Y888   `\"8bbdP\"Y8   8P'     `Y8   88  ");
    }


    public static void registerNewTaxiAndAddToMap(VehicleApp vehicleApp) {
        Scanner newSc = new Scanner(System.in);

        try {
            System.out.println("What name for your taxi (preferably under 4 characters)");
            String newTaxiID = newSc.next();

            System.out.println("What starting x coordinate");
            int newXTaxi = newSc.nextInt();

            System.out.println("What starting y coordinate");
            int newYTaxi = newSc.nextInt();


            vehicleApp.testAddToMap(newTaxiID, new Location(newXTaxi, newYTaxi));
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter valid data.");

        }
    }

    public static void newMethod(VehicleApp vehicleApp){
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

//this is for moving taxis
                    String taxiToBeMoved;
                    while (true) {
                        System.out.println("Which taxi ID to move? e.g - (AB12, Tony, Mark)");
                        taxiToBeMoved = scanner.next();


                        if (vehicleApp.vehicleExists(taxiToBeMoved)) {
                            startingDriverLocation = vehicleApp.testGetVehicleLoc(taxiToBeMoved);


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

//this is to print out grid.

                    vehicleApp.visualizeGrid(5, xToGo,yToGo);
                    double fare = vehicleApp.calculateFare(xWhereCustomerIs, yWhereCustomerIs, xToGo, yToGo, startingDriverLocation);

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

            System.out.println("Would you like to add a driver - Type Y for Yes, or N for No");
            String repeatLoop = scanner.next();
            if(repeatLoop.equals("Y")){
                registerNewTaxiAndAddToMap(vehicleApp);
                System.out.println("finished this process");
            }else{

                break;
            }



            System.out.println("Would you like another taxi? - Type Y for Yes, or N for No");
            String newTaxirepeatLoop = scanner.next();
            if(newTaxirepeatLoop.equals("Y")){
                System.out.println("Repeating now");
            }else{
                System.out.println("Thank you for using Fabcabs");
                scanner.close();
                break;
            }

        }
    }


}
