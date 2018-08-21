package me.ProSl3nderMan.Commands;

import java.util.List;
import java.util.logging.Level;

import me.ProSl3nderMan.Main.Main;
import me.ProSl3nderMan.Managers.ModTpManager;
import me.ProSl3nderMan.Managers.VariableManager;
import me.ProSl3nderMan.Managers.WinGameManager;
import me.ProSl3nderMan.Menus.InGameGUI;
import me.ProSl3nderMan.Menus.ModGUI;
import net.md_5.bungee.api.ChatColor;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.scheduler.BukkitRunnable;

public class ModExecutor implements CommandExecutor {
	
	public ModExecutor(Main plugin) {}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
	    Player p = (Player)sender;
	    if (p.hasPermission("ProCandr.modperms")) {
	    	if (args.length == 0) {
				p.sendMessage(ChatColor.DARK_GREEN + "Do /mod help for commands.");
				ModGUI.openModGUI(p);
				return true;
	    	}
	    	if (args[0].equalsIgnoreCase("help")) {
	    		p.sendMessage(ChatColor.DARK_RED + "/mod help:" + ChatColor.RED + " Do this command to see all the mod commands.");
	    		p.sendMessage(ChatColor.DARK_RED + "/mod:" + ChatColor.RED + " Do this command to open the GUI for mods");
	    		p.sendMessage(ChatColor.DARK_RED + "/mod tp <game number>:" + ChatColor.RED + " Do this command to tp to a game to moderate.");
	    		p.sendMessage(ChatColor.DARK_RED + "/mod end <game number>:" + ChatColor.RED + " Do this command to end a game.");
	    		p.sendMessage(ChatColor.DARK_RED + "/mod kick <game number>:" + ChatColor.RED + " Do this command to kick a player from a game.");
	    		p.sendMessage(ChatColor.DARK_RED + "/mod ironfist <player name>:" + ChatColor.RED + " Do this command on a player if they have iron fists, it'll remove it from them.");
	    		p.sendMessage(ChatColor.DARK_RED + "/mod opencell:" + ChatColor.RED + " Force open cells broadcast, enabling the time to do /open cell for players.");
	    		p.sendMessage(ChatColor.DARK_RED + "/mod unvanish:" + ChatColor.RED + " Takes away invisiablity.");
	    		p.sendMessage(ChatColor.DARK_RED + "/mod vanish:" + ChatColor.RED + " Gives you invisiablity.");
	    		p.sendMessage(ChatColor.DARK_RED + "/mod copban <player name> <time[1d, 1h]>:" + ChatColor.RED + " Gives you invisiablity.");
	    	}
	    	if (args[0].equalsIgnoreCase("tp")) {
				int ng = Main.plugin.getConfig().getInt("nextmap");
	    		if (SetExecutor.getInt(args[1]) < ng) {
		        	ModTpManager.ModTp(p, args[1]);
		        } else {
		        	p.sendMessage(ChatColor.RED + "The game " + args[1] + " does not exist!");
		        }
	    	}
	    	if (args[0].equalsIgnoreCase("end")) {
				int ng = Main.plugin.getConfig().getInt("nextmap");
	    		if (SetExecutor.getInt(args[1]) < ng) {
					WinGameManager.processEndGame(args[1], 3, p);
		        } else {
		        	p.sendMessage(ChatColor.RED + "The game " + args[1] + " does not exist!");
		        }
	    	}
	    	if (args[0].equalsIgnoreCase("ironfist")) {
	      	  	for (Player modding : Bukkit.getServer().getOnlinePlayers()) {
	      	  		if (modding.getName().equalsIgnoreCase(args[1])) {
	      	  			final Player selected = Bukkit.getPlayer(args[1]);
	      	  			ItemStack shovel = new ItemStack(Material.WOOD_SPADE, 1);
	      	  			final ItemStack steak = new ItemStack(Material.COOKED_BEEF, 10);
	      	  			for (int j = 0; j < 36; j++) {
	      	  				selected.getInventory().setItem(j, shovel);
	      	  			}
	      	  			Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin , new Runnable() {
	      	  				public void run() {
	      	  					for (int j = 0; j < 36; j++) {
	      	  						selected.getInventory().setItem(j, null);
	      	  					}
	      	  					selected.getInventory().setItem(1, steak);
	      	  					InGameGUI.IGWatch(selected);
	      	  				}
	      	  			}, 40L);
	      	  		}
	      	  	}
	        }
	    	if (args[0].equalsIgnoreCase("kick")) {
	    		//
				int ng = Main.plugin.getConfig().getInt("nextmap");
	    		if (SetExecutor.getInt(args[1]) < ng) {
			    	List<String> Players = VariableManager.players.get(args[1]);
			    	Inventory inv = Bukkit.createInventory(null, 36, ChatColor.DARK_AQUA + "Kick Menu");
			    	int i = 10;
			    	for (String s : Players) {
			    		Player player = Bukkit.getPlayer(s);
			    		ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short) SkullType.PLAYER.ordinal());
			               
			    		SkullMeta meta = (SkullMeta) skull.getItemMeta();
			    		meta.setOwner(player.getPlayer().getName());
			    		meta.setDisplayName(ChatColor.RED + player.getPlayer().getName());
			    		skull.setItemMeta(meta);
			    		
			    		inv.setItem(i, skull);
			    		i++;
			    		if (i == 18) {
			    			i = 19;
			    		}
			    	}
			    	p.openInventory(inv);
		    	} else {
		    		p.sendMessage(ChatColor.DARK_RED + "This is not a game number!");
		    	}
	    		//
	    	}
	    	if (args[0].equalsIgnoreCase("opencell")) {
				int ng = Main.plugin.getConfig().getInt("nextmap");
	    		Main.OpenCellNo();
	    		for (int aaa = 1; aaa < ng; aaa++) {
	    			VariableManager.opencell.put(aaa + "" , "yes");
					Bukkit.broadcastMessage(ChatColor.GOLD + "Do " + ChatColor.RED + "/open cell " + ChatColor.GOLD + "in game " + aaa + " to open the cells quickly!");
	    		}
	    	}
	    	if (args[0].equalsIgnoreCase("unvanish")) {
	            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "v " + p.getName());
	    		p.sendMessage(ChatColor.LIGHT_PURPLE + "Unvanished!");
	    	}
	    	if (args[0].equalsIgnoreCase("vanish")) {
	            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "v " + p.getName());
	    		p.sendMessage(ChatColor.LIGHT_PURPLE + "Vanished!");
	    	}
	    	if (args[0].equalsIgnoreCase("copban")) {
	    		String name = "f";
	    		String uuidd = "test";
	    		for (String s : Main.plugin.getConfig().getConfigurationSection("players").getKeys(false)) {
	    			if (Main.plugin.getConfig().getString("players." + s + ".IGN").equalsIgnoreCase(args[1])) {
	    				name = args[1];
	    				uuidd = s;
	    			}
	    		}
	    		final String uuid = uuidd;
	    		if (name.equalsIgnoreCase("f")) {
	    			p.sendMessage(ChatColor.RED + "The player " + args[1] + " does not exist.");
	    			return true;
	    		}
	    		if (args[2].contains("h")) {
	    			String s = args[2].replace("h", "");
	    			if (Integer.parseInt(s) > 23) {
	    				p.sendMessage(ChatColor.RED + "Please do not use hours if the number is 24 or over, instead use days: 1d.");
	    				return true;
	    			}
	    			if (Main.plugin.getConfig().contains("cops.banned.hours." + uuid)) {
	    				p.sendMessage(ChatColor.RED + "This user has already been banned for " + Main.plugin.getConfig().getInt("cops.banned.hours." + uuid) + " hour[s].");
	    				return true;
	    			}
	    			if (Main.plugin.getConfig().contains("cops.banned.days." + uuid)) {
	    				p.sendMessage(ChatColor.RED + "This user has already been banned for " + Main.plugin.getConfig().getInt("cops.banned.days." + uuid) + " day[s].");
	    				return true;
	    			}
	    			Main.plugin.getConfig().set("cops.banned.hours." + uuid, Integer.parseInt(s));
	    			Main.plugin.saveConfig();
	    			Main.plugin.reloadConfig();
	    			Main.VM.cooldown.put(uuid, Integer.parseInt(s));
	    			p.sendMessage(ChatColor.RED + "The player " + args[1] + " has been banned for " + args[2] + " hour[s]. If you made a mistake, do /mod copunban <player name>");
	    			Bukkit.getLogger().log(Level.SEVERE, "Player " + p.getName() + " has just banned " + args[1] + " for " + args[2] + " hour[s].");
	    			new BukkitRunnable() {
	    				public void run() {
	    					Main.VM.cooldown.put(uuid, Main.VM.cooldown.get(uuid) - 1);
	    					if (Main.VM.cooldown.get(uuid) == 0) {
	    						cancel();
	    						Main.VM.cooldown.remove(uuid);
	    						Main.plugin.getConfig().set("cops.banned.hours." + uuid, null);
	    						Main.plugin.saveConfig();
	    						Main.plugin.reloadConfig();
	    						return; 
	    					}
	    				}
	    			}.runTaskTimer(Main.plugin, 72000L , 72000L);
	    			return true;
	    		}
	    		if (args[2].contains("d")) {
	    			String s = args[2].replace("d", "");
	    			if (Main.plugin.getConfig().contains("cops.banned.hours." + uuid)) {
	    				p.sendMessage(ChatColor.RED + "This user has already been banned for " + Main.plugin.getConfig().getInt("cops.banned.hours." + uuid) + " hours.");
	    				return true;
	    			}
	    			if (Main.plugin.getConfig().contains("cops.banned.days." + uuid)) {
	    				p.sendMessage(ChatColor.RED + "This user has already been banned for " + Main.plugin.getConfig().getInt("cops.banned.days." + uuid) + " days.");
	    				return true;
	    			}
	    			Main.plugin.getConfig().set("cops.banned.days." + uuid, Integer.parseInt(s) + 1);
	    			Main.plugin.saveConfig();
	    			Main.plugin.reloadConfig();
	    			p.sendMessage(ChatColor.RED + "The player " + args[1] + " has been banned for " + args[2] + " day[s].");
	    			Bukkit.getLogger().log(Level.SEVERE, "Player " + p.getName() + " has just banned " + args[1] + " for " + args[2] + " day[s].");
	    			return true;
	    		}
	    		return true;
	    	}
	    	if (args[0].equalsIgnoreCase("reload")) {
	    		Main.plugin.saveDefaultConfig();
	    		Main.PlayersConfig.saveDefaultPlayers();
	    		Main.MenuConfig.saveDefaultMenu();
	    		
	    		Main.PlayersConfig.reloadPlayers();
	    		Main.MenuConfig.reloadMenu();
	    		p.sendMessage(ChatColor.GREEN + "Configs have been reloaded!");
	    	}
	    	if (args[0].equalsIgnoreCase("cellreset")) {
	    		for (String s : Main.PlayersConfig.getPlayers().getConfigurationSection("players").getKeys(false)) {
	    			if (Main.PlayersConfig.getPlayers().contains("players." + s + ".cell"))
	    				Main.PlayersConfig.getPlayers().set("players." + s + ".cell", null);
	    		}
	    		Main.PlayersConfig.srPlayers();
	    	}
	    } else {
	    	p.sendMessage(ChatColor.DARK_RED + "You do not have access to this command!");
	    }
	    return true;
	}
}
