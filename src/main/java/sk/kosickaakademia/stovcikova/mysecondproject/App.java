package sk.kosickaakademia.stovcikova.mysecondproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Collections;

/**
 * Hello world!
 *
 */
@SpringBootApplication
public class App
{
    public static void main( String[] args )
    {
        //SpringApplication.run(App.class,args);
        SpringApplication app = new SpringApplication(App.class);
        app.setDefaultProperties(Collections.<String, Object>singletonMap("server.port", "8083"));
        app.run(args);
    }

}
