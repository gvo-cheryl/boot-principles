package tobyspring.helloboot;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

@Configuration
public class HellobootSelfApplication {

    @Bean
    public HelloController helloController(HelloService helloService){
        return new HelloController(helloService);
    }

    @Bean
    public HelloService helloService(){
        return new SimpleHelloService();
    }

    public static void main(String[] args) {
        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext () {
            @Override
            protected void onRefresh() {
                super.onRefresh();

                // 서블릿 컨테이너 띄우기
                ServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
                WebServer webServer = serverFactory.getWebServer(servletContext -> {

                    // 서블릿 등록
                    servletContext.addServlet("dispatcherServlet",
                            new DispatcherServlet(this)).addMapping("/*"); // 슬래시 이후 모든 경로를 처리 한다.
                });
                webServer.start(); // Tomcat 서블릿 컨테이너 동작


            }
        };

        applicationContext.register(HellobootSelfApplication.class);
        applicationContext.refresh(); // 스프링 컨테이너의 초기화는 여기서 발생한다.


    }

}
