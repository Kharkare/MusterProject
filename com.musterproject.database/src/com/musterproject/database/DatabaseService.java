package com.musterproject.database;

import java.net.InetAddress;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.derby.drda.NetworkServerControl;

import com.musterproject.controller.Links;
import com.musterproject.controller.Portals;

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
	
	public Connection getConnection(){
		Connection conn = null;
		try {
			Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
			conn = DriverManager.getConnection(CONNECTION_URL);
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
	
	public Portals populatePortals() {
		
		Portals rootCategory = getRootCategory();
		Connection conn = getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT * FROM tbl_portals");
			while (rs.next()) {
				Portals portal = new Portals(rs.getString("portal_name"), rootCategory);
				portal.setId(rs.getInt("id"));
				populateLinks(rs.getInt("id"),portal);
				rootCategory.addSubCategories(portal);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return rootCategory;
	}
	
	private void populateLinks(int id, Portals portal) {
		// TODO Auto-generated method stub
		Connection conn = getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT * FROM tbl_portal_links WHERE portal_id="+id);
			while(rs.next()) {
				portal.addLink(new Links(rs.getString("link_name"), rs.getString("link"),portal));
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public boolean saveLink(Links new_link) {
		Connection conn = getConnection();
		Statement stmt = null;
		Portals portal = new_link.getParent();
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate("INSERT INTO tbl_portal_links(portal_id,link_name,link) VALUES ("+portal.getId()+",'"+new_link.getLineName()+"','"+new_link.getLinkURL()+"')");
			
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return true;
		
	}
	
	public boolean deleteLink(Links link) {
		Connection conn = getConnection();
		Statement stmt = null;
		Portals portal = link.getParent();
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate("DELETE FROM tbl_portal_links WHERE link_name='"+link.getLineName()+"' AND portal_id="+portal.getId());
			
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return true;
	}
	
	private Portals getRootCategory() {
		
		Portals rootCategory = new Portals("Information Technology",null);
		return rootCategory;
	}
}
