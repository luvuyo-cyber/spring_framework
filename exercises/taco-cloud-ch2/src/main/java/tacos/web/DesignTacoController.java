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

import lombok.extern.slf4j.Slf4j; // Lombok annotation for logging
import tacos.Ingredient;
import tacos.Ingredient.Type; // Enum representing ingredient types (WRAP, PROTEIN, etc.)
import tacos.Taco;
import tacos.TacoOrder; // Holds a collection of tacos in a user’s order

import jakarta.validation.Valid; // For bean validation (JSR-303)
import org.springframework.validation.Errors; // To capture validation errors

/**
 * Controller to handle the taco design page.
 *
 * - @Slf4j enables easy logging.
 * - @Controller marks this class as a Spring MVC controller.
 * - @RequestMapping("/design") maps all requests in this controller under /design URL path.
 * - @SessionAttributes("tacoOrder") keeps the TacoOrder in the HTTP session so it's available across multiple requests.
 */
@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("tacoOrder")
public class DesignTacoController {

    /**
     * Adds all ingredients to the model grouped by their type.
     * This runs before any handler method to prepare data for the view.
     */
    @ModelAttribute
    public void addIngredientsToModel(Model model) {
        // Hardcoded list of available ingredients (could be from DB in real apps)
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

        // For each ingredient type, filter and add the corresponding ingredients to the model
        Type[] types = Ingredient.Type.values();
        for (Type type : types) {
            model.addAttribute(
                    type.toString().toLowerCase(), // attribute name like "wrap", "protein"
                    filterByType(ingredients, type) // list of ingredients of this type
            );
        }
    }

    /**
     * Creates a new TacoOrder instance and adds it to the model/session.
     * This ensures the order persists across multiple requests.
     */
    @ModelAttribute(name = "tacoOrder")
    public TacoOrder order() {
        return new TacoOrder();
    }

    /**
     * Creates a new empty Taco object and adds it to the model.
     * This object will be populated with user input from the form.
     */
    @ModelAttribute(name = "taco")
    public Taco taco() {
        return new Taco();
    }

    /**
     * Handles GET requests to /design.
     * Returns the view name "design" to display the taco design form.
     */
    @GetMapping
    public String showDesignForm() {
        return "design"; // Spring resolves this to templates/design.html
    }

    /**
     * Handles POST requests from the taco design form submission.
     * Validates the Taco object, and if valid, adds it to the TacoOrder.
     * If validation fails, returns the design form to show errors.
     */
    @PostMapping
    public String processTaco(
            @Valid Taco taco, // The taco filled by user input, validated
            Errors errors,    // Holds validation errors if any
            @ModelAttribute TacoOrder tacoOrder) { // The current order from session

        if (errors.hasErrors()) {
            // Validation errors found, return to design page to fix them
            return "design";
        }

        // Add the designed taco to the current order
        tacoOrder.addTaco(taco);

        // Log the taco details for debugging or tracking
        log.info("Processing taco: {}", taco);

        // Redirect user to the order review/checkout page
        return "redirect:/orders/current";
    }

    /**
     * Helper method to filter a list of ingredients by their type.
     * Returns a list containing only ingredients of the specified type.
     */
    private Iterable<Ingredient> filterByType(
            List<Ingredient> ingredients, Type type) {
        return ingredients.stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }
}
