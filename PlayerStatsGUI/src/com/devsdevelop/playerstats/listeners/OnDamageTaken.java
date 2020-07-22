package com.devsdevelop.playerstats.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import com.devsdevelop.playerstats.Main;

public class OnDamageTaken implements Listener{
	
	private Main plugin;
	
	public OnDamageTaken(Main plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void playerHitPlayerEvent(EntityDamageByEntityEvent e) {
		
		if (e.getEntity() instanceof Player)
		{
			Player p = (Player)e.getEntity();
			int damage = (int)e.getDamage()*10; // convert double to int
			plugin.data.addPoints(p.getUniqueId(), damage, "damagetaken");
		}
	}
}
