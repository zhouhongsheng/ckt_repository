package Trans.Trans;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Hello world!
 *
 */
@EnableAutoConfiguration
@SpringBootApplication
public class App {
    private static Logger logger = LoggerFactory.getLogger(App.class);
 
     /**
     * Main Start
     */
    public static void main(String[] args) {
    	SpringApplication.run(App.class, args);
        logger.info("============= Robot Server Start Success =============");
    	logger.debug("============= Robot Server Start with logging debug Success =============");
    }
 
}