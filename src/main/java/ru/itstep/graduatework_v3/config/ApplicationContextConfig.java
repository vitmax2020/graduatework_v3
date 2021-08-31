package ru.itstep.graduatework_v3.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@ComponentScan("ru.itstep.graduatework_v3.*")
public class ApplicationContextConfig {



    @Bean(name = "viewResolver")
    public InternalResourceViewResolver getViewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
  //      viewResolver.setPrefix("/");
        viewResolver.setSuffix(".html");
        return viewResolver;
    }
}
