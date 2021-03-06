package com.example.springblog.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.core.type.filter.RegexPatternTypeFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.regex.Pattern;


@Configuration
@Import(DataConfig.class)
@ComponentScan(basePackages = {"com.example.springblog"},
    excludeFilters = {
        @Filter(type= FilterType.CUSTOM, value= WebPackage.class)
    })
public class RootConfig {
	public static class WebPackage extends RegexPatternTypeFilter {
    public WebPackage() {
      super(Pattern.compile("springblog\\.web"));
    }    
  }

}
