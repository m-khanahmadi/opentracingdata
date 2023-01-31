package com.faraz.finance.config.other;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

@Configuration
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CustomCORSFilter extends CorsFilter {
    public CustomCORSFilter() {
        super(configurationSource());
    }

    private static UrlBasedCorsConfigurationSource configurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        //main
//        config.addAllowedOrigin("https://ipinbar.net");
//        config.addAllowedOrigin("https://sep.shaparak.ir");
//        config.addAllowedOrigin("https://cp.ipinbar.net");
        config.addAllowedOrigin("*");

        //develop

        config.addAllowedHeader("Content-Type");
        config.addAllowedHeader("Origin");
        config.addAllowedHeader("X-Auth-Token");
        config.addAllowedHeader("Accept");
        config.addAllowedHeader("X-Requested-With");
        config.addAllowedHeader("remember-me");
        config.addAllowedHeader("X-CSRF-TOKEN");
        config.addAllowedHeader("x-requested-with");
        config.addAllowedHeader("authorization");
        config.addAllowedHeader("Authorization");
        config.addAllowedHeader("responseType");
        config.addAllowedHeader("farazKey");
        config.addAllowedHeader("username");
        config.addAllowedHeader("password");
//        config.addAllowedHeader("captcha");
        config.addExposedHeader("xsrf-token");
        config.setMaxAge(3600L);
        config.setAllowedMethods(Arrays.asList("GET", "HEAD", "POST", "PUT", "DELETE", "OPTIONS"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }

}
