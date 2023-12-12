package dev.all_things.reference.app.web.rest;

import java.sql.*;
import java.util.UUID;
import javax.sql.DataSource;
import jakarta.xml.bind.annotation.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class HelloWorldController
{
	private static final Logger logger = LogManager.getLogger(HelloWorldController.class);

	private final DataSource dataSource;

	@Autowired
	public HelloWorldController(final @Autowired(required = false) DataSource dataSource)
	{
		this.dataSource = dataSource;
	}

	@GetMapping(value = "/greet/{name}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Greeting> greet(final @PathVariable("name") String name)
	{
		if (name.equals("Neo"))
		{
			throw new RuntimeException("Neo is not allowed to use this service.");
		}

		return ResponseEntity.ok(new Greeting(name, new Timestamp(System.currentTimeMillis())));
	}

	@GetMapping(value = "/welcome/{name}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<WelcomeMessage> welcome(final @PathVariable("name") String name) throws SQLException
	{
		try (final Connection connection = this.dataSource.getConnection();
			 final PreparedStatement statement = connection.prepareStatement("SELECT uuid_generate_v4()");
			 final ResultSet result = statement.executeQuery())
		{
			result.next();

			final UUID id = (UUID) result.getObject(1);

			return ResponseEntity.ok(new WelcomeMessage(id, name, new Timestamp(System.currentTimeMillis())));

		}
	}

	/**
	 * A sample record to hold the greeting message.
	 *
	 * @param name      The name of the person to greet.
	 * @param timestamp The timestamp of the greeting.
	 */
	public record Greeting(String name, Timestamp timestamp)
	{

	}

	/**
	 * A sample record to hold the welcome message.
	 *
	 * @param name      The name of the person to greet.
	 * @param timestamp The timestamp of the greeting.
	 */
	@XmlAccessorType(XmlAccessType.FIELD)
	@XmlRootElement(name = "WelcomeMessage")
	public record WelcomeMessage(@XmlElement(name = "id") UUID id, @XmlElement(name = "name") String name,
								 @XmlElement(name = "timestamp") Timestamp timestamp)
	{

	}

}
