//package interface_adapter.view;
//
//import org.junit.jupiter.api.Test;
//
//import java.awt.Point;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//public class SearchViewStateTest {
//
//    @Test
//    public void testDefaultConstructor() {
//        SearchViewState state = new SearchViewState();
//
//        assertNull(state.getMousePosition());
//        assertNull(state.getDistance());
//        assertNull(state.getSelectedDishType());
//        assertNull(state.getDistanceError());
//    }
//
//    @Test
//    public void testCopyConstructor() {
//        SearchViewState original = new SearchViewState();
//        original.setMousePosition(new Point(10, 20));
//        original.setDistance("500");
//        original.setSelectedDishType("Chinese");
//        original.setDistanceError("Error");
//
//        SearchViewState copy = new SearchViewState(original);
//
//        assertEquals(original.getMousePosition(), copy.getMousePosition());
//        assertEquals(original.getDistance(), copy.getDistance());
//        assertEquals(original.getSelectedDishType(), copy.getSelectedDishType());
//        assertEquals(original.getDistanceError(), copy.getDistanceError());
//    }
//
//    @Test
//    public void testSetAndGetMousePosition() {
//        SearchViewState state = new SearchViewState();
//        Point mousePosition = new Point(10, 20);
//        state.setMousePosition(mousePosition);
//
//        assertEquals(mousePosition, state.getMousePosition());
//    }
//
//    @Test
//    public void testSetAndGetDistance() {
//        SearchViewState state = new SearchViewState();
//        String distance = "500";
//        state.setDistance(distance);
//
//        assertEquals(distance, state.getDistance());
//    }
//
//    @Test
//    public void testSetAndGetSelectedDishType() {
//        SearchViewState state = new SearchViewState();
//        String dishType = "Chinese";
//        state.setSelectedDishType(dishType);
//
//        assertEquals(dishType, state.getSelectedDishType());
//    }
//
//    @Test
//    public void testSetAndGetDistanceError() {
//        SearchViewState state = new SearchViewState();
//        String distanceError = "Error";
//        state.setDistanceError(distanceError);
//
//        assertEquals(distanceError, state.getDistanceError());
//    }
//
//    @Test
//    public void testToString() {
//        SearchViewState state = new SearchViewState();
//        state.setMousePosition(new Point(10, 20));
//        state.setDistance("500");
//        state.setSelectedDishType("Chinese");
//        state.setDistanceError("Error");
//
//        String expected = "SearchViewState{mousePosition=java.awt.Point[x=10,y=20], distance='500', selectedDishType='Chinese', distanceError='Error'}";
//        assertEquals(expected, state.toString());
//    }
//}
