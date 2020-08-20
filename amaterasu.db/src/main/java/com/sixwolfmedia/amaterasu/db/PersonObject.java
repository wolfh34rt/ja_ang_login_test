package com.sixwolfmedia.amaterasu.db;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the person_objects database table.
 * 
 */
@Entity
@Table(name="person_objects")
@NamedQueries({
		@NamedQuery(name="PersonObject.findAll", query="SELECT p FROM PersonObject p"),
		@NamedQuery(name="PersonObject.findSecurityObjectByPersonId", query="SELECT p FROM PersonObject p WHERE (FUNCTION('JSON_EXTRACT', p.jsonData, '$.security') IS NOT NULL or FUNCTION('JSON_EXTRACT', p.jsonData, '$.security') <> '') and p.person = :personId")
})
public class PersonObject implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="person_objects_id")
	private String personObjectsId;

	@Lob
	@Column(name="json_data")
	private String jsonData;

	//bi-directional many-to-one association to AuthToken
	@OneToMany(mappedBy="personObject")
	private List<AuthToken> authTokens;

	//bi-directional many-to-one association to Person
	@ManyToOne
	@JoinColumn(name="person_id")
	private Person person;

	//bi-directional many-to-one association to SessionData
	@OneToMany(mappedBy="personObject")
	private List<SessionData> sessionData;

	public PersonObject() {
	}

	public String getPersonObjectsId() {
		return this.personObjectsId;
	}

	public void setPersonObjectsId(String personObjectsId) {
		this.personObjectsId = personObjectsId;
	}

	public String getJsonData() {
		return this.jsonData;
	}

	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}

	public List<AuthToken> getAuthTokens() {
		return this.authTokens;
	}

	public void setAuthTokens(List<AuthToken> authTokens) {
		this.authTokens = authTokens;
	}

	public AuthToken addAuthToken(AuthToken authToken) {
		getAuthTokens().add(authToken);
		authToken.setPersonObject(this);

		return authToken;
	}

	public AuthToken removeAuthToken(AuthToken authToken) {
		getAuthTokens().remove(authToken);
		authToken.setPersonObject(null);

		return authToken;
	}

	public Person getPerson() {
		return this.person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public List<SessionData> getSessionData() {
		return this.sessionData;
	}

	public void setSessionData(List<SessionData> sessionData) {
		this.sessionData = sessionData;
	}

	public SessionData addSessionData(SessionData sessionData) {
		getSessionData().add(sessionData);
		sessionData.setPersonObject(this);

		return sessionData;
	}

	public SessionData removeSessionData(SessionData sessionData) {
		getSessionData().remove(sessionData);
		sessionData.setPersonObject(null);

		return sessionData;
	}

}