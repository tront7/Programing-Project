package fleetmanagement;

public class Truck extends Vehicle {
    private final double cargoCapacityTons;

    public Truck(String make, String model, int year, double cargoCapacityTons) {
        super(make, model, year);
        this.cargoCapacityTons = cargoCapacityTons;
    }

    @Override
    public String getDetails() {
        return String.format("Truck: %d %s %s with %.1f ton capacity", getYear(), getMake(), getModel(), this.cargoCapacityTons);
    }
}