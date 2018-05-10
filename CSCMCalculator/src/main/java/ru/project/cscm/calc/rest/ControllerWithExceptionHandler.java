package ru.project.cscm.calc.rest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

import ru.project.cscm.calc.base.EventPublishService;

/**
 * Базовый контроллер с обработкой ошибок.
 * 
 * @author Alimurad A. Ramazanov
 *
 */
@ControllerAdvice
public abstract class ControllerWithExceptionHandler {

	@Autowired
	private EventPublishService publishService;
	
	@ExceptionHandler(value = RuntimeException.class)
	public void handle(final RuntimeException e,
			final HttpServletResponse response, final HttpServletRequest request) {
		writeStatusAndMessageIntoResponse(response,
				HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
	}

	@ExceptionHandler(value = HttpClientErrorException.class)
	public void handle(final HttpClientErrorException e,
			final HttpServletResponse response, final HttpServletRequest request) {
		writeStatusAndMessageIntoResponse(response, e.getStatusCode(),
				e.getMessage());
	}

	private static void writeStatusAndMessageIntoResponse(
			@NotNull final HttpServletResponse response,
			@NotNull final HttpStatus status, final String message) {
		response.setStatus(status.value());
		response.setContentType("text/plain; charset=UTF-8");
		try {
			final byte[] message_ = message == null ? new byte[0] : message.getBytes("UTF-8");
			response.setContentLength(message_.length);
			response.getOutputStream().write(message_);
			response.getOutputStream().flush();
		} catch (Exception e) {
			throw new IllegalStateException("Unable to write text response", e);
		}
	}
}
