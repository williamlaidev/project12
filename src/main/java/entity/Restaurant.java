package main.java.entity;

/**
 * Represents a dining establishment.
 */
public class Restaurant {
    private int restaurantId;
    private String name;
    private String cuisineType;
    private double averageRating;
    private double latitude;
    private double longitude;
    private String address;
    private String photoUrl;
    private String summarizedReview;

    /**
     * @param restaurantId    the unique restaurant identifier
     * @param name            the official name of the restaurant
     * @param cuisineType     the cuisine offered by the restaurant
     * @param averageRating   the average user rating of the restaurant
     * @param latitude        the geographical latitude of the restaurant
     * @param longitude       the geographical longitude of the restaurant
     * @param address         the physical address of the restaurant
     * @param photoUrl        the URL link to a photo of the restaurant
     * @param summarizedReview the AI-generated review summary of the restaurant
     */
    public Restaurant(int restaurantId, String name, String cuisineType, double averageRating,
                      double latitude, double longitude, String address,
                      String photoUrl, String summarizedReview) {
        this.restaurantId = restaurantId;
        this.name = name;
        this.cuisineType = cuisineType;
        this.averageRating = averageRating;
        this.latitude = latitude;
        this.longitude = longitude;
        this.address = address;
        this.photoUrl = photoUrl;
        this.summarizedReview = summarizedReview;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public String getName() {
        return name;
    }

    public String getCuisineType() {
        return cuisineType;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getAddress() {
        return address;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public String getSummarizedReview() {
        return summarizedReview;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCuisineType(String cuisineType) {
        this.cuisineType = cuisineType;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public void setSummarizedReview(String summarizedReview) {
        this.summarizedReview = summarizedReview;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "restaurantId=" + restaurantId +
                ", name='" + name + '\'' +
                ", cuisineType='" + cuisineType + '\'' +
                ", averageRating=" + averageRating +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", address='" + address + '\'' +
                ", photoUrl='" + photoUrl + '\'' +
                ", summarizedReview='" + summarizedReview + '\'' +
                '}';
    }
}
