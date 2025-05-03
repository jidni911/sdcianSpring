package com.jidnivai.sdcian.sdcian.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Value("${sdcian.app.images-upload-path}")
    private String imageFolder;

    @Value("${sdcian.app.videos-upload-path}")
    private String videoFolder;

    @Value("${sdcian.app.home-upload-path}")
    String homeImageFolder;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Using the injected paths from application.properties
        // registry.addResourceHandler("/images/**")
        //         .addResourceLocations("file:" + imageFolder);

        // registry.addResourceHandler("/videos/**")
        //         .addResourceLocations("file:" + videoFolder);

        // registry.addResourceHandler("/homeImages/**")
        //         .addResourceLocations("file:" + homeImageFolder);
    }
}