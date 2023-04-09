package reboot.spring.config.autoConfig;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import reboot.spring.config.MyAutoConfig;

@MyAutoConfig
@ConditionalMyOnClass("org.apache.catalina.startup.Tomcat")
public class TomcatWebServerConfig {

    @Bean
    public ServletWebServerFactory tomcatWebServerFactory() {
        return new TomcatServletWebServerFactory();
    }

}
