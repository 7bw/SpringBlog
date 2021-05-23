package com.example.springblog.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.regex.Pattern;


@Configuration
@ComponentScan(basePackages = {"com.example.springblog"},
    excludeFilters = {
        @ComponentScan.Filter(type= FilterType.ANNOTATION, value= EnableWebMvc.class)
    })
public class RootConfig {


}
