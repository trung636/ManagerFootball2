//package com.fball.config;
//
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletAutoConfiguration;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Description;
//import org.springframework.core.Ordered;
//import org.springframework.web.servlet.DispatcherServlet;
//import org.springframework.web.servlet.ViewResolver;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//import org.thymeleaf.spring5.SpringTemplateEngine;
//import org.thymeleaf.spring5.view.ThymeleafViewResolver;
//import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
//
//@Configuration
//public class DispastcherConfig implements WebMvcConfigurer {
//
//  @Bean
//  @Description("Thymeleaf template resolver serving HTML 5")
//  public ClassLoaderTemplateResolver templateResolver() {
//      System.out.println("\n################## Init Thymeleaf Config ##################");
//      ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
//      templateResolver.setCacheable(Boolean.parseBoolean("false"));
//      templateResolver.setSuffix(".html");
//      templateResolver.setPrefix("html/");
//      templateResolver.setTemplateMode("HTML");
//      templateResolver.setCharacterEncoding("UTF-8");
//      templateResolver.setOrder(Ordered.HIGHEST_PRECEDENCE);
//      System.out.println("################## Init Thymeleaf Config Successfully ##################");
//      return templateResolver;
//  }
//
//  @Bean
//  @Description("Thymeleaf template engine with Spring integration")
//  public SpringTemplateEngine templateEngine() {
//
//      SpringTemplateEngine templateEngine = new SpringTemplateEngine();
//      templateEngine.setTemplateResolver(templateResolver());
//      return templateEngine;
//  }
//
//  @Bean
//  @Description("Thymeleaf view resolver")
//  public ViewResolver viewResolver() {
//
//      ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
//      viewResolver.setOrder(Ordered.HIGHEST_PRECEDENCE);
//      viewResolver.setProducePartialOutputWhileProcessing(false);
//      viewResolver.setTemplateEngine(templateEngine());
//      viewResolver.setCharacterEncoding("UTF-8");
//
//      return viewResolver;
//  }
//  
//  @Bean(name = DispatcherServletAutoConfiguration.DEFAULT_DISPATCHER_SERVLET_BEAN_NAME)
//  public DispatcherServlet dispatcherServlet() {
//      return new ThymeleafDispatcherServlet();
//  }
//}
//
