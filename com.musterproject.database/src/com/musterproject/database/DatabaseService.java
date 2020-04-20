package com.musterproject.database;

import java.net.InetAddress;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.derby.drda.NetworkServerControl;

public class DatabaseService {
	private NetworkServerControl server;
	private Connection conn;
	private final String CONNECTION_URL="jdbc:derby://localhost:3301/db\\MusterDB";
	private Statement STMT = null;

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
	
	public boolean insertPortals() {
		try {
			String sql = "INSERT INTO tbl_portals(portal_name) VALUES ('LinkedIn Portal'), ('Xing Portal')";
			STMT = getConnection().createStatement();
			STMT.execute(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} finally {
			try {
				this.STMT.close();
				this.conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
			
		}
		return true;
	}
	
}
