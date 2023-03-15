package tobyspring.helloboot;

import org.springframework.boot.SpringApplication;
import tobyspring.config.annotation.MySpringBootApplication;

@MySpringBootApplication
public class HellobootSelfApplication {


    public static void main(String[] args) {
        SpringApplication.run(HellobootSelfApplication.class, args);

    }


}
