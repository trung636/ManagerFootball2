package com.fball.config;

import javax.servlet.ServletContext;

import org.springframework.context.annotation.Configuration;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

@Configuration
public class ThymeleafConfig {
	
	private final TemplateEngine templateEngine;

	public ThymeleafConfig(final ServletContext servletContext) {
		super();
		ServletContextTemplateResolver templateResolver =
				new ServletContextTemplateResolver(servletContext);
		// HTML is the default mode, but we set it anyway for better understanding of code
		templateResolver.setTemplateMode(TemplateMode.HTML);
		// This will convert "home" to "/WEB-INF/templates/home.html"
		templateResolver.setPrefix("/templates/");
		templateResolver.setSuffix(".html");
		// Template cache TTL=1h. If not set, entries would be cached until expelled
		templateResolver.setCacheTTLMs(Long.valueOf(3600000L));
		// Cache is set to true by default. Set to false if you want templates to
		// be automatically updated when modified.
		templateResolver.setCacheable(true);
		this.templateEngine = new TemplateEngine();
		this.templateEngine.setTemplateResolver(templateResolver);

	}

}
