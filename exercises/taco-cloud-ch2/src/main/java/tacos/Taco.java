package tacos;

import java.util.List; // To hold multiple ingredients
import jakarta.validation.constraints.NotNull; // Bean validation to ensure fields are not null
import jakarta.validation.constraints.Size; // Bean validation for minimum size constraints
import lombok.Data; // Lombok annotation for boilerplate reduction

/**
 * Represents a Taco composed of a name and a list of ingredients.
 *
 * Uses validation annotations to enforce:
 * - name is not null and at least 5 characters long
 * - ingredients list is not null and contains at least 1 ingredient
 *
 * Lombok's @Data generates:
 * - getters and setters
 * - toString, equals, and hashCode
 */
@Data
public class Taco {

    /**
     * Name of the taco.
     * Must be non-null and at least 5 characters long.
     */
    @NotNull(message = "Taco name cannot be null")
    @Size(min = 5, message = "Name must be at least 5 characters long")
    private String name;

    /**
     * List of ingredients that make up the taco.
     * Must be non-null and contain at least one ingredient.
     */
    @NotNull(message = "Ingredients cannot be null")
    @Size(min = 1, message = "You must choose at least 1 ingredient")
    private List<Ingredient> ingredients;
}
