
package fleetmanagement;

public abstract class Vehicle 
{
    // Information Hiding: fields are private
    private final String make;
    private final String model;
    private final int year;

    // Constructor
    public Vehicle(String make, String model, int year) {
        this.make = make;
        this.model = model;
        this.year = year;
    }

    // Public getters to access private fields
    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    // A method to be overridden by subclasses
    public abstract String getDetails(); 
}
