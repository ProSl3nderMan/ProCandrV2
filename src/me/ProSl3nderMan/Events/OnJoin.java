package me.ProSl3nderMan.Events;

import java.util.ArrayList;
import java.util.List;

import me.ProSl3nderMan.Main.Main;
import me.ProSl3nderMan.Managers.VariableManager;
import me.ProSl3nderMan.Menus.ItemsGUI;
import me.ProSl3nderMan.Menus.LobbyGUI;
import me.ProSl3nderMan.Menus.ModGUI;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class OnJoin implements Listener {
	
	public OnJoin(Main accessControl) {
		accessControl.getServer().getPluginManager().registerEvents(this, accessControl);
	}

	public OnJoin() {}

	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		Player p = event.getPlayer();
		p.getInventory().clear();
    	LobbyGUI.LobbyWatch(p);
    	ItemsGUI.IWatch(p);
        p.setGameMode(GameMode.ADVENTURE);
        VariableManager.cop.put(p.getName(), false);
    	if (p.hasPermission("ProCandr.modperms"))
    		ModGUI.ModWatch(p);
    	if (p.hasPermission("ProCandr.vip")) {
    		if (Main.ItemsConfig.getItems().contains("players." + p.getUniqueId())) {
    			if (Main.ItemsConfig.getItems().contains("players." + p.getUniqueId() + ".trails")) {
    				List<String> trails = Main.ItemsConfig.getItems().getStringList("players." + p.getUniqueId() + ".trails");
    				if (!trails.contains("GLOWSTONE")) {
    					trails.add("GLOWSTONE");
    					trails.add("SLIME_BLOCK");
    					Main.ItemsConfig.getItems().set("players." + p.getUniqueId() + ".trails", trails);
    					Main.ItemsConfig.srItems();
    				}
    			} else {
        			List<String> trails = new ArrayList<String>();
        			trails.add("GLOWSTONE");
        			trails.add("SLIME_BLOCK");
        			Main.ItemsConfig.getItems().set("players." + p.getUniqueId() + ".trails", trails);
        			Main.ItemsConfig.srItems();
    			}
    			
    			if (Main.ItemsConfig.getItems().contains("players." + p.getUniqueId() + ".buddys")) {
    				List<String> buddys = Main.ItemsConfig.getItems().getStringList("players." + p.getUniqueId() + ".buddys");
    				if (!buddys.contains("jscherry/Master_Chief")) {
            			buddys.add("jscherry/Master_Chief");
            			buddys.add("BobaFett/Boba_Fett");
            			buddys.add("Eye/Eyeball");
    					Main.ItemsConfig.getItems().set("players." + p.getUniqueId() + ".buddys", buddys);
    					Main.ItemsConfig.srItems();
    				}
    			} else {
    				List<String> buddys = new ArrayList<String>();
        			buddys.add("jscherry/Master_Chief");
        			buddys.add("BobaFett/Boba_Fett");
        			buddys.add("Eye/Eyeball");
        			Main.ItemsConfig.getItems().set("players." + p.getUniqueId() + ".buddys", buddys);
        			Main.ItemsConfig.srItems();
    			}
    		} else {
    			List<String> trails = new ArrayList<String>();
    			trails.add("GLOWSTONE");
    			trails.add("SLIME_BLOCK");
    			Main.ItemsConfig.getItems().set("players." + p.getUniqueId() + ".trails", trails);
    			
    			List<String> buddys = new ArrayList<String>();
    			buddys.add("jscherry/Master_Chief");
    			buddys.add("BobaFett/Boba_Fett");
    			buddys.add("Eye/Eyeball");
    			Main.ItemsConfig.getItems().set("players." + p.getUniqueId() + ".buddys", buddys);
    			
    			Main.ItemsConfig.srItems();
    		}
    	}
		Location loc = Main.plugin.getSpawn();
		p.teleport(loc);
		Main.VM.addLocation(p, loc);
        p.setHealth(20);
        p.setFoodLevel(20);
        VariableManager.game.put(p.getName(), "0");
        if (hasPersonalBuddy(p)) { //if player has their own personal buddy.
			ItemStack itemSkull = new ItemStack(Material.SKULL_ITEM, 1, (short)3);
		    SkullMeta metaSkull = (SkullMeta)itemSkull.getItemMeta();
			metaSkull.setOwner(p.getName());
			itemSkull.setItemMeta(metaSkull);
			Main.VM.skull.put(p.getName() + "/" + p.getName(), itemSkull);
        }
		String uuid = p.getPlayer().getUniqueId().toString();
		if(Main.PlayersConfig.getPlayers().contains("players." + p.getPlayer().getUniqueId())) {
			Main.PlayersConfig.getPlayers().set("players." + p.getPlayer().getUniqueId() + ".IGN" , p.getName());
			Main.PlayersConfig.srPlayers();
		} else {
			Main.PlayersConfig.getPlayers().set("players." + p.getPlayer().getUniqueId() , uuid);
			Main.PlayersConfig.getPlayers().set("players." + p.getPlayer().getUniqueId() + ".IGN" , p.getName());
            Main.PlayersConfig.getPlayers().set("players." + p.getPlayer().getUniqueId() + ".GamesWon", 0);
    		Main.PlayersConfig.srPlayers();
		}
	}
	
	public Boolean hasPersonalBuddy(Player p) {
		if (Main.ItemsConfig.getItems().contains("players." + p.getUniqueId())) {
			if (Main.ItemsConfig.getItems().contains("players." + p.getUniqueId() + ".buddys")) {
				List<String> buddys = Main.ItemsConfig.getItems().getStringList("players." + p.getUniqueId() + ".buddys");
				if (buddys.contains(p.getName() + "/" + p.getName()))
					return true;
			}
		}
		return false;
	}
}
