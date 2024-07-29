package interface_adapter.view;

import java.beans.PropertyChangeListener;

// TODO: Add JavaDoc documentation to describe the purpose and usage.

public abstract class ViewModel {

    private String viewName;

    public ViewModel(String viewName) {
        this.viewName = viewName;
    }
    public String getViewName() {
        return this.viewName;
    }

    public abstract void firePropertyChanged();
    public abstract void addPropertyChangeListener(PropertyChangeListener listener);
}
