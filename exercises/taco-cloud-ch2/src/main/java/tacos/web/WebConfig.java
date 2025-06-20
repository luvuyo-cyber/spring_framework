package tacos.web;

import org.springframework.context.annotation.Configuration; // Annotation to mark this class as a Spring configuration class
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry; // Used to register simple automated controllers
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer; // Interface for customizing Spring MVC configuration

/**
 * Configuration class for Spring MVC.
 * This class implements WebMvcConfigurer to provide custom configuration
 * for Spring's web MVC framework without requiring a full @EnableWebMvc.
 * Annotated with @Configuration to indicate that this class contains
 * Spring bean definitions and configuration.
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * Overrides the addViewControllers method from WebMvcConfigurer.
     * This method allows for quick registration of simple, automated
     * controllers that just forward to a view. This is particularly useful
     * for static views (like a home page) that don't require any
     * controller logic or data population.
     *
     * @param registry The ViewControllerRegistry used to register view controllers.
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // Registers a view controller for the root path ("/").
        // When a request comes in for "/", Spring MVC will directly forward
        // it to a view named "home". This avoids the need for a separate
        // @Controller class with a @GetMapping("/") method just to show the home page.
        registry.addViewController("/").setViewName("home");
    }

}
