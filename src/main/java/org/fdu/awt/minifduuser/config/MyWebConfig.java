package org.fdu.awt.minifduuser.config;

import org.fdu.awt.minifduuser.token.JWTInterceptor;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author ZMARK
 */
@SpringBootConfiguration
public class MyWebConfig implements WebMvcConfigurer {
    /**
     * token验证
     * 对于除了注册和登录的请求均拦截,拦截转向到 JWTInterceptor
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new JWTInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/user-service/sign-up", "/user-service/log-in");
    }
}
