package interface_adapter;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class SearchViewModel extends ViewModel {

    public final String TITLE_LABEL = "Search View";
    public final String DISTANCE_LABEL = "Choose distance";
    public final String DISH_TYPE_LABEL = "Choose dish type";

    public final String SEARCH_BUTTON_LABEL = "Search";

    private SearchViewState state = new SearchViewState();

    public SearchViewModel() {
        super("search");
    }

    public void setState(SearchViewState state) {
        this.state = state;
    }

    private final PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    // This is what the Search Presenter will call to let the ViewModel know to alert the View
    public void firePropertyChanged() {
        changeSupport.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public SearchViewState getState() {
        return state;
    }
}
