package ru.project.cscm.calc.rest;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ru.project.cscm.calc.base.NotNullOrEmpty;
import ru.project.cscm.calc.base.UserService;
import ru.project.cscm.calc.sec.rest.CscmUserResource;

import com.google.common.collect.Sets;

@RestController
public class UserController extends ControllerWithExceptionHandler {

	private static final String PATH = "/user";

	@Autowired
	private UserService userService;

	@RequestMapping(value = PATH, method = { RequestMethod.PUT, RequestMethod.POST })
	@ResponseStatus(HttpStatus.OK)
	public CscmUserResource modify(@NotNull @RequestBody final CscmUserResource user) {
		userService.load(user.getId(), user.getPassword(), Sets.newHashSet(user.getAppRoles()));
		return new CscmUserResource(userService.getUserByUsername(user.getId()));
	}

	@RequestMapping(value = PATH + "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void doDelete(@NotNullOrEmpty @PathVariable("id") final String id) {
		userService.deactivate(id);
	}

	@GetMapping(value = PATH + "/{id}")
	@ResponseStatus(HttpStatus.OK)
	public CscmUserResource doGet(@NotNullOrEmpty @PathVariable("id") final String id)
			throws UnsupportedEncodingException {
		return new CscmUserResource(userService.getUserByUsername(id));
	}

	@RequestMapping(value = PATH, method = RequestMethod.OPTIONS)
	@ResponseStatus(HttpStatus.OK)
	public void doOptions(final HttpServletRequest request, final HttpServletResponse response) {
		response.setHeader("Allow", "GET, POST, OPTIONS, PUT");
		if (request.getHeader("Origin") != null) {
			response.setHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS, PUT");
		}
	}
}
