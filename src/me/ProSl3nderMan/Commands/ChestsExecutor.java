package me.ProSl3nderMan.Commands;

import java.util.Arrays;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.ProSl3nderMan.Main.Main;
import me.ProSl3nderMan.Managers.CratesManager;
import me.ProSl3nderMan.Menus.ItemsGUI;

public class ChestsExecutor implements CommandExecutor {
	public ChestsExecutor(Main main) {}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
	    if (sender instanceof ConsoleCommandSender) {
	    	if (args[0].equalsIgnoreCase("give")) {
				//            /chests give <player> <chest type> <amount>
				//                    0    1        2            3
				int numb = 0;
				if (args[2].equalsIgnoreCase("hats")) {
					if (Main.ItemsConfig.getItems().contains("players." + Bukkit.getPlayer(args[1]).getUniqueId() + ".hatscrate"))
						numb = Main.ItemsConfig.getItems().getInt("players." + Bukkit.getPlayer(args[1]).getUniqueId() + ".hatscrate");
					Main.ItemsConfig.getItems().set("players." + Bukkit.getPlayer(args[1]).getUniqueId() + ".hatscrate", (numb + Integer.parseInt(args[3])));
					Main.ItemsConfig.srItems();
				}
				if (args[2].equalsIgnoreCase("trails")) {
					if (Main.ItemsConfig.getItems().contains("players." + Bukkit.getPlayer(args[1]).getUniqueId() + ".trailscrate"))
						numb = Main.ItemsConfig.getItems().getInt("players." + Bukkit.getPlayer(args[1]).getUniqueId() + ".trailscrate");
					Main.ItemsConfig.getItems().set("players." + Bukkit.getPlayer(args[1]).getUniqueId() + ".trailscrate", (numb + Integer.parseInt(args[3])));
					Main.ItemsConfig.srItems();
				}
				if (args[2].equalsIgnoreCase("buddys")) {
					if (Main.ItemsConfig.getItems().contains("players." + Bukkit.getPlayer(args[1]).getUniqueId() + ".buddyscrate"))
						numb = Main.ItemsConfig.getItems().getInt("players." + Bukkit.getPlayer(args[1]).getUniqueId() + ".buddyscrate");
					Main.ItemsConfig.getItems().set("players." + Bukkit.getPlayer(args[1]).getUniqueId() + ".buddyscrate", (numb + Integer.parseInt(args[3])));
					Main.ItemsConfig.srItems();
				}
			}
	    	return true;
	    }
	    Player p = (Player)sender;
		if (args.length == 0){
			opengui(p);
			return true;
	    }
		
		if (args[0].equalsIgnoreCase("give") && p.isOp()) {
			//            /chests give <player> <chest type> <amount>
			//                    0    1        2            3
			int numb = 0;
			if (args[2].equalsIgnoreCase("hats")) {
				if (Main.ItemsConfig.getItems().contains("players." + Bukkit.getPlayer(args[1]).getUniqueId() + ".hatscrate"))
					numb = Main.ItemsConfig.getItems().getInt("players." + Bukkit.getPlayer(args[1]).getUniqueId() + ".hatscrate");
				Main.ItemsConfig.getItems().set("players." + Bukkit.getPlayer(args[1]).getUniqueId() + ".hatscrate", (numb + Integer.parseInt(args[3])));
				Main.ItemsConfig.srItems();
			}
			if (args[2].equalsIgnoreCase("trails")) {
				if (Main.ItemsConfig.getItems().contains("players." + Bukkit.getPlayer(args[1]).getUniqueId() + ".trailscrate"))
					numb = Main.ItemsConfig.getItems().getInt("players." + Bukkit.getPlayer(args[1]).getUniqueId() + ".trailscrate");
				Main.ItemsConfig.getItems().set("players." + Bukkit.getPlayer(args[1]).getUniqueId() + ".trailscrate", (numb + Integer.parseInt(args[3])));
				Main.ItemsConfig.srItems();
			}
			if (args[2].equalsIgnoreCase("buddys")) {
				if (Main.ItemsConfig.getItems().contains("players." + Bukkit.getPlayer(args[1]).getUniqueId() + ".buddyscrate"))
					numb = Main.ItemsConfig.getItems().getInt("players." + Bukkit.getPlayer(args[1]).getUniqueId() + ".buddyscrate");
				Main.ItemsConfig.getItems().set("players." + Bukkit.getPlayer(args[1]).getUniqueId() + ".buddyscrate", (numb + Integer.parseInt(args[3])));
				Main.ItemsConfig.srItems();
			}
			p.sendMessage("Chest given to " + args[1]);
			Bukkit.getLogger().log(Level.FINE, "Chest given to " + args[1] + " from " + p.getName());
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
		if (event.getCurrentItem().getType() != Material.ENCHANTMENT_TABLE) {
			p.closeInventory();
			return;
		}
		p.closeInventory();
		String name = ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName());
		if (name.equalsIgnoreCase("Hats Crate")) {
			CratesManager.HatsCrate(p);
			int crates = Main.ItemsConfig.getItems().getInt("players." + p.getUniqueId() + ".hatscrate");
			if (crates == 1)
				Main.ItemsConfig.getItems().set("players." + p.getUniqueId() + ".hatscrate", null);
			else 
				Main.ItemsConfig.getItems().set("players." + p.getUniqueId() + ".hatscrate", crates - 1);
			Main.ItemsConfig.srItems();
		}
		if (name.equalsIgnoreCase("Trails Crate")) {
			CratesManager.TrailsCrate(p);
			int crates = Main.ItemsConfig.getItems().getInt("players." + p.getUniqueId() + ".trailscrate");
			if (crates == 1)
				Main.ItemsConfig.getItems().set("players." + p.getUniqueId() + ".trailscrate", null);
			else 
				Main.ItemsConfig.getItems().set("players." + p.getUniqueId() + ".trailscrate", crates - 1);
			Main.ItemsConfig.srItems();
		}
		if (name.equalsIgnoreCase("Buddys Crate")) {
			CratesManager.BuddysCrate(p);
			int crates = Main.ItemsConfig.getItems().getInt("players." + p.getUniqueId() + ".buddyscrate");
			if (crates == 1)
				Main.ItemsConfig.getItems().set("players." + p.getUniqueId() + ".buddyscrate", null);
			else 
				Main.ItemsConfig.getItems().set("players." + p.getUniqueId() + ".buddyscrate", crates - 1);
			Main.ItemsConfig.srItems();
		}
		return;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public void opengui(Player p) {
		Inventory inv = Bukkit.createInventory(null, 9, ChatColor.DARK_BLUE + "Crates");
		ItemStack glasspane = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 15);
		for (int i = 0; i < 9; i++) {
			inv.setItem(i, glasspane);
		}
		ItemStack crate = new ItemStack(Material.ENCHANTMENT_TABLE);
		ItemMeta meta = crate.getItemMeta();
		int i = 0;
		if (Main.ItemsConfig.getItems().contains("players." + p.getUniqueId() + ".hatscrate")) {
			int hats = Main.ItemsConfig.getItems().getInt("players." + p.getUniqueId() + ".hatscrate");
			crate = new ItemStack(Material.ENCHANTMENT_TABLE, hats);
			meta.setDisplayName(ChatColor.GOLD + "Hats Crate");
			meta.setLore(Arrays.asList(ChatColor.YELLOW + "Click to open your hats crate!"));
			crate.setItemMeta(meta);
			inv.setItem(i, crate);
			i++;
		}
		if (Main.ItemsConfig.getItems().contains("players." + p.getUniqueId() + ".trailscrate")) {
			int trails = Main.ItemsConfig.getItems().getInt("players." + p.getUniqueId() + ".trailscrate");
			crate = new ItemStack(Material.ENCHANTMENT_TABLE, trails);
			meta.setDisplayName(ChatColor.DARK_GREEN + "Trails Crate");
			meta.setLore(Arrays.asList(ChatColor.GREEN + "Click to open your trails crate!"));
			crate.setItemMeta(meta);
			inv.setItem(i, crate);
			i++;
		}
		if (Main.ItemsConfig.getItems().contains("players." + p.getUniqueId() + ".buddyscrate")) {
			int pets = Main.ItemsConfig.getItems().getInt("players." + p.getUniqueId() + ".buddyscrate");
			crate = new ItemStack(Material.ENCHANTMENT_TABLE, pets);
			meta.setDisplayName(ChatColor.DARK_RED + "Buddys Crate");
			meta.setLore(Arrays.asList(ChatColor.RED + "Click to open your buddys crate!"));
			crate.setItemMeta(meta);
			inv.setItem(i, crate);
			i++;
		}
		crate = new ItemStack(Material.ARROW, 1);
		meta = crate.getItemMeta();
		meta.setDisplayName(ChatColor.DARK_GRAY + "Back");
		meta.setLore(Arrays.asList(ChatColor.GRAY + "Go back to the main menu!"));
		crate.setItemMeta(meta);
		inv.setItem(8, crate);
		
		p.openInventory(inv);
	}
}
