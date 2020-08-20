package com.sixwolfmedia.amaterasu.db;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the session_data database table.
 * 
 */
@Entity
@Table(name="session_data")
@NamedQuery(name="SessionData.findAll", query="SELECT s FROM SessionData s")
public class SessionData implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="session_data_id")
	private String sessionDataId;

	//bi-directional many-to-one association to Person
	@ManyToOne
	@JoinColumn(name="person_id")
	private Person person;

	//bi-directional many-to-one association to PersonObject
	@ManyToOne
	@JoinColumn(name="person_objects_id")
	private PersonObject personObject;

	public SessionData() {
	}

	public String getSessionDataId() {
		return this.sessionDataId;
	}

	public void setSessionDataId(String sessionDataId) {
		this.sessionDataId = sessionDataId;
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