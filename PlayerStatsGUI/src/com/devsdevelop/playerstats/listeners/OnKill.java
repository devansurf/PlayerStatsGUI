package com.devsdevelop.playerstats.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import com.devsdevelop.playerstats.Main;

public class OnKill implements Listener{

	private Main plugin;
	
	public OnKill(Main plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onMobKill(EntityDeathEvent e) {
		if (e.getEntity().getKiller() instanceof Player) {
			Player p = (Player)e.getEntity().getKiller();
			plugin.data.addPoints(p.getUniqueId(), 1, "entitykills"); // adds points to the counter
		}
	}
}
