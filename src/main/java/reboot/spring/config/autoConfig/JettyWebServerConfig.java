package reboot.spring.config.autoConfig;

import org.springframework.boot.web.embedded.jetty.JettyServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import reboot.spring.config.ConditionalMyOnClass;
import reboot.spring.config.MyAutoConfig;

@MyAutoConfig
@ConditionalMyOnClass("org.eclipse.jetty.server.Server")
public class JettyWebServerConfig {

    @Bean
    public ServletWebServerFactory jettyWebServerFactory() {
        return new JettyServletWebServerFactory();
    }
}
