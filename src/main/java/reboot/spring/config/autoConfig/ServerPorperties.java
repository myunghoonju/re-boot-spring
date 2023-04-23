package reboot.spring.config.autoConfig;

import lombok.Getter;
import lombok.Setter;
import reboot.spring.config.MyConfigurationProperties;

@Getter
@Setter
@MyConfigurationProperties(prefix="server")
public class ServerPorperties {

    private String contextPath;

    private int port;
}
