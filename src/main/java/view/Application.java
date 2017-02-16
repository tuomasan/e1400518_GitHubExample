/*
 * Test http://localhost:8080/hello
 * http://localhost:8080/material
 */
package view;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
 * Run the application run-run as application by selecting this class
 * Then use browser with url http://localhost:8080/
 */
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
