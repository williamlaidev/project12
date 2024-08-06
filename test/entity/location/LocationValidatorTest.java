package entity.location;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LocationValidatorTest {

    @Test
    public void testValidatorChain() {
        LocationValidator validatorChain = new LocationLatitudeValidator();
        validatorChain.linkWith(new LocationLongitudeValidator());

        Location validLocation = new Location(37.7749, -122.4194);
        assertTrue(validatorChain.check(validLocation));

        assertThrows(IllegalArgumentException.class, () -> new Location(91.0, -122.4194));
        assertThrows(IllegalArgumentException.class, () -> new Location(37.7749, 181.0));
    }
}
