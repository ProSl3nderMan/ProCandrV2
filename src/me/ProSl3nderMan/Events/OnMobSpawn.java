package me.ProSl3nderMan.Events;

import me.ProSl3nderMan.Main.Main;

import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;

public class OnMobSpawn implements Listener {
	public OnMobSpawn (Main main) {
		main.getServer().getPluginManager().registerEvents(this, main);
	}
	
	@EventHandler(priority = EventPriority.LOWEST)
	public void mobSpawn(final EntitySpawnEvent event) {
		Entity e = event.getEntity();
		if (e instanceof Player || e instanceof ArmorStand) {
			event.setCancelled(false);
		} else {
			event.setCancelled(true);
		}
	}
}
