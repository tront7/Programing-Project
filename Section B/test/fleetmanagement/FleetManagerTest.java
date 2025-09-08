package fleetmanagement;

// JUNIT 4 IMPORTS
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Unit tests for the FleetManager class.
 * This class verifies the core functionality of adding, removing, and managing vehicles in the fleet.
 */
public class FleetManagerTest {

    private FleetManager manager;
    private Vehicle car;
    private Vehicle truck;

    @Before // JUNIT 4 Annotation
    public void setUp() {
        manager = new FleetManager();
        // A standard car for testing
        car = new Car("Toyota", "Corolla", 2023, 4);
        // A standard truck for testing
        truck = new Truck("Ford", "F-150", 2024, 1.5);
    }

    /**
     * Tests adding multiple vehicles and confirms the fleet size increases accordingly.
     */
    @Test
    public void testAddVehicle() {
        // JUNIT 4 Assertions (message is the first parameter)
        assertEquals("Initial fleet should be empty.", 0, manager.getFleetSize());
        manager.addVehicle(car);
        assertEquals("Fleet size should be 1 after adding a car.", 1, manager.getFleetSize());
        manager.addVehicle(truck);
        assertEquals("Fleet size should be 2 after adding a truck.", 2, manager.getFleetSize());
    }

    /**
     * Tests removing an existing vehicle by its object reference.
     */
    @Test
    public void testRemoveExistingVehicle() {
        manager.addVehicle(car);
        manager.addVehicle(truck);
        assertEquals("Pre-condition: Fleet should have 2 vehicles.", 2, manager.getFleetSize());

        boolean result = manager.removeVehicle(car);
        assertTrue("Removing an existing vehicle should return true.", result);
        assertEquals("Fleet size should be 1 after removing the car.", 1, manager.getFleetSize());
    }

    /**
     * Tests that attempting to remove an already removed vehicle fails.
     */
    @Test
    public void testRemoveAlreadyRemovedVehicle() {
        manager.addVehicle(car);
        manager.removeVehicle(car); // First removal

        assertEquals("Pre-condition: Fleet should be empty.", 0, manager.getFleetSize());
        boolean result = manager.removeVehicle(car); // Second removal attempt
        assertFalse("Removing an already-removed vehicle should return false.", result);
    }

    /**
     * Ensures that attempting to remove a vehicle that is not in the fleet
     * does not change the fleet size and returns false.
     */
    @Test
    public void testRemoveNonExistentVehicle() {
        manager.addVehicle(truck);
        assertEquals("Pre-condition: Fleet should have 1 vehicle.", 1, manager.getFleetSize());

        // Attempt to remove a car that was never added
        boolean result = manager.removeVehicle(car);
        assertFalse("Removing a non-existent vehicle should return false.", result);
        assertEquals("Fleet size should not change when removal fails.", 1, manager.getFleetSize());
    }

    /**
     * Verifies that removing the only vehicle in the fleet results in an empty fleet.
     */
    @Test
    public void testRemoveLastVehicle() {
        manager.addVehicle(car);
        assertEquals("Pre-condition: Fleet should have 1 vehicle.", 1, manager.getFleetSize());

        boolean result = manager.removeVehicle(car);
        assertTrue("Removing the last vehicle should return true.", result);
        assertEquals("Fleet should be empty after removing the last vehicle.", 0, manager.getFleetSize());
    }

    /**
     * This test highlights the need for an equals() method in the Vehicle classes.
     * It should now PASS because you implemented the equals() and hashCode() methods.
     */
    @Test
    public void testRemoveVehicleByValue() {
        // Add a car to the fleet
        manager.addVehicle(new Car("Toyota", "Corolla", 2023, 4));
        assertEquals("Pre-condition: Fleet should contain one car.", 1, manager.getFleetSize());

        // Create a new, separate car object with the exact same details
        Vehicle carToRemove = new Car("Toyota", "Corolla", 2023, 4);

        // This remove operation will now SUCCEED because of your equals() method
        boolean result = manager.removeVehicle(carToRemove);

        assertTrue("Should be able to remove vehicle by value with a proper equals() method.", result);
        assertEquals("Fleet should be empty if removal by value was successful.", 0, manager.getFleetSize());
    }
}