package me.ProSl3nderMan.Managers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import me.ProSl3nderMan.Main.Main;
import me.ProSl3nderMan.Menus.ItemsGUI;
import me.ProSl3nderMan.Menus.LobbyGUI;
import me.ProSl3nderMan.Menus.ModGUI;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.entity.Player;
import org.bukkit.material.MaterialData;
import org.bukkit.material.Openable;
import org.bukkit.potion.PotionEffect;

public class WinGameManager {
	
	public WinGameManager(Main plugin) {}
	
	static ArrayList<String> test = new ArrayList<String>();
	public static HashMap<String, ArrayList<String>> testt = new HashMap<String, ArrayList<String>>();
	
	//type configuration: 1 = robber won, 2 = cop won, and 3 = mod end game.
	public static void processEndGame(final String game, int type, Player p) {
		List<String> players = VariableManager.players.get(game);
		List<String> playersWon = Main.VM.getPlayersWhoWon(game, players);
		for (String myPlayerName : players) {
			Player myPlayer = Bukkit.getPlayer(myPlayerName);
			Location loc = Main.plugin.getSpawn();
			Main.VM.addLocation(myPlayer, loc);
			myPlayer.teleport(loc);
			VariableManager.game.put(myPlayer.getName() , "0");
			Main.econ.depositPlayer(myPlayer, 10.0D);
			myPlayer.getInventory().clear();
	        myPlayer.getInventory().setArmorContents(null);
			LobbyGUI.LobbyWatch(myPlayer);
	        ItemsGUI.IWatch(myPlayer);
	        for (PotionEffect pt : myPlayer.getActivePotionEffects())
	        	myPlayer.removePotionEffect(pt.getType());
	    	if (myPlayer.hasPermission("ProCandr.modperms"))
	    		ModGUI.ModWatch(myPlayer);
        }
		List<String> cops = VariableManager.cops.get(game);
		for (String myPlayerName : cops) {
			Player myPlayer = Bukkit.getPlayer(myPlayerName);
			Location loc = Main.plugin.getSpawn();
			Main.VM.addLocation(myPlayer, loc);
            myPlayer.teleport(loc);
            VariableManager.cop.put(myPlayer.getName(), false);
            VariableManager.game.put(myPlayer.getName(), "0");
            VariableManager.copkickvote.remove(myPlayer);
            VariableManager.copkickvoters.remove(myPlayer);
            Main.econ.depositPlayer(myPlayer, 10.0D);
            myPlayer.setPlayerListName(ChatColor.WHITE + myPlayer.getName());
			myPlayer.getInventory().clear();
	        myPlayer.getInventory().setArmorContents(null);
			LobbyGUI.LobbyWatch(myPlayer);
	        ItemsGUI.IWatch(myPlayer);
	    	if (myPlayer.hasPermission("ProCandr.modperms"))
	    		ModGUI.ModWatch(myPlayer);
        }
        
        
        List<String> reset = VariableManager.players.get("");
    	reset = new ArrayList<String>();
        VariableManager.players.remove(game);
        VariableManager.players.put(game, reset);
        VariableManager.cops.remove(game);
        VariableManager.cops.put(game, reset);
        VariableManager.playeramount.put(game, 0);
        VariableManager.copamount.put(game, 0);
        VariableManager.copjoinlist.remove(game);
        List<String> copjoinlistreset = VariableManager.copjoinlist.get("");
    	copjoinlistreset = new ArrayList<String>();
        VariableManager.copjoinlist.put(game, copjoinlistreset);
        testt.put(game, new ArrayList<String>());
        Main.VM.part.put(game, 1);
        for(String i : Main.plugin.getConfig().getConfigurationSection("map" + game + ".doors").getKeys(false)) {
            int x = Main.plugin.getConfig().getInt("map" + game + ".doors." + i + ".x");
			int y = Main.plugin.getConfig().getInt("map" + game + ".doors." + i + ".y");
			int z = Main.plugin.getConfig().getInt("map" + game + ".doors." + i + ".z");
			Block irondoor = Bukkit.getWorld((Main.plugin.getConfig().getString("map" + game + ".world"))).getBlockAt(x, y, z);
			BlockState state = irondoor.getState();
			Openable openable = (Openable) state.getData();
			openable.setOpen(false);
			state.setData((MaterialData) state.getData());
			state.update();
		}
		VariableManager.restarting.put(game, "yes");
    	VariableManager.coptimer.put(game, "yes");
        Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
        	@Override
            public void run() {
        		VariableManager.restarting.put(game, "no");
            }
        }, 200L);
        Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
            @Override
            public void run() {
            	VariableManager.coptimer.put(game, "no");
            	testt.get(game).addAll(VariableManager.copjoinlist.get(game));
	    		VariableManager.copjoinlist.remove(game);
        		if (testt.get(game).size() == 0) {
        			Bukkit.broadcastMessage(ChatColor.DARK_GREEN + "Do /cr join cops in game " + game + "! It's now open!");
        		} else {
        			Random randomM = new Random();
    	    		String random = (String)testt.get(game).get(randomM.nextInt(testt.get(game).size()));
    	    		Player selected = Bukkit.getPlayer(random);
            		JoinCopsManager.JoinCops(selected, game);
    	    		if (testt.get(game).size() >= 8) {
            			Random randomM2 = new Random();
        	    		String random2 = (String)testt.get(game).get(randomM2.nextInt(testt.get(game).size()));
        	    		if (random2.equalsIgnoreCase(random)) {
        	    			random2 = (String)testt.get(game).get(randomM2.nextInt(testt.get(game).size()));
        	    			if (random2.equalsIgnoreCase(random2)) {
        	    				random2 = (String)testt.get(game).get(randomM2.nextInt(testt.get(game).size()));
        	    				if (random2.equalsIgnoreCase(random2)) {
        	    					random2 = (String)testt.get(game).get(randomM2.nextInt(testt.get(game).size()));
        	    				}
        	    			}
        	    		}
        	    		Player selected2 = Bukkit.getPlayer(random2);
                		JoinCopsManager.JoinCops(selected2, game);
    	    		}
    	    		testt.remove(game);
    	            List<String> reset = VariableManager.copjoinlist.get("");
    	        	reset = new ArrayList<String>();
    	            VariableManager.copjoinlist.put(game, reset);
        		}
            }
        }, 800L);
        Main.PlayersConfig.srPlayers();
        
        if (type == 1)
        	robsWinGame(game, playersWon);
        if (type == 2) {}
        	//copsWinGame(game);
        if (type == 3)
        	Bukkit.broadcastMessage(ChatColor.DARK_PURPLE + p.getName() + " has force ended map " + game + ".");
	}
	
	private static void robsWinGame(final String game, List<String> playersWon) {
        Bukkit.broadcastMessage(getBroadcastMessage(game, playersWon));
        
        Random randomM = new Random();
        for (String player : playersWon) {
        	Player p = Bukkit.getPlayer(player);
            Main.econ.depositPlayer(p, 10.0D);
            p.sendMessage(ChatColor.DARK_GREEN + "You have just recieved 20$ for winning! Good game.");
            int gameswon = Main.PlayersConfig.getPlayers().getInt("players." + p.getPlayer().getUniqueId() + ".GamesWon");
            Main.PlayersConfig.getPlayers().set("players." + p.getPlayer().getUniqueId() + ".GamesWon", (gameswon + 1));
            
            
            int random = randomM.nextInt(100);
            ProcessRewards(p, random);
        }
        
	}

	private static void ProcessRewards(Player p, int random) { 
		if (p.hasPermission("ProCandr.vip+")) {
        	if (random < 20) {
        		CrateGiver(p);
        	} else {
        		p.sendMessage(ChatColor.BOLD + "" + ChatColor.BLUE + "You did not win a Crate, better luck next time!");
        	}
        } else if (p.hasPermission("ProCandr.vip")) {
        	if (random < 15) {
        		CrateGiver(p);
        	} else {
        		p.sendMessage(ChatColor.BOLD + "" + ChatColor.BLUE + "You did not win a Crate, better luck next time!");
        		p.sendMessage(ChatColor.BOLD + "" + ChatColor.YELLOW + "Donate higher to get a higher chance at winning a crate, /donate.");
        	}
        } else {
        	if (random < 10) {
        		CrateGiver(p);
        	} else {
        		p.sendMessage(ChatColor.BOLD + "" + ChatColor.BLUE + "You did not win a Crate, better luck next time!");
        		p.sendMessage(ChatColor.BOLD + "" + ChatColor.YELLOW + "Donate to get a higher chance at winning a crate, /donate.");
        	}
        }
	}

	private static String getBroadcastMessage(String game, List<String> playersWon) {
		String bmessage = "";
		String playerList;
        if (playersWon.size() == 1) {
        	bmessage = ChatColor.DARK_PURPLE + "Congratulations " + ChatColor.DARK_RED + playersWon.get(0) + ChatColor.DARK_PURPLE + "!!! You just won " + ChatColor.DARK_RED + 
				"Cops and Robbers " + ChatColor.DARK_PURPLE + "on map #" + game + ".";
        }
        else if (playersWon.size() == 2) {
        	playerList = ChatColor.DARK_RED + playersWon.get(0) + ChatColor.DARK_PURPLE + " and " + ChatColor.DARK_RED + playersWon.get(1);
        	bmessage = ChatColor.DARK_PURPLE + "Congratulations to " + playerList + ChatColor.DARK_PURPLE + " for winning Cops and Robbers on map #" + game + "!!!";
        } else {
        	playerList = ChatColor.DARK_PURPLE + "and " + ChatColor.DARK_RED + playersWon.get(0);
        	for (int i = 1; i < playersWon.size(); i++)
        		playerList = ChatColor.DARK_RED + playersWon.get(i) + ChatColor.DARK_PURPLE + ", " + playerList;
        	bmessage = ChatColor.DARK_PURPLE + "Congratulations to " + playerList + ChatColor.DARK_PURPLE + " for winning Cops and Robbers on map #" + game + "!!!";
        }
		return bmessage;
	}

	public static void CrateGiver(Player p) {
		List<String> chests = new ArrayList<String>();
		chests.add("hats");
		chests.add("trails");
		chests.add("buddy");
		Random randomMM = new Random();
		String randomm = (String)chests.get(randomMM.nextInt(chests.size()));
		if (randomm.equalsIgnoreCase("hats")) {
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "chests give " + p.getName() + " " + randomm + " 1");
			p.sendMessage(ChatColor.BOLD + "" + ChatColor.GOLD + "You have just won a Hats Crate! View your crates with /chests or by using your Emerald.");
		} if (randomm.equalsIgnoreCase("trails")) {
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "chests give " + p.getName() + " " + randomm + " 1");
			p.sendMessage(ChatColor.BOLD + "" + ChatColor.GOLD + "You have just won a Trails Crate! View your crates with /chests or by using your Emerald.");
		} if (randomm.equalsIgnoreCase("buddys")) {
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "chests give " + p.getName() + " " + randomm + " 1");
			p.sendMessage(ChatColor.BOLD + "" + ChatColor.GOLD + "You have just won a Buddys Crate! View your crates with /chests or by using your Emerald.");
		}
		Bukkit.broadcastMessage(ChatColor.BOLD + "" + ChatColor.YELLOW + p.getName() + " has just won a random crate! You have a 20% chance of getting one by winning a game.");
		Bukkit.broadcastMessage(ChatColor.BOLD + "" + ChatColor.YELLOW + "Donate to get a higher chance at winning a crate, /donate.");
	}
}