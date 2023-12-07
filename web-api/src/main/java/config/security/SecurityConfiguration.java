package config.security;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.*;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.PathPatternRequestMatcher;
import org.springframework.web.util.pattern.PathPatternParser;

@EnableWebSecurity
@EnableMethodSecurity
@Configuration(proxyBeanMethods = false)
public class SecurityConfiguration
{
	private static final Logger logger = LogManager.getLogger(SecurityConfiguration.class);

	/**
	 * Creates a {@link UserDetailsService} bean to stop Spring Security from logging a default password on the console.
	 *
	 * @return a {@link UserDetailsService} instance.
	 */
	@Bean
	UserDetailsService userDetailsService()
	{
		return _ -> {
			throw new UnsupportedOperationException("UserDetailsService is not implemented yet.");
		};
	}

	/**
	 * Creates a {@link PathPatternRequestMatcher.Builder} bean for MVC request matching.
	 *
	 * @param parser the {@link PathPatternParser} used for parsing.
	 * @return a {@link PathPatternRequestMatcher.Builder} instance.
	 */
	@Bean
	public PathPatternRequestMatcher.Builder matcher(final PathPatternParser parser)
	{
		return PathPatternRequestMatcher.withPathPatternParser(parser);
	}

	@Bean
	public SecurityFilterChain configure(final HttpSecurity http, final PathPatternRequestMatcher.Builder pathPattern)
	{
		// Configuring session management
		http.sessionManagement(SecurityConfiguration::configureSessionManagement);

		// Disabling CSRF protection due to stateless authentication
		http.csrf(AbstractHttpConfigurer::disable);

		// Configuring CORS with the default configuration
		http.cors(Customizer.withDefaults());

		// Configuring HTTP authentication rules and exceptions
		http.authorizeHttpRequests(registry -> configureHttpRequestAuthorization(registry, pathPattern));

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
	private static void configureHttpRequestAuthorization(final AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry registry,
														  final PathPatternRequestMatcher.Builder pathPattern)
	{
		// Allowing only POST requests for user login
		registry.requestMatchers(pathPattern.matcher(HttpMethod.GET, "/authentications/login")).permitAll();

		// Allowing API endpoints to be authenticated
		registry.requestMatchers(pathPattern.matcher("/**")).authenticated();
	}
}
