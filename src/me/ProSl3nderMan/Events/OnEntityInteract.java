package me.ProSl3nderMan.Events;

import me.ProSl3nderMan.Main.Main;

import org.bukkit.entity.ArmorStand;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;

public class OnEntityInteract implements Listener {
	public OnEntityInteract(Main main) {
		main.getServer().getPluginManager().registerEvents(this, main);
	}
	
	@EventHandler(priority = EventPriority.LOWEST)
	public void onEntityInteract(PlayerInteractAtEntityEvent e) {
		if (e.getRightClicked() instanceof ArmorStand)
			e.setCancelled(true);
	}
}
