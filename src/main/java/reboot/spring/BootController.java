package reboot.spring;

import java.util.Objects;

public class BootController {

    public String reboot(String name) {
        SimpleBootService service = new SimpleBootService();
        return service.reboot(Objects.requireNonNull(name));
    }
}
