package tobyspring.helloboot;

import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class MySpringApplication {

    public static void run(Class<?> applicationClass, String... args) {
        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext () {
            @Override
            protected void onRefresh() {
                super.onRefresh();

                // 서블릿 컨테이너 띄우기
                ServletWebServerFactory serverFactory = this.getBean(ServletWebServerFactory.class);
                DispatcherServlet dispatcherServlet = this.getBean(DispatcherServlet.class);

                WebServer webServer = serverFactory.getWebServer(servletContext -> {

                    // 서블릿 등록
                    servletContext.addServlet("dispatcherServlet", dispatcherServlet).addMapping("/*"); // 슬래시 이후 모든 경로를 처리 한다.
                });
                webServer.start(); // Tomcat 서블릿 컨테이너 동작

            }
        };

        applicationContext.register(applicationClass);
        applicationContext.refresh(); // 스프링 컨테이너의 초기화는 여기서 발생한다.
    }

}
