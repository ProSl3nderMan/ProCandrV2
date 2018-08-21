package me.ProSl3nderMan.Commands;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import me.ProSl3nderMan.Main.Main;
import me.ProSl3nderMan.Managers.JoinCopsManager;
import me.ProSl3nderMan.Managers.JoinGameManager;
import me.ProSl3nderMan.Managers.LeaveGameManager;
import me.ProSl3nderMan.Managers.VariableManager;

public class CrExecutor implements CommandExecutor {
	
	public CrExecutor(Main plugin) {}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
	    Player p = (Player)sender;
		if (args.length == 0){
			p.sendMessage("Unknown command. Type /help for help.");
			return true;
	    }
		
	    if (args[0].equalsIgnoreCase("join")) {
	    	if (args.length == 1) {
	    		p.sendMessage(ChatColor.DARK_GREEN + "To join a game, do '/cr join <game number>'. To join the cops, do '/cr join cops'");
	    	}
			int ng = Main.plugin.getConfig().getInt("nextmap");
		    if (args[1].equalsIgnoreCase("cops")) {
		    	String game = VariableManager.game.get(p.getName());
		    	if (Main.plugin.getConfig().contains("cops.banned.hours." + p.getUniqueId().toString())) {
		    		p.sendMessage(ChatColor.RED + "You have been banned for " + Main.plugin.getConfig().getInt("cops.banned.hours." + p.getUniqueId().toString()) + " more hour[s]. If you think this is a mistake, go to " 
		    				+ "http://dragonsdoom.net/forum/index.php?forums/appeal-a-ban.20/ and post a thread including how long you were banned. Thank you.");
		    		return true;
		    	}
		    	if (Main.plugin.getConfig().contains("cops.banned.days." + p.getUniqueId().toString())) {
		    		p.sendMessage(ChatColor.RED + "You have been banned for " + Main.plugin.getConfig().getInt("cops.banned.days." + p.getUniqueId().toString()) + " more day[s]. If you think this is a mistake, go to " 
		    				+ "http://dragonsdoom.net/forum/index.php?forums/appeal-a-ban.20/ and post a thread including how long you were banned. Thank you.");
		    		return true;
		    	}
		    	if (game.equalsIgnoreCase("0")) {
		    		p.sendMessage(ChatColor.RED + "You have to be in game to join the cops! Do /cr join [game number] to join a game.");
		    	} else {
		    		if (VariableManager.cop.get(p.getName())) {
						p.sendMessage(ChatColor.DARK_GREEN + "You are already a cop!");
					} else if (VariableManager.copamount.get(game) == 2) {
						p.sendMessage(ChatColor.DARK_GREEN + "There is already 2 cops!");
					} else if ((VariableManager.copamount.get(game) == 1 && (VariableManager.playeramount.get(game) < 8))) {
						p.sendMessage(ChatColor.DARK_GREEN + "There must be at least 8 people in the game for there to be 2 cops!");
					} else if ((VariableManager.copamount.get(game) == 1 && VariableManager.playeramount.get(game) >= 8) || (VariableManager.copamount.get(game) == 0)) {
						JoinCopsManager.JoinCops(p, game);
					}
		    	}
		    	return true;
		    }
		    if (!VariableManager.game.get(p.getName()).equalsIgnoreCase("0")) {
	    		p.sendMessage(ChatColor.DARK_GREEN + "You are already in a game! Do /cr leave to join another game.");
	        } else if (SetExecutor.getInt(args[1]) >= ng) {
				p.sendMessage(ChatColor.DARK_GREEN + "This is not a game number! Do '/cr place for a list of game numbers.");
	        } else if (VariableManager.restarting.get(args[1]).equalsIgnoreCase("yes")) {
	        	p.sendMessage(ChatColor.DARK_RED + "Game " + args[1] + " is restarting!");
	        } else if (VariableManager.playeramount.get(args[1]) == 16) {
	        	p.sendMessage(ChatColor.RED + "This game is full! Do /cr place to find a joinable game.");
	        } else if (SetExecutor.getInt(args[1]) < ng) {
	        	JoinGameManager.JoinGame(p, args[1]);
	        }
	    	return true;
	    }
	    
	    if (args[0].equalsIgnoreCase("leave")) {
	    	String game = VariableManager.game.get(p.getName());
	    	if (game.equalsIgnoreCase("0")) {
	    		p.sendMessage(ChatColor.RED + "You're not in a game! Do /cr join [game number] to join a game.");
	    	} else {
	    		LeaveGameManager.LeaveGame(p, game);
	    	}
	    	return true;
	    }
	    
	    if (args[0].equalsIgnoreCase("place")) {
	    	for (int aaa = 1; aaa < Main.plugin.getConfig().getInt("nextmap"); aaa++){
	    		int PlayerCount = VariableManager.playeramount.get("" + aaa);
	    		p.sendMessage(ChatColor.DARK_PURPLE + "Game #" + aaa + " " + PlayerCount + "/16");
	    	}
	    	return true;
	    }
	    
	    if (args[0].equalsIgnoreCase("cops")) {
	    	if (VariableManager.game.get(p.getName()).equalsIgnoreCase("0")) {
	    		p.sendMessage(ChatColor.DARK_RED + "You must be in game to do this command!");
	    	} 
	    	if (!VariableManager.game.get(p.getName()).equalsIgnoreCase("0")) {
	    		Location loc = p.getLocation();
	    		double radius = 1000D;
				int check = 0;
	    		for (Entity e : p.getWorld().getEntities()) {
	    			if (e instanceof Player) {
	    				if (p.getLocation().distance(loc) <= radius && VariableManager.cop.get(e.getName())) {
		    				p.sendMessage(ChatColor.DARK_PURPLE + "Cop: " + ChatColor.LIGHT_PURPLE + e.getName() + ChatColor.DARK_PURPLE + ".");
		    				check++;
	    				}
	    			}
	    		}
	    		if (check==0)
	    			p.sendMessage(ChatColor.DARK_GREEN + "There is no cop yet!");
	    	}
	    	return true;
	    }
	    return true;
	}
}
