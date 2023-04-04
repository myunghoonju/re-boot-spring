package reboot.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Objects;

@RequestMapping
@MyComponent
public class BootController {

    private final BootService service;
    private final ApplicationContext applicationContext;

    public BootController(BootService service, ApplicationContext applicationContext) {
        this.service = service;
        this.applicationContext = applicationContext;
    }

    @ResponseBody
    @GetMapping("/hello")
    public String reboot(String name) {
        return service.reboot(Objects.requireNonNull(name));
    }

}
