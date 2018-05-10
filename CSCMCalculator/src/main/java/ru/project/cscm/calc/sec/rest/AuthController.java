package ru.project.cscm.calc.sec.rest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

import ru.project.cscm.calc.rest.ControllerWithExceptionHandler;
import ru.project.cscm.calc.sec.Security;

@Controller
public class AuthController extends ControllerWithExceptionHandler {

	@Autowired
	private Security security;

	@PostMapping(value = SecurityImpl.PATH_LOGOUT)
	@ResponseStatus(HttpStatus.OK)
	public void logout() {
		security.logout();
	}

	@RequestMapping(value = { SecurityImpl.PATH_LOGOUT }, method = RequestMethod.OPTIONS)
	@ResponseStatus(HttpStatus.OK)
	public void doOptions(final HttpServletRequest request,
			final HttpServletResponse response) {
		response.setHeader("Allow", "POST, OPTIONS");
		if (request.getHeader("Origin") != null) {
			response.setHeader("Access-Control-Allow-Methods", "POST, OPTIONS");
		}
	}
}
