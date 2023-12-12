package dev.all_things.reference.common.security.jwt;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

public class JwtAuthentication
		extends PreAuthenticatedAuthenticationToken
{

	public JwtAuthentication(final Object aPrincipal, final Object aCredentials, final Collection<? extends GrantedAuthority> anAuthorities)
	{
		super(aPrincipal, aCredentials, anAuthorities);
	}
}
