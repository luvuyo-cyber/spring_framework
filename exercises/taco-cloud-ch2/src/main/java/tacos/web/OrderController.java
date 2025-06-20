package tacos.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus; // Used to manage session attributes

import lombok.extern.slf4j.Slf4j; // Used for logging
import tacos.TacoOrder; // Represents a collection of tacos in an order

import jakarta.validation.Valid; // For JSR-303 Bean Validation
import org.springframework.validation.Errors; // For handling validation errors

/**
 * Controller for handling taco order submission.
 * Annotated with @Slf4j for Lombok-provided logging.
 * Annotated with @Controller to mark this class as a Spring MVC controller.
 * Annotated with @RequestMapping("/orders") to map all requests within this class to the /orders path.
 * Annotated with @SessionAttributes("tacoOrder") to indicate that the "tacoOrder"
 * model attribute should be stored in the session, making it available across multiple requests.
 * This allows the TacoOrder object designed in DesignTacoController to be carried over.
 */
@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("tacoOrder")
public class OrderController {

    /**
     * Handles HTTP GET requests to /orders/current.
     * This method displays the order form where the user can review and
     * submit their taco order.
     *
     * @return The logical view name "orderForm".
     */
    @GetMapping("/current")
    public String orderForm() {
        return "orderForm";
    }

    /**
     * Handles HTTP POST requests to /orders.
     * This method processes the submitted taco order.
     *
     * @param order The TacoOrder object, populated with data from the form.
     * This object is typically retrieved from the session due to @SessionAttributes.
     * Annotated with @Valid to trigger JSR-303 validation on the order details.
     * @param errors An Errors object that holds any validation errors encountered.
     * @param sessionStatus The SessionStatus object, used to mark the session attribute
     * (tacoOrder) as complete, effectively cleaning up the session.
     * @return A redirect string to the application's root ("/") if the order is successfully
     * processed, or back to "orderForm" view if validation errors exist.
     */
    @PostMapping
    public String processOrder(@Valid TacoOrder order, Errors errors,
                               SessionStatus sessionStatus) {
        // Check if there are any validation errors
        if (errors.hasErrors()) {
            // If errors exist, return to the "orderForm" view to display them
            return "orderForm";
        }

        // Log the submitted order information for debugging/information purposes
        log.info("Order submitted: {}", order);

        // Mark the current session attribute ("tacoOrder") as complete.
        // This clears the TacoOrder from the session, preparing for a new order.
        sessionStatus.setComplete();

        // Redirect the user to the home page (or a confirmation page)
        return "redirect:/";
    }
}
