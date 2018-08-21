package me.ProSl3nderMan.Managers;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import me.ProSl3nderMan.Commands.SetExecutor;
import me.ProSl3nderMan.Main.Main;
import net.md_5.bungee.api.ChatColor;

public class SetObjectManager {
	
	public SetObjectManager(Main plugin) {}
	
	public static void SetCell(Player p, String map, String cell) {
		Main.plugin.getConfig().set("map" + map + ".cell" + cell + ".x" , p.getLocation().getX());
		Main.plugin.getConfig().set("map" + map + ".cell" + cell + ".y" , p.getLocation().getY());
		Main.plugin.getConfig().set("map" + map + ".cell" + cell + ".z" , p.getLocation().getZ());
		Main.plugin.getConfig().set("map" + map + ".cell" + cell + ".yaw", p.getLocation().getYaw());
		Main.plugin.getConfig().set("map" + map + ".cell" + cell + ".pitch", p.getLocation().getPitch());
		Main.plugin.getConfig().set("map" + map + ".world", p.getWorld().getName());
		Main.plugin.saveConfig();
		Main.plugin.reloadConfig();
		p.sendMessage(ChatColor.DARK_GREEN + "Location for cell " + cell + " has been set in map " + map + "!");
	}
	public static void SetCopSpawn(Player p, String map) {
		Main.plugin.getConfig().set("map" + map + ".cop.x" , p.getLocation().getX());
		Main.plugin.getConfig().set("map" + map + ".cop.y" , p.getLocation().getY());
		Main.plugin.getConfig().set("map" + map + ".cop.z" , p.getLocation().getZ());
		Main.plugin.getConfig().set("map" + map + ".cop.yaw", p.getLocation().getYaw());
		Main.plugin.getConfig().set("map" + map + ".cop.pitch", p.getLocation().getPitch());
		Main.plugin.getConfig().set("map" + map + ".world", p.getWorld().getName());
		Main.plugin.saveConfig();
		Main.plugin.reloadConfig();
		p.sendMessage(ChatColor.DARK_GREEN + "Location for cop has been set in map " + map + "!");
	}
	public static void SetDoor(Player p, String map, String door) {
		Main.plugin.getConfig().set("map" + map + ".doors.door" + door + ".x" , p.getLocation().getX());
		Main.plugin.getConfig().set("map" + map + ".doors.door" + door + ".y" , p.getLocation().getY());
		Main.plugin.getConfig().set("map" + map + ".doors.door" + door + ".z" , p.getLocation().getZ());
		Main.plugin.saveConfig();
		Main.plugin.reloadConfig();
		p.sendMessage(ChatColor.DARK_GREEN + "Location for door " + door + " has been set in map " + map + "!");
	}
	public static void SetPressurePlate(Player p, String map) {
		Main.plugin.getConfig().set("map" + map + ".cell.pressure.x" , p.getLocation().getX());
		Main.plugin.getConfig().set("map" + map + ".cell.pressure.y" , p.getLocation().getY());
		Main.plugin.getConfig().set("map" + map + ".cell.pressure.z" , p.getLocation().getZ());
		Main.plugin.saveConfig();
		Main.plugin.reloadConfig();
		p.sendMessage(ChatColor.DARK_GREEN + "Location for pressure plate teleporting has been set in map " + map + "!");
	}

	public static void SetCellamount(Player p, String map, String cellamount) {
		Main.plugin.getConfig().set("map" + map + ".cellamount" , SetExecutor.getInt(cellamount));
		Main.plugin.saveConfig();
		Main.plugin.reloadConfig();
		p.sendMessage(ChatColor.DARK_GREEN + "The cellamount has been set to " + cellamount + " in map " + map + "!");
	}

	public static void SetWinRg(Player p, String map, String blocknum, Block targetBlock) {
		Main.plugin.getConfig().set("map" + map + ".rg.win." + blocknum , Main.plugin.getStringFromLoc(targetBlock.getLocation()));
		Main.plugin.saveConfig();
		Main.plugin.reloadConfig();
		p.sendMessage(ChatColor.DARK_GREEN + "The location for " + blocknum + " has been set to '" + Main.plugin.getStringFromLoc(targetBlock.getLocation()) + "' in map " + map + "!");
	}
	
	public static void SetRestrictRg(Player p, String map, String blocknum, Block targetBlock) {
		Main.plugin.getConfig().set("map" + map + ".rg.restrict." + blocknum , Main.plugin.getStringFromLoc(targetBlock.getLocation()));
		Main.plugin.saveConfig();
		Main.plugin.reloadConfig();
		p.sendMessage(ChatColor.DARK_GREEN + "The location for " + blocknum + " has been set to '" + Main.plugin.getStringFromLoc(targetBlock.getLocation()) + "' in map " + map + "!");
	}
}
