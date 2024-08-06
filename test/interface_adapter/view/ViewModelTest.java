package interface_adapter.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ViewModelTest {

    private ViewModel viewModel;
    private PropertyChangeListener listener;

    private static class TestViewModel extends ViewModel {
        private PropertyChangeListener listener;

        public TestViewModel(String viewName) {
            super(viewName);
        }

        @Override
        public void firePropertyChanged() {
            if (listener != null) {
                listener.propertyChange(new PropertyChangeEvent(this, "testProperty", null, "newValue"));
            }
        }

        @Override
        public void addPropertyChangeListener(PropertyChangeListener listener) {
            this.listener = listener;
        }
    }

    @BeforeEach
    public void setUp() {
        viewModel = new TestViewModel("Test View");
        listener = mock(PropertyChangeListener.class);
        viewModel.addPropertyChangeListener(listener);
    }

    @Test
    public void testGetViewName() {
        assertEquals("Test View", viewModel.getViewName());
    }

    @Test
    public void testAddPropertyChangeListener() {
        viewModel.firePropertyChanged();
        verify(listener, times(1)).propertyChange(any(PropertyChangeEvent.class));
    }
}
