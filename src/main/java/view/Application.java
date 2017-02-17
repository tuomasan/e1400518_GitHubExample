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
 * 
 * Here is the sql to create the table for the example
 * CREATE TABLE IF NOT EXISTS `MATERIAL` (`id` int(11) NOT NULL,  `code` varchar(100) DEFAULT NULL,  `name` varchar(100) DEFAULT NULL,  `price` int(11) DEFAULT NULL) ENGINE=MyISAM AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;
 * INSERT INTO `MATERIAL` (`id`, `code`, `name`, `price`) VALUES (1, 'ABCD', 'Laite ABCD', 1222);
 * 
 * taskkill /f /im javaw.exe
 */
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
