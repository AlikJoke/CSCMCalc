package ru.project.cscm.calc.sec.rest;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import ru.project.cscm.calc.base.Properties;


/**
 * Перехватчик запросов к приложению для проверки наличия данных об авторизации
 * и схемы запроса.
 * 
 * @see HttpScheme
 * 
 * @author Alimurad A. Ramazanov
 * @since 11.11.2017
 *
 */
@Component
public class RequestInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	private Properties props;

	@Override
	public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler)
			throws Exception {
		final String authHeader = request.getHeader("Authorization");
		final Optional<Cookie> authCookie = Arrays.stream(Optional.ofNullable(request.getCookies()).orElse(new Cookie[0]))
				.filter(cookie -> "CscmAuth".equals(cookie.getName())).findFirst();
		if (StringUtils.isEmpty(authHeader) && !authCookie.isPresent()) {
			throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED);
		}
		/*
		if (!HttpScheme.isHttps(request.getScheme())) {
			throw new HttpClientErrorException(HttpStatus.GONE);
		}
		 */
		final String userAgent = request.getHeader("User-Agent");
		final Collection<String> allowedAgents = Arrays
				.asList(StringUtils.tokenizeToStringArray(props.getProperty("server.included.user-agents"), ",;"));
		if ((StringUtils.isEmpty(userAgent) || !allowedAgents.contains(userAgent))
				&& !allowedAgents.stream().anyMatch(agent -> userAgent.contains(agent))) {
			throw new HttpClientErrorException(HttpStatus.GONE);
		}

		return super.preHandle(request, response, handler);
	}
}