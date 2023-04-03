package reboot.spring;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class BootApplication {

	public static void main(String[] args) {
		GenericApplicationContext applicationContext = new GenericApplicationContext();
		applicationContext.registerBean(BootController.class);
		applicationContext.refresh();

		ServletWebServerFactory factory = new TomcatServletWebServerFactory();
		WebServer webServer = factory.getWebServer(servletContext -> {

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
		});
		webServer.start();
	}
}
