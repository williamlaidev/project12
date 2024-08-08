package entity;

/**
 * Enum representing various dish types with associated API query types.
 */
public enum DishType {

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

    private final String[] apiTypes;

    /**
     * Constructs a DishType with associated API types.
     *
     * @param apiTypes API type strings for the dish type.
     */
    DishType(String... apiTypes) {
        this.apiTypes = apiTypes;
    }

    /**
     * Returns the API types for this DishType.
     *
     * @return Array of API type strings.
     */
    public String[] getApiTypes() {
        return apiTypes;
    }

    /**
     * Converts a string to its corresponding DishType enum constant.
     *
     * @param dishTypeString The dish type string.
     * @return The corresponding DishType, or null if not found.
     */
    public static DishType fromDishTypeString(String dishTypeString) {
        if (dishTypeString == null || dishTypeString.trim().isEmpty()) {
            return null;
        }

        String normalizedInput = dishTypeString.trim().toLowerCase();

        for (DishType dishType : DishType.values()) {
            for (String apiType : dishType.getApiTypes()) {
                if (apiType.equalsIgnoreCase(normalizedInput)) {
                    return dishType;
                }
            }
        }

        return null;
    }

    @Override
    public String toString() {
        return name();
    }
}
