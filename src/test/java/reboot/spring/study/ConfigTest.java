package reboot.spring.study;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.assertj.core.api.Assertions.assertThat;

public class ConfigTest {

    @Test
    void config() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(MyConfig.class);
        applicationContext.refresh();

        BeanOne bean1 = applicationContext.getBean(BeanOne.class);
        BeanTwo bean2 = applicationContext.getBean(BeanTwo.class);

        assertThat(bean1.common).isSameAs(bean2.common);
    }

    //when @Configuration(proxyBeanMethods = true)
    //when @Configuration(proxyBeanMethods = false) is equal to @Component(no proxy)
    @Test
    void proxy_common_method() {
        MyConfigProxy myConfigProxy = new MyConfigProxy();

        BeanOne beanOne = myConfigProxy.beanOne();
        BeanTwo beanTwo = myConfigProxy.beanTwo();

        assertThat(beanOne.common).isSameAs(beanTwo.common);
    }

    static class MyConfigProxy extends MyConfig {
        private Common common; //caching obj, singleTone

        @Override
        Common common() {
            if (this.common == null) {
                common = super.common();
            }

            return this.common;
        }
    }


    @Configuration
    static class MyConfig {
        @Bean
        Common common() {
            return new Common();
        }

        @Bean
        BeanOne beanOne() {
            return new BeanOne(common());
        }

        @Bean
        BeanTwo beanTwo() {
            return new BeanTwo(common());
        }
    }

    static class BeanOne {
        private final Common common;

        public BeanOne(Common common) {
            this.common = common;
        }
    }

    static class BeanTwo {
        private final Common common;

        public BeanTwo(Common common) {
            this.common = common;
        }
    }

    static class Common {}
}
