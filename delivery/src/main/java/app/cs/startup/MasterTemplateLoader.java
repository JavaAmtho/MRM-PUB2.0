package app.cs.startup;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MasterTemplateLoader implements ServletContextListener {
	public void contextInitialized(ServletContextEvent event) {
		System.out.println("Startup==>" + event);
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {

	}
}