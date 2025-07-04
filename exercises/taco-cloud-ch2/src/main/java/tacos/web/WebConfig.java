package tacos.web;

import org.springframework.context.annotation.Configuration; // Marks this class as a config provider
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry; // For registering simple view controllers
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer; // Interface to customize Spring MVC setup

/**
 * Spring MVC configuration class.
 *
 * Implements WebMvcConfigurer to customize MVC without overriding defaults completely.
 *
 * @Configuration marks it as a Spring bean provider for app context.
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * Registers simple automated view controllers.
     *
     * This method is useful when you want to map a URL directly to a view name
     * without writing a controller method.
     *
     * @param registry The registry to add view controllers.
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // Map "/" URL directly to the "home" view.
        // When users visit "/", Spring forwards them to home.html (or other view resolver result).
        registry.addViewController("/").setViewName("home");
    }
}
