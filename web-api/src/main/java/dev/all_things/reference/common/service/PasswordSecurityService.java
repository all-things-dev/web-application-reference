package dev.all_things.reference.common.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Facilitates password security functions like hashing and matching.
 */
@Service
public class PasswordSecurityService
{
	private static final Logger logger = LogManager.getLogger(PasswordSecurityService.class);

	private final PasswordEncoder passwordEncoder;

	@Autowired
	public PasswordSecurityService(final PasswordEncoder passwordEncoder)
	{
		this.passwordEncoder = passwordEncoder;
	}

	/**
	 * Hashes the given password using configured {@link PasswordEncoder}.
	 *
	 * @param password plain text password to be hashed.
	 * @return hashed password.
	 */
	public String hash(final String password)
	{
		return this.passwordEncoder.encode(password);
	}

	/**
	 * Matches the raw password against the encoded password using the configured {@link PasswordEncoder}.
	 *
	 * @param password     plain text password to be matched with provided {passwordHash}.
	 * @param passwordHash stored hash of the actual password.
	 * @return {@code true} if {password} matches the {passwordHash}, {@code false} otherwise.
	 */
	public boolean matches(final String password, final String passwordHash)
	{
		return this.passwordEncoder.matches(password, passwordHash);
	}
}
