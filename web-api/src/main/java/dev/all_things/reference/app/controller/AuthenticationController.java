package dev.all_things.reference.app.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/authentications")
public class AuthenticationController
{
	private static final Logger logger = LogManager.getLogger(AuthenticationController.class);

	@GetMapping(path = "/hello")
	public ResponseEntity<String> sayHello(@RequestHeader(value = "Authorization", defaultValue = "") final String token)
	{
		logger.info("Token : '{}' ..", token);

		return ResponseEntity.ok("Hello");
	}

	@GetMapping(path = "/hello/{fileName}")
	public ResponseEntity<String> sayHelloWithFile(@RequestHeader(value = "Authorization", defaultValue = "") final String token)
	{
		logger.info("Token : '{}' ..", token);

		return ResponseEntity.ok("Hello");
	}

}
