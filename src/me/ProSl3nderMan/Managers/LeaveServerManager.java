package me.ProSl3nderMan.Managers;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import me.ProSl3nderMan.Main.Main;
import net.md_5.bungee.api.ChatColor;

public class LeaveServerManager {
	
	public LeaveServerManager(Main plugin) {}
	
	public static void CopLeaving(Player p, String game) {
		int PlayerCount1 = VariableManager.playeramount.get(game);
		VariableManager.playeramount.put(game, (PlayerCount1 - 1));
		List<String> cops = VariableManager.cops.get(game);
		if (cops.contains(p.getName())) {
			cops.remove(p.getName());
			VariableManager.cops.put(game, cops);
		}
		List<String> coptest = VariableManager.cops.get(game);
		if (coptest == null) {
			coptest = new ArrayList<String>();
			VariableManager.cops.put(game, coptest);
		}
		Bukkit.broadcastMessage(ChatColor.DARK_GREEN + "A cop just left in game " + game + "! Do /cr join cops.");
	    int CopsCount1 = VariableManager.copamount.get(game);
	    VariableManager.copamount.put(game, (CopsCount1 - 1));
		VariableManager.cop.put(p.getName(), false);
		VariableManager.game.put(p.getName(), "0");
		Main.VM.removeLocation(p);
		VariableManager.copkickvote.remove(p);
		VariableManager.copkickvoters.remove(p);
	}

	public static void RobberLeaving(Player p, String game) {
		int PlayerCount1 = VariableManager.playeramount.get(game);
        VariableManager.playeramount.put(game, (PlayerCount1 - 1));
    	List<String> names = VariableManager.players.get(game);
    	if (names.contains(p.getName())) {
    		names.remove(p.getName());
    	}
    	VariableManager.players.put(game, names);
    	if (VariableManager.copjoinlist.containsKey(game)) {
    		List<String> names1 = VariableManager.copjoinlist.get(game);
    		if (names1.size() == 0) {} 
    		else if (names1.contains(p.getName())) {
    			names1.remove(p.getName());
    			VariableManager.copjoinlist.put(game, names1);
    		}
    	}
    	VariableManager.game.put(p.getName(), "0");
		Main.VM.removeLocation(p);
	}

}
