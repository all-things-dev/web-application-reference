package dev.all_things.reference.app.web.rest.authentication;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.xml.bind.annotation.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/authentications")
public class AuthenticationController
{
	private static final Logger logger = LogManager.getLogger(AuthenticationController.class);

	@PostMapping(value = "/login", consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE },
			produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<AuthenticationResponse> login(final @RequestBody @Valid AuthenticationRequest request)
	{
		if (request.username().equals("admin") && request.password().equals("admin"))
		{
			logger.info("User logged in successfully.");
			return ResponseEntity.ok(new AuthenticationResponse("OK", "Login successful."));
		}

		return ResponseEntity.badRequest().body(new AuthenticationResponse("ERROR", "Invalid username or password."));
	}



	@XmlAccessorType(XmlAccessType.FIELD)
	@XmlRootElement(name = "AuthenticationRequest")
	public record AuthenticationRequest(@NotNull @XmlElement(name = "username") String username,
										@NotNull @XmlElement(name = "password") String password)
	{

	}

	@XmlAccessorType(XmlAccessType.FIELD)
	@XmlRootElement(name = "AuthenticationResponse")
	public record AuthenticationResponse(@NotNull @XmlElement(name = "status") String status,
										 @NotNull @XmlElement(name = "message") String message)
	{

	}
}
