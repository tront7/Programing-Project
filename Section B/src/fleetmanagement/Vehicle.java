package fleetmanagement;

import java.util.Objects;

public abstract class Vehicle {
    private String make;
    private String model;
    private int year;

    public Vehicle(String make, String model, int year) {
        this.make = make;
        this.model = model;
        this.year = year;
    }

    // Getters for make, model, and year...
    public String getMake() { return make; }
    public String getModel() { return model; }
    public int getYear() { return year; }
    
    public abstract String getDetails();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return year == vehicle.year &&
               Objects.equals(make, vehicle.make) &&
               Objects.equals(model, vehicle.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(make, model, year);
    }
}