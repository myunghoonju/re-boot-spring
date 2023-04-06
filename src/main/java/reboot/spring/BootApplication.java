package reboot.spring;

import org.springframework.boot.SpringApplication;
import reboot.spring.config.MySpringbootApplication;

@MySpringbootApplication
public class BootApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootApplication.class, args);
        //MySpringApp.run(BootApplication.class, args);
    }
}
