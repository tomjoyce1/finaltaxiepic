import org.example.VehicleApp;
import org.example.Location;
import org.example.Vehicle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TaxiTest {

    private VehicleApp vehicleApp;

    @BeforeEach
    public void setUp() {
        vehicleApp = new VehicleApp();
    }

    @Test
    public void testAddToMap() {
        assertTrue(vehicleApp.testAddToMap("ABC123", new Location(1, 1)));
        assertFalse(vehicleApp.testAddToMap("ABC123", new Location(2, 2)));
        // Add more assertions if needed
    }

    @Test
    public void testMoveVehicle() {
        vehicleApp.testAddToMap("ABC123", new Location(1, 1));
        assertTrue(vehicleApp.testMoveVehicle("ABC123", new Location(2, 2)));
        // Add assertions to verify the movement
    }

    @Test
    public void testRemoveVehicle() {
        vehicleApp.testAddToMap("ABC123", new Location(1, 1));
        assertTrue(vehicleApp.testRemoveVehicle("ABC123"));
        assertFalse(vehicleApp.testRemoveVehicle("Nonexistent"));
        // Add assertions to verify removal
    }

    @Test
    public void testGetVehicleLoc() {
        vehicleApp.testAddToMap("ABC123", new Location(1, 1));
        assertEquals(new Location(1, 1), vehicleApp.testGetVehicleLoc("ABC123"));
        assertNull(vehicleApp.testGetVehicleLoc("Nonexistent"));
    }

    @Test
    public void testGetVehiclesInRange() {
        vehicleApp.testAddToMap("ABC123", new Location(1, 1));
        vehicleApp.testAddToMap("XYZ789", new Location(3, 3));

        Location testLocation = new Location(1, 2);
        int range = 1;

        assertEquals(1, vehicleApp.testGetVehiclesInRange(testLocation, range).size());
        assertEquals("ABC123", vehicleApp.testGetVehiclesInRange(testLocation, range).get(0));
    }
}
