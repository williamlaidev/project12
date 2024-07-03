package entity;
import java.util.Arrays;

public class Restaurant {
    // Attributes of the restaurant
    private String name;
    private String location;
    private int accumulatedReviewers;
    private float averageRating;
    private String[] dishTypes;
    private String menu;
    private String summarizedReview;

    // Constructor
    public Restaurant(String name, String location, int accumulatedReviewers, float averageRating, String[] dishTypes, String menu, String summarizedReview) {
        this.name = name;
        this.location = location;
        this.accumulatedReviewers = accumulatedReviewers;
        this.averageRating = averageRating;
        this.dishTypes = dishTypes;
        this.menu = menu;
        this.summarizedReview = summarizedReview;
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public int getAccumulatedReviewers() {
        return accumulatedReviewers;
    }

    public float getAverageRating() {
        return averageRating;
    }

    public String[] getDishTypes() {
        return dishTypes;
    }

    public String getMenu() {
        return menu;
    }

    public String getSummarizedReview() {
        return summarizedReview;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setAccumulatedReviewers(int accumulatedReviewers) {
        this.accumulatedReviewers = accumulatedReviewers;
    }

    public void setAverageRating(float averageRating) {
        this.averageRating = averageRating;
    }

    public void setDishTypes(String[] dishTypes) {
        this.dishTypes = dishTypes;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    public void setSummarizedReview(String summarizedReview) {
        this.summarizedReview = summarizedReview;
    }

    // toString method for easy printing
    @Override
    public String toString() {
        return "Restaurant{" +
                "name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", accumulatedReviewers=" + accumulatedReviewers +
                ", averageRating=" + averageRating +
                ", dishTypes=" + Arrays.toString(dishTypes) +
                ", menu='" + menu + '\'' +
                ", summarizedReview='" + summarizedReview + '\'' +
                '}';
    }
