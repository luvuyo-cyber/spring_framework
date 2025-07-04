package tacos;

import lombok.Data; // Lombok annotation to auto-generate boilerplate code

/**
 * Represents a single ingredient that can be used to build a taco.
 *
 * The @Data annotation generates:
 * - getters for all fields
 * - setters for non-final fields (none here since all fields are final)
 * - toString(), equals(), and hashCode() methods
 * - a constructor for all final fields (id, name, type)
 */
@Data
public class Ingredient {

    // Unique ID for the ingredient (e.g., "FLTO")
    private final String id;

    // Descriptive name (e.g., "Flour Tortilla")
    private final String name;

    // The type/category of this ingredient (see enum below)
    private final Type type;

    /**
     * Enum to define the possible ingredient categories.
     * This enforces type safety and helps organize ingredients.
     */
    public enum Type {
        WRAP,    // Tortilla or wrap types
        PROTEIN, // Meat or other protein options
        VEGGIES, // Vegetables
        CHEESE,  // Cheese varieties
        SAUCE    // Sauces and toppings
    }
}
