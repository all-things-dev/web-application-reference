package config.security;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "application.security")
public class SecurityProperties
{
	/**
	 * JWT signing key.
	 */
	private String jwtSigningKey;

	public String getJwtSigningKey()
	{
		return this.jwtSigningKey;
	}

	public void setJwtSigningKey(final String jwtSigningKey)
	{
		this.jwtSigningKey = jwtSigningKey;
	}
}
