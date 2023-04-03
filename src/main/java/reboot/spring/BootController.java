package reboot.spring;

import java.util.Objects;

public class BootController {

    private final BootService service;

    public BootController(BootService service) {
        this.service = service;
    }

    public String reboot(String name) {
        return service.reboot(Objects.requireNonNull(name));
    }
}
