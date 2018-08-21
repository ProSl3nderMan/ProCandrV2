package me.ProSl3nderMan.Commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;

import me.ProSl3nderMan.Events.OnJoin;
import me.ProSl3nderMan.Main.Main;
import me.ProSl3nderMan.Managers.CratesManager;
import me.ProSl3nderMan.Menus.ItemsGUI;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class ShopExecutor implements CommandExecutor, Listener{
	public ShopExecutor (Main main) {}
	
	public static OnJoin OJ = new OnJoin();
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
	    Player p = (Player)sender;
		if (args.length == 0){
			opengui(p);
			return true;
	    }
		return true;		
	}
	
	
	
	
	public static void OpenGUIListener(InventoryClickEvent event) {
		Player p = (Player) event.getWhoClicked();
		event.setCancelled(true);
		InventoryAction a = event.getAction();
		ItemStack is = event.getCurrentItem();
		if (a == InventoryAction.UNKNOWN || a == InventoryAction.NOTHING || is == null || is.getType() == Material.AIR || is.getType() == Material.STAINED_GLASS_PANE) {
			return;
		}
		String display = event.getCurrentItem().getItemMeta().getDisplayName();
		if (display.equalsIgnoreCase(ChatColor.DARK_GRAY + "Back")) {
			p.closeInventory();
			ItemsGUI.openIGUI(p);
			return;
		}
		if (display.equalsIgnoreCase(ChatColor.DARK_PURPLE + "Hats Chest!")) {
			p.closeInventory();
			List<String> items = Main.MenuConfig.getMenu().getStringList("menus.hatsmenu.hats");
			if (Main.ItemsConfig.getItems().contains("players." + p.getUniqueId() + ".hats")) {
				for (String s : Main.ItemsConfig.getItems().getStringList("players." + p.getUniqueId() + ".hats")) {
					items.remove(s);
				}
			}
			if (items.size() == 0) {
				p.sendMessage(ChatColor.RED + "There's no more items available for you.");
				return;
			} else if (Main.econ.getBalance(p) >= 10000) {
				CratesManager.HatsCrate(p);
				Main.econ.withdrawPlayer(p, 10000);
			} else {
				p.sendMessage(ChatColor.RED + "You do not have $10,000!");
			}
		}
		if (display.equalsIgnoreCase(ChatColor.DARK_PURPLE + "Trails Chest!")) {
			p.closeInventory();
			List<String> items = Main.MenuConfig.getMenu().getStringList("menus.trailsmenu.trails");
			if (Main.ItemsConfig.getItems().contains("players." + p.getUniqueId() + ".trails")) {
				for (String s : Main.ItemsConfig.getItems().getStringList("players." + p.getUniqueId() + ".trails")) {
					items.remove(s);
				}
			}
			if (items.size() == 0) {
				p.sendMessage(ChatColor.RED + "There's no more items available for you.");
				return;
			} else if (Main.econ.getBalance(p) >= 10000) {
				CratesManager.TrailsCrate(p);
				Main.econ.withdrawPlayer(p, 10000);
			} else {
				p.sendMessage(ChatColor.RED + "You do not have $10,000!");
			}
		}
		if (display.equalsIgnoreCase(ChatColor.DARK_PURPLE + "Buddys Chest!")) {
			p.closeInventory();
			List<String> items = Main.MenuConfig.getMenu().getStringList("menus.buddymenu.buddys");
			if (Main.ItemsConfig.getItems().contains("players." + p.getUniqueId() + ".buddys")) {
				for (String s : Main.ItemsConfig.getItems().getStringList("players." + p.getUniqueId() + ".buddys")) {
					items.remove(s);
				}
			}
			if (items.size() == 0) {
				p.sendMessage(ChatColor.RED + "There's no more items available for you.");
				return;
			} else if (Main.econ.getBalance(p) >= 10000) {
				CratesManager.BuddysCrate(p);
				Main.econ.withdrawPlayer(p, 10000);
			} else {
				p.sendMessage(ChatColor.RED + "You do not have $10,000!");
			}
		}
		if (display.equalsIgnoreCase(ChatColor.DARK_PURPLE + "Personal Buddy!")) {
			p.closeInventory();
			if (Main.PlayersConfig.getPlayers().getInt("players." + p.getPlayer().getUniqueId() + ".GamesWon") > 99) {
				ItemStack itemSkull = new ItemStack(Material.SKULL_ITEM, 1, (short)3);
				SkullMeta metaSkull = (SkullMeta)itemSkull.getItemMeta();
				metaSkull.setOwner(p.getName());
				itemSkull.setItemMeta(metaSkull);
				Main.VM.skull.put(p.getName() + "/" + p.getName(), itemSkull);
	    		if (Main.ItemsConfig.getItems().contains("players." + p.getUniqueId())) {
	    			if (Main.ItemsConfig.getItems().contains("players." + p.getUniqueId() + ".buddys")) {
	    				List<String> buddys = Main.ItemsConfig.getItems().getStringList("players." + p.getUniqueId() + ".buddys");
	    				buddys.add(p.getName() + "/" + p.getName());
    					Main.ItemsConfig.getItems().set("players." + p.getUniqueId() + ".buddys", buddys);
	    			} else {
	    				List<String> buddys = new ArrayList<String>();
	    				buddys.add(p.getName() + "/" + p.getName());
    					Main.ItemsConfig.getItems().set("players." + p.getUniqueId() + ".buddys", buddys);
	    			}
	    		} else {
    				List<String> buddys = new ArrayList<String>();
    				buddys.add(p.getName() + "/" + p.getName());
					Main.ItemsConfig.getItems().set("players." + p.getUniqueId() + ".buddys", buddys);
	    		}
				Main.ItemsConfig.srItems();
				p.sendMessage(ChatColor.DARK_PURPLE + "Congrats! You now have your own mini you buddy! To activate you buddy, go to the buddy menu in the emerald or do /buddys.");
				Bukkit.getLogger().log(Level.INFO, p.getName() + " just got a personal buddy."); 
			} else
				p.sendMessage(ChatColor.RED + "You do not have 100 wins!");
		}
		return;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static void opengui(Player p) {
		Inventory inv = Bukkit.createInventory(null, 9, ChatColor.DARK_PURPLE + "Shop");
		ItemStack glasspane = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 15);
		for (int i = 0; i < 9; i++)
			inv.setItem(i, glasspane);
		ItemStack item = new ItemStack(Material.CHEST, 1);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.DARK_PURPLE + "Hats Chest!");
		meta.setLore(Arrays.asList(ChatColor.LIGHT_PURPLE + "Open to get a random hat for $10,000!"));
		item.setItemMeta(meta);
		inv.setItem(0, item);
		meta.setDisplayName(ChatColor.DARK_PURPLE + "Trails Chest!");
		meta.setLore(Arrays.asList(ChatColor.LIGHT_PURPLE + "Open to get a random trail for $10,000!"));
		item.setItemMeta(meta);
		inv.setItem(2, item);
		meta.setDisplayName(ChatColor.DARK_PURPLE + "Buddys Chest!");
		meta.setLore(Arrays.asList(ChatColor.LIGHT_PURPLE + "Open to get a random buddy for $10,000!"));
		item.setItemMeta(meta);
		inv.setItem(4, item);
		if (!OJ.hasPersonalBuddy(p)) {
			item = new ItemStack(Material.ARMOR_STAND, 1);
			meta = item.getItemMeta();
			meta.setDisplayName(ChatColor.DARK_PURPLE + "Personal Buddy!");
			meta.setLore(Arrays.asList(ChatColor.LIGHT_PURPLE + "Open to get a mini you buddy!", ChatColor.LIGHT_PURPLE + "(must have 100 wins)"));
			item.setItemMeta(meta);
			inv.setItem(5, item);
		}
		ItemStack arrow = new ItemStack(Material.ARROW, 1); 
		ItemMeta itemmeta = arrow.getItemMeta();
		itemmeta.setDisplayName(ChatColor.DARK_GRAY+ "Back");
		itemmeta.setLore(Arrays.asList(ChatColor.GRAY + "Go back to the main menu!"));
		arrow.setItemMeta(itemmeta);
		inv.setItem(8, arrow);
		p.openInventory(inv);
	}
}
