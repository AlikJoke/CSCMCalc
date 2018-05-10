package ru.project.cscm.calc.sec.impl;

import java.util.Arrays;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.stereotype.Service;

import ru.project.cscm.calc.config.ResourceServerConfiguration;
import ru.project.cscm.calc.sec.AppRole;

@Service("detailsService")
public class UserDetailsServiceImpl implements UserDetailsService, ClientDetailsService {

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return new User(username,  (String) SecurityContextHolder.getContext().getAuthentication().getCredentials(),
				 SecurityContextHolder.getContext().getAuthentication().getAuthorities());
	}

	@Override
	public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
		final BaseClientDetails clientDetails = new BaseClientDetails(clientId, null, "read,write,trust", null,
				AppRole.ROLE_TRUSTED_CLIENT.name());
		clientDetails.setClientSecret(null);
		clientDetails.setRefreshTokenValiditySeconds(600);
		clientDetails.setAccessTokenValiditySeconds(Integer.MAX_VALUE);
		clientDetails.setAuthorizedGrantTypes(Arrays.asList("refresh_token", "client_credentials"));
		clientDetails.setResourceIds(Arrays.asList(ResourceServerConfiguration.RESOURCE_ID));
		return clientDetails;

	}
}
