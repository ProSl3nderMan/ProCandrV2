package me.ProSl3nderMan.Managers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import me.ProSl3nderMan.Main.Main;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class VariableManager {
	public VariableManager(Main plugin) {}
	
	public VariableManager() {}

	public static HashMap<String , String> game = new HashMap<String , String>();
	public static HashMap<String, Boolean> cop = new HashMap<String , Boolean>();
	public static HashMap<String, Integer> playeramount = new HashMap<String , Integer>();
	public static HashMap<String, List<String>> players = new HashMap<String , List<String>>();
	public static HashMap<String, Integer> copamount = new HashMap<String , Integer>();
	public static HashMap<String, List<String>> cops = new HashMap<String , List<String>>();
	public static HashMap<String, String> opencell = new HashMap<String , String>();
	public static HashMap<String, String> coptimer = new HashMap<String , String>();
	public static HashMap<String, String> restarting = new HashMap<String , String>();
	public static HashMap<String, List<String>> copjoinlist = new HashMap<String , List<String>>();
	public static HashMap<String, Integer> copkickvote = new HashMap<String , Integer>();
	public static HashMap<String, List<String>> copkickvoters = new HashMap<String , List<String>>();
	public static HashMap<String, ItemStack> trail = new HashMap<String , ItemStack>();
	public static ArrayList<String> hidetrail = new ArrayList<String>();
	public HashMap<String, Integer> cooldown = new HashMap<String, Integer>();
	public HashMap<String, Entity> nano = new HashMap<String, Entity>();
	public HashMap<Entity, Boolean> floating = new HashMap<Entity, Boolean>();
	public HashMap<Entity, Boolean> flying = new HashMap<Entity, Boolean>();
	private HashMap<String, String> locations = new HashMap<String , String>();
	public HashMap<String, ItemStack> skull = new HashMap<String, ItemStack>();
	public HashMap<String, Integer> part = new HashMap<String, Integer>(); //String = map; Integer = part of map. 1 = chopper, 2 = boat part 1, 3 = boat part 2
	public HashMap<String, Cuboid> resRG = new HashMap<String, Cuboid>(); //string = map; cuboid equals the cuboid of the rg restrtict.
	
	public void addLocation(Player p, Location loc) {
		locations.put(p.getName(), getStringLocation(loc));
	}
	
	public void removeLocation(Player p) {
		locations.remove(p.getName());
	}
	
	public Location getLocation(Player p) {
		return getLocationString(locations.get(p.getName()));
	}
	
	
	public String getStringLocation(final Location l) {
	    if (l == null) {
	        return "";
	    }
	    return l.getWorld().getName() + ";" + l.getBlockX() + ";" + l.getBlockY() + ";" + l.getBlockZ();
	}
	
	public Location getLocationString(final String s) {
	    if (s == null || s.trim() == "") {
	        return null;
	    }
	    final String[] parts = s.split(";");
	    if (parts.length == 4) {
	    	final World w = Bukkit.getWorld(parts[0]);
	        int x = Integer.parseInt(parts[1]);
	        final int y = Integer.parseInt(parts[2]);
	        int z = Integer.parseInt(parts[3]);
	        
	        return new Location(w, x, y, z);
	    }
	    return null;
	}
	
	public Cuboid getWinRegion(String map) {
		Location block1 = Main.plugin.getLocFromString(Main.plugin.getConfig().getString("map" + map + ".rg.win.block1"));
		Location block2 = Main.plugin.getLocFromString(Main.plugin.getConfig().getString("map" + map + ".rg.win.block2"));
		return new Cuboid(block1, block2);
	}
	
	public Cuboid getRestrictRegion(String map) {
		if (!Main.plugin.getConfig().contains("map" + map + ".rg.restrict"))
			return null;
		Location block1 = Main.plugin.getLocFromString(Main.plugin.getConfig().getString("map" + map + ".rg.restrict.block1"));
		Location block2 = Main.plugin.getLocFromString(Main.plugin.getConfig().getString("map" + map + ".rg.restrict.block2"));
		return new Cuboid(block1, block2);
	}
	
	public List<String> getPlayersWhoWon(String map, List<String> players) {
		Cuboid rgWin = getWinRegion(map);
		for (String player : players) {
			Player p = Bukkit.getPlayer(player);
			if (!rgWin.contains(p.getLocation()))
				players.remove(player);
		}
		return players;
	}
}
