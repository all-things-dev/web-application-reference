package dev.all_things.reference.app.model.user;

import java.sql.Timestamp;
import java.util.UUID;
import jakarta.persistence.Table;
import jakarta.persistence.*;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlTransient;

import org.hibernate.annotations.*;
import org.hibernate.generator.EventType;

@Entity
@Table(name = "user_roles")
public class UserRole
{
	@Id
	@XmlTransient
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Generated(event = EventType.INSERT)
	@Column(name = "uid", updatable = false, insertable = false)
	private UUID uid;

	@XmlElement(name = "type")
	@Column(name = "role_type_id")
	private UserRoleType type;

	@Column(name = "name")
	@XmlElement(name = "name")
	private String name;

	@XmlTransient
	@Column(name = "is_active")
	private Boolean isActive;

	@XmlTransient
	@CreationTimestamp
	@Column(name = "creation_timestamp")
	private Timestamp creationTimestamp;

	@XmlTransient
	@UpdateTimestamp
	@Column(name = "update_timestamp")
	private Timestamp updateTimestamp;

	@XmlTransient
	@Column(name = "deletion_timestamp")
	private Timestamp deletionTimestamp;

	@Version
	@XmlTransient
	@Column(name = "version")
	private Integer version;

	public Long getId()
	{
		return this.id;
	}

	public void setId(final Long id)
	{
		this.id = id;
	}

	public UUID getUid()
	{
		return this.uid;
	}

	public void setUid(final UUID uid)
	{
		this.uid = uid;
	}

	public UserRoleType getType()
	{
		return this.type;
	}

	public void setType(final UserRoleType type)
	{
		this.type = type;
	}

	public String getName()
	{
		return this.name;
	}

	public void setName(final String name)
	{
		this.name = name;
	}

	public Boolean getIsActive()
	{
		return this.isActive;
	}

	public void setIsActive(final Boolean isActive)
	{
		this.isActive = isActive;
	}

	public Timestamp getCreationTimestamp()
	{
		return this.creationTimestamp;
	}

	public void setCreationTimestamp(final Timestamp creationTimestamp)
	{
		this.creationTimestamp = creationTimestamp;
	}

	public Timestamp getUpdateTimestamp()
	{
		return this.updateTimestamp;
	}

	public void setUpdateTimestamp(final Timestamp updateTimestamp)
	{
		this.updateTimestamp = updateTimestamp;
	}

	public Timestamp getDeletionTimestamp()
	{
		return this.deletionTimestamp;
	}

	public void setDeletionTimestamp(final Timestamp deletionTimestamp)
	{
		this.deletionTimestamp = deletionTimestamp;
	}

	public Integer getVersion()
	{
		return this.version;
	}

	public void setVersion(final Integer version)
	{
		this.version = version;
	}
}
