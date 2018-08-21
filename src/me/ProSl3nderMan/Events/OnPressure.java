package me.ProSl3nderMan.Events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import me.ProSl3nderMan.Main.Main;
import me.ProSl3nderMan.Managers.VariableManager;

public class OnPressure implements Listener {
	public OnPressure(Main plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}


	@EventHandler
    public void pressurePlate(PlayerInteractEvent event){
        if(event.getAction().equals(Action.PHYSICAL)){
        	Block block = event.getClickedBlock();
            if(block.getType() == Material.GOLD_PLATE){
                Player p = event.getPlayer();
                if (!VariableManager.cop.get(p.getName())) {
                	p.sendMessage(ChatColor.GOLD + "You must be cop to use this fast pass!");
                } else if (VariableManager.cop.get(p.getName())) {
                	if (VariableManager.game.get(p.getName()).equalsIgnoreCase("1")) {
                		int x = Main.plugin.getConfig().getInt("map1.cell.pressure.x");
                        int y = Main.plugin.getConfig().getInt("map1.cell.pressure.y");
                        int z = Main.plugin.getConfig().getInt("map1.cell.pressure.z");
                        p.teleport(new Location(Bukkit.getWorld(Main.plugin.getConfig().getString("map1.world")), x, y, z));
                        p.sendMessage(ChatColor.DARK_GREEN + "Fast pass card accepted!");
                	} else if (VariableManager.game.get(p.getName()).equalsIgnoreCase("2")) {
                		int x = Main.plugin.getConfig().getInt("map2.cell.pressure.x");
                        int y = Main.plugin.getConfig().getInt("map2.cell.pressure.y");
                        int z = Main.plugin.getConfig().getInt("map2.cell.pressure.z");
                        p.teleport(new Location(Bukkit.getWorld(Main.plugin.getConfig().getString("map2.world")), x, y, z));
                        p.sendMessage(ChatColor.DARK_GREEN + "Fast pass card accepted!");
                	} else if (VariableManager.game.get(p.getName()).equalsIgnoreCase("3")) {
                		int x = Main.plugin.getConfig().getInt("map3.cell.pressure.x");
                        int y = Main.plugin.getConfig().getInt("map3.cell.pressure.y");
                        int z = Main.plugin.getConfig().getInt("map3.cell.pressure.z");
                        p.teleport(new Location(Bukkit.getWorld(Main.plugin.getConfig().getString("map3.world")), x, y, z));
                        p.sendMessage(ChatColor.DARK_GREEN + "Fast pass card accepted!");
                	}
                }
            }
        }
    }
}
