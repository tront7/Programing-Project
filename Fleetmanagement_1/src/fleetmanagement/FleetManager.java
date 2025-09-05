package fleetmanagement;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages the collection of vehicles.
 * Demonstrates use of ArrayList and loops.
 */
public class FleetManager {
    // Using List<Vehicle> to store any type of Vehicle (Car, Truck, etc.)
    private final List<Vehicle> fleet = new ArrayList<>();

    public void addVehicle(Vehicle vehicle) {
        fleet.add(vehicle);
    }

    public boolean removeVehicle(Vehicle vehicle) {
        return fleet.remove(vehicle);
    }

    public int getFleetSize() {
        return fleet.size();
    }
    
    // This is the "report"
    public void displayFleetDetails() {
        System.out.println("--- Fleet Report ---");
        if (fleet.isEmpty()) {
            System.out.println("The fleet is empty.");
        } else {
            // Loop through the array/list
            for (Vehicle vehicle : fleet) {
                System.out.println(vehicle.getDetails());
            }
        }
        System.out.println("--------------------");
    }
}