package dev.all_things.reference.common.security.key;

import java.util.Collection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

public class ApiKeyAuthentication
		extends PreAuthenticatedAuthenticationToken
{
	private static final Logger logger = LogManager.getLogger(ApiKeyAuthentication.class);

	public ApiKeyAuthentication(final Object aPrincipal, final Object aCredentials, final Collection<? extends GrantedAuthority> anAuthorities)
	{
		super(aPrincipal, aCredentials, anAuthorities);
	}
}
