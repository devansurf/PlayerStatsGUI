package com.devsdevelop.playerstats.listeners;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import com.devsdevelop.playerstats.Main;

public class OnJoin implements Listener{
private Main plugin;
	
	public OnJoin(Main plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		plugin.data.createPlayer(p);
		
		Date now = new Date();
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		plugin.data.setDate(p.getUniqueId(), "[" + format.format(now) + "] ");
		
	}
}
