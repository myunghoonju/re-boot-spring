package reboot.spring;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

@Configuration
@ComponentScan
public class BootApplication {

/*
	@Bean
	public BootController bootController(BootService bootService) {
		return new BootController(bootService);
	}

	@Bean
	public BootService bootService() {
		return new SimpleBootService();
	}
*/

	public static void main(String[] args) {
		//create spring container
		//GenericApplicationContext applicationContext = new GenericApplicationContext();
		//GenericWebApplicationContext applicationContext = new GenericWebApplicationContext() {
		AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext() {
			@Override
			protected void onRefresh() {
				super.onRefresh();//never delete
				ServletWebServerFactory factory = new TomcatServletWebServerFactory();
				WebServer webServer = factory.getWebServer(servletContext -> {
					servletContext.addServlet("dispatcherServlet", new DispatcherServlet(this))
							.addMapping("/*");
				});
				webServer.start();
			}
		};
		//register beans
		applicationContext.register(BootApplication.class);
		//init container
		applicationContext.refresh();


/* second refactoring-> DispatcherServlet

		//create servlet container add servlet
		ServletWebServerFactory factory = new TomcatServletWebServerFactory();
		WebServer webServer = factory.getWebServer(servletContext -> {

			*//** first refactoring
				servletContext.addServlet("front-controller", new HttpServlet() {
					@Override
					protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
						// responsibilities:: authenticate, security, multi-language support etc..,
						if (req.getRequestURI().equals("/hello") && req.getMethod().equals(HttpMethod.GET.name())) { //mapping
							String name = req.getParameter("name");

							BootController controller = applicationContext.getBean(BootController.class);
							String result = controller.reboot(name); //binding

							resp.setStatus(HttpStatus.OK.value());//optional
							resp.setContentType(MediaType.APPLICATION_JSON_VALUE);
							PrintWriter writer = resp.getWriter();
							writer.println(result);
						} else if (req.getRequestURI().equals("/user")) {
							//do things
						} else {
							resp.setStatus(HttpStatus.NOT_FOUND.value());
						}
					}
				}).addMapping("/*");
			*//*

			//front-controller
			servletContext.addServlet("dispatcherServlet", new DispatcherServlet(applicationContext))
						  .addMapping("/*");
		});
		webServer.start();
*/
	}
}
