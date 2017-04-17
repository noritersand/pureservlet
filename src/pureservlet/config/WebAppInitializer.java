package pureservlet.config;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRegistration.Dynamic;
import javax.servlet.annotation.WebListener;

import pureservlet.mvc.DispatcherServlet;

@WebListener
public class WebAppInitializer implements ServletContextListener {
	@Override
	public void contextInitialized(ServletContextEvent event) {
		Dynamic registration = event.getServletContext().addServlet("servlet", new DispatcherServlet());
		registration.addMapping("*.do");
		registration.setLoadOnStartup(1);
	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		// do some when tomcat shutdown
	}
}
