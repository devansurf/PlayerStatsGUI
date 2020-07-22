package com.devsdevelop.playerstats;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.devsdevelop.playerstats.utils.Utils;

public class PlayerStatsGUI {

	public static final int rowMultiplier = 9;
	public static Inventory inv;
	public static String inventory_name;
	public static int inv_boxes = 4*rowMultiplier;
	

	public static void initialize() {
		inventory_name = Utils.chat("&5Player Stats");
		inv = Bukkit.createInventory(null, inv_boxes);
	}
	
	public static Inventory GUI (Player p) {
		Inventory toReturn = Bukkit.createInventory(null, inv_boxes, inventory_name);
		
		Utils.createItem(inv, Material.COBBLESTONE.name(), 1, 0, 
				"&7Blocks Placed", "&2Find out how many blocks you ", "&2have placed!");
		Utils.createItem(inv, Material.DIAMOND_SWORD.name(), 1, 1, 
				"&7Entities Killed", "&2Find out how many entities", "&2you have killed!");
		Utils.createItem(inv, Material.SHIELD.name(), 1, 2, 
				"&7Damage Taken", "&2Find out how much damage ", "&2you have taken!");
		
		
		toReturn.setContents(inv.getContents());
		return toReturn;
	}
	
	public static void clicked(Player p, int slot, ItemStack clicked, Inventory inv, Main plugin) {
		
		if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(Utils.chat("&7Blocks Placed"))){
			p.sendMessage(Utils.chat("&2You have placed " + 
					plugin.data.getPoints(p.getUniqueId(), "blocksplaced") + " blocks!"));
		}
		if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(Utils.chat("&7Entities Killed"))){
			p.sendMessage(Utils.chat("&2You have killed a total of " + 
					plugin.data.getPoints(p.getUniqueId(), "entitykills") + " entities!"));
		}
		if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(Utils.chat("&7Damage Taken"))){
			p.sendMessage(Utils.chat("&2You have taken " + 
					(double)plugin.data.getPoints(p.getUniqueId(), "damagetaken")/10D + " points of damage!"));
		}
	}
}
