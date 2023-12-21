package dev.all_things.reference.common.repository;

public record EntityReference<T>(T entity)
{
	public boolean isValid()
	{
		return this.entity != null;
	}
}
