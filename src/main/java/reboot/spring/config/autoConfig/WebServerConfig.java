package reboot.spring.config.autoConfig;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reboot.spring.config.EnableMyConfigurationProperties;

/**
 *  in general user-config preferred to auto-config
 * */


@EnableMyConfigurationProperties(element = ServerPorperties.class)
@Configuration(proxyBeanMethods = false)
public class WebServerConfig {

/*    @Bean
    public ServerPorperties properties(Environment environment) {
        return Binder.get(environment).bind("", ServerPorperties.class).get();
    }*/

    @Bean
    ServletWebServerFactory customWebServerFactory(ServerPorperties properties) {
        TomcatServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
        serverFactory.setPort(properties.getPort());
        serverFactory.setContextPath(properties.getContextPath());

        return serverFactory;
    }
}
