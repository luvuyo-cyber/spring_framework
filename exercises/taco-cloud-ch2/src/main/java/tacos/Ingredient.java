package tacos;

import lombok.Data; // Import Lombok's @Data annotation for boilerplate reduction

/**
 * Represents a single ingredient that can be used in a taco.
 * Annotated with @Data from Lombok, which automatically generates:
 * - Getters for all fields
 * - Setters for all non-final fields
 * - toString() method
 * - equals() and hashCode() methods
 * - A constructor that initializes all final fields.
 */
@Data
public class Ingredient {

    // Unique identifier for the ingredient (e.g., "FLTO" for Flour Tortilla).
    private final String id;

    // Human-readable name of the ingredient (e.g., "Flour Tortilla").
    private final String name;

    // The type or category of the ingredient (e.g., WRAP, PROTEIN).
    private final Type type;

    /**
     * Enum (enumeration) to define the distinct types of ingredients.
     * This helps categorize ingredients and ensures type safety.
     */
    public enum Type {
        WRAP,    // For tortillas
        PROTEIN, // For meats or protein sources
        VEGGIES, // For vegetables
        CHEESE,  // For cheese types
        SAUCE    // For sauces and toppings
    }
}
