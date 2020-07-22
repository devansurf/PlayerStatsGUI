package com.devsdevelop.playerstats;

import java.sql.SQLException;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.devsdevelop.playerstats.SQL.MySQL;
import com.devsdevelop.playerstats.SQL.SQLGetter;
import com.devsdevelop.playerstats.commands.PlayerStatsCommand;
import com.devsdevelop.playerstats.listeners.InventoryClick;
import com.devsdevelop.playerstats.listeners.OnBlockPlace;
import com.devsdevelop.playerstats.listeners.OnDamageTaken;
import com.devsdevelop.playerstats.listeners.OnJoin;
import com.devsdevelop.playerstats.listeners.OnKill;

public class Main extends JavaPlugin{
	
	public MySQL SQL;
	public SQLGetter data;
	
	@Override
	public void onEnable() {
		this.SQL = new MySQL();
		this.data = new SQLGetter(this);
		
		try {
			SQL.connect();
		}catch(ClassNotFoundException | SQLException e) {
			// Login info is incorrect
			// Not using a Database
			Bukkit.getLogger().info("Database is not connected");
		}
		
		if (SQL.isConnected()) {
			Bukkit.getLogger().info("Database is connected!");
			data.createTable();
		}
		
		PluginManager pm = this.getServer().getPluginManager();
		
		pm.registerEvents(new OnKill(this), this);
		pm.registerEvents(new OnBlockPlace(this), this);
		pm.registerEvents(new OnDamageTaken(this), this);
		pm.registerEvents(new OnJoin(this), this);
		
		new PlayerStatsCommand(this);
		new InventoryClick(this);
		PlayerStatsGUI.initialize();
	}
	
	@Override
	public void onDisable() {
		SQL.disconnect();
	}

}
