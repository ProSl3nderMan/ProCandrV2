package me.ProSl3nderMan.Managers;

import me.ProSl3nderMan.Main.Main;
import me.ProSl3nderMan.Menus.ModGUI;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class ModTpManager {
	
	public ModTpManager(Main plugin) {}
	
	public static void ModTp(Player p, String game) {
		 int x = Main.plugin.getConfig().getInt("map" + game + ".cop.x");
         int y = Main.plugin.getConfig().getInt("map" + game + ".cop.y");
         int z = Main.plugin.getConfig().getInt("map" + game + ".cop.z");
         p.teleport(new Location(Bukkit.getWorld(Main.plugin.getConfig().getString("map" + game + ".world")), x, y, z));
         Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "v " + p.getName());
         p.setGameMode(GameMode.CREATIVE);
    	if (p.hasPermission("ProCandr.modperms"))
    		ModGUI.ModWatch(p);
	}
}
