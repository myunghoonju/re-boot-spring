package reboot.spring;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import reboot.spring.config.MySpringbootApplication;

@MySpringbootApplication
public class BootApplication {

    @Bean
    ApplicationRunner applicationRunner(Environment environment) {
        return args -> {
            String myName = environment.getProperty("my.name");
            System.out.println("my.name:  " + myName);
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(BootApplication.class, args);
        //MySpringApp.run(BootApplication.class, args);
    }
}
