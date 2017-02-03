package tran.tran;

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
@ComponentScan
public class App 
{
    public static void main( String[] args )
    {
    	SpringApplication.run(App.class, args);
        System.out.println( "tran data tool start success!" );
    }
}
