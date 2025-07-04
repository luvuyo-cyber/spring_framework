package tacos.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus; // To manage session lifecycle

import lombok.extern.slf4j.Slf4j; // For logging support
import tacos.TacoOrder; // Represents the entire taco order

import jakarta.validation.Valid; // For validating input data
import org.springframework.validation.Errors; // Holds validation errors

/**
 * Controller for handling the taco order submission process.
 *
 * - @Slf4j provides a logger named 'log'.
 * - @Controller marks it as a Spring MVC controller.
 * - @RequestMapping("/orders") maps all handler methods here under /orders URL.
 * - @SessionAttributes("tacoOrder") ensures TacoOrder is kept in session across requests.
 */
@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("tacoOrder")
public class OrderController {

    /**
     * Handles GET requests to /orders/current.
     * Shows the order form where the user can review and finalize their taco order.
     *
     * @return the logical view name "orderForm" (typically resolved to orderForm.html).
     */
    @GetMapping("/current")
    public String orderForm() {
        return "orderForm";
    }

    /**
     * Handles POST requests to /orders.
     * Processes the submitted taco order form.
     *
     * @param order The TacoOrder object, populated from the form and session.
     *              Validated with @Valid to enforce constraints.
     * @param errors Holds any validation errors.
     * @param sessionStatus Used to mark the session attribute as complete.
     * @return Redirects to root ("/") if successful, otherwise returns to the order form to fix errors.
     */
    @PostMapping
    public String processOrder(@Valid TacoOrder order, Errors errors,
                               SessionStatus sessionStatus) {
        // If there are validation errors, return to the order form to show messages
        if (errors.hasErrors()) {
            return "orderForm";
        }

        // Log order details for debugging or auditing
        log.info("Order submitted: {}", order);

        // Mark the session's tacoOrder attribute as complete to clear it from session
        sessionStatus.setComplete();

        // Redirect to home or confirmation page after successful order submission
        return "redirect:/";
    }
}
