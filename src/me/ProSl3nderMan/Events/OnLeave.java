package me.ProSl3nderMan.Events;

import me.ProSl3nderMan.Main.Main;
import me.ProSl3nderMan.Managers.LeaveServerManager;
import me.ProSl3nderMan.Managers.VariableManager;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class OnLeave implements Listener {
	
	public OnLeave(Main plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	  public void onPlayerQuit(final PlayerQuitEvent event) {
			event.getPlayer().getInventory().clear();
	    	Player p = event.getPlayer();
	    	String game = VariableManager.game.get(p.getName());
	    	Boolean cop = VariableManager.cop.get(p.getName());
	    	p.teleport(p.getWorld().getSpawnLocation());
	    	p.setHealth(20);
	    	p.setFoodLevel(20);
	    	Entity e;
	    	if (Main.VM.nano.containsKey(p.getName())) {
	    			e = Main.VM.nano.get(p.getName());
	    			Main.VM.nano.remove(p.getName());
	    			e.remove();
	    	}
	    	if (!game.equalsIgnoreCase("0")) {
	    		if (cop) {
	    			LeaveServerManager.CopLeaving(p, game);
	    		} else if (!cop) {
	    			LeaveServerManager.RobberLeaving(p, game);
	    		}
	    	}
	  }
}
