package com.sh.sys.config;

import com.sh.sys.interceptor.AdminLoginInterceptor;
import com.sh.sys.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

@Configuration
public class SYSWebMvcConfig implements WebMvcConfigurer {

    @Resource
    private LoginInterceptor loginInterceptor;

    @Resource
    private AdminLoginInterceptor adminLoginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(adminLoginInterceptor)
                .addPathPatterns("/admin/**")
                .excludePathPatterns("/admin/loginView")
                .excludePathPatterns("/admin/captcha")
                .excludePathPatterns("/admin/login")
                .excludePathPatterns("/**.js")
                .excludePathPatterns("/images/**")
                .excludePathPatterns("/js/**")
                .excludePathPatterns("/plugins/**")
                .excludePathPatterns("/style/**");

        registry.addInterceptor(loginInterceptor)
                .excludePathPatterns("/")
                .excludePathPatterns("/loginView")
                .excludePathPatterns("/login")
                .excludePathPatterns("/registerView")
                .excludePathPatterns("/register")
                .excludePathPatterns("/detail")
                .excludePathPatterns("/sendComment")
                .excludePathPatterns("/addWish")
                .excludePathPatterns("/addCollection")
                .excludePathPatterns("/index")
                .excludePathPatterns("/captcha")
                .excludePathPatterns("/**.js")
                .excludePathPatterns("/images/**")
                .excludePathPatterns("/js/**")
                .excludePathPatterns("/plugins/**")
                .excludePathPatterns("/style/**")
                .excludePathPatterns("/admin/**");
    }
}
