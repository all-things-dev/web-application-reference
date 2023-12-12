package config.security.authentication.header;

import java.io.IOException;
import java.util.Collections;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import dev.all_things.reference.common.security.jwt.JwtAuthentication;
import dev.all_things.reference.common.security.key.ApiKeyAuthentication;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * Implements 'Authorization' header-based authentication.
 */
public class AuthorizationHeaderFilter
		extends OncePerRequestFilter
{
	private static final Logger logger = LogManager.getLogger(AuthorizationHeaderFilter.class);

	private static final int MINIMUM_TOKEN_LENGTH = 6;

	/**
	 * Extracts authentication token from 'Authorization' header and sets it to {@link SecurityContextHolder}.
	 * <p>
	 * Security context is cleared if authentication token is not present or is invalid.
	 *
	 * @param request     current HTTP request.
	 * @param response    current HTTP response.
	 * @param filterChain current filter chain.
	 * @throws ServletException when subsequent calls to {@link FilterChain#doFilter(ServletRequest, ServletResponse)} fails.
	 * @throws IOException      when subsequent calls to {@link FilterChain#doFilter(ServletRequest, ServletResponse)} fails.
	 */
	@Override
	protected void doFilterInternal(final HttpServletRequest request, final HttpServletResponse response,
									final FilterChain filterChain) throws ServletException, IOException
	{
		final Authentication authentication = authenticate(request);

		if (authentication == null)
		{
			// No valid authentication information is provided
			SecurityContextHolder.clearContext();
		}
		else
		{
			// Updating security context with authentication information
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}

		// Passing request to the next filter in the chain
		filterChain.doFilter(request, response);
	}

	/**
	 * Extracts authentication token from 'Authorization' header and creates {@link Authentication}
	 * instance based on the token type.
	 *
	 * @param request current HTTP request.
	 * @return a valid {@link Authentication} instance or {@code null} if no valid authentication information is provided.
	 */
	private static Authentication authenticate(final HttpServletRequest request)
	{
		final String header = StringUtils.defaultString(request.getHeader(HttpHeaders.AUTHORIZATION));

		if (header.isBlank() || header.length() < MINIMUM_TOKEN_LENGTH)
		{
			return null;
		}

		final String type = header.substring(0, 6);
		final String authorization = header.substring(6);

		return switch (type)
		{
			case "Bearer" -> createJwtAuthentication(authorization);
			case "ApiKey" -> createApiKeyAuthentication(authorization);

			default -> null;
		};
	}

	private static JwtAuthentication createJwtAuthentication(final String token)
	{
		return new JwtAuthentication(null, token, Collections.emptyList());
	}

	private static Authentication createApiKeyAuthentication(final String apiKey)
	{
		return new ApiKeyAuthentication(null, apiKey, Collections.emptyList());
	}
}