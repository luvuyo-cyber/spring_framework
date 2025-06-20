package tacos;

import jakarta.validation.constraints.Digits; // For validation: ensures a field contains only digits
import jakarta.validation.constraints.NotBlank; // For validation: ensures a string is not null, empty, or just whitespace
import jakarta.validation.constraints.Pattern; // For validation: validates a string against a regular expression
import org.hibernate.validator.constraints.CreditCardNumber; // For validation: checks if a string is a valid credit card number
import java.util.List; // For holding a list of Taco objects
import java.util.ArrayList; // For initializing the list of Taco objects
import lombok.Data; // Import Lombok's @Data annotation for boilerplate reduction

/**
 * Represents a customer's order for tacos.
 * This class holds delivery information, payment details, and a list of tacos included in the order.
 * It includes validation constraints to ensure necessary information is provided and formatted correctly.
 * Annotated with @Data from Lombok, which automatically generates:
 * - Getters for all fields
 * - Setters for all non-final fields
 * - toString() method
 * - equals() and hashCode() methods
 * - A constructor that initializes all final fields (though none are final here as they need setters).
 */
@Data
public class TacoOrder {

    /**
     * The name for delivery.
     * Annotated with @NotBlank to ensure it's not null, empty, or just whitespace.
     */
    @NotBlank(message="Delivery name is required")
    private String deliveryName;

    /**
     * The street address for delivery.
     * Annotated with @NotBlank.
     */
    @NotBlank(message="Street is required")
    private String deliveryStreet;

    /**
     * The city for delivery.
     * Annotated with @NotBlank.
     */
    @NotBlank(message="City is required")
    private String deliveryCity;

    /**
     * The state for delivery.
     * Annotated with @NotBlank.
     */
    @NotBlank(message="State is required")
    private String deliveryState;

    /**
     * The zip code for delivery.
     * Annotated with @NotBlank.
     */
    @NotBlank(message="Zip code is required")
    private String deliveryZip;

    /**
     * The credit card number for payment.
     * Annotated with @CreditCardNumber to validate it as a legitimate credit card format
     * (e.g., passes the Luhn algorithm check).
     */
    @CreditCardNumber(message="Not a valid credit card number")
    private String ccNumber;

    /**
     * The credit card expiration date.
     * Annotated with @Pattern to ensure it matches the MM/YY format using a regular expression.
     * The regex ensures month is 01-12 and year is 20-99.
     */
    @Pattern(regexp="^(0[1-9]|1[0-2])([\\/])([2-9][0-9])$",
            message="Must be formatted MM/YY")
    private String ccExpiration;

    /**
     * The credit card CVV (Card Verification Value).
     * Annotated with @Digits to ensure it's a number with exactly 3 integer digits and no fraction.
     */
    @Digits(integer=3, fraction=0, message="Invalid CVV")
    private String ccCVV;

    /**
     * A list to hold the Taco objects that are part of this order.
     * Initialized as an empty ArrayList to prevent NullPointerExceptions when adding tacos.
     */
    private List<Taco> tacos = new ArrayList<>();

    /**
     * Adds a single Taco object to the list of tacos in this order.
     * @param taco The Taco object to add to the order.
     */
    public void addTaco(Taco taco) {
        this.tacos.add(taco);
    }
}
