package fleetmanagement;

import java.util.Objects;

public class Truck extends Vehicle {
    private double cargoCapacityTons;

    public Truck(String make, String model, int year, double cargoCapacityTons) {
        super(make, model, year);
        this.cargoCapacityTons = cargoCapacityTons;
    }

    // ... getDetails() method ...

    @Override
    public boolean equals(Object o) {
        if (!super.equals(o)) return false;
        Truck truck = (Truck) o;
        return Double.compare(truck.cargoCapacityTons, cargoCapacityTons) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), cargoCapacityTons);
    }

    @Override
    public String getDetails() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}