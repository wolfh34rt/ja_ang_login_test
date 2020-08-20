package com.sixwolfmedia.amaterasu.fireweb.jsonobjects;

public class Password {
	private String pw_hash;

	public String getPw_hash() {
		return pw_hash;
	}

	public void setPw_hash(String pw_hash) {
		this.pw_hash = pw_hash;
	}

	@Override
	public String toString() {
		return "Password [pw_hash=" + pw_hash + "]";
	}
}
