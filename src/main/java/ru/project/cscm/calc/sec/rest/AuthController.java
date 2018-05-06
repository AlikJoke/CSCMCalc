package ru.project.cscm.calc.sec.rest;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;

import ru.project.cscm.calc.rest.ControllerWithExceptionHandler;
import ru.project.cscm.calc.sec.Security;

@Controller
public class AuthController extends ControllerWithExceptionHandler {

	@Autowired
	private Security security;

	@GetMapping(value = SecurityImpl.PATH_LOGIN)
	@ResponseStatus(HttpStatus.OK)
	public void login(final HttpServletRequest request,
			final HttpServletResponse response)
			throws UnsupportedEncodingException {
		final String authHeader = request.getHeader("Authorization");
		final String[] parts = authHeader.split(" ");
		if (parts.length != 2) {
			throw new HttpClientErrorException(HttpStatus.BAD_REQUEST);
		}

		final String args = new String(Base64.getDecoder().decode(
				parts[1].getBytes("UTF-8")));
		final String[] authParams = args.split(":");

		final Cookie cookie = new Cookie("CscmAuth", authHeader);
		response.addCookie(cookie);

		security.login(authParams[0], authParams[1]);
	}

	@GetMapping(value = SecurityImpl.PATH_LOGOUT)
	@ResponseStatus(HttpStatus.OK)
	public void logout() {
		security.logout();
	}

	@RequestMapping(value = { SecurityImpl.PATH_LOGIN, SecurityImpl.PATH_LOGOUT }, method = RequestMethod.OPTIONS)
	@ResponseStatus(HttpStatus.OK)
	public void doOptions(final HttpServletRequest request,
			final HttpServletResponse response) {
		response.setHeader("Allow", "GET, OPTIONS");
		if (request.getHeader("Origin") != null) {
			response.setHeader("Access-Control-Allow-Methods", "GET, OPTIONS");
		}
	}
}
