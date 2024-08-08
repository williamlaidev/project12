package entity;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class DishTypeTest {

    @Test
    void testGetApiTypes() {
        assertArrayEquals(new String[]{"american_restaurant"}, DishType.AMERICAN.getApiTypes());
        assertArrayEquals(new String[]{"breakfast_restaurant", "brunch_restaurant"}, DishType.BREAKFAST.getApiTypes());
        assertArrayEquals(new String[]{"fast_food_restaurant", "hamburger_restaurant", "pizza_restaurant", "sandwich_shop"}, DishType.FAST_FOOD.getApiTypes());
    }

    @Test
    void testFromDishTypeString() {
        assertEquals(DishType.AMERICAN, DishType.fromDishTypeString("american_restaurant"));
        assertEquals(DishType.BREAKFAST, DishType.fromDishTypeString("brunch_restaurant"));
        assertEquals(DishType.FAST_FOOD, DishType.fromDishTypeString("pizza_restaurant"));
        assertNull(DishType.fromDishTypeString("unknown_restaurant"));
        assertNull(DishType.fromDishTypeString(null));
        assertNull(DishType.fromDishTypeString(""));
    }

    @Test
    void testToString() {
        assertEquals("AMERICAN", DishType.AMERICAN.toString());
        assertEquals("BREAKFAST", DishType.BREAKFAST.toString());
        assertEquals("FAST_FOOD", DishType.FAST_FOOD.toString());
    }
}
