package tacos.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.core.convert.converter.Converter; // Spring interface for type conversion
import org.springframework.stereotype.Component; // Marks this as a Spring-managed bean

import tacos.Ingredient; // Your Ingredient domain object
import tacos.Ingredient.Type; // Enum for ingredient types (WRAP, PROTEIN, etc.)

/**
 * Converts a String ingredient ID (from web form submissions)
 * into a full Ingredient object.
 *
 * This helps Spring MVC automatically convert request parameters
 * (like "FLTO") into Ingredient objects when binding form data.
 */
@Component
public class IngredientByIdConverter implements Converter<String, Ingredient> {

    // Map to store all ingredients keyed by their ID strings for fast lookup
    private Map<String, Ingredient> ingredientMap = new HashMap<>();

    /**
     * Constructor: initializes the map with all ingredients.
     * This is a simple, in-memory "database" of ingredients for conversion.
     */
    public IngredientByIdConverter() {
        ingredientMap.put("FLTO", new Ingredient("FLTO", "Flour Tortilla", Type.WRAP));
        ingredientMap.put("COTO", new Ingredient("COTO", "Corn Tortilla", Type.WRAP));
        ingredientMap.put("GRBF", new Ingredient("GRBF", "Ground Beef", Type.PROTEIN));
        ingredientMap.put("CARN", new Ingredient("CARN", "Carnitas", Type.PROTEIN));
        ingredientMap.put("TMTO", new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES));
        ingredientMap.put("LETC", new Ingredient("LETC", "Lettuce", Type.VEGGIES));
        ingredientMap.put("CHED", new Ingredient("CHED", "Cheddar", Type.CHEESE));
        ingredientMap.put("JACK", new Ingredient("JACK", "Monterrey Jack", Type.CHEESE));
        ingredientMap.put("SLSA", new Ingredient("SLSA", "Salsa", Type.SAUCE));
        ingredientMap.put("SRCR", new Ingredient("SRCR", "Sour Cream", Type.SAUCE));
    }

    /**
     * Converts the incoming String ID to the corresponding Ingredient object.
     *
     * @param id the ingredient ID from the form (like "FLTO")
     * @return the Ingredient object or null if no match found
     */
    @Override
    public Ingredient convert(String id) {
        return ingredientMap.get(id);
    }
}
