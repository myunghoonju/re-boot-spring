package reboot.spring;

@MyComponent
public class SimpleBootService implements BootService {

    @Override
    public String reboot(String name) {
        return "SimpleBootService reboot " + name;
    }
}
