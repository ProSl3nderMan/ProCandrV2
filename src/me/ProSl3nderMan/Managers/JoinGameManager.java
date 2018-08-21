package me.ProSl3nderMan.Managers;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

import me.ProSl3nderMan.Main.Main;
import me.ProSl3nderMan.Menus.InGameGUI;
import me.ProSl3nderMan.Menus.ItemsGUI;
import me.ProSl3nderMan.Menus.ModGUI;

public class JoinGameManager {
	
	public JoinGameManager(Main plugin) {}
	
	public static void JoinGame(Player p, String game) {
        for (PotionEffect pt : p.getActivePotionEffects())
        	p.removePotionEffect(pt.getType());
		VariableManager.game.put(p.getName() , game);
        int PlayerCount = VariableManager.playeramount.get(game);
        VariableManager.playeramount.put(game , (PlayerCount + 1));
        VariableManager.players.get(game).add(p.getName());
        Location loc; int x; int y; int z; int yaw; int pitch;
        if (!Main.PlayersConfig.getPlayers().contains("players." + p.getUniqueId() + ".map" + game)) {
            Random randomM = new Random();
            int random = randomM.nextInt(100);
        	random = (int)(Math.random() * (Main.plugin.getConfig().getInt("map" + game + ".cellamount") + .0D) + 1.0D);
        	x = Main.plugin.getConfig().getInt("map" + game + ".cell" + random + ".x");
        	y = Main.plugin.getConfig().getInt("map" + game + ".cell" + random + ".y");
        	z = Main.plugin.getConfig().getInt("map" + game + ".cell" + random + ".z");
        	yaw = Main.plugin.getConfig().getInt("map" + game + ".cell" + random + ".yaw");
        	pitch = Main.plugin.getConfig().getInt("map" + game + ".cell" + random + ".pitch");
        	loc = new Location(Bukkit.getWorld(Main.plugin.getConfig().getString("map" + game + ".world")), x, y, z, yaw, pitch);
    		Main.VM.addLocation(p, loc);
        } else {
        	String cellnumb = Main.PlayersConfig.getPlayers().getString("players." + p.getUniqueId() + ".map" + game);
        	x = Main.plugin.getConfig().getInt("map" + game + ".cell" + cellnumb + ".x");
        	y = Main.plugin.getConfig().getInt("map" + game + ".cell" + cellnumb + ".y");
        	z = Main.plugin.getConfig().getInt("map" + game + ".cell" + cellnumb + ".z");
        	yaw = Main.plugin.getConfig().getInt("map" + game + ".cell" + cellnumb + ".yaw");
        	pitch = Main.plugin.getConfig().getInt("map" + game + ".cell" + cellnumb + ".pitch");
        	loc = new Location(Bukkit.getWorld(Main.plugin.getConfig().getString("map" + game + ".world")), x, y, z, yaw, pitch);
    		Main.VM.addLocation(p, loc);
        }
        p.teleport(loc);
        ItemStack shovel = new ItemStack(Material.WOOD_SPADE, 1);
        for (int j = 0; j < 36; j++)
      	  p.getInventory().setItem(j, shovel);
        p.getInventory().clear();
        p.getInventory().setArmorContents(null);
        ItemStack food = new ItemStack(Material.COOKED_BEEF, 10);
        p.getInventory().addItem(new ItemStack[] { food });
        InGameGUI.IGWatch(p);
        ItemsGUI.IWatch(p);
    	if (p.hasPermission("ProCandr.modperms"))
    		ModGUI.ModWatch(p);
        
        p.sendMessage(ChatColor.DARK_GREEN + "Hi " + p.getName() + "! You just joined the cops and robbers game " + game + "! If there is no cop yet, type /cr join cops or click the sword in the clock gui. Respect the rules"
        		+ " and have fun!");
	}
}
