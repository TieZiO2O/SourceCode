package com.nciae.community.service;

import java.sql.Connection;

public interface DatabaseService {
	public Connection connect() throws Exception;
	public void close() throws Exception;
}
