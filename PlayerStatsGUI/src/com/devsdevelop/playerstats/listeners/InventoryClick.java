package com.devsdevelop.playerstats.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import com.devsdevelop.playerstats.Main;
import com.devsdevelop.playerstats.PlayerStatsGUI;

public class InventoryClick implements Listener{

	private Main plugin;
	
	public InventoryClick(Main plugin) {
		this.plugin = plugin;
		
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	@EventHandler
	public void onClick(InventoryClickEvent e) {
		String title = e.getView().getTitle();
		
		if (title.equals(PlayerStatsGUI.inventory_name)) {
			e.setCancelled(true);
			
			if (e.getCurrentItem() == null) {
				return;
			}
			PlayerStatsGUI.clicked((Player)e.getWhoClicked(), e.getSlot(),e.getCurrentItem(), e.getInventory(), plugin);
		}
	}
}
