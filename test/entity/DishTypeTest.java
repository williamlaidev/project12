package entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DishTypeTest {

    @Test
    public void testGetApiTypes() {
        assertArrayEquals(new String[]{"american_restaurant"}, DishType.AMERICAN.getApiTypes());
        assertArrayEquals(new String[]{"breakfast_restaurant", "brunch_restaurant"}, DishType.BREAKFAST.getApiTypes());
        assertArrayEquals(new String[]{"fast_food_restaurant", "hamburger_restaurant", "pizza_restaurant", "sandwich_shop"}, DishType.FAST_FOOD.getApiTypes());
        assertArrayEquals(new String[]{"pizza_restaurant", "italian_restaurant", "fast_food_restaurant"}, DishType.PIZZA.getApiTypes());
    }

    @Test
    public void testFromDishTypeStringValid() {
        assertEquals(DishType.AMERICAN, DishType.fromDishTypeString("american_restaurant"));
        assertEquals(DishType.CHINESE, DishType.fromDishTypeString("chinese_restaurant"));
        assertEquals(DishType.ITALIAN, DishType.fromDishTypeString("Italian_restaurant"));
    }

    @Test
    public void testFromDishTypeStringInvalid() {
        assertNull(DishType.fromDishTypeString("unknown_restaurant"));
        assertNull(DishType.fromDishTypeString(""));
        assertNull(DishType.fromDishTypeString(null));
    }

    @Test
    public void testToString() {
        assertEquals("AMERICAN", DishType.AMERICAN.toString());
        assertEquals("CHINESE", DishType.CHINESE.toString());
        assertEquals("ITALIAN", DishType.ITALIAN.toString());
    }
}
