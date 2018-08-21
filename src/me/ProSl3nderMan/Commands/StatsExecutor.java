package me.ProSl3nderMan.Commands;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.ProSl3nderMan.Main.Main;

public class StatsExecutor implements CommandExecutor {
	
	public StatsExecutor(Main main){}
	
	HashMap<String, Integer> hm = new HashMap<String, Integer>();
	public static TreeMap<String, Integer> sorted = new TreeMap<String, Integer>();
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player)sender;
		if (args.length == 0) {
			p.sendMessage(ChatColor.DARK_PURPLE + "Your Wins: " + ChatColor.DARK_GREEN + Main.PlayersConfig.getPlayers().getInt("players." + p.getPlayer().getUniqueId() + ".GamesWon"));
			p.sendMessage(ChatColor.DARK_PURPLE + "Do '/stats <player name>' to see someone elses stats.");
			p.sendMessage(ChatColor.DARK_PURPLE + "Do '/stats top' to see the leaderboards.");
			return true;
		} else if (args[0].equalsIgnoreCase("top")) {
	        for(String s : Main.PlayersConfig.getPlayers().getConfigurationSection("players").getKeys(false)) {
	            hm.put(s, Integer.valueOf(Main.PlayersConfig.getPlayers().getInt("players." + s + ".GamesWon")));
	        }
	        List<String> sorted = new ArrayList<String>(hm.keySet());
	        Collections.sort(sorted, new Comparator<String>() {
	        	public int compare(String s1, String s2) {
	        		return Integer.valueOf(hm.get(s2)).compareTo(hm.get(s1));
	        	}
	        });
	        for (int i = 0; i < hm.size(); i++) {
	        	if (i == 10) break; {
	        		p.sendMessage(ChatColor.GREEN + "(" + (i + 1) + "). " + ChatColor.DARK_RED + Main.PlayersConfig.getPlayers().getString("players." + sorted.get(i)+ ".IGN") 
	        				+ ChatColor.DARK_GREEN + " : " + ChatColor.DARK_RED + hm.get(sorted.get(i)) + " wins");
	            }     
	        }
	        p.sendMessage(ChatColor.DARK_GREEN + "Do /stats to see your stats!");
	        p.sendMessage(ChatColor.DARK_GREEN + "Or do /stats <player> to see someone's stats!");
    		return true;
    	} else if (Bukkit.getPlayer(args[0]).getUniqueId() != null) {
    		if (Main.PlayersConfig.getPlayers().getConfigurationSection("players").contains(Bukkit.getPlayer(args[0]).getUniqueId().toString())) {
    			p.sendMessage(ChatColor.DARK_RED + args[0] + "'s"+ ChatColor.DARK_PURPLE + " Wins: " + ChatColor.DARK_GREEN + Main.PlayersConfig.getPlayers().get("players." + Bukkit.getPlayer(args[0]).getUniqueId() + ".GamesWon"));
    			p.sendMessage(ChatColor.DARK_PURPLE + "Do '/stats' to see your stats.");
    			p.sendMessage(ChatColor.DARK_PURPLE + "Do '/stats top' to see the leaderboards.");
    		}
    		return true;
    	} else {
    		p.sendMessage(ChatColor.DARK_RED + "The player " + ChatColor.RED + args[0] + ChatColor.DARK_RED + " does not exist!");
    		return true;
    	}
	}

}
