package reboot.spring;

import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class MySpringApp {

    public static void run(Class<?> applicationClass, String... args) {
        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext() {
            @Override
            protected void onRefresh() {
                super.onRefresh();//never delete

                ServletWebServerFactory factory = getBean(ServletWebServerFactory.class);
                DispatcherServlet dispatcherServlet = getBean(DispatcherServlet.class);
                //dispatcherServlet.setApplicationContext(this);

                WebServer webServer = factory.getWebServer(servletContext -> {
                    servletContext.addServlet("dispatcherServlet", dispatcherServlet)
                            .addMapping("/*");
                });
                webServer.start();
            }
        };
        //register beans
        applicationContext.register(applicationClass);
        //init container
        applicationContext.refresh();
    }
}
