package me.ProSl3nderMan.Commands;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.ProSl3nderMan.Main.Main;
import me.ProSl3nderMan.Managers.LeaveGameManager;
import me.ProSl3nderMan.Managers.VariableManager;
import net.md_5.bungee.api.ChatColor;

public class CopKickExecutor implements CommandExecutor {
	
	public CopKickExecutor(Main plugin) {}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
	    Player p = (Player)sender;
	    if (args.length == 0) {
			p.sendMessage(ChatColor.DARK_GREEN + "Do /cop kick <cop name> to vote to kick a cop that is in the same game.");
			return true;
	    }
	    
	    
	    
	    if (args[0].equalsIgnoreCase("kick")) {
	    	if (VariableManager.cop.get(p.getName())) {
	    		p.sendMessage(ChatColor.DARK_RED + "You are a cop, you aren't allowed to do this.");
	    		return true;
	    	}
	    	if (VariableManager.game.get(p.getName()).equalsIgnoreCase("0")) {
	    		p.sendMessage(ChatColor.DARK_RED + "You aren't in a game! You must be in a game to do this.");
	    		return true;
	    	}
	    	String game = VariableManager.game.get(p.getName());
	    	int i = 0;
	    	for (String s : VariableManager.cops.keySet()) {
	    		if (!args[1].equalsIgnoreCase(s))
	    			i++;
	    	}
	    	if (i == VariableManager.cops.size()) {
	    		p.sendMessage(ChatColor.RED + "There is no cop named " + args[1] + "!");
	    		return true;
	    	}
	    	if (!game.equalsIgnoreCase(VariableManager.game.get(args[1]))) {
	    		p.sendMessage(ChatColor.RED + "This cop is in " + VariableManager.game.get(args[1]) + ", you are in game " + game);
	    		return true;
	    	}
	    	if (VariableManager.copkickvoters.get(args[1]).size() == 0) {
	    		if (VariableManager.playeramount.get(game) >= 4) {
	    			p.sendMessage(ChatColor.RED + "There must be at least 4 players to do this! There is currently " + VariableManager.playeramount.get(game) + " players in game " + game + ".");
	    			return true;
	    		}
	    		Bukkit.broadcastMessage(ChatColor.GOLD + "Someone has just started a copkick vote for " + ChatColor.RED + args[1] + ChatColor.GOLD + " in game " + ChatColor.RED + game + ChatColor.GOLD + "! Do " 
	    				+ ChatColor.RED + "/cop kick " + args[1] + ChatColor.GOLD + " to add your vote.");
	    		List<String> voters = VariableManager.copkickvoters.get(args[1]);
	    		voters.add(p.getName());
	    		VariableManager.copkickvoters.put(args[1], voters);
	    		voters.clear();
	    		VariableManager.copkickvote.put(args[1], (VariableManager.copkickvote.get(args[1]) + 1));
	    	} else {
	    		if (VariableManager.playeramount.get(game) >= 6) {
	    			p.sendMessage(ChatColor.RED + "There must be at least 6 players to do this! There is currently " + VariableManager.playeramount.get(game) + " players in game " + game + ".");
	    			return true;
	    		}
	    		if (VariableManager.copkickvoters.get(args[1]).contains(p.getName())) {
	    			p.sendMessage(ChatColor.RED + "You have already voted!");
	    			return true;
	    		}
	    		Bukkit.broadcastMessage(ChatColor.GOLD + "Someone just added their vote to copkick " + ChatColor.RED + args[1] + ChatColor.GOLD + "! To kick the cop, " + ChatColor.RED + "75%"
	    				+ ChatColor.GOLD + " of the players in the same game have to vote.");
	    		List<String> voters = VariableManager.copkickvoters.get(args[1]);
	    		voters.add(p.getName());
	    		VariableManager.copkickvoters.put(args[1], voters);
	    		voters.clear();
	    		VariableManager.copkickvote.put(args[1], (VariableManager.copkickvote.get(args[1]) + 1));
	    		if (VariableManager.copkickvote.get(args[1]) == VariableManager.playeramount.get(game) % 0.75D) {
	    			Bukkit.broadcastMessage(ChatColor.YELLOW + "The cop " + ChatColor.GOLD + args[1] + ChatColor.RED + " has been voted to be kicked from game " + ChatColor.GOLD + game + ChatColor.RED + "! Do " 
	    					+ ChatColor.GOLD + "/cr join cops " + ChatColor.RED + "if you are in game " + ChatColor.GOLD + game + ChatColor.RED + "!");
	                Player cop = Bukkit.getPlayer(args[1]);
	                LeaveGameManager.LeaveGame(cop, game);
	    		}
	    	}
	    }
	    return true;
	}
}
