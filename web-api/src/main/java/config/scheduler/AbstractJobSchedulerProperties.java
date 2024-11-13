package config.scheduler;

/**
 * Common job scheduler properties.
 */
public abstract class AbstractJobSchedulerProperties
{
	/**
	 * Indicates whether the job scheduler is enabled.
	 */
	protected boolean enabled;

	/**
	 * Cron expression defining the schedule for the job.
	 */
	protected String schedule;

	public boolean isEnabled()
	{
		return this.enabled;
	}

	public void setEnabled(final boolean enabled)
	{
		this.enabled = enabled;
	}

	public String getSchedule()
	{
		return this.schedule;
	}

	public void setSchedule(final String schedule)
	{
		this.schedule = schedule;
	}
}
