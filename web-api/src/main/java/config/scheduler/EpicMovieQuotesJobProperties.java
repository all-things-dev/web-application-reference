package config.scheduler;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Scheduler properties for Epic movie quotes job.
 */
@ConfigurationProperties(prefix = "application.job-scheduler.epic-movie-quotes-job")
public class EpicMovieQuotesJobProperties
		extends AbstractJobSchedulerProperties
{
	/**
	 * Path to the source file containing epic movie quotes.
	 */
	private String sourceFile;

	public String getSourceFile()
	{
		return this.sourceFile;
	}

	public void setSourceFile(final String sourceFile)
	{
		this.sourceFile = sourceFile;
	}
}
