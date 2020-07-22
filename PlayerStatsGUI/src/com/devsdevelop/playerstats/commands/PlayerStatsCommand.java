package com.devsdevelop.playerstats.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.devsdevelop.playerstats.Main;
import com.devsdevelop.playerstats.PlayerStatsGUI;


public class PlayerStatsCommand implements CommandExecutor{

	@SuppressWarnings("unused")
	private Main plugin;
	
	public PlayerStatsCommand(Main plugin) {
		this.plugin = plugin;
		plugin.getCommand("stats").setExecutor(this);
	}
	
	public boolean onCommand(CommandSender sender,  Command cmd, String label,
			String[] args) {

		if (!(sender instanceof Player)) {
			// if not a player
			return true;
		}
		
		Player p = (Player) sender;
		
		p.openInventory(PlayerStatsGUI.GUI(p));
		return false;
	}

}
