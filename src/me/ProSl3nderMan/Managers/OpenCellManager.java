package me.ProSl3nderMan.Managers;

import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.entity.Player;
import org.bukkit.material.Openable;

import me.ProSl3nderMan.Main.Main;
import net.md_5.bungee.api.ChatColor;

public class OpenCellManager {
	
	public OpenCellManager(Main plugin) {}
	
	public static void OpenCell(Player p, final String game) {
			if (VariableManager.opencell.get(game).equalsIgnoreCase("yes")) {
				for (String i : Main.plugin.getConfig().getConfigurationSection("map" + game + ".doors").getKeys(false)) {
					int x = Main.plugin.getConfig().getInt("map" + game + ".doors." + i + ".x");
					int y = Main.plugin.getConfig().getInt("map" + game + ".doors." + i + ".y");
					int z = Main.plugin.getConfig().getInt("map" + game + ".doors." + i + ".z");
					Block irondoor = Bukkit.getWorld(Main.plugin.getConfig().getString("map" + game + ".world")).getBlockAt(x, y, z);
					BlockState state = irondoor.getState();
					Openable openable = (Openable)state.getData();
					openable.setOpen(true);
					state.setData(state.getData());
					state.update();
				}
				Bukkit.broadcastMessage(ChatColor.GOLD + "Cells have been open in game " + game + "!");
				Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
					public void run() {
						for (String i : Main.plugin.getConfig().getConfigurationSection("map" + game + ".doors").getKeys(false)) {
							int x = Main.plugin.getConfig().getInt("map" + game + ".doors." + i + ".x");
							int y = Main.plugin.getConfig().getInt("map" + game + ".doors." + i + ".y");
							int z = Main.plugin.getConfig().getInt("map" + game + ".doors." + i + ".z");
							Block irondoor = Bukkit.getWorld(Main.plugin.getConfig().getString("map" + game + ".world")).getBlockAt(x, y, z);
							BlockState state = irondoor.getState();
							Openable openable = (Openable)state.getData();
							openable.setOpen(false);
	                  	state.setData(state.getData());
	                  	state.update();
						}
						Bukkit.broadcastMessage(ChatColor.GOLD + "Cells have been closed in game " + game + "!");
					}
				}, 60L);
				VariableManager.opencell.put(game, "no");
			} else if (VariableManager.opencell.get(game).equalsIgnoreCase("no")) {
	        	p.sendMessage(ChatColor.GOLD + "Right now is not the right time to do this!");
	        }
	}
}
