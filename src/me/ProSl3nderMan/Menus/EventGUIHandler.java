package me.ProSl3nderMan.Menus;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType.SlotType;

import me.ProSl3nderMan.Commands.ChestsExecutor;
import me.ProSl3nderMan.Commands.HatsExecutor;
import me.ProSl3nderMan.Commands.BuddysExecutor;
import me.ProSl3nderMan.Commands.ShopExecutor;
import me.ProSl3nderMan.Commands.TrailsExecutor;
import me.ProSl3nderMan.Main.Main;

public class EventGUIHandler implements Listener{
	
	public EventGUIHandler(Main main) {
		main.getServer().getPluginManager().registerEvents(this, main);
	}
	
	@EventHandler
	public void onInventoryLeftClick(InventoryClickEvent event) {
		if (event.getSlotType() == SlotType.RESULT) {
			event.setCancelled(true);
		}
		if(ChatColor.stripColor(event.getInventory().getName()).equalsIgnoreCase("Lobby Menu")) {
			LobbyGUI.OpenGUIListener(event);
			return;
		}
		if(ChatColor.stripColor(event.getInventory().getName()).equalsIgnoreCase("In Game Menu")) {
			InGameGUI.OpenGUIListener(event);
			return;
		}
		if(ChatColor.stripColor(event.getInventory().getName()).equalsIgnoreCase("Mod Menu") || ChatColor.stripColor(event.getInventory().getName()).equalsIgnoreCase("Kick Menu")) {
			ModGUI.OpenGUIListener(event);
			return;
		}
		if(ChatColor.stripColor(event.getInventory().getName()).equalsIgnoreCase("Items")) {
			ItemsGUI.OpenGUIListener(event);
			return;
		}
		if(ChatColor.stripColor(event.getInventory().getName()).equalsIgnoreCase("Hats")) {
			HatsExecutor.OpenGUIListener(event);
			return;
		}
		if(ChatColor.stripColor(event.getInventory().getName()).equalsIgnoreCase("Trails")) {
			TrailsExecutor.OpenGUIListener(event);
			return;
		}
		if(ChatColor.stripColor(event.getInventory().getName()).equalsIgnoreCase("Buddys")) {
			BuddysExecutor.OpenGUIListener(event);
			return;
		}
		if(ChatColor.stripColor(event.getInventory().getName()).equalsIgnoreCase("Shop")) {
			ShopExecutor.OpenGUIListener(event);
			return;
		}
		if(ChatColor.stripColor(event.getInventory().getName()).equalsIgnoreCase("Crates")) {
			ChestsExecutor.OpenGUIListener(event);
			return;
		}
		if(ChatColor.stripColor(event.getInventory().getName()).equalsIgnoreCase("Hats Crate") || ChatColor.stripColor(event.getInventory().getName()).equalsIgnoreCase("Trails Crate") || ChatColor.stripColor(event.getInventory().getName()).equalsIgnoreCase("Buddys Crate")) {
			event.setCancelled(true);
		}
	}
	
}
