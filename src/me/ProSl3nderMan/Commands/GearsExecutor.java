package me.ProSl3nderMan.Commands;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.ProSl3nderMan.Main.Main;
import me.ProSl3nderMan.Managers.VariableManager;
import me.ProSl3nderMan.Menus.ItemsGUI;

public class GearsExecutor implements CommandExecutor {
	public GearsExecutor(Main main) {}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
	    Player p = (Player)sender;
		if (args.length == 0){
			p.sendMessage(ChatColor.GREEN + "Coming soon!");
			//opengui(p);
			return true;
	    }
		if (args[0].equalsIgnoreCase("give") && p.isOp()) {
			List<String> trails = Main.ItemsConfig.getItems().getStringList("players." + Bukkit.getPlayer(args[1]).getUniqueId() + ".gears");
			trails.add(args[2]);
			Main.ItemsConfig.getItems().set("players." + Bukkit.getPlayer(args[1]).getUniqueId() + ".gears", trails);
			Main.ItemsConfig.srItems();
			p.sendMessage("Player " + args[1] + " now has the trail " + args[2] + "!");
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
		if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.DARK_GRAY + "Back")) {
			p.closeInventory();
			ItemsGUI.openIGUI(p);
			return;
		}
		if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.RED + "Unequip")) {
			//Method to remove the gear.
			p.sendMessage(ChatColor.GREEN + "Gear unequiped!");
			p.closeInventory();
			return;
		}
		if (!event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GREEN + "Equip")) {
			p.sendMessage(ChatColor.RED + "You do not have this piece of gear unlocked!");
			p.closeInventory();
			return;
		}
		//Method to give the gear.
		p.sendMessage(ChatColor.GREEN + "Gear equiped!");
		p.closeInventory();
		return;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public void opengui(Player p) {
		Inventory inv = Bukkit.createInventory(null, 54, ChatColor.DARK_AQUA + "Gear");
		ItemStack glasspane = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 5);
		for (int i = 1; i < 50; i++) {
			inv.setItem((i - 1), glasspane);
			if (i == 4) {
				i = 45;
			}
		}
		glasspane = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 15);
		for (int i = 5; i < 50; i= i+9) {
			inv.setItem((i - 1), glasspane);
		}
		inv.setItem(49, glasspane);
		glasspane = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 14);
		for (int i = 6; i < 55; i ++) {
			inv.setItem((i - 1), glasspane);
			if (i == 9) {
				i = 50;
			}
		}
		if (Main.ItemsConfig.getItems().contains("players." + p.getUniqueId() + ".gears") && Main.ItemsConfig.getItems().getStringList("players." + p.getUniqueId() + ".gears").size() == 0) {
			int i = 15;
			for (String s : Main.MenuConfig.getMenu().getStringList("menus.gearsmenu.gears")) {
				ItemStack item = new ItemStack(Material.getMaterial(s), 1);
				ItemMeta itemmeta = item.getItemMeta();
				itemmeta.setDisplayName(ChatColor.DARK_RED + "Locked Item");
				itemmeta.setLore(Arrays.asList(ChatColor.RED + "Unlock items through game chests."));
				item.setItemMeta(itemmeta);
				inv.setItem((i - 1), item);
				i++;
				if (i == 19)
					i = 24;
				if (i == 28)
					i = 33;
				if (i == 37)
					i = 42;
			}
		} else {
			List<String> trails = Main.ItemsConfig.getItems().getStringList("players." + p.getUniqueId() + ".gears");
			int i = 15;
			for (String s : Main.MenuConfig.getMenu().getStringList("menus.gearsmenu.gears")) {
				if (trails.contains(s)) {}
				else {
					ItemStack item = new ItemStack(Material.getMaterial(s), 1);
					ItemMeta itemmeta = item.getItemMeta();
					itemmeta.setDisplayName(ChatColor.DARK_RED + "Locked Item");
					itemmeta.setLore(Arrays.asList(ChatColor.RED + "Unlock items through game chests."));
					item.setItemMeta(itemmeta);
					inv.setItem((i - 1), item);
					i++;
					if (i == 19)
						i = 24;
					if (i == 28)
						i = 33;
					if (i == 37)
						i = 42;
				}
			}
			i = 10;
			for (String s : trails) {
				ItemStack item = new ItemStack(Material.getMaterial(s), 1); 
				ItemMeta itemmeta = item.getItemMeta();
				if (!VariableManager.trail.containsKey(p.getName())) {
					itemmeta.setDisplayName(ChatColor.GREEN + "Equip");
					itemmeta.setLore(Arrays.asList(ChatColor.DARK_GREEN + "Click to equip!"));
				} else if (VariableManager.trail.get(p.getName()) == new ItemStack(Material.getMaterial(s))) {
					itemmeta.setDisplayName(ChatColor.RED + "Unequip");
					itemmeta.setLore(Arrays.asList(ChatColor.DARK_GREEN + "Click to unequip!"));
				} else {
					itemmeta.setDisplayName(ChatColor.GREEN + "Equip");
					itemmeta.setLore(Arrays.asList(ChatColor.DARK_GREEN + "Click to equip!"));
				}
				item.setItemMeta(itemmeta);
				inv.setItem((i - 1), item);
				i++;
				if (i == 14)
					i = 19;
				if (i == 23)
					i = 28;
				if (i == 32)
					i = 37;
			}
		}
		ItemStack item = new ItemStack(Material.ARROW, 1); 
		ItemMeta itemmeta = item.getItemMeta();
		itemmeta.setDisplayName(ChatColor.DARK_GRAY+ "Back");
		itemmeta.setLore(Arrays.asList(ChatColor.GRAY + "Go back to the main menu!"));
		item.setItemMeta(itemmeta);
		inv.setItem(49, item);
		p.openInventory(inv);
	}
}
