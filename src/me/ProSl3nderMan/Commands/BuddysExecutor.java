package me.ProSl3nderMan.Commands;

import java.util.Arrays;
import java.util.List;

import me.ProSl3nderMan.Main.Main;
import me.ProSl3nderMan.Menus.ItemsGUI;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class BuddysExecutor implements CommandExecutor, Listener {
	public BuddysExecutor(Main main) {}
  
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player)sender;
		if (args.length == 0)  {
			opengui(p);
			return true;
		}
		if ((args[0].equalsIgnoreCase("give")) && (p.isOp()))  {
			List<String> buddys = Main.ItemsConfig.getItems().getStringList("players." + Bukkit.getPlayer(args[1]).getUniqueId() + ".buddys");
			buddys.add(args[2]);
			Main.ItemsConfig.getItems().set("players." + Bukkit.getPlayer(args[1]).getUniqueId() + ".buddys", buddys);
			Main.ItemsConfig.srItems();
			p.sendMessage("Player " + args[1] + " now has the buddy " + args[2] + "!");
		}
		return true;
	}
  
	public static void OpenGUIListener(InventoryClickEvent event) {
		Player p = (Player)event.getWhoClicked();
		event.setCancelled(true);
		InventoryAction a = event.getAction();
		ItemStack is = event.getCurrentItem();
		if ((a == InventoryAction.UNKNOWN) || (a == InventoryAction.NOTHING) || (is == null) || (is.getType() == Material.AIR) || (is.getType() == Material.STAINED_GLASS_PANE))
			return;
		if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.DARK_GRAY + "Back")) {
			p.closeInventory();
			ItemsGUI.openIGUI(p);
			return;
		}
		if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.RED + "Unequip"))  {
			if (Main.VM.nano.containsKey(p.getName())) {
				Entity e = Main.VM.nano.get(p.getName());
				e.remove();
				Main.VM.nano.remove(p.getName());
			}
			p.sendMessage(ChatColor.GREEN + "Buddy unequiped!");
			p.closeInventory();
			return;
		}
		if (!event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GREEN + "Equip"))  {
			p.sendMessage(ChatColor.RED + "You do not have this buddy unlocked!");
			p.closeInventory();
			return;
		}
		if (Main.VM.nano.containsKey(p.getName())) {
			Entity e = Main.VM.nano.get(p.getName());
			Main.VM.nano.remove(p.getName());
			e.remove();
			p.sendMessage(ChatColor.GREEN + "Buddy unequiped!");
		}
		Main.NM.nanoSendOut(p, event.getCurrentItem());
		p.sendMessage(ChatColor.GREEN + "Buddy equiped!");
		p.closeInventory();
	}
  
	public static void opengui(Player p) {
		Inventory inv = Bukkit.createInventory(null, 54, ChatColor.DARK_RED + "Buddys");
		ItemStack glasspane = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)5);
		for (int i = 1; i < 50; i++) {
			inv.setItem(i - 1, glasspane);
			if (i == 4)
				i = 45;
		}
		glasspane = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)15);
		for (int i = 5; i < 50; i += 9)
			inv.setItem(i - 1, glasspane);
		inv.setItem(49, glasspane);
		glasspane = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)14);
		for (int i = 6; i < 55; i++) {
			inv.setItem(i - 1, glasspane);
			if (i == 9)
				i = 50;
		}
		if (!(Main.ItemsConfig.getItems().contains("players." + p.getUniqueId() + ".buddys")) || Main.ItemsConfig.getItems().getStringList("players." + p.getUniqueId() + ".buddys").size() == 0) {
			int i = 15;
			for (String s : Main.MenuConfig.getMenu().getStringList("menus.buddymenu.buddys")) {
				String[] parts = s.split("/");
				//String skullOwner = parts[0]; //the skin owner's in game name.
				String skullName = parts[1]; //the label of the skin's head.
				if (skullName.contains("_"))
					skullName = skullName.replace("_", " ");
				ItemStack itemSkull = Main.VM.skull.get(s);
			    SkullMeta metaSkull = (SkullMeta)itemSkull.getItemMeta();
				metaSkull.setDisplayName(ChatColor.DARK_RED + "Locked Item");
				metaSkull.setLore(Arrays.asList(new String[] { ChatColor.RED + "Unlock items through game chests." }));
				if ((skullName.equalsIgnoreCase("Master Chief")) || (skullName.equalsIgnoreCase("Boba Fett")) || (skullName.equalsIgnoreCase("Eyeball"))) {
					metaSkull = (SkullMeta)itemSkull.getItemMeta();
					metaSkull.setDisplayName(ChatColor.GREEN + "Vip Item");
					metaSkull.setLore(Arrays.asList(new String[] { ChatColor.DARK_GREEN + "Only for Vip's. Do /donate to be a Vip!" }));
				}
				itemSkull.setItemMeta(metaSkull);
				inv.setItem(i - 1, itemSkull);
				i++;
				if (i == 19)
					i = 24;
				if (i == 28)
					i = 33;
				if (i == 37)
					i = 42;
			}
		} else {
			List<String> buddys = Main.ItemsConfig.getItems().getStringList("players." + p.getUniqueId() + ".buddys");
			int i = 15;
			for (String s : Main.MenuConfig.getMenu().getStringList("menus.buddymenu.buddys")) {
				if (!buddys.contains(s)) {
					String[] parts = s.split("/");
					//String skullOwner = parts[0]; //the skin owner's in game name.
					String skullName = parts[1]; //the label of the skin's head.
					if (skullName.contains("_"))
						skullName = skullName.replace("_", " ");
					ItemStack itemSkull = Main.VM.skull.get(s);
				    SkullMeta metaSkull = (SkullMeta)itemSkull.getItemMeta();
					metaSkull.setDisplayName(ChatColor.DARK_RED + "Locked Item");
					metaSkull.setLore(Arrays.asList(new String[] { ChatColor.RED + "Unlock items through game chests." }));
					if ((skullName.equalsIgnoreCase("Master Chief")) || (skullName.equalsIgnoreCase("Boba Fett")) || (skullName.equalsIgnoreCase("Eyeball"))) {
						metaSkull = (SkullMeta)itemSkull.getItemMeta();
						metaSkull.setDisplayName(ChatColor.GREEN + "Vip Item");
						metaSkull.setLore(Arrays.asList(new String[] { ChatColor.DARK_GREEN + "Only for Vip's. Do /donate to be a Vip!" }));
					}
					itemSkull.setItemMeta(metaSkull);
					inv.setItem(i - 1, itemSkull);
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
			for (String s : buddys) {
				String[] parts = s.split("/");
				String skullOwner = parts[0]; //the skin owner's in game name.
				String skullName = parts[1]; //the label of the skin's head.
				if (skullName.contains("_"))
					skullName = skullName.replace("_", " ");
				ItemStack itemSkull = Main.VM.skull.get(s);
			    SkullMeta metaSkull = (SkullMeta)itemSkull.getItemMeta();
				metaSkull.setDisplayName(ChatColor.GREEN + "Equip");
				metaSkull.setLore(Arrays.asList(new String[] { ChatColor.DARK_GREEN + skullName }));
				itemSkull.setItemMeta(metaSkull);
				if (Main.VM.nano.containsKey(p.getName())) {
					if (getSkullOwner(Main.VM.nano.get(p.getName())).equalsIgnoreCase(skullOwner)) {
						metaSkull.setDisplayName(ChatColor.RED + "Unequip");
						metaSkull.setLore(Arrays.asList(new String[] { ChatColor.DARK_GREEN + skullName }));
					}
				}
				itemSkull.setItemMeta(metaSkull);
				inv.setItem(i - 1, itemSkull);
				i++;
				if (i == 14)
					i = 19;
				if (i == 23)
					i = 28;
				if (i == 32)
					i = 37;
			}
		}
		ItemStack item;
		item = new ItemStack(Material.ARROW, 1);
		ItemMeta itemmeta = item.getItemMeta();
		itemmeta.setDisplayName(ChatColor.DARK_GRAY + "Back");
		itemmeta.setLore(Arrays.asList(new String[] { ChatColor.GRAY + "Go back to the main menu!" }));
		item.setItemMeta(itemmeta);
		inv.setItem(49, item);
		p.openInventory(inv);
	}
	
	public static String getSkullOwner(Entity e) {
		ArmorStand m = (ArmorStand) e;
		ItemStack skull = m.getHelmet();
		SkullMeta skullMeta = (SkullMeta)skull.getItemMeta();
		return skullMeta.getOwner();
	}
}