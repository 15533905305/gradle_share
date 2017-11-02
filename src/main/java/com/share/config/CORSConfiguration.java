package com.share.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by antLuck on 2017/8/10.
 */
@Configuration
public class CORSConfiguration extends WebMvcConfigurerAdapter {

    public void addCorsMappings(CorsRegistry registry){
        registry.addMapping("/*").allowedMethods("*").allowedOrigins("*").allowedHeaders("*");
    }
}
