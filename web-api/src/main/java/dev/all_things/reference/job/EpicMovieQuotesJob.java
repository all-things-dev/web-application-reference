package dev.all_things.reference.job;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import config.scheduler.EpicMovieQuotesJobProperties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * Sample job for demonstrating basic setup required for a Quartz job.
 * <p>
 * This job simply quotes epic lines from movies.
 */
public class EpicMovieQuotesJob
		extends QuartzJobBean
{
	private static final Logger logger = LogManager.getLogger(EpicMovieQuotesJob.class);

	private final EpicMovieQuotesJobProperties properties;
	private final Map<String, List<String>> quotes = new ConcurrentHashMap<>();

	public EpicMovieQuotesJob(final EpicMovieQuotesJobProperties properties)
	{
		this.properties = properties;

		// Initializing epic quotes
		this.initialize();
	}

	/**
	 * Initializes epic movie quotes from the source file.
	 */
	protected void initialize()
	{
		final URL url = EpicMovieQuotesJob.class.getClassLoader().getResource(this.properties.getSourceFile());

		Objects.requireNonNull(url, "Source file is required");

		try
		{
			final List<String> lines = Files.readAllLines(Paths.get(url.toURI()));

			lines.replaceAll(String::strip);
			lines.removeIf(String::isBlank);

			for (final String line : lines)
			{
				final int index = line.indexOf(":");

				if (index == -1)
				{
					continue;
				}

				this.quotes.computeIfAbsent(line.substring(0, index).strip(), key -> new ArrayList<>())
						   .add(line.substring(index + 1).strip().replace("\\\\", System.lineSeparator()));
			}
		}
		catch (final IOException | URISyntaxException e)
		{
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	/**
	 *
	 * @param context
	 */
	@Override
	protected void executeInternal(final JobExecutionContext context)
	{
		final Random random = new Random(System.nanoTime());
		final String author = List.copyOf(this.quotes.keySet()).get(random.nextInt(this.quotes.size()));
		final List<String> authorsQuotes = this.quotes.get(author);

		logger.info("{}{}{}- {}", System.lineSeparator(), authorsQuotes.get(random.nextInt(authorsQuotes.size())), System.lineSeparator(), author);
	}
}
