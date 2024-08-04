package entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DishTypeTest {

    @Test
    public void testGetApiTypes() {
        String[] expectedApiTypes = {"american_restaurant"};
        assertArrayEquals(expectedApiTypes, DishType.AMERICAN.getApiTypes());

        expectedApiTypes = new String[]{"breakfast_restaurant", "brunch_restaurant"};
        assertArrayEquals(expectedApiTypes, DishType.BREAKFAST.getApiTypes());
    }

    @Test
    public void testFromDishTypeStringValid() {
        assertEquals(DishType.AMERICAN, DishType.fromDishTypeString("AMERICAN"));
        assertEquals(DishType.PIZZA, DishType.fromDishTypeString("pizza"));
        assertEquals(DishType.CAFE, DishType.fromDishTypeString("cafe"));
        assertEquals(DishType.JAPANESE, DishType.fromDishTypeString("JAPANESE"));
    }

    @Test
    public void testFromDishTypeStringInvalid() {
        assertNull(DishType.fromDishTypeString("invalid"));
        assertNull(DishType.fromDishTypeString(null));
        assertNull(DishType.fromDishTypeString(""));
    }

    @Test
    public void testToString() {
        assertEquals("AMERICAN", DishType.AMERICAN.toString());
        assertEquals("BAKERY", DishType.BAKERY.toString());
    }
}
