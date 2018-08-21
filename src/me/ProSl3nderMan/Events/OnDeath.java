package me.ProSl3nderMan.Events;

import me.ProSl3nderMan.Main.Main;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

public class OnDeath implements Listener {
	
	public OnDeath(Main plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler(priority = EventPriority.LOWEST)
	public void onRespawn(PlayerRespawnEvent event) {
    	event.setRespawnLocation(Main.VM.getLocation(event.getPlayer()));
	}
}
