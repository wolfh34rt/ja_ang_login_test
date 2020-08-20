package com.sixwolfmedia.amaterasu.db;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the person database table.
 * 
 */
@Entity
@Table(name="person")
@NamedQueries({
	@NamedQuery(name="Person.findAll", query="SELECT p FROM Person p"),
	@NamedQuery(name="Person.findPersonByName", query="SELECT p FROM Person p where p.name = :name")
})
public class Person implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="person_id")
	private String personId;

	@Column(name="display_name")
	private String displayName;

	private String name;

	//bi-directional many-to-one association to AuthToken
	@OneToMany(mappedBy="person")
	private List<AuthToken> authTokens;

	//bi-directional many-to-one association to PersonObject
	@OneToMany(mappedBy="person")
	private List<PersonObject> personObjects;

	//bi-directional many-to-one association to SessionData
	@OneToMany(mappedBy="person")
	private List<SessionData> sessionData;

	public Person() {
	}

	public String getPersonId() {
		return this.personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

	public String getDisplayName() {
		return this.displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<AuthToken> getAuthTokens() {
		return this.authTokens;
	}

	public void setAuthTokens(List<AuthToken> authTokens) {
		this.authTokens = authTokens;
	}

	public AuthToken addAuthToken(AuthToken authToken) {
		getAuthTokens().add(authToken);
		authToken.setPerson(this);

		return authToken;
	}

	public AuthToken removeAuthToken(AuthToken authToken) {
		getAuthTokens().remove(authToken);
		authToken.setPerson(null);

		return authToken;
	}

	public List<PersonObject> getPersonObjects() {
		return this.personObjects;
	}

	public void setPersonObjects(List<PersonObject> personObjects) {
		this.personObjects = personObjects;
	}

	public PersonObject addPersonObject(PersonObject personObject) {
		getPersonObjects().add(personObject);
		personObject.setPerson(this);

		return personObject;
	}

	public PersonObject removePersonObject(PersonObject personObject) {
		getPersonObjects().remove(personObject);
		personObject.setPerson(null);

		return personObject;
	}

	public List<SessionData> getSessionData() {
		return this.sessionData;
	}

	public void setSessionData(List<SessionData> sessionData) {
		this.sessionData = sessionData;
	}

	public SessionData addSessionData(SessionData sessionData) {
		getSessionData().add(sessionData);
		sessionData.setPerson(this);

		return sessionData;
	}

	public SessionData removeSessionData(SessionData sessionData) {
		getSessionData().remove(sessionData);
		sessionData.setPerson(null);

		return sessionData;
	}

}