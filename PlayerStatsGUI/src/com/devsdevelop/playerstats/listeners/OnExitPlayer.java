package com.devsdevelop.playerstats.listeners;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import com.devsdevelop.playerstats.Main;

public class OnExitPlayer implements Listener{
private Main plugin;
	
	public OnExitPlayer(Main plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onExit(PlayerQuitEvent e) {
		Player p = e.getPlayer();
		
		Date now = new Date();
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		plugin.data.setDate(p.getUniqueId(), "[" + format.format(now) + "] ");
		
	}
}
