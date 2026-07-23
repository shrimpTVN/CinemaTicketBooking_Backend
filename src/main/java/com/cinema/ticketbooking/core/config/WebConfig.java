package com.cinema.ticketbooking.core.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ApiVersionConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.beans.factory.annotation.Value;

@Configuration
public class WebConfig  implements WebMvcConfigurer {

    @Value("${app.frontend-url}")
    private String frontendUrl;

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        configurer.addPathPrefix("/api", config -> true);
    }

//    API versioning
//    @Override
//    public void configureApiVersioning(ApiVersionConfigurer configurer) {
//        configurer.useMediaTypeParameter(MediaType.parseMediaType("application/vnd.cinema+json"), "v")
//                .addSupportedVersions("1.0", "2.0", "3.0").setDefaultVersion("1.0");
//    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:5173", "http://localhost:5175", frontendUrl) //replace with the url of the Frontend.
                .allowedMethods("*")
                .allowedHeaders("*")
                .allowCredentials(true).maxAge(3600*24);
    }
}
