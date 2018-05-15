package ru.project.cscm.calc.rest;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import ru.project.cscm.calc.base.Properties;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class OptionsCorsFilter implements Filter {
	
	@Autowired
	private Properties props;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		final HttpServletResponse response = (HttpServletResponse) res;
        	final HttpServletRequest request = (HttpServletRequest) req;
		final String requestOrigin = request.getHeader("Access-Control-Allow-Origin");
		final List<String> allowedOrigins = Arrays.asList(StringUtils.tokenizeToStringArray(props.getProperty("allowedOrigins"), ",;")));
		if (allowedOrigins.contains(requestOrigin)) {
		    response.setHeader("Access-Control-Allow-Origin", requestOrigin);
		    response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT");
		    response.setHeader("Access-Control-Max-Age", "3600");
		    response.setHeader("Access-Control-Allow-Headers", "x-requested-with, authorization");
		    response.setHeader("Access-Control-Allow-Credentials", "true");
		}

		if (RequestMethod.OPTIONS.name().equalsIgnoreCase(request.getMethod())) {
		    response.setStatus(HttpServletResponse.SC_OK);
		} else {
		    chain.doFilter(req, res);
		}
	}

	@Override
	public void destroy() {
		
	}

}
