package tobyspring.helloboot;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HellobootSelfApplication {

    public static void main(String[] args) {
        ServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();

        // 서블릿 컨테이너 띄우기
        WebServer webServer = serverFactory.getWebServer(servletContext -> {

            // 서블릿 등록
            servletContext.addServlet("frontController", new HttpServlet() {

                final HelloController helloController = new HelloController();

                @Override
                protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                    // 인증, 보안, 다국어 및 공통 기능 처리
                    if (req.getRequestURI().equals("/hello") && req.getMethod().equals(HttpMethod.GET.name())){

                        // 서블릿 요청 처리 - request
                        String name = req.getParameter("name");

                        // 컨트롤러 매핑
                        // 바인딩 : 직접적인 웹 요청에 데이터 타입에 맞게 전달
                        String ret = helloController.hello(name);

                        // 서블릿 요청 처리 - response
                        resp.setStatus(HttpStatus.OK.value());
                        resp.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN_VALUE);
                        resp.getWriter().println(ret);

                    } else if (req.getRequestURI().equals("/user")) {
                        //
                    } else {
                        resp.setStatus(HttpStatus.NOT_FOUND.value());
                    }

                }
            }).addMapping("/*"); // 슬래시 이후 모든 경로를 처리한다.
        });
        webServer.start(); // Tomcat 서블릿 컨테이너 동작

    }

}
