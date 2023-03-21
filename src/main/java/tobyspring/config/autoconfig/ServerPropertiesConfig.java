package tobyspring.config.autoconfig;

import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import tobyspring.config.MyAutoConfiguration;

@MyAutoConfiguration
public class ServerPropertiesConfig {

    @Bean
    ServerProperties serverProperties(Environment env) {
        return Binder.get(env).bind("", ServerProperties.class).get();
//        ServerProperties serverProperties = new ServerProperties();
//        serverProperties.setContextPath(env.getProperty("contextPath"));
//        serverProperties.setPort(Integer.parseInt(env.getProperty("port")));
//        return serverProperties;
    }

}
