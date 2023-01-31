//package com.faraz.authservice.config.other;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpMethod;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
//
//import javax.servlet.*;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
///**
// * @author Meghdad Hajilo
// */
////@Order(Ordered.HIGHEST_PRECEDENCE)
//@Configuration
//@EnableWebMvc
//public class CorsFilter implements WebMvcConfigurer {
//
//
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**");
//
//        /**
//         * registry.addMapping("/api/**")
//         *             .allowedOrigins("http://domain2.com")
//         *             .allowedMethods("PUT", "DELETE")
//         *             .allowedHeaders("header1", "header2", "header3")
//         *             .exposedHeaders("header1", "header2")
//         *             .allowCredentials(false).maxAge(3600);
//         */
//    }
//}