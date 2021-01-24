package com.geekbrains.knigopoisk.configs;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@ComponentScan("com.geekbrains.knigopoisk")
public class AppConfig implements WebMvcConfigurer {

}
