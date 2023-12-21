package dev.all_things.reference.common.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

/**
 * Provides common functionality to be used by repositories.
 */
public abstract class BaseRepository<T, Id>
{
	@PersistenceContext
	protected EntityManager db;

	private final Class<T> entityClass;

	protected BaseRepository(final Class<T> entityClass)
	{
		this.entityClass = entityClass;
	}

	/**
	 * Returns the entity associated with provided database identity {id}, i.e. primary key.
	 *
	 * @param id identity of the requested entity.
	 * @return entity reference <T> if associated with the provided identity, {@code null} otherwise.
	 */
	public T findById(final Id id)
	{
		return this.db.find(this.entityClass, id);
	}

	/**
	 * Returns a reference to the entity associated with provided database identity {id}, i.e. primary key.
	 *
	 * @param id identity of the requested entity.
	 * @return entity reference <T> if associated with the provided identity, {@code null} otherwise.
	 */
	public EntityReference<T> findReferenceById(final Id id)
	{
		return new EntityReference<>(this.db.getReference(this.entityClass, id));
	}
}


