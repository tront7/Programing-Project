package fleetmanagement;

import java.util.ArrayList;
import java.util.List;

public class FleetManager {
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

    public void displayFleetDetails() {
        System.out.println("--- Fleet Report ---");
        if (fleet.isEmpty()) {
            System.out.println("The fleet is empty.");
        } else {
            for (Vehicle vehicle : fleet) {
                System.out.println(vehicle.getDetails());
            }
        }
        System.out.println("--------------------");
    }
}