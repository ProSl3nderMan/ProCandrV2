package me.ProSl3nderMan.Managers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;

import me.ProSl3nderMan.Main.Main;
import net.md_5.bungee.api.ChatColor;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.scheduler.BukkitRunnable;

public class CratesManager {
	public CratesManager(Main main) {}
	public static HashMap<String, Integer> secs = new HashMap<String, Integer>();
	
	
	
	
	
	
	
	public static void HatsCrate(final Player p) {
		final Inventory inv = Bukkit.createInventory(null, 9, ChatColor.DARK_PURPLE + "Hats Crate");
		
		final ItemStack black = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 15);
		final ItemStack green = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 5);
		
		inv.setItem(0, black);
		inv.setItem(8, black);
		p.openInventory(inv);
		
		secs.put(p.getName(), 0);
		new BukkitRunnable() {
			@Override
			public void run() {
				secs.put(p.getName(), secs.get(p.getName()) + 1);
				int sec = secs.get(p.getName());
				if (sec == 2) {
					inv.setItem(1, black);
					inv.setItem(7, black);
				}
				if (sec == 4) {
					inv.setItem(2, black);
					inv.setItem(6, black);
				}
				if (sec == 6) {
					inv.setItem(3, green);
					inv.setItem(5, green);
					p.updateInventory();
					List<String> items = Main.MenuConfig.getMenu().getStringList("menus.hatsmenu.hats");
					if (Main.ItemsConfig.getItems().contains("players." + p.getUniqueId() + ".hats")) {
						for (String s : Main.ItemsConfig.getItems().getStringList("players." + p.getUniqueId() + ".hats")) {
							items.remove(s);
						}
					}
					Random randomM = new Random();
    	    		String random = (String)items.get(randomM.nextInt(items.size()));
    	    		ItemStack item = new ItemStack(Material.getMaterial(random), 1);
    	    		inv.setItem(4, item);
    	    		p.updateInventory();
    	    		List<String> hats = new ArrayList<String>();
    	    		if (Main.ItemsConfig.getItems().contains("players." + p.getUniqueId() + ".hats"))
    	    			hats = Main.ItemsConfig.getItems().getStringList("players." + p.getUniqueId() + ".hats");
    				hats.add(random);
    				Main.ItemsConfig.getItems().set("players." + p.getUniqueId() + ".hats", hats);
    				Main.ItemsConfig.srItems();
				}
				if (sec == 10) {
    	    		cancel();
    	    		secs.remove(p.getName());
				}
			}
		}.runTaskTimer(Main.plugin, 20L , 20L);
	}
	
	
	
	
	
	
	
	
	
	
	
	public static void TrailsCrate(final Player p) {
		final Inventory inv = Bukkit.createInventory(null, 9, ChatColor.DARK_PURPLE + "Trails Crate");
		
		final ItemStack black = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 15);
		final ItemStack green = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 5);
		p.openInventory(inv);
		inv.setItem(0, black);
		inv.setItem(8, black);
		p.updateInventory();
		secs.put(p.getName(), 0);
		new BukkitRunnable() {
			@Override
			public void run() {
				secs.put(p.getName(), secs.get(p.getName()) + 1);
				int sec = secs.get(p.getName());
				if (sec == 2) {
					inv.setItem(1, black);
					inv.setItem(7, black);
					p.updateInventory();
				}
				if (sec == 4) {
					inv.setItem(2, black);
					inv.setItem(6, black);
					p.updateInventory();
				}
				if (sec == 6) {
					inv.setItem(3, green);
					inv.setItem(5, green);
					p.updateInventory();
					List<String> items = Main.MenuConfig.getMenu().getStringList("menus.trailsmenu.trails");
					if (Main.ItemsConfig.getItems().contains("players." + p.getUniqueId() + ".trails")) {
						for (String s : Main.ItemsConfig.getItems().getStringList("players." + p.getUniqueId() + ".trails")) {
							items.remove(s);
						}
					}
					Random randomM = new Random();
    	    		String random = (String)items.get(randomM.nextInt(items.size()));
    	    		if (random.equalsIgnoreCase("SLIME_BLOCK") || random.equalsIgnoreCase("GLOWSTONE")) {
    	    			random = (String)items.get(randomM.nextInt(items.size()));
        	    		if (random.equalsIgnoreCase("SLIME_BLOCK") || random.equalsIgnoreCase("GLOWSTONE")) {
        	    			random = (String)items.get(randomM.nextInt(items.size()));
            	    		if (random.equalsIgnoreCase("SLIME_BLOCK") || random.equalsIgnoreCase("GLOWSTONE")) {
            	    			random = (String)items.get(randomM.nextInt(items.size()));
                	    		if (random.equalsIgnoreCase("SLIME_BLOCK") || random.equalsIgnoreCase("GLOWSTONE")) {
                	    			random = (String)items.get(randomM.nextInt(items.size()));
                    	    		if (random.equalsIgnoreCase("SLIME_BLOCK") || random.equalsIgnoreCase("GLOWSTONE")) {
                    	    			p.closeInventory();
                    	    			p.sendMessage(ChatColor.GOLD + "Your money has been refunded due to no more trails left.");
                        	    		secs.remove(p.getName());
                        	    		cancel();
                    	    		}
                	    		}
            	    		}
        	    		}
    	    		}
    	    		ItemStack item = new ItemStack(Material.getMaterial(random), 1);
    	    		inv.setItem(4, item);
    	    		p.updateInventory();
    	    		List<String> hats = new ArrayList<String>();
    	    		if (Main.ItemsConfig.getItems().contains("players." + p.getUniqueId() + ".trails"))
    	    			hats = Main.ItemsConfig.getItems().getStringList("players." + p.getUniqueId() + ".trails");
    				hats.add(random);
    				Main.ItemsConfig.getItems().set("players." + p.getUniqueId() + ".trails", hats);
    				Main.ItemsConfig.srItems();
				}
				if (sec == 10) {
    	    		secs.remove(p.getName());
    	    		cancel();
				}
			}
		}.runTaskTimer(Main.plugin, 20L , 20L);
	}
	
	
	
	
	
	
	
	
	
	
	
	public static void BuddysCrate(final Player p) {
		final Inventory inv = Bukkit.createInventory(null, 9, ChatColor.DARK_PURPLE + "Buddys Crate");
		
		final ItemStack black = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 15);
		final ItemStack green = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 5);
		p.openInventory(inv);
		inv.setItem(0, black);
		inv.setItem(8, black);
		p.updateInventory();
		secs.put(p.getName(), 0);
		new BukkitRunnable() {
			@Override
			public void run() {
				secs.put(p.getName(), secs.get(p.getName()) + 1);
				int sec = secs.get(p.getName());
				if (sec == 2) {
					inv.setItem(1, black);
					inv.setItem(7, black);
					p.updateInventory();
				}
				if (sec == 4) {
					inv.setItem(2, black);
					inv.setItem(6, black);
					p.updateInventory();
				}
				if (sec == 6) {
					inv.setItem(3, green);
					inv.setItem(5, green);
					p.updateInventory();
					List<String> items = Main.MenuConfig.getMenu().getStringList("menus.buddymenu.buddys");
					if (Main.ItemsConfig.getItems().contains("players." + p.getUniqueId() + ".buddys")) {
						for (String s : Main.ItemsConfig.getItems().getStringList("players." + p.getUniqueId() + ".buddys")) {
							items.remove(s);
						}
					}
					Random randomM = new Random();
    	    		String random = (String)items.get(randomM.nextInt(items.size()));
    				String[] parts = random.split("/");
					//String skullOwner = parts[0]; //the skin owner's in game name.
					String skullName = parts[1]; //the label of the skin's head.
					if (skullName.contains("_"))
						skullName = skullName.replace("_", " ");
					if ((skullName.equalsIgnoreCase("Master Chief")) || (skullName.equalsIgnoreCase("Boba Fett")) || (skullName.equalsIgnoreCase("Eyeball"))) {
						int i = 0;
						while((skullName.equalsIgnoreCase("Master Chief")) || (skullName.equalsIgnoreCase("Boba Fett")) || (skullName.equalsIgnoreCase("Eyeball"))) {
							random = (String)items.get(randomM.nextInt(items.size()));
							parts = random.split("/");
							//skullOwner = parts[0]; //the skin owner's in game name.
							skullName = parts[1]; //the label of the skin's head.
							if (skullName.contains("_"))
								skullName = skullName.replace("_", " ");
							if (i==10) {
								p.closeInventory();
								p.sendMessage(ChatColor.GOLD + "Your money has been refunded due to no more buddys left.");
								secs.remove(p.getName());
								cancel();
								return;
							}
							i++;
						}
    	    		}
    	    		Bukkit.getLogger().log(Level.FINE, p.getName() + " has just got " + random);
    				ItemStack itemSkull = Main.VM.skull.get(random);
    			    SkullMeta metaSkull = (SkullMeta)itemSkull.getItemMeta();
					metaSkull.setLore(Arrays.asList(new String[] { ChatColor.DARK_GREEN + skullName }));
					itemSkull.setItemMeta(metaSkull);
    	    		inv.setItem(4, itemSkull);
    	    		p.updateInventory();
    	    		List<String> buddys = new ArrayList<String>();
    	    		if (Main.ItemsConfig.getItems().contains("players." + p.getUniqueId() + ".buddys"))
    	    			buddys = Main.ItemsConfig.getItems().getStringList("players." + p.getUniqueId() + ".buddys");
    				buddys.add(random);
    				Main.ItemsConfig.getItems().set("players." + p.getUniqueId() + ".buddys", buddys);
    				Main.ItemsConfig.srItems();
				}
				if (sec == 10) {
    	    		secs.remove(p.getName());
    	    		cancel();
				}
			}
		}.runTaskTimer(Main.plugin, 20L , 20L);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static void GearCrate(final Player p) {
		final Inventory inv = Bukkit.createInventory(null, 9, ChatColor.DARK_PURPLE + "Gear Crate");
		
		final ItemStack black = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 15);
		final ItemStack green = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 5);
		p.openInventory(inv);
		inv.setItem(0, black);
		inv.setItem(8, black);
		p.updateInventory();
		secs.put(p.getName(), 0);
		new BukkitRunnable() {
			@Override
			public void run() {
				secs.put(p.getName(), secs.get(p.getName()) + 1);
				int sec = secs.get(p.getName());
				if (sec == 2) {
					inv.setItem(1, black);
					inv.setItem(7, black);
					p.updateInventory();
				}
				if (sec == 4) {
					inv.setItem(2, black);
					inv.setItem(6, black);
					p.updateInventory();
				}
				if (sec == 6) {
					inv.setItem(3, green);
					inv.setItem(5, green);
					p.updateInventory();
					List<String> items = Main.MenuConfig.getMenu().getStringList("menus.gearsmenu.gears");
					if (Main.ItemsConfig.getItems().contains("players." + p.getUniqueId() + ".gears")) {
						for (String s : Main.ItemsConfig.getItems().getStringList("players." + p.getUniqueId() + ".gears")) {
							items.remove(s);
						}
					}
					Random randomM = new Random();
    	    		String random = (String)items.get(randomM.nextInt(items.size()));
    	    		ItemStack item = new ItemStack(Material.getMaterial(random), 1);
    	    		inv.setItem(4, item);
    	    		p.updateInventory();
    	    		List<String> hats = new ArrayList<String>();
    	    		if (Main.ItemsConfig.getItems().contains("players." + p.getUniqueId() + ".gears"))
    	    			hats = Main.ItemsConfig.getItems().getStringList("players." + p.getUniqueId() + ".gears");
    				hats.add(random);
    				Main.ItemsConfig.getItems().set("players." + p.getUniqueId() + ".gears", hats);
    				Main.ItemsConfig.srItems();
    				Main.econ.withdrawPlayer(p, 10000);
				}
				if (sec == 10) {
    	    		secs.remove(p.getName());
    	    		cancel();
				}
			}
		}.runTaskTimer(Main.plugin, 20L , 20L);
	}
}
