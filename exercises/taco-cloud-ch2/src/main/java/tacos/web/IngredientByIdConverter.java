package tacos.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.core.convert.converter.Converter; // Import for Spring's Converter interface
import org.springframework.stereotype.Component; // Annotation to mark this class as a Spring component

import tacos.Ingredient; // Import for the Ingredient domain class
import tacos.Ingredient.Type; // Import for the nested Type enum within Ingredient

/**
 * A Spring Converter implementation that converts an Ingredient ID (String)
 * into an Ingredient object. This is useful for Spring MVC to automatically
 * bind string IDs from web requests (e.g., form submissions) to actual
 * Ingredient domain objects.
 * Annotated with @Component to ensure Spring discovers and registers this
 * converter as a bean in its application context.
 */
@Component
public class IngredientByIdConverter implements Converter<String, Ingredient> {

    // A map to store available ingredients, using their IDs as keys for quick lookup.
    private Map<String, Ingredient> ingredientMap = new HashMap<>();

    /**
     * Constructor for IngredientByIdConverter.
     * Initializes the ingredientMap with all the predefined ingredients.
     * This acts as a static lookup source for ingredient objects.
     */
    public IngredientByIdConverter() {
        // Populate the map with Ingredient objects, keyed by their respective IDs.
        // These ingredients correspond to those defined in the DesignTacoController.
        ingredientMap.put("FLTO",
                new Ingredient("FLTO", "Flour Tortilla", Type.WRAP));
        ingredientMap.put("COTO",
                new Ingredient("COTO", "Corn Tortilla", Type.WRAP));
        ingredientMap.put("GRBF",
                new Ingredient("GRBF", "Ground Beef", Type.PROTEIN));
        ingredientMap.put("CARN",
                new Ingredient("CARN", "Carnitas", Type.PROTEIN));
        ingredientMap.put("TMTO",
                new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES));
        ingredientMap.put("LETC",
                new Ingredient("LETC", "Lettuce", Type.VEGGIES));
        ingredientMap.put("CHED",
                new Ingredient("CHED", "Cheddar", Type.CHEESE));
        ingredientMap.put("JACK",
                new Ingredient("JACK", "Monterrey Jack", Type.CHEESE));
        ingredientMap.put("SLSA",
                new Ingredient("SLSA", "Salsa", Type.SAUCE));
        ingredientMap.put("SRCR",
                new Ingredient("SRCR", "Sour Cream", Type.SAUCE));
    }

    /**
     * The core conversion method required by the Converter interface.
     * It takes an ingredient ID (String) and returns the corresponding
     * Ingredient object from the pre-populated map.
     *
     * @param id The String ID of the ingredient to convert.
     * @return The Ingredient object corresponding to the given ID, or null if not found.
     */
    @Override
    public Ingredient convert(String id) {
        return ingredientMap.get(id);
    }
}
