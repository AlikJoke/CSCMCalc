package ru.project.cscm.calc.sec.rest;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Service;

import ru.project.cscm.calc.base.NotNullOrEmpty;
import ru.project.cscm.calc.sec.AppRole;
import ru.project.cscm.calc.sec.Security;

@Service
public class SecurityImpl implements Security {

	static final String PATH_LOGIN = "/internal/login";
	static final String PATH_LOGOUT = "/exit";

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private TokenStore tokenStore;

	@Override
	public void login(@NotNullOrEmpty final String username, @NotNullOrEmpty final String password) {
		final UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
				username, null, Arrays.asList(new SimpleGrantedAuthority(AppRole.ROLE_TRUSTED_CLIENT.name())));

		if (usernamePasswordAuthenticationToken.isAuthenticated()) {
			SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
		}
	}

	@Override
	public void logout() {
		tokenStore.findTokensByClientId(SecurityContextHolder.getContext().getAuthentication().getName())
				.forEach(token -> tokenStore.removeAccessToken(token));
		SecurityContextHolder.getContext().getAuthentication().setAuthenticated(false);
		SecurityContextHolder.clearContext();
	}

}
