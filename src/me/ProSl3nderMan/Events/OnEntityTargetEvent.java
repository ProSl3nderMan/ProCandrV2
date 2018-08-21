package me.ProSl3nderMan.Events;

import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityTargetLivingEntityEvent;

import me.ProSl3nderMan.Main.Main;

public class OnEntityTargetEvent implements Listener {
	public OnEntityTargetEvent(Main main) {
		main.getServer().getPluginManager().registerEvents(this, main);
	}
	
	@EventHandler
    public void onEntityTarget(EntityTargetLivingEntityEvent event){
        if (event.getTarget() instanceof LivingEntity){
            event.setCancelled(true);
        }
    }
}
