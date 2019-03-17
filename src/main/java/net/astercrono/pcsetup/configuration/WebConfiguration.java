package net.astercrono.pcsetup.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import net.astercrono.pcsetup.interceptor.UserAuthenticationInterceptor;

@Configuration
@EnableWebMvc
public class WebConfiguration implements WebMvcConfigurer {
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new UserAuthenticationInterceptor()).excludePathPatterns("/login", "/profile/create", "/logout");
	}
}
