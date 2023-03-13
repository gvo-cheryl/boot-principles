package tobyspring.helloboot;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

//@RestController
@RequestMapping
public class HelloController {

    private final HelloService helloService;

    public HelloController(HelloService helloService) {
        this.helloService = helloService;
    }

    @GetMapping("/hello")
    @ResponseBody
    // @RestController 를 사용하느 경우 이 어노테이션이 포함되어 있기 때문에 생략 가능
    // 그렇지 않은 경우 dispatcherServlet에서는 view 형태로 반환하려고 하기 때문에 에러 발생
    public String hello(String name) {
        return helloService.sayHello(Objects.requireNonNull(name));
    }

}
