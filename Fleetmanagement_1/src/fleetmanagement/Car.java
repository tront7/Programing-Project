
package fleetmanagement;

public class Car extends Vehicle {
    private final int numberOfDoors;

    public Car(String make, String model, int year, int numberOfDoors) {
        super(make, model, year);
        this.numberOfDoors = numberOfDoors;
    }

    @Override
    public String getDetails() {
        return String.format("Car: %d %s %s with %d doors", getYear(), getMake(), getModel(), this.numberOfDoors);
    }
}