package interface_adapter.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ViewManagerModelTest {

    private ViewManagerModel viewManagerModel;
    private TestPropertyChangeListener listener;

    @BeforeEach
    public void setUp() {
        viewManagerModel = new ViewManagerModel();
        listener = new TestPropertyChangeListener();
        viewManagerModel.addPropertyChangeListener(listener);
    }

    @Test
    public void testSetActiveView() {
        String activeView = "newView";
        viewManagerModel.setActiveView(activeView);

        assertEquals(activeView, viewManagerModel.getActiveView());
    }

    @Test
    public void testFirePropertyChanged() {
        String activeView = "newView";
        viewManagerModel.setActiveView(activeView);
        viewManagerModel.firePropertyChanged();

        assertEquals(1, listener.getEventCount());
        PropertyChangeEvent event = listener.getLastEvent();
        assertEquals("view", event.getPropertyName());
        assertEquals(null, event.getOldValue());
        assertEquals(activeView, event.getNewValue());
    }

    // Inner class to test PropertyChangeListener
    private static class TestPropertyChangeListener implements PropertyChangeListener {
        private int eventCount = 0;
        private PropertyChangeEvent lastEvent;

        @Override
        public void propertyChange(PropertyChangeEvent evt) {
            eventCount++;
            lastEvent = evt;
        }

        public int getEventCount() {
            return eventCount;
        }

        public PropertyChangeEvent getLastEvent() {
            return lastEvent;
        }
    }
}
