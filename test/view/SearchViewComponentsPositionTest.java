package view;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SearchViewComponentsPositionTest {

    @Test
    public void testSearchViewComponentsPosition() {
        assertEquals(10, SearchViewComponentsPosition.TITLE_X);
        assertEquals(0, SearchViewComponentsPosition.TITLE_Y);
        assertEquals(200, SearchViewComponentsPosition.TITLE_WIDTH);
        assertEquals(20, SearchViewComponentsPosition.TITLE_HEIGHT);

        assertEquals(10, SearchViewComponentsPosition.DISTANCE_LABEL_X);
        assertEquals(250, SearchViewComponentsPosition.DISTANCE_LABEL_Y);
        assertEquals(100, SearchViewComponentsPosition.DISTANCE_LABEL_WIDTH);
        assertEquals(20, SearchViewComponentsPosition.DISTANCE_LABEL_HEIGHT);

        assertEquals(120, SearchViewComponentsPosition.DISTANCE_INPUT_X);
        assertEquals(250, SearchViewComponentsPosition.DISTANCE_INPUT_Y);
        assertEquals(150, SearchViewComponentsPosition.DISTANCE_INPUT_WIDTH);
        assertEquals(20, SearchViewComponentsPosition.DISTANCE_INPUT_HEIGHT);

        assertEquals(10, SearchViewComponentsPosition.DISH_TYPE_LABEL_X);
        assertEquals(280, SearchViewComponentsPosition.DISH_TYPE_LABEL_Y);
        assertEquals(200, SearchViewComponentsPosition.DISH_TYPE_LABEL_WIDTH);
        assertEquals(20, SearchViewComponentsPosition.DISH_TYPE_LABEL_HEIGHT);

        assertEquals(120, SearchViewComponentsPosition.DISH_TYPE_COMBO_BOX_X);
        assertEquals(280, SearchViewComponentsPosition.DISH_TYPE_COMBO_BOX_Y);
        assertEquals(150, SearchViewComponentsPosition.DISH_TYPE_COMBO_BOX_WIDTH);
        assertEquals(20, SearchViewComponentsPosition.DISH_TYPE_COMBO_BOX_HEIGHT);

        assertEquals(10, SearchViewComponentsPosition.SEARCH_BUTTON_X);
        assertEquals(310, SearchViewComponentsPosition.SEARCH_BUTTON_Y);
        assertEquals(100, SearchViewComponentsPosition.SEARCH_BUTTON_WIDTH);
        assertEquals(20, SearchViewComponentsPosition.SEARCH_BUTTON_HEIGHT);

        assertEquals(10, SearchViewComponentsPosition.MAP_AREA_X);
        assertEquals(30, SearchViewComponentsPosition.MAP_AREA_Y);
        assertEquals(200, SearchViewComponentsPosition.MAP_AREA_WIDTH);
        assertEquals(200, SearchViewComponentsPosition.MAP_AREA_HEIGHT);
    }
}
