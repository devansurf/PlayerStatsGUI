package com.devsdevelop.playerstats.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import com.devsdevelop.playerstats.Main;

public class OnBlockPlace implements Listener{
	
	private Main plugin;
	
	public OnBlockPlace(Main plugin) {
		this.plugin = plugin;
	}
	@EventHandler
	public void onPlace(BlockPlaceEvent e) {
		if (e.getPlayer() instanceof Player) {
				Player p = (Player)e.getPlayer();	
				plugin.data.addPoints(p.getUniqueId(), 1, "blocksplaced");
			}
	}
}
