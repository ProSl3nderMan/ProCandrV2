package me.ProSl3nderMan.Menus;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.ProSl3nderMan.Main.Main;

public class ItemsGUI implements Listener {
	public ItemsGUI(Main main) {
		main.getServer().getPluginManager().registerEvents(this, main);
	}
	
	public static Player IWatch(Player p) {
		ItemStack ig = new ItemStack(Material.EMERALD);
		ItemMeta igMeta = ig.getItemMeta();
		igMeta.setDisplayName("Items");
		ig.setItemMeta(igMeta);
		p.getInventory().setItem(8, ig);
		p.updateInventory();
		return p;
  }
	
	public static void openIGUI(Player player) {
		Inventory inv = Bukkit.createInventory(null, Main.MenuConfig.getMenu().getInt("menus.imenu.slotamount"), ChatColor.DARK_AQUA + "Items");
		
		for (int i = 0 ; i < Main.MenuConfig.getMenu().getInt("menus.imenu.slotamount") ; i++) {
			ItemStack barrier = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 15);
			ItemMeta barrierMeta = barrier.getItemMeta();
			
			barrierMeta.setDisplayName(ChatColor.DARK_AQUA + "");
			barrier.setItemMeta(barrierMeta);
			
			inv.setItem(i, barrier);
		}
		
		for (String slot : Main.MenuConfig.getMenu().getConfigurationSection("menus.imenu.slots").getKeys(false)) {
			String direct = "menus.imenu.slots." + slot;
			
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
		InventoryAction a = event.getAction();
		ItemStack is = event.getCurrentItem();
		if (a == InventoryAction.UNKNOWN || a == InventoryAction.NOTHING || is == null || is.getType() == Material.AIR) {
			return;
		}
		for (String slot : Main.MenuConfig.getMenu().getConfigurationSection("menus.imenu.slots").getKeys(false)) {
			if (event.getCurrentItem().getType() == Material.getMaterial(Main.MenuConfig.getMenu().getString("menus.imenu.slots." + slot + ".block"))) {
				p.closeInventory();
				p.performCommand(Main.MenuConfig.getMenu().getString("menus.imenu.slots." + slot + ".command"));
				return;
			}
		}
	}
}