package tobyspring.config.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME) // 언제까지 살아있을 것인가
@Target(ElementType.TYPE)
@Component
public @interface MyComponent {

    /*
    * 어노테이션 커스터마이징 하는 경우
    * - 계층형 아키텍쳐를 만들 때 어느 계층에서 어떤 역할을 하는지 구분하고자 할 때
    *
    * */

}
