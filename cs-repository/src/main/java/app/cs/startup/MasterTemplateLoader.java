package app.cs.startup;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import app.cs.impl.mastertemplate.MasterTemplateImpl;

import com.cs.data.api.core.nosql.redis.InMemoryNoSqlRepository;
import com.cs.data.core.nosql.redis.RedisRepository;

public class MasterTemplateLoader implements ServletContextListener {

	private MasterTemplateImpl masterTemplates;
	private InMemoryNoSqlRepository redisRepository;

	public void contextInitialized(ServletContextEvent event) {
		final WebApplicationContext springContext = WebApplicationContextUtils
				.getWebApplicationContext(event.getServletContext());
		redisRepository = springContext.getBean(RedisRepository.class);
		masterTemplates = springContext.getBean(MasterTemplateImpl.class);
		redisRepository.set("masterTemplates",
				masterTemplates.getMasterTemplates());

	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {

	}
}