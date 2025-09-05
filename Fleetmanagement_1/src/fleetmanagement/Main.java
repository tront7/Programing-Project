package fleetmanagement;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Create an instance of the fleet manager
        FleetManager manager = new FleetManager();
        // Create a Scanner object to read user input
        Scanner scanner = new Scanner(System.in);
        int choice = -1;

        // Loop to keep the menu running until the user chooses to exit
        while (choice != 0) {
            System.out.println("\n--- Fleet Management System ---");
            System.out.println("1. Add a Car");
            System.out.println("2. Add a Truck");
            System.out.println("3. Display Fleet Details");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            try {
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character

                switch (choice) {
                    case 1:
                        addCar(manager, scanner);
                        break;
                    case 2:
                        addTruck(manager, scanner);
                        break;
                    case 3:
                        manager.displayFleetDetails();
                        break;
                    case 0:
                        System.out.println("Exiting the program. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // Consume the invalid input
            }
        }
        scanner.close(); // Close the scanner when done
    }

    // Method to handle adding a new Car
    private static void addCar(FleetManager manager, Scanner scanner) {
        System.out.println("\n--- Add a New Car ---");
        System.out.print("Enter make: ");
        String make = scanner.nextLine();
        System.out.print("Enter model: ");
        String model = scanner.nextLine();
        System.out.print("Enter year: ");
        int year = scanner.nextInt();
        System.out.print("Enter number of doors: ");
        int numberOfDoors = scanner.nextInt();
        scanner.nextLine(); // Consume the newline

        Vehicle car = new Car(make, model, year, numberOfDoors);
        manager.addVehicle(car);
        System.out.println("Car added successfully!");
    }

    // Method to handle adding a new Truck
    private static void addTruck(FleetManager manager, Scanner scanner) {
        System.out.println("\n--- Add a New Truck ---");
        System.out.print("Enter make: ");
        String make = scanner.nextLine();
        System.out.print("Enter model: ");
        String model = scanner.nextLine();
        System.out.print("Enter year: ");
        int year = scanner.nextInt();
        System.out.print("Enter cargo capacity (in tons): ");
        double cargoCapacity = scanner.nextDouble();
        scanner.nextLine(); // Consume the newline

        Vehicle truck = new Truck(make, model, year, cargoCapacity);
        manager.addVehicle(truck);
        System.out.println("Truck added successfully!");
    }
}