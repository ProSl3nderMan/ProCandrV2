package me.ProSl3nderMan.Commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.ProSl3nderMan.Main.Main;
import me.ProSl3nderMan.Managers.VariableManager;
import net.md_5.bungee.api.ChatColor;

public class GameExecutor implements CommandExecutor {
	
	public GameExecutor(Main plugin) {}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
	    Player p = (Player)sender;
	    if (p.hasPermission("ProCandr.adminperms")) {
	    	if (args.length == 0){
				p.sendMessage(ChatColor.WHITE + "Correct usage is /game create.");
			}
			
			if (args[0].equalsIgnoreCase("create")) {
				int nm = Main.plugin.getConfig().getInt("nextmap");
				Main.plugin.getConfig().set("map" + nm + ".world", "world");
				Main.plugin.getConfig().set("map" + nm + ".cellamount", 8.0);
				Main.plugin.getConfig().set("nextmap", nm + 1);
				Main.plugin.saveConfig();
				Main.plugin.reloadConfig();

				VariableManager.restarting.put("" + nm , "no");
				VariableManager.playeramount.put("" + nm , 0);
				VariableManager.opencell.put("" + nm , "no");
				VariableManager.copamount.put("" + nm , 0);
				VariableManager.coptimer.put("" + nm , "no");
				List<String> game = VariableManager.players.get("");
				game = new ArrayList<String>();
				VariableManager.players.put("" + nm, game);
				VariableManager.cops.put("" + nm, game);
				VariableManager.copjoinlist.put("" + nm, game);
				p.sendMessage(ChatColor.DARK_GREEN + "The game " + nm + " has just been created! Make sure you go to the");
			}
	    } else {
	    	p.sendMessage(ChatColor.RED + "You do not have permission to this command!");
	    }
		return false;
	}
	
}
