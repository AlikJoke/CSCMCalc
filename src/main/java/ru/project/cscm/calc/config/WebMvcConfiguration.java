package ru.project.cscm.calc.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import ru.project.cscm.calc.sec.rest.RequestInterceptor;

@Configuration
@EnableWebMvc
public class WebMvcConfiguration implements WebMvcConfigurer {

	@Autowired
	private RequestInterceptor requestInterceptor;

	@Value("${allowedOrigins}")
	private String allowedOrigins;

	@Override
	public void addInterceptors(final InterceptorRegistry registry) {
		registry.addInterceptor(requestInterceptor);
		WebMvcConfigurer.super.addInterceptors(registry);
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
				.allowedMethods("POST", "GET", "PUT", "OPTIONS")
				.allowedOrigins(
						StringUtils.tokenizeToStringArray(allowedOrigins, ",;"));
		WebMvcConfigurer.super.addCorsMappings(registry);
	}

}
