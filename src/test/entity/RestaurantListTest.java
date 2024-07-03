package test.entity;
import main.entity.RestaurantList;
import main.entity.RestaurantListItem;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RestaurantListTest {

    @Test
    public void testRestaurantList() {
        // Create some sample restaurant list items
        RestaurantListItem item1 = new RestaurantListItem(1, 1, 101);
        RestaurantListItem item2 = new RestaurantListItem(2, 1, 102);
        List<RestaurantListItem> items = new ArrayList<>();
        items.add(item1);
        items.add(item2);

        // Create a restaurant list instance
        RestaurantList list = new RestaurantList(1, 1, "My List", "A list of favorite restaurants", items);

        // Test getters
        assertEquals(1, list.getRestaurantListId());
        assertEquals(1, list.getUserId());
        assertEquals("My List", list.getName());
        assertEquals("A list of favorite restaurants", list.getDescription());
        assertEquals(items, list.getRestaurantItems());

        // Test setters
        list.setRestaurantListId(2);
        list.setUserId(2);
        list.setName("Updated List");
        list.setDescription("An updated list of restaurants");
        RestaurantListItem newItem = new RestaurantListItem(3, 2, 103);
        List<RestaurantListItem> updatedItems = new ArrayList<>();
        updatedItems.add(newItem);
        list.setRestaurantItems(updatedItems);

        assertEquals(2, list.getRestaurantListId());
        assertEquals(2, list.getUserId());
        assertEquals("Updated List", list.getName());
        assertEquals("An updated list of restaurants", list.getDescription());
        assertEquals(updatedItems, list.getRestaurantItems());
    }

    @Test
    public void testRestaurantListItem() {
        // Create a restaurant list item instance
        RestaurantListItem item = new RestaurantListItem(1, 1, 101);

        // Test getters
        assertEquals(1, item.getRestaurantListItemId());
        assertEquals(1, item.getRestaurantListId());
        assertEquals(101, item.getRestaurantId());

        // Test setters
        item.setRestaurantListItemId(2);
        item.setRestaurantListId(2);
        item.setRestaurantId(102);

        assertEquals(2, item.getRestaurantListItemId());
        assertEquals(2, item.getRestaurantListId());
        assertEquals(102, item.getRestaurantId());
    }
}