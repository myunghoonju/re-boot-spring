package reboot.spring;

public class BootController {

    public String reboot(String name) {
        return "rebooted" + name;
    }
}
