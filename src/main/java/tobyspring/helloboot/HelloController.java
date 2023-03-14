package tobyspring.helloboot;

import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
//@MyComponent
public class HelloController {

    private final HelloService helloService;
    private final ApplicationContext applicationContext;

    public HelloController(HelloService helloService, ApplicationContext applicationContext) {
        this.helloService = helloService;
        this.applicationContext = applicationContext;
    }

    @GetMapping("/hello")
    // @RestController 를 사용하느 경우 이 어노테이션이 (메타 어노테이션으로) 포함되어 있기 때문에 생략 가능
    // 그렇지 않은 경우 dispatcherServlet에서는 view 형태로 반환하려고 하기 때문에 에러 발생
    public String hello(String name) {
        return helloService.sayHello(Objects.requireNonNull(name));
    }


}
