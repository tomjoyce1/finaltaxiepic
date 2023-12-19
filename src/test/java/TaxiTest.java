import org.example.CustomArrayList;
import org.example.Location;
import org.example.Vehicle;
import org.example.VehicleApp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.example.CustomArrayList;

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

        assertNotEquals(new Location(1, 2), vehicleApp.testGetVehicleLoc("ABC123"));
        assertEquals(new Location(2, 2), vehicleApp.testGetVehicleLoc("ABC123"));
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

        CustomArrayList<String> vehiclesInRange = vehicleApp.testGetVehiclesInRange(testLocation, range);
        assertEquals(1, vehiclesInRange.size());
        assertEquals("ABC123", vehiclesInRange.get(0));
    }
}
