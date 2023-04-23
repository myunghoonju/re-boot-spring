package reboot.spring.config.autoConfig;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.env.Environment;
import reboot.spring.config.MyAutoConfig;
import reboot.spring.config.MyConfigurationProperties;

import java.util.Map;

import static org.springframework.core.annotation.AnnotationUtils.getAnnotationAttributes;

@MyAutoConfig
public class PropertyPostProcessorConfig {

    @Bean
    BeanPostProcessor postProcessor(Environment env) {
        return new BeanPostProcessor() {
            @Override
            public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
                MyConfigurationProperties annotation = AnnotationUtils.findAnnotation(bean.getClass(), MyConfigurationProperties.class);
                if (annotation == null) {
                    return bean;
                }

                Map<String, Object> attributes = getAnnotationAttributes(annotation);
                String prefix = (String) attributes.get("prefix");

                return Binder.get(env).bindOrCreate(prefix, bean.getClass());
            }
        };
    }
}
