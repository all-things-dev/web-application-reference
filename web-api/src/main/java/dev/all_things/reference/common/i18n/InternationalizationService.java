package dev.all_things.reference.common.i18n;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.LocaleResolver;

/**
 * Provides internationalization of application generated messages.
 * <p>
 * This service supports message resolution using user-provided locale or resolving it automatically from the current context.
 * <p>
 * Locale can be automatically set, in the current context, by the configured {@link LocaleResolver} or
 * by explicitly setting the locale using {@link LocaleContextHolder#setLocale(Locale)}.
 */
@Service
public class InternationalizationService
{
	private final MessageSource messageSource;

	@Autowired
	public InternationalizationService(final MessageSource messageSource)
	{
		this.messageSource = messageSource;
	}

	/**
	 * Resolves the message for provided {code} and {locale}.
	 *
	 * @param code       message code.
	 * @param locale     locale to use for resolving the message.
	 * @param parameters message placeholder parameters, if any.
	 * @return resolved message.
	 */
	public String getMessage(final String code, final Locale locale, final Object... parameters)
	{
		return this.messageSource.getMessage(code, parameters, locale);
	}

	/**
	 * Resolves the message for provided {code}. Locale associated with the current context is used for message resolution.
	 *
	 * @param code       message code.
	 * @param parameters message placeholder parameters, if any.
	 * @return resolved message.
	 */
	public String getMessage(final String code, final Object... parameters)
	{
		return this.getMessage(code, LocaleContextHolder.getLocale(), parameters);
	}
}
