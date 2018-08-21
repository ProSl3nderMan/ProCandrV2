package me.ProSl3nderMan.Managers;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;

import me.ProSl3nderMan.Main.Main;
import me.ProSl3nderMan.Menus.ItemsGUI;
import me.ProSl3nderMan.Menus.LobbyGUI;

public class LeaveGameManager {

	public LeaveGameManager(Main plugin) {}
	
	public static void LeaveGame(Player p, String game) {
		if (VariableManager.game.get(p.getName()).equalsIgnoreCase(game)) {
	        for (PotionEffect pt : p.getActivePotionEffects())
	        	p.removePotionEffect(pt.getType());
			if (VariableManager.cop.get(p.getName())) {
				Bukkit.broadcastMessage(ChatColor.DARK_GREEN + "A cop just left in game " + game + "! Do /cr join cops.");
				p.setPlayerListName(ChatColor.WHITE + p.getName());
				VariableManager.cop.put(p.getName() , false);
				int CopsCount1 = VariableManager.copamount.get(game);
				VariableManager.copamount.put(game , (CopsCount1 - 1));
				if (VariableManager.cops.get(game).contains(p.getName())) {
					List<String> names = VariableManager.cops.get(game);
					if (names.contains(p.getName())) {
						names.remove(p.getName());
					}
					VariableManager.cops.put(game , names);
					List<String> coptest = VariableManager.cops.get(game);
					if (coptest == null) {
						coptest = new ArrayList<String>();
						VariableManager.cops.put(game, coptest);
					}
				}
				VariableManager.copkickvote.remove(p.getName());
				VariableManager.copkickvoters.remove(p.getName());
	        }
			Location loc = Main.plugin.getSpawn();
			Main.VM.addLocation(p, loc);
	        p.teleport(loc);
	        for (int j = 0; j < 30; j++) {
	        	p.getInventory().setItem(j, null);
	        }
	        p.getInventory().setHelmet(null);
	        p.getInventory().setChestplate(null);
	        p.getInventory().setLeggings(null);
	        p.getInventory().setBoots(null);
	        p.sendMessage(ChatColor.DARK_GREEN + "You just left the game!");
	        VariableManager.game.put(p.getName() , "0");
	        LobbyGUI.LobbyWatch(p);
	        ItemsGUI.IWatch(p);
	        int PlayerCount1 = VariableManager.playeramount.get(game);
	        VariableManager.playeramount.put(game, (PlayerCount1 - 1));
	        if (VariableManager.players.get(game).contains(p.getName())) {
	        	List<String> names = VariableManager.players.get(game);
	        	if (names.contains(p.getName())) {
	        		names.remove(p.getName());
	        	}
	        	VariableManager.players.put(game, names);
	        }

	    	if (VariableManager.copjoinlist.containsKey(game)) {
	    		List<String> names1 = VariableManager.copjoinlist.get(game);
	    		if (names1.size() == 0) {  } 
	    		else if (names1.contains(p.getName())) {
	        		names1.remove(p.getName());
	        		VariableManager.copjoinlist.put(game, names1);
	        	}
	    	}
		}
	}
}
