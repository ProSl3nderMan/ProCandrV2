package me.ProSl3nderMan.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.ProSl3nderMan.Main.Main;
import net.md_5.bungee.api.ChatColor;

public class StartExecutor implements CommandExecutor 	{

	public StartExecutor(Main plugin) {}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
	    Player p = (Player)sender;
	    if (p.hasPermission("ProCandr.adminperms")) {
	    	if (args.length == 0){
				p.sendMessage(ChatColor.AQUA + "Thank you for choosing ProCandrV2 for your cops and robbers plugin!");
				p.sendMessage(ChatColor.AQUA + "The Author of this plugin is " + ChatColor.DARK_AQUA + "ProSl3nderMan.");
				p.sendMessage(ChatColor.AQUA + "");
				p.sendMessage(ChatColor.AQUA + "Click this link to learn how to set up Cops and Robbers Version 2!: ");
				return true;
	    	}
	    } else {
	    	p.sendMessage(ChatColor.RED + "You do not have permission to use this command!");
	    }
		
		return false;
	}
	
}
