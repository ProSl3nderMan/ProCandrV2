package me.ProSl3nderMan.Events;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

import me.ProSl3nderMan.Main.Main;

public class OnItemDrop implements Listener {
	
	public OnItemDrop(Main plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onItemDrop(PlayerDropItemEvent event) {
		Player p = event.getPlayer();
		Material item = event.getItemDrop().getItemStack().getType();
		if(item == Material.DIAMOND_SWORD || item == Material.DIAMOND_HELMET || item == Material.DIAMOND_CHESTPLATE || item == Material.DIAMOND_LEGGINGS || item == Material.DIAMOND_BOOTS || item == Material.BOW 
				|| item == Material.ARROW) {
			event.setCancelled(true);
			p.sendMessage(ChatColor.RED + "You are not allowed to drop this item! You may only drop sticks and steaks.");
		} else if(event.getItemDrop().getItemStack().getItemMeta().hasDisplayName()) {
			if (event.getItemDrop().getItemStack().getItemMeta().getDisplayName().equalsIgnoreCase("Lobby Commands")
					|| event.getItemDrop().getItemStack().getItemMeta().getDisplayName().equalsIgnoreCase("In Game Commands") 
					|| event.getItemDrop().getItemStack().getItemMeta().getDisplayName().equalsIgnoreCase("Items")) {
				event.setCancelled(true);
				p.sendMessage(ChatColor.RED + "You are not allowed to drop this item! You may only drop sticks and steaks.");
			}
		}
	}
}
