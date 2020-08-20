package com.sixwolfmedia.amaterasu.db;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the auth_token database table.
 * 
 */
@Entity
@Table(name="auth_token")
@NamedQuery(name="AuthToken.findAll", query="SELECT a FROM AuthToken a")
public class AuthToken implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="auth_data_id")
	private String authDataId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="time_created")
	private Date timeCreated;

	private String token;

	//bi-directional many-to-one association to Person
	@ManyToOne
	@JoinColumn(name="person_id")
	private Person person;

	//bi-directional many-to-one association to PersonObject
	@ManyToOne
	@JoinColumn(name="person_objects_id")
	private PersonObject personObject;

	public AuthToken() {
	}

	public String getAuthDataId() {
		return this.authDataId;
	}

	public void setAuthDataId(String authDataId) {
		this.authDataId = authDataId;
	}

	public Date getTimeCreated() {
		return this.timeCreated;
	}

	public void setTimeCreated(Date timeCreated) {
		this.timeCreated = timeCreated;
	}

	public String getToken() {
		return this.token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Person getPerson() {
		return this.person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public PersonObject getPersonObject() {
		return this.personObject;
	}

	public void setPersonObject(PersonObject personObject) {
		this.personObject = personObject;
	}

}