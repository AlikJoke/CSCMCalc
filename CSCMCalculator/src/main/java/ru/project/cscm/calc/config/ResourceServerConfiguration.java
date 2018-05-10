package ru.project.cscm.calc.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import ru.project.cscm.calc.sec.rest.AuthServerFilter;

@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

	public static final String RESOURCE_ID = "CSCM";

	@Autowired
	private AuthServerFilter filter;
	
	@Override
	public void configure(ResourceServerSecurityConfigurer resources) {
		resources.resourceId(RESOURCE_ID).stateless(false);
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.anonymous().disable().csrf().disable().requiresChannel().requestMatchers().requiresSecure()
				.antMatchers("/CSCM/**").requiresSecure().and().authorizeRequests().antMatchers("/CSCM/**")
				.authenticated().and().exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler())
				.and().addFilterBefore(filter, BasicAuthenticationFilter.class);;
	}

}
