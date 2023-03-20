package tobyspring.helloboot;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import tobyspring.config.annotation.MySpringBootApplication;

@MySpringBootApplication
public class HellobootSelfApplication {

    @Bean
    ApplicationRunner applicationRunner(Environment env) {
        return args -> {
            String name = env.getProperty("my.name");
            System.out.println("my.name = " + name);
        };
    }


    public static void main(String[] args) {
        SpringApplication.run(HellobootSelfApplication.class, args);

    }


}
