package reboot.spring;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BootServiceTest {

    @Test
    void serviceTest() {
        SimpleBootService service = new SimpleBootService();
        String reboot = service.reboot("Test");
        assertThat(reboot).isEqualTo("Test");
    }
}