package entity;

/**
 * Enum representing different types of dishes that a restaurant can offer.
 * Each type is associated with one or more API types used to query restaurant information.
 */
public enum DishType {

    // Enum constants, each associated with one or more API type strings.
    AMERICAN("american_restaurant"),
    BAKERY("bakery"),
    BAR("bar"),
    BARBECUE("barbecue_restaurant"),
    BRAZILIAN("brazilian_restaurant"),
    BREAKFAST("breakfast_restaurant", "brunch_restaurant"),
    CAFE("cafe", "coffee_shop"),
    CHINESE("chinese_restaurant"),
    FAST_FOOD("fast_food_restaurant", "hamburger_restaurant", "pizza_restaurant", "sandwich_shop"),
    FRENCH("french_restaurant"),
    GREEK("greek_restaurant"),
    ICE_CREAM("ice_cream_shop"),
    INDIAN("indian_restaurant"),
    INDONESIAN("indonesian_restaurant"),
    ITALIAN("italian_restaurant", "pizza_restaurant"),
    JAPANESE("japanese_restaurant", "ramen_restaurant", "sushi_restaurant"),
    KOREAN("korean_restaurant"),
    LEBANESE("lebanese_restaurant", "middle_eastern_restaurant"),
    MEDITERRANEAN("mediterranean_restaurant", "middle_eastern_restaurant"),
    MEXICAN("mexican_restaurant"),
    PIZZA("pizza_restaurant", "italian_restaurant", "fast_food_restaurant"),
    RAMEN("ramen_restaurant", "japanese_restaurant"),
    SANDWICH("sandwich_shop", "fast_food_restaurant"),
    SEAFOOD("seafood_restaurant"),
    SPANISH("spanish_restaurant"),
    STEAK("steak_house"),
    THAI("thai_restaurant"),
    TURKISH("turkish_restaurant", "middle_eastern_restaurant"),
    VEGETARIAN("vegetarian_restaurant", "vegan_restaurant"),
    VIETNAMESE("vietnamese_restaurant");

    // Array of API types associated with each DishType enum constant.
    private final String[] apiTypes;

    /**
     * Constructor for the enum. Associates one or more API type strings with the DishType.
     *
     * @param apiTypes Array of API type strings.
     */
    DishType(String... apiTypes) {
        this.apiTypes = apiTypes;
    }

    /**
     * Gets the array of API types associated with this DishType.
     *
     * @return Array of API type strings.
     */
    public String[] getApiTypes() {
        return apiTypes;
    }

    /**
     * Converts a string representing the dish type into the corresponding DishType enum constant.
     *
     * @param dishTypeString The string representation of the dish type.
     * @return The corresponding DishType enum constant, or null if no match is found.
     */
    public static DishType fromDishTypeString(String dishTypeString) {
        if (dishTypeString == null || dishTypeString.trim().isEmpty()) {
            return null; // Return null if the input is null or empty
        }

        // Normalize the input string
        String normalizedInput = dishTypeString.trim().toLowerCase();

        // Check each DishType for a matching API type
        for (DishType dishType : DishType.values()) {
            for (String apiType : dishType.getApiTypes()) {
                if (apiType.equalsIgnoreCase(normalizedInput)) {
                    return dishType;
                }
            }
        }

        return null; // Return null if no match is found
    }

    @Override
    public String toString() {
        return name();
    }
}
