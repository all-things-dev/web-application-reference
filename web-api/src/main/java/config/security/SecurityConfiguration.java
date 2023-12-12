package config.security;

import config.security.authentication.header.AuthorizationHeaderFilterConfigurer;
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
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfiguration
{
	private static final Logger logger = LogManager.getLogger(SecurityConfiguration.class);

	/**
	 * Disabling default user details service.
	 *
	 * @return method never returns normally.
	 * @throws UsernameNotFoundException on every invocation.
	 */
	@Bean
	public UserDetailsService userDetailsServiceBean()
	{
		return username -> { throw new UsernameNotFoundException(username); };
	}

	@Bean
	public SecurityFilterChain configure(final HttpSecurity http) throws Exception
	{
		// Disabling basic authentication in favor of JWT authentication
		http.httpBasic(HttpBasicConfigurer::disable);

		// Disabling form login in favor of dedicated authentication controller
		http.formLogin(FormLoginConfigurer::disable);

		// Configuring session management
		http.sessionManagement(SecurityConfiguration::configureSessionManagement);

		// Disabling CSRF protection due to stateless authentication
		http.csrf(CsrfConfigurer::disable);

		// Configuring CORS with default configuration
		http.cors(Customizer.withDefaults());

		// Configuring HTTP authentication rules and exceptions
		http.authorizeHttpRequests(SecurityConfiguration::configureHttpRequestAuthorization);

		// Configuring authentication filter for supporting authentication with 'Authorization' header
		http.with(new AuthorizationHeaderFilterConfigurer(), Customizer.withDefaults());

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
		registry.requestMatchers(HttpMethod.POST, "/authenticate").permitAll();
		registry.requestMatchers(HttpMethod.GET, "/greet/**").permitAll();

		// Configuring all other API endpoints to be authenticated
		registry.anyRequest().authenticated();
	}
}
