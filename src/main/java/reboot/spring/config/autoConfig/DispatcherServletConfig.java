package reboot.spring.config.autoConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.DispatcherServlet;
import reboot.spring.config.MyAutoConfig;

@MyAutoConfig
public class DispatcherServletConfig {

    @Bean
    public DispatcherServlet dispatcherServlet() {
        return new DispatcherServlet();
    }
}
