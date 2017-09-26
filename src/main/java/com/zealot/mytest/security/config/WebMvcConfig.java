package com.zealot.mytest.security.config;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import freemarker.ext.jsp.TaglibFactory;


@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {
	
	@Autowired
	private FreeMarkerConfigurer configurer;
	
	@PostConstruct
	public void freeMarkerConfigurer()
	{
		List<String> tlds = new ArrayList<>();
		tlds.add("/static/security.tld");
		
		TaglibFactory taglibFactory = configurer.getTaglibFactory();
		taglibFactory.setClasspathTlds(tlds);
		
		if(taglibFactory.getObjectWrapper() == null)
		{
			taglibFactory.setObjectWrapper(configurer.getConfiguration().getObjectWrapper());
		}
		
	}
}
