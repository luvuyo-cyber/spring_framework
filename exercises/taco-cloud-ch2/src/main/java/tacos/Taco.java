package tacos;

import java.util.List; // Import for List to hold multiple ingredients
import jakarta.validation.constraints.NotNull; // For validation: ensures a field is not null
import jakarta.validation.constraints.Size; // For validation: ensures a collection/string has a specific size
import lombok.Data; // Import Lombok's @Data annotation for boilerplate reduction

/**
 * Represents a single taco, composed of a name and a list of ingredients.
 * This class also includes validation constraints for its fields.
 * Annotated with @Data from Lombok, which automatically generates:
 * - Getters for all fields
 * - Setters for all non-final fields
 * - toString() method
 * - equals() and hashCode() methods
 * - A constructor that initializes all final fields.
 */
@Data
public class Taco {

    /**
     * The name of the taco.
     * Annotated with @NotNull to ensure the name is not null when submitted.
     * Annotated with @Size to ensure the name has a minimum length of 5 characters,
     * providing a custom error message if the constraint is violated.
     */
    @NotNull(message="Taco name cannot be null") // Added a more specific message for null constraint
    @Size(min=5, message="Name must be at least 5 characters long")
    private String name;

    /**
     * A list of ingredients that make up the taco.
     * Annotated with @NotNull to ensure the list of ingredients is not null.
     * Annotated with @Size to ensure at least one ingredient is chosen,
     * providing a custom error message if the constraint is violated.
     */
    @NotNull(message="Ingredients cannot be null") // Added a more specific message for null constraint
    @Size(min=1, message="You must choose at least 1 ingredient")
    private List<Ingredient> ingredients;

}
