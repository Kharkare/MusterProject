package com.musterproject.database;

import java.net.InetAddress;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.derby.drda.NetworkServerControl;

public class DatabaseService {
	private NetworkServerControl server;
	private Connection conn;
	private final String CONNECTION_URL="jdbc:derby://localhost:3301/db"; 

	public DatabaseService() throws Exception {
		this.server = new NetworkServerControl
				(InetAddress.getByName("localhost"),3301);
		server.start(null);
	}
	
	public Connection getConnection() throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
		this.conn = DriverManager.getConnection(CONNECTION_URL);
		return this.conn;
	}
	
	
}
