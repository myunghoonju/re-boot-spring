package reboot.spring;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BootController {

    @GetMapping("/reboot")
    public String reboot() {
        return "rebooted";
    }
}
