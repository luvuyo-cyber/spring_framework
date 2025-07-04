package tacos;

import jakarta.validation.constraints.Digits; // Validation for numeric fields
import jakarta.validation.constraints.NotBlank; // Validation to ensure non-empty strings
import jakarta.validation.constraints.Pattern; // Validation with regex
import org.hibernate.validator.constraints.CreditCardNumber; // Validation for credit card numbers
import java.util.List; // List to hold tacos in the order
import java.util.ArrayList; // Implementation of List
import lombok.Data; // Lombok annotation for getters, setters, etc.

/**
 * Represents a customer's taco order.
 * Includes delivery info, payment info, and a list of tacos.
 * Uses validation annotations to ensure data correctness.
 */
@Data
public class TacoOrder {

    // Delivery name (required, non-blank)
    @NotBlank(message = "Delivery name is required")
    private String deliveryName;

    // Delivery street address (required, non-blank)
    @NotBlank(message = "Street is required")
    private String deliveryStreet;

    // Delivery city (required, non-blank)
    @NotBlank(message = "City is required")
    private String deliveryCity;

    // Delivery state (required, non-blank)
    @NotBlank(message = "State is required")
    private String deliveryState;

    // Delivery zip code (required, non-blank)
    @NotBlank(message = "Zip code is required")
    private String deliveryZip;

    // Credit card number (must be valid, passes Luhn check)
    @CreditCardNumber(message = "Not a valid credit card number")
    private String ccNumber;

    // Credit card expiration date (format MM/YY, months 01-12, years 20-99)
    @Pattern(regexp = "^(0[1-9]|1[0-2])([\\/])([2-9][0-9])$",
            message = "Must be formatted MM/YY")
    private String ccExpiration;

    // Credit card CVV (exactly 3 digits)
    @Digits(integer = 3, fraction = 0, message = "Invalid CVV")
    private String ccCVV;

    // List of tacos included in the order, initialized to avoid nulls
    private List<Taco> tacos = new ArrayList<>();

    // Convenience method to add a taco to the order
    public void addTaco(Taco taco) {
        this.tacos.add(taco);
    }
}
