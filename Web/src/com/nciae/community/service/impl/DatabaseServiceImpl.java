package com.nciae.community.service.impl;

import java.sql.Connection;
import java.sql.DriverManager;

import com.nciae.community.service.DatabaseService;

public class DatabaseServiceImpl implements DatabaseService {

	private String uri;
	private String driver;
	private String userName;
	private String password;
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	public String getDriver() {
		return driver;
	}
	public void setDriver(String driver) {
		this.driver = driver;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	private Connection conn=null;
	
	private DatabaseServiceImpl(){
	}
	
	@Override
	public Connection connect() throws Exception {
		if(conn==null){
			Class.forName(driver);
			conn=DriverManager.getConnection(uri,userName,password);
		}
		return conn;
	}

	@Override
	public void close() throws Exception {
		if(conn!=null){
			conn.close();
			conn=null;
		}
	}

}
