package reboot.spring;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Objects;

@RequestMapping
@MyComponent
public class BootController {

    private final BootService service;

    public BootController(BootService service) {
        this.service = service;
    }

    @ResponseBody
    @GetMapping("/hello")
    public String reboot(String name) {
        return service.reboot(Objects.requireNonNull(name));
    }
}
