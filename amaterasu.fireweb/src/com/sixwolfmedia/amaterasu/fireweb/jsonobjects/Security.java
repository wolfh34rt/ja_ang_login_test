package com.sixwolfmedia.amaterasu.fireweb.jsonobjects;

public class Security {
	private Password security;

	public Password getSecurity() {
		return security;
	}

	public void setSecurity(Password security) {
		this.security = security;
	}

	@Override
	public String toString() {
		return "Security [security=" + security + "]";
	}
}

