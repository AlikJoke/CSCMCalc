package ru.project.cscm.calc.sec.rest;

import java.io.IOException;
import java.util.Base64;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.GenericFilterBean;

import ru.project.cscm.calc.base.Properties;
import ru.project.cscm.calc.sec.Security;

@Component
public class AuthServerFilter extends GenericFilterBean {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private Properties props;

	@Autowired
	private Security security;

	@Autowired
	private TokenStore tokenStore;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		final HttpServletRequest httpRequest = (HttpServletRequest) request;
		final HttpServletResponse httpResponse = (HttpServletResponse) response;

		if (!httpResponse.isCommitted()) {

			if (httpRequest.getRequestURI().startsWith("/oauth/token")
					&& RequestMethod.POST.name().equalsIgnoreCase(httpRequest.getMethod())) {

				final String authHeader = httpRequest.getHeader("Authorization");
				if (StringUtils.isEmpty(authHeader)) {
					httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED);
				}

				final String[] parts = authHeader.split(" ");
				if (parts.length != 2) {
					httpResponse.sendError(HttpServletResponse.SC_BAD_REQUEST);
				}

				final String args = new String(Base64.getDecoder().decode(parts[1].getBytes("UTF-8")));

				final String[] authParams = args.split(":");
				final String encodedPassword = passwordEncoder.encode(authParams[1]);
				final ResponseEntity<ApproveResultResource> approveResult = restTemplate.postForEntity(props.getProperty("auth.server.url"),
								new ApproveCredentialsResource(authParams[0], encodedPassword), ApproveResultResource.class);

				if (approveResult.getStatusCodeValue() != 202) {
					httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Authentication service is unavailable");
				} else if (!approveResult.getBody().isApproved()) {
					httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, approveResult.getBody().getReason());
				}

				security.login(authParams[0], encodedPassword);
			} else {
				final String token = httpRequest.getParameter("access_token");
				if (!StringUtils.isEmpty(token)) {
					
					final OAuth2AccessToken tokenObject = tokenStore.readAccessToken(token);
					if (tokenObject == null) {
						httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token isn't valid");
					} else if (tokenObject.isExpired()) {
						httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token is expired");
					}
				} else {
					httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Access token is not present");
				}

			}

		}
		
		if (httpResponse.getStatus() == HttpServletResponse.SC_OK) {
			chain.doFilter(request, response);
		}
	}

}
