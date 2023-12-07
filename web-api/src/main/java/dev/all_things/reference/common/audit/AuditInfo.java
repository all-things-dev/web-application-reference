package dev.all_things.reference.common.audit;


import java.sql.Timestamp;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Embeddable
public class AuditInfo
{
	@CreationTimestamp
	@Column(name = "created_at", nullable = false, updatable = false)
	protected Timestamp createdAt;

	@UpdateTimestamp
	@Column(name = "created_at", nullable = false)
	protected Timestamp updatedAt;

	@Column(name = "deleted_at")
	protected Timestamp deletedAt;

	@Column(name = "created_by", nullable = false, updatable = false)
	protected Long createdBy;

	@Column(name = "updated_by", nullable = false)
	protected Long updatedBy;

	@Column(name = "deleted_by")
	protected Long deletedBy;

	public Timestamp getCreatedAt()
	{
		return createdAt;
	}

	public void setCreatedAt(final Timestamp createdAt)
	{
		this.createdAt = createdAt;
	}

	public Timestamp getUpdatedAt()
	{
		return updatedAt;
	}

	public void setUpdatedAt(final Timestamp updatedAt)
	{
		this.updatedAt = updatedAt;
	}

	public Timestamp getDeletedAt()
	{
		return deletedAt;
	}

	public void setDeletedAt(final Timestamp deletedAt)
	{
		this.deletedAt = deletedAt;
	}

	public Long getCreatedBy()
	{
		return createdBy;
	}

	public void setCreatedBy(final Long createdBy)
	{
		this.createdBy = createdBy;
	}

	public Long getUpdatedBy()
	{
		return updatedBy;
	}

	public void setUpdatedBy(final Long updatedBy)
	{
		this.updatedBy = updatedBy;
	}

	public Long getDeletedBy()
	{
		return deletedBy;
	}

	public void setDeletedBy(final Long deletedBy)
	{
		this.deletedBy = deletedBy;
	}
}
