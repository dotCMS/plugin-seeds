package com.dotmarketing.osgi.custom.spring;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.DispatcherServlet;

@Configuration
@PropertySource("classpath:default.properties")
public class CustomDispatcherServlet extends DispatcherServlet {

}
