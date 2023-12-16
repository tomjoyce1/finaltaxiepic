import org.example.Taxi;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaxiTest {

    @Test
    public void testTaxiInitialization() {
        Taxi taxi = new Taxi("ABC123", "Toyota");
        assertEquals("ABC123", taxi.getLicensePlate());
        assertEquals("Toyota", taxi.getModel());
    }
}