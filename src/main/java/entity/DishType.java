package entity;

/**
 * Enum representing different types of dishes that a restaurant can offer.
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

    DishType(String... apiTypes) {
        this.apiTypes = apiTypes;
    }

    public String[] getApiTypes() {
        return apiTypes;
    }

    public static DishType fromApiType(String apiType) {
        for (DishType type : values()) {
            for (String typeString : type.apiTypes) {
                if (typeString.equals(apiType)) {
                    return type;
                }
            }
        }
        return null;
    }
}