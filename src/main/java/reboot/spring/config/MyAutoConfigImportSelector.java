package reboot.spring.config;

import org.springframework.context.annotation.DeferredImportSelector;
import org.springframework.core.type.AnnotationMetadata;

public class MyAutoConfigImportSelector implements DeferredImportSelector {

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[]{
                "reboot.spring.config.autoConfig.DispatcherServletConfig",
                "reboot.spring.config.autoConfig.TomcatWebServerConfig"
        };
    }
}
