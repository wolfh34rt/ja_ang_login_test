package com.sixwolfmedia.amaterasu.fireweb.jsonobjects;

public class AuthorizationTokens {
	private String person_id;
	private String access_token;
	private String refresh_token;
	
	public String getPerson_id() {
		return person_id;
	}

	public void setPerson_id(String person_id) {
		this.person_id = person_id;
	}

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public String getRefresh_token() {
		return refresh_token;
	}

	public void setRefresh_token(String refresh_token) {
		this.refresh_token = refresh_token;
	}

	@Override
	public String toString() {
		return "AuthorizationTokens [person_id=" + person_id + ", access_token=" + access_token + ", refresh_token="
				+ refresh_token + "]";
	}
}
