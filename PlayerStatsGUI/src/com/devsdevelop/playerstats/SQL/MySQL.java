package com.devsdevelop.playerstats.SQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQL {

	private String host = "38.74.1.9";
	private String port = "3306";
	private String database = "s116_devsdevelop";
	private String username = "u116_HD9vQ52vk7";
	private String password = "vN8+r8O9N2CVs!6i^84^Fxr+";
	
	private Connection connection;
	
	
	public boolean isConnected() {
		return (connection == null ? false : true); // if the connection is null return false, if else return true.
	}
	
	public void connect() throws ClassNotFoundException, SQLException{
		if (!isConnected()) {	
			connection = DriverManager.getConnection("jdbc:mysql://" +
			     host + ":" + port + "/" + database + "?useSSL=false",
			     username, password);
		}
	}
	public void disconnect() {
		if (isConnected()) {
			try {
				connection.close();
			}catch (SQLException e){
				e.printStackTrace();
			}
			
		}
	}
	
	public Connection getConnection() {
		return connection;
	}
}
