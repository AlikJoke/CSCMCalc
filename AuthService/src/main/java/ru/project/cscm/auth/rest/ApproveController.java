package ru.project.cscm.auth.rest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ru.project.cscm.auth.core.UserCredentialsApproveHandler;

@RestController
public class ApproveController {

	private static final String PATH = "/credentials/approve";

	@Autowired
	private UserCredentialsApproveHandler approveHandler;

	@PostMapping(PATH)
	@ResponseStatus(HttpStatus.ACCEPTED)
	public ApproveResultResource approve(
			@RequestBody final ApproveCredentialsResource resource) {
		return new ApproveResultResource(
				approveHandler
						.approve(ApproveCredentialsResource.convertToUserCredentials
								.apply(resource)));
	}
	
	@RequestMapping(value = PATH, method = RequestMethod.OPTIONS)
	@ResponseStatus(HttpStatus.OK)
	public void doOptions(final HttpServletRequest request, final HttpServletResponse response) {
		response.setHeader("Allow", "POST, OPTIONS");
		if (request.getHeader("Origin") != null) {
			response.setHeader("Access-Control-Allow-Methods", "POST, OPTIONS");
		}
	}
}
