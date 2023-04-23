package reboot.spring.config.autoConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import reboot.spring.config.MyAutoConfig;

@MyAutoConfig
public class PropertyPlaceHolerConfig {

    @Bean
    PropertySourcesPlaceholderConfigurer configurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
