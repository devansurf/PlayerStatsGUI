package com.devsdevelop.playerstats.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.devsdevelop.playerstats.Main;
import com.devsdevelop.playerstats.utils.Utils;

public class SeenCommand implements CommandExecutor{
	
	private Main plugin;
	
	public SeenCommand(Main plugin) {
		this.plugin = plugin;
		plugin.getCommand("seen").setExecutor(this);
	}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
	 String[] args) {
		if (!(sender instanceof Player)) {
			// if not a player
			return true;
		}
		Player p = (Player) sender;
		
		if (args.length == 0) {
			p.sendMessage("Please specify the player name.");
		}
		else {
			Player target = Bukkit.getPlayerExact(args[0]); //assigns the target player
			if (target instanceof Player) {
				if (target.isOnline()) {
					p.sendMessage(Utils.chat("&b" + target.getName() + " &2is currently &aOnline"));
				}
				else {
					p.sendMessage(Utils.chat("&2The player &b" + target.getName() + " &2was last seen: &b" + plugin.data.getDate(target.getUniqueId())));
				}
				
			}
			else {
				p.sendMessage(Utils.chat("&4The specified player does not exist."));
			}
		}

		return false;
	}

}
