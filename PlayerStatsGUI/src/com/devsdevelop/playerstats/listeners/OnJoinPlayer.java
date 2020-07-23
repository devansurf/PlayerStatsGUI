package com.devsdevelop.playerstats.listeners;


import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import com.devsdevelop.playerstats.Main;

public class OnJoinPlayer implements Listener{
private Main plugin;
	
	public OnJoinPlayer(Main plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		plugin.data.createPlayer(p);
		
	}
}
