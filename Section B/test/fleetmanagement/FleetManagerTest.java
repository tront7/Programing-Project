package fleetmanagement;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FleetManagerTest {
    
    private FleetManager manager;
    private Vehicle car;
    private Vehicle truck;
    
    @BeforeEach
    public void setUp() {
        manager = new FleetManager();
        car = new Car("TestMake", "TestModel", 2025, 4);
        truck = new Truck("TestTruckMake", "TestTruckModel", 2024, 5.0);
    }

    @Test
    public void testAddVehicle() {
        assertEquals(0, manager.getFleetSize());
        manager.addVehicle(car);
        assertEquals(1, manager.getFleetSize());
        manager.addVehicle(truck);
        assertEquals(2, manager.getFleetSize());
    }

    @Test
    public void testRemoveVehicle() {
        manager.addVehicle(car);
        manager.addVehicle(truck);
        assertEquals(2, manager.getFleetSize());
        
        boolean result = manager.removeVehicle(car);
        assertTrue(result);
        assertEquals(1, manager.getFleetSize());
    }
    
    @Test
    public void testRemoveNonExistentVehicle() {
        manager.addVehicle(truck);
        assertEquals(1, manager.getFleetSize());
        
        boolean result = manager.removeVehicle(car); // car was never added
        assertFalse(result);
        assertEquals(1, manager.getFleetSize());
    }
}