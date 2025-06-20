package tacos.web;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import lombok.extern.slf4j.Slf4j; // Used for logging
import tacos.Ingredient;
import tacos.Ingredient.Type; // Enum for ingredient types
import tacos.Taco;
import tacos.TacoOrder; // Represents a collection of tacos in an order

import jakarta.validation.Valid; // For JSR-303 Bean Validation
import org.springframework.validation.Errors; // For handling validation errors

/**
 * Controller for handling the taco design page.
 * Annotated with @Slf4j for Lombok-provided logging.
 * Annotated with @Controller to mark this class as a Spring MVC controller.
 * Annotated with @RequestMapping("/design") to map all requests within this class to the /design path.
 * Annotated with @SessionAttributes("tacoOrder") to indicate that the "tacoOrder"
 * model attribute should be stored in the session, making it available across multiple requests.
 */
@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("tacoOrder")
public class DesignTacoController {

    /**
     * This method runs before any handler method and populates the Model with
     * a list of available ingredients, categorized by their type.
     * The data is then made accessible to the view (e.g., a Thymeleaf template).
     *
     * @param model The Spring Model object to which attributes are added.
     */
    @ModelAttribute
    public void addIngredientsToModel(Model model) {
        // Create a list of all available ingredients
        List<Ingredient> ingredients = Arrays.asList(
                new Ingredient("FLTO", "Flour Tortilla", Type.WRAP),
                new Ingredient("COTO", "Corn Tortilla", Type.WRAP),
                new Ingredient("GRBF", "Ground Beef", Type.PROTEIN),
                new Ingredient("CARN", "Carnitas", Type.PROTEIN),
                new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES),
                new Ingredient("LETC", "Lettuce", Type.VEGGIES),
                new Ingredient("CHED", "Cheddar", Type.CHEESE),
                new Ingredient("JACK", "Monterrey Jack", Type.CHEESE),
                new Ingredient("SLSA", "Salsa", Type.SAUCE),
                new Ingredient("SRCR", "Sour Cream", Type.SAUCE)
        );

        // Get all possible ingredient types
        Type[] types = Ingredient.Type.values();
        // For each ingredient type, filter the main list and add it to the model
        // The attribute name will be the lowercase string representation of the type (e.g., "wrap", "protein").
        for (Type type : types) {
            model.addAttribute(type.toString().toLowerCase(),
                    filterByType(ingredients, type));
        }
    }

    /**
     * Creates and returns a new TacoOrder object. This object will be
     * added to the Model and, due to @SessionAttributes, also stored in the session.
     * This ensures that the same TacoOrder instance is used across multiple
     * requests as the user designs different tacos.
     *
     * @return A new TacoOrder instance.
     */
    @ModelAttribute(name = "tacoOrder")
    public TacoOrder order() {
        return new TacoOrder();
    }

    /**
     * Creates and returns a new Taco object. This object will be added to the Model.
     * It represents the current taco being designed by the user.
     *
     * @return A new Taco instance.
     */
    @ModelAttribute(name = "taco")
    public Taco taco() {
        return new Taco();
    }

    /**
     * Handles HTTP GET requests to /design.
     * This method simply returns the logical view name "design",
     * which Spring MVC will resolve to a physical view (e.g., design.html).
     *
     * @return The view name "design".
     */
    @GetMapping
    public String showDesignForm() {
        return "design";
    }

    /**
     * Handles HTTP POST requests to /design.
     * This method processes the submitted taco design.
     *
     * @param taco      The Taco object, populated with data from the form.
     * Annotated with @Valid to trigger JSR-303 validation.
     * @param errors    An Errors object that holds any validation errors.
     * @param tacoOrder The TacoOrder object retrieved from the session (due to @SessionAttributes).
     * @return A redirect string to /orders/current if successful, or back to "design" view if errors exist.
     */
    @PostMapping
    public String processTaco(
            @Valid Taco taco, Errors errors,
            @ModelAttribute TacoOrder tacoOrder) {
        // Check if there are any validation errors
        if (errors.hasErrors()) {
            // If errors exist, return to the "design" view to display them
            return "design";
        }

        // If no validation errors, add the designed taco to the current order
        tacoOrder.addTaco(taco);
        // Log the processed taco for debugging/information
        log.info("Processing taco: {}", taco);
        // Redirect the user to the current order page
        return "redirect:/orders/current";
    }

    /**
     * Helper method to filter a list of ingredients by their specified type.
     *
     * @param ingredients The list of all ingredients.
     * @param type        The Type of ingredient to filter by.
     * @return An Iterable (specifically a List) containing only the ingredients of the specified type.
     */
    private Iterable<Ingredient> filterByType(
            List<Ingredient> ingredients, Type type) {
        return ingredients
                .stream() // Convert the list to a stream
                .filter(x -> x.getType().equals(type)) // Filter elements where the ingredient's type matches the given type
                .collect(Collectors.toList()); // Collect the filtered elements back into a new List
    }
}
