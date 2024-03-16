package dev.all_things.reference.app.model.user;

import jakarta.persistence.*;
import jakarta.xml.bind.annotation.XmlTransient;

import dev.all_things.reference.app.model.AuthenticationRecord;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Entity
@Table(name = "users")
public class User
{
	@Id
	@XmlTransient
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JdbcTypeCode(SqlTypes.JSON)
	@Column(name = "authentication_data")
	private AuthenticationRecord authentication;

	public Long getId()
	{
		return this.id;
	}

	public void setId(final Long id)
	{
		this.id = id;
	}

	public AuthenticationRecord getAuthentication()
	{
		return this.authentication;
	}

	public void setAuthentication(final AuthenticationRecord authentication)
	{
		this.authentication = authentication;
	}
}
