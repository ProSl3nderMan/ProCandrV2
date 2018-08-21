package me.ProSl3nderMan.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.ProSl3nderMan.Main.Main;
import me.ProSl3nderMan.Managers.OpenCellManager;
import me.ProSl3nderMan.Managers.VariableManager;
import net.md_5.bungee.api.ChatColor;

public class OpenCellExecutor implements CommandExecutor {
	
	public OpenCellExecutor(Main plugin) {}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
	    Player p = (Player)sender;
	    if (args.length == 0) {
	    	p.sendMessage(ChatColor.DARK_RED + "The correct ussage is /open cell.");
	    }
	    if (args[0].equalsIgnoreCase("cell")) {
			int ng = Main.plugin.getConfig().getInt("nextmap");
	    	String game = VariableManager.game.get(p.getName());
	    	if (VariableManager.cop.get(p.getName())) {
	            p.sendMessage(ChatColor.DARK_GREEN + "You have to be a robber to do that command!");
	        } else if (game.equalsIgnoreCase("0")) {
	        	p.sendMessage(ChatColor.DARK_RED + "You have to be in game to do this command!");
	        } else if (SetExecutor.getInt(game) < ng) {
	        	OpenCellManager.OpenCell(p, game);
	        }
	    }
	    return true;
	}
}
