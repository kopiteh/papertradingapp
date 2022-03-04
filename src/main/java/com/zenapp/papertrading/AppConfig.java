package com.zenapp.papertrading;

import com.zenapp.papertrading.services.JwtFilter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Autowired
    private JwtFilter myfilter;

    @Bean
    public FilterRegistrationBean<JwtFilter> registerJwtFilter(JwtFilter filter) {

        FilterRegistrationBean<JwtFilter> regFilterBean = new FilterRegistrationBean<>();
        regFilterBean.setFilter(filter);
        regFilterBean.addUrlPatterns("/secure/*");
        regFilterBean.setEnabled(true);

        return regFilterBean;
    }

}
