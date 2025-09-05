
package fleetmanagement;


public class Car extends Vehicle 
{
    private final int numberOfDoors;

    // Constructor using super() to call the base class constructor
    public Car(String make, String model, int year, int numberOfDoors) {
        super(make, model, year); // Call to parent constructor
        this.numberOfDoors = numberOfDoors;
    }

    @Override
    public String getDetails() {
        return String.format("Car: %d %s %s with %d doors", getYear(), getMake(), getModel(), numberOfDoors);
    }
}
