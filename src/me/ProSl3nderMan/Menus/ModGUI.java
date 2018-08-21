package me.ProSl3nderMan.Menus;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.ProSl3nderMan.Main.Main;
import me.ProSl3nderMan.Managers.LeaveGameManager;
import me.ProSl3nderMan.Managers.VariableManager;

public class ModGUI implements Listener {
	public ModGUI(Main main) {
		main.getServer().getPluginManager().registerEvents(this, main);
	}
	public static Player ModWatch(Player p) {
		ItemStack ig = new ItemStack(Material.WATCH);
		ItemMeta igMeta = ig.getItemMeta();
		igMeta.setDisplayName("Mod Commands");
		ig.setItemMeta(igMeta);
		p.getInventory().addItem(new ItemStack[] { ig });
		p.updateInventory();
		return p;
  }
	
	public static void openModGUI(Player player) {
		Inventory inv = Bukkit.createInventory(null, Main.MenuConfig.getMenu().getInt("menus.modmenu.slotamount"), ChatColor.DARK_AQUA + "Mod Menu");
		
		for (int i = 0 ; i < Main.MenuConfig.getMenu().getInt("menus.modmenu.slotamount") ; i++) {
			ItemStack barrier = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 15);
			ItemMeta barrierMeta = barrier.getItemMeta();
			
			barrierMeta.setDisplayName(ChatColor.DARK_AQUA + "");
			barrier.setItemMeta(barrierMeta);
			
			inv.setItem(i, barrier);
		}
		
		for (String slot : Main.MenuConfig.getMenu().getConfigurationSection("menus.modmenu.slots").getKeys(false)) {
			String direct = "menus.modmenu.slots." + slot;
			
			ItemStack item = new ItemStack(Material.getMaterial(Main.MenuConfig.getMenu().getString(direct + ".block")));
			ItemMeta itemMeta = item.getItemMeta();
			
			itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', Main.MenuConfig.getMenu().getString(direct + ".name")));
			int amountofdesc = Main.MenuConfig.getMenu().getInt(direct + ".description.lineamount");
			if (amountofdesc == 1)
				itemMeta.setLore(Arrays.asList(ChatColor.translateAlternateColorCodes('&', Main.MenuConfig.getMenu().getString(direct + ".description.lines.line" + amountofdesc))));
			else if (amountofdesc == 2)
				itemMeta.setLore(Arrays.asList(ChatColor.translateAlternateColorCodes('&', Main.MenuConfig.getMenu().getString(direct + ".description.lines.line" + (amountofdesc-1))),
						ChatColor.translateAlternateColorCodes('&', Main.MenuConfig.getMenu().getString(direct + ".description.lines.line" + (amountofdesc)))));
			else if (amountofdesc == 3)
				itemMeta.setLore(Arrays.asList(ChatColor.translateAlternateColorCodes('&', Main.MenuConfig.getMenu().getString(direct + ".description.lines.line" + (amountofdesc-2))),
						ChatColor.translateAlternateColorCodes('&', Main.MenuConfig.getMenu().getString(direct + ".description.lines.line" + (amountofdesc -1))),
						ChatColor.translateAlternateColorCodes('&', Main.MenuConfig.getMenu().getString(direct + ".description.lines.line" + (amountofdesc)))));
			else if (amountofdesc == 4)
				itemMeta.setLore(Arrays.asList(ChatColor.translateAlternateColorCodes('&', Main.MenuConfig.getMenu().getString(direct + ".description.lines.line" + (amountofdesc-3))),
						ChatColor.translateAlternateColorCodes('&', Main.MenuConfig.getMenu().getString(direct + ".description.lines.line" + (amountofdesc -2))),
						ChatColor.translateAlternateColorCodes('&', Main.MenuConfig.getMenu().getString(direct + ".description.lines.line" + (amountofdesc -1))),
						ChatColor.translateAlternateColorCodes('&', Main.MenuConfig.getMenu().getString(direct + ".description.lines.line" + (amountofdesc)))));
			else if (amountofdesc == 5)
				itemMeta.setLore(Arrays.asList(ChatColor.translateAlternateColorCodes('&', Main.MenuConfig.getMenu().getString(direct + ".description.lines.line" + (amountofdesc-4))),
						ChatColor.translateAlternateColorCodes('&', Main.MenuConfig.getMenu().getString(direct + ".description.lines.line" + (amountofdesc -3))),
						ChatColor.translateAlternateColorCodes('&', Main.MenuConfig.getMenu().getString(direct + ".description.lines.line" + (amountofdesc -2))),
						ChatColor.translateAlternateColorCodes('&', Main.MenuConfig.getMenu().getString(direct + ".description.lines.line" + (amountofdesc -1))),
						ChatColor.translateAlternateColorCodes('&', Main.MenuConfig.getMenu().getString(direct + ".description.lines.line" + (amountofdesc)))));
			else if (amountofdesc == 6)
				itemMeta.setLore(Arrays.asList(ChatColor.translateAlternateColorCodes('&', Main.MenuConfig.getMenu().getString(direct + ".description.lines.line" + (amountofdesc-5))),
						ChatColor.translateAlternateColorCodes('&', Main.MenuConfig.getMenu().getString(direct + ".description.lines.line" + (amountofdesc -4))),
						ChatColor.translateAlternateColorCodes('&', Main.MenuConfig.getMenu().getString(direct + ".description.lines.line" + (amountofdesc -3))),
						ChatColor.translateAlternateColorCodes('&', Main.MenuConfig.getMenu().getString(direct + ".description.lines.line" + (amountofdesc -2))),
						ChatColor.translateAlternateColorCodes('&', Main.MenuConfig.getMenu().getString(direct + ".description.lines.line" + (amountofdesc -1))),
						ChatColor.translateAlternateColorCodes('&', Main.MenuConfig.getMenu().getString(direct + ".description.lines.line" + (amountofdesc)))));
			else if (amountofdesc == 7)
				itemMeta.setLore(Arrays.asList(ChatColor.translateAlternateColorCodes('&', Main.MenuConfig.getMenu().getString(direct + ".description.lines.line" + (amountofdesc-6))),
						ChatColor.translateAlternateColorCodes('&', Main.MenuConfig.getMenu().getString(direct + ".description.lines.line" + (amountofdesc -5))),
						ChatColor.translateAlternateColorCodes('&', Main.MenuConfig.getMenu().getString(direct + ".description.lines.line" + (amountofdesc -4))),
						ChatColor.translateAlternateColorCodes('&', Main.MenuConfig.getMenu().getString(direct + ".description.lines.line" + (amountofdesc -3))),
						ChatColor.translateAlternateColorCodes('&', Main.MenuConfig.getMenu().getString(direct + ".description.lines.line" + (amountofdesc -2))),
						ChatColor.translateAlternateColorCodes('&', Main.MenuConfig.getMenu().getString(direct + ".description.lines.line" + (amountofdesc -1)))));
			item.setItemMeta(itemMeta);
			
			inv.setItem(Integer.parseInt(slot), item);
		}
		
		player.openInventory(inv);
	}

	public static void OpenGUIListener(InventoryClickEvent event) {
		Player p = (Player) event.getWhoClicked();
		event.setCancelled(true);
		
		if (event.getClick() == null) {
			return;
		}
		for (String slot : Main.MenuConfig.getMenu().getConfigurationSection("menus.modmenu.slots").getKeys(false)) {
			if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(Main.MenuConfig.getMenu().getString("menus.modmenu.slots." + slot + ".name"))) {
				p.closeInventory();
				p.performCommand(Main.MenuConfig.getMenu().getString("menus.modmenu.slots." + slot + ".command"));
				return;
			}
		}
		if (event.getCurrentItem().getType() == Material.SKULL_ITEM) {
			Player player = Bukkit.getPlayer(ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName()));
			if (!VariableManager.game.containsKey(p.getName()) || VariableManager.game.get(player.getName()).equalsIgnoreCase("0")) {
				p.sendMessage(ChatColor.DARK_RED + "The player " + ChatColor.RED + player.getName() + ChatColor.DARK_RED + " is not in a game!");
			} else {
				LeaveGameManager.LeaveGame(player, VariableManager.game.get(player.getName()));
				p.sendMessage(ChatColor.RED + "You have been kicked by " + p.getName() + "! Make sure you are following the rules.");
			}
		}
	}
}
