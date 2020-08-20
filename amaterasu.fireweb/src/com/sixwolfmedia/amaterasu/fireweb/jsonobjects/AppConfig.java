package com.sixwolfmedia.amaterasu.fireweb.jsonobjects;

public class AppConfig {
	private String my_sql_connection_string;
	private String my_sql_username;
	private String my_sql_password;
	private String my_sql_driver;
	
	public String getMySqlConnectionString() {
		return my_sql_connection_string;
	}
	
	public void setMySqlConnectionString(String my_sql_connection_string) {
		this.my_sql_connection_string = my_sql_connection_string;
	}
	
	public String getMySqlUsername() {
		return my_sql_username;
	}
	
	public void setMySqlUsername(String my_sql_username) {
		this.my_sql_username = my_sql_username;
	}
	
	public String getMySqlPassword() {
		return my_sql_password;
	}
	
	public void setMySqlPassword(String my_sql_password) {
		this.my_sql_password = my_sql_password;
	}
	
	public String getMySqlDriver() {
		return my_sql_driver;
	}
	
	public void setMySqlDriver(String my_sql_driver) {
		this.my_sql_driver = my_sql_driver;
	}
}
