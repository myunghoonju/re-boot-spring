package reboot.spring;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Primary
@Service
public class SimpleDecorator implements BootService {

    private final BootService bootService;

    public SimpleDecorator(BootService bootService) {
        this.bootService = bootService;
    }

    @Override
    public String reboot(String name) {
        return bootService.reboot(name) + " through decorator";
    }
}
