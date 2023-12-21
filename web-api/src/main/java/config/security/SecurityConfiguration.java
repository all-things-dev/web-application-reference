package config.security;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.*;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder.SecretKeyFactoryAlgorithm;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Provides web security configuration.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration
{
	private static final Logger logger = LogManager.getLogger(SecurityConfiguration.class);

	/**
	 * Provides {@link PasswordEncoder} instance to be used for password hashing.
	 * <p>
	 * The instance is configured with the following properties -
	 * 	1. Algorithm: PBKDF2 with HMAC SHA512
	 * 	2. Salt: 16 bytes
	 * 	3. Iterations: 310_000
	 *
	 * @return {@link PasswordEncoder} instance.
	 */
	@Bean
	public PasswordEncoder passwordEncoder()
	{
		return new Pbkdf2PasswordEncoder("", 16, 310000, SecretKeyFactoryAlgorithm.PBKDF2WithHmacSHA512);
	}

	@Bean
	public SecurityFilterChain configure(final HttpSecurity http) throws Exception
	{
		// Configuring session management
		http.sessionManagement(SecurityConfiguration::configureSessionManagement);

		// Disabling CSRF protection due to stateless authentication
		http.csrf(AbstractHttpConfigurer::disable);

		// Configuring CORS with default configuration
		http.cors(Customizer.withDefaults());

		// Configuring HTTP authentication rules and exceptions
		http.authorizeHttpRequests(SecurityConfiguration::configureHttpRequestAuthorization);

		logger.info("Security configuration complete ..");

		return http.build();
	}

	/**
	 * Configures session management.
	 *
	 * @param configurer Session management configurer.
	 */
	private static void configureSessionManagement(final SessionManagementConfigurer<HttpSecurity> configurer)
	{
		// Configuring stateless session management
		configurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}

	/**
	 * Configures HTTP authentication rules and exceptions.
	 *
	 * @param registry HTTP authentication rules registry.
	 */
	private static void configureHttpRequestAuthorization(final AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry registry)
	{
		// Allowing only POST requests for user login
		registry.requestMatchers(HttpMethod.POST, "/authentications/login").permitAll();

		// Allowing API endpoints to be authenticated
		registry.requestMatchers("/**").authenticated();
	}
}
