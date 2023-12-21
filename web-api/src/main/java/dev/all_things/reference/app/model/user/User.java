package dev.all_things.reference.app.model.user;

import java.sql.Timestamp;
import java.util.UUID;
import jakarta.persistence.*;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlTransient;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "users")
public class User
{
	@Id
	@XmlTransient
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@XmlElement(name = "uid")
	@Column(name = "uid", updatable = false)
	private UUID uid;

	@XmlTransient
	@Column(name = "role_id")
	private Long roleId;

	@Column(name = "first_name")
	@XmlElement(name = "firstName")
	private String firstName;

	@Column(name = "last_name")
	@XmlElement(name = "lastName")
	private String lastName;

	@Column(name = "display_name")
	@XmlElement(name = "displayName")
	private String displayName;

	@Column(name = "email")
	@XmlElement(name = "email")
	private String email;

	@Column(name = "contact_number")
	@XmlElement(name = "contactNumber")
	private String contactNumber;

	@Column(name = "user_status_id")
	private UserStatus status;

	@XmlTransient
	@Column(name = "created_by")
	private Long createdBy;

	@XmlTransient
	@Column(name = "updated_by")
	private Long updatedBy;

	@XmlTransient
	@Column(name = "deleted_by")
	private Long deletedBy;

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
	@Transient
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

	public Long getRoleId()
	{
		return this.roleId;
	}

	public void setRoleId(final Long roleId)
	{
		this.roleId = roleId;
	}

	public String getFirstName()
	{
		return this.firstName;
	}

	public void setFirstName(final String firstName)
	{
		this.firstName = firstName;
	}

	public String getLastName()
	{
		return this.lastName;
	}

	public void setLastName(final String lastName)
	{
		this.lastName = lastName;
	}

	public String getDisplayName()
	{
		return this.displayName;
	}

	public void setDisplayName(final String displayName)
	{
		this.displayName = displayName;
	}

	public String getEmail()
	{
		return this.email;
	}

	public void setEmail(final String email)
	{
		this.email = email;
	}

	public String getContactNumber()
	{
		return this.contactNumber;
	}

	public void setContactNumber(final String contactNumber)
	{
		this.contactNumber = contactNumber;
	}

	public UserStatus getStatus()
	{
		return this.status;
	}

	public void setStatus(final UserStatus status)
	{
		this.status = status;
	}

	public Long getCreatedBy()
	{
		return this.createdBy;
	}

	public void setCreatedBy(final Long createdBy)
	{
		this.createdBy = createdBy;
	}

	public Long getUpdatedBy()
	{
		return this.updatedBy;
	}

	public void setUpdatedBy(final Long updatedBy)
	{
		this.updatedBy = updatedBy;
	}

	public Long getDeletedBy()
	{
		return this.deletedBy;
	}

	public void setDeletedBy(final Long deletedBy)
	{
		this.deletedBy = deletedBy;
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
