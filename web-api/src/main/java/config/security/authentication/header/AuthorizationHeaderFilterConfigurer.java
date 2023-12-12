package config.security.authentication.header;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Configures {@link AuthorizationHeaderFilter} to enable request authentication with 'Authorization' header.
 */
public class AuthorizationHeaderFilterConfigurer
		extends AbstractHttpConfigurer<AuthorizationHeaderFilterConfigurer, HttpSecurity>
{
	/**
	 * Configures {filter} to be executed before {@link UsernamePasswordAuthenticationFilter}.
	 *
	 * @param http {@link HttpSecurity} instance to modify further by chained methods.
	 */
	@Override
	public void configure(final HttpSecurity http)
	{
		http.addFilterBefore(new AuthorizationHeaderFilter(), UsernamePasswordAuthenticationFilter.class);
	}
}
