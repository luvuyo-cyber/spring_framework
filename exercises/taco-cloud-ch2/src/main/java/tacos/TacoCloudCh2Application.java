package tacos;

import org.springframework.boot.SpringApplication; // Import for SpringApplication class
import org.springframework.boot.autoconfigure.SpringBootApplication; // Annotation for Spring Boot applications

/**
 * Main application class for the Taco Cloud Chapter 2 application.
 * This class serves as the entry point for the Spring Boot application.
 * Annotated with @SpringBootApplication, which is a convenience annotation that adds:
 * - @Configuration: Tags the class as a source of bean definitions for the application context.
 * - @EnableAutoConfiguration: Tells Spring Boot to start adding beans based on classpath settings,
 * other beans, and various property settings.
 * - @ComponentScan: Tells Spring to look for other components, configurations, and services
 * in the 'tacos' package, allowing it to discover controllers, converters, etc.
 */
@SpringBootApplication
public class TacoCloudCh2Application {

    /**
     * The main method that serves as the entry point for the Java application.
     * It uses SpringApplication.run() to bootstrap and launch a Spring application
     * from a Java main method.
     *
     * @param args Command line arguments passed to the application.
     */
    public static void main(String[] args) {
        // Runs the Spring Boot application.
        // It initializes the Spring application context, performs component scanning,
        // configures embedded servers (like Tomcat), and starts the application.
        SpringApplication.run(TacoCloudCh2Application.class, args);
    }

}
