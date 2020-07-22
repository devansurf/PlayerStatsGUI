package com.devsdevelop.playerstats.SQL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.devsdevelop.playerstats.Main;

public class SQLGetter {

	private Main plugin;
	private List<String> intTables = new ArrayList<String>();
	private List<String> charTables = new ArrayList<String>();
	
	public SQLGetter (Main plugin) {
		this.plugin = plugin;
		
		intTables.add("entitykills");
		intTables.add("damagetaken");
		intTables.add("blocksplaced");
		
		charTables.add("lastseen");
	}
	
	public void createTable() {
		PreparedStatement ps;
		try {
			for (String s : intTables) {
				ps = plugin.SQL.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS " + s +
						" (NAME VARCHAR (100), UUID VARCHAR(100), VALUE INT(100), PRIMARY KEY (NAME))");
				ps.executeUpdate();
			}	
			for (String s : charTables) {
				ps = plugin.SQL.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS " + s +
						" (NAME VARCHAR (100), UUID VARCHAR(100), VALUE VARCHAR(100), PRIMARY KEY (NAME))");
				ps.executeUpdate();
			}	
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void createPlayer(Player p){
		remove(p.getUniqueId()); // ensures that each time a player joins it resets the stats for testing purposes
		UUID uuid = p.getUniqueId();
		try {
			if (!exists(uuid)) {
				for (String s : intTables) {
					PreparedStatement ps2 = plugin.SQL.getConnection().prepareStatement("INSERT IGNORE INTO " + s +
							" (NAME,UUID) VALUES (?,?)");
					ps2.setString(1, p.getName());
					ps2.setString(2, uuid.toString());
					ps2.executeUpdate();
				}
				for (String s : charTables) {
					PreparedStatement ps2 = plugin.SQL.getConnection().prepareStatement("INSERT IGNORE INTO " + s +
							" (NAME,UUID) VALUES (?,?)");
					ps2.setString(1, p.getName());
					ps2.setString(2, uuid.toString());
					ps2.executeUpdate();
				}
				return;
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public boolean exists(UUID uuid) {  // in the scenario where a new attribute is added, have this function return a list of the missing attributes
		try {
			// only need to check if one exists, unless database was manually altered.
			// change if used professionally!
			PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT * FROM entitykills WHERE UUID=?");
			ps.setString(1, uuid.toString());
			ResultSet results = ps.executeQuery();
			if (results.next() ? true : false);
		
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public void addPoints(UUID uuid, int value, String attribute) { // increments the selected attribute by the given value
		try {
			if (!intTables.contains(attribute)) {
				Bukkit.getLogger().info("The selected action has a undefined attribute in the database.");
				return;
			}
			PreparedStatement ps = plugin.SQL.getConnection().prepareStatement
					("UPDATE " + attribute + " SET VALUE=? WHERE UUID=?");
			ps.setInt(1, getPoints(uuid, attribute) + value);
			ps.setString(2, uuid.toString());
			ps.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public int getPoints(UUID uuid, String attribute) {
		try {
			if (!intTables.contains(attribute)) {
				Bukkit.getLogger().info("The selected action has a undefined attribute in the database.");
				return 0;
			}
			PreparedStatement ps = plugin.SQL.getConnection().prepareStatement
					("SELECT VALUE FROM " + attribute + " WHERE UUID=?");
			ps.setString(1, uuid.toString());
			ResultSet results = ps.executeQuery();
			int value = 0;
			if (results.next()) {
				value = results.getInt("VALUE");
				return value;
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public void setDate(UUID uuid, String dateFormat) { // not in use.
		try {
			PreparedStatement ps = plugin.SQL.getConnection().prepareStatement
					("UPDATE " + "lastseen" + " SET VALUE=? WHERE UUID=?");
			ps.setString(1, dateFormat);
			ps.setString(2, uuid.toString());
			ps.executeUpdate();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	public String getDate(UUID uuid) { // not in use.
		String output = "";
		if (exists(uuid)) {
			try {
				PreparedStatement ps = plugin.SQL.getConnection().prepareStatement
						("SELECT VALUE FROM " + "lastseen" + " WHERE UUID=?");
				ps.setString(1, uuid.toString());
				ResultSet results = ps.executeQuery();
				if (results.next()) {
					output = results.getString("VALUE");
				}
				
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		else
		{
			output = "The selected Player does not exist";
		}
		return output;
	}
	
	public void emptyTable() {
		try {
			PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("TRUNCATE entitykills");
			ps.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void remove(UUID uuid) {
		try {
			for (String s : intTables) {
				PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("DELETE FROM " + s + " WHERE UUID=?");
				ps.setString(1, uuid.toString());
				ps.executeUpdate();
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	

}
