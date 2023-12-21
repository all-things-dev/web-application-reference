package config.rest;

import dev.all_things.reference.common.i18n.InternationalizationService;
import dev.all_things.reference.common.web.rest.model.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Configures global exception handling for REST controllers.
 */
@ControllerAdvice
public class RestExceptionHandler
		extends ResponseEntityExceptionHandler
{
	private static final Logger logger = LogManager.getLogger(RestExceptionHandler.class);

	private final InternationalizationService i18n;

	@Autowired
	public RestExceptionHandler(final InternationalizationService i18n)
	{
		this.i18n = i18n;
	}

	/**
	 * 'Catch all' handler for all the unhandled exceptions.
	 *
	 * @param e instance of {@link Exception} occurred during request processing.
	 * @return appropriate {@link ResponseEntity} for {@link Exception} being handled.
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Response<Void>> processException(final Exception e)
	{
		logger.error(e.getMessage(), e);
		logger.error(e.getMessage(), (Object[]) e.getSuppressed());

		final Response<Void> response = Response.of(HttpStatus.INTERNAL_SERVER_ERROR, this.i18n.getMessage("common.rest.http_500"));

		return ResponseEntity.internalServerError().body(response);
	}

}
