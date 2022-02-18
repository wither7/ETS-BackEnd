package com.example.helloworld.config;/**
 * @Classname CorsConfig
 * @Description TODO
 * @Date 2021/11/13 14:32
 * @Created by 86150
 */

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @ program: Helloworld
 * @ description:
 * @ author: YXJ
 * @ date: 2021-11-13 14:32:13
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("*")
                .allowedMethods("*")
                .allowCredentials(true)
                .maxAge(3600)
                .allowedHeaders("*");
    }
}
