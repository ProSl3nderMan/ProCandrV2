package me.ProSl3nderMan.Managers;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.ProSl3nderMan.Main.Main;
import me.ProSl3nderMan.Menus.InGameGUI;

public class JoinCopsManager {

	public JoinCopsManager(Main plugin) {}
	
	public static void JoinCops(Player p, String game) {
		if (VariableManager.coptimer.get(game).equalsIgnoreCase("no")) {
				int x = Main.plugin.getConfig().getInt("map" + game + ".cop.x");
				int y = Main.plugin.getConfig().getInt("map" + game + ".cop.y");
				int z = Main.plugin.getConfig().getInt("map" + game + ".cop.z");
				int yaw = Main.plugin.getConfig().getInt("map" + game + ".cop.yaw");
				int pitch = Main.plugin.getConfig().getInt("map" + game + ".cop.pitch");
				Location loc = new Location(Bukkit.getWorld(Main.plugin.getConfig().getString("map" + game + ".world")), x, y, z, yaw, pitch);
				Main.VM.addLocation(p, loc);
				p.teleport(loc);
				p.sendMessage(ChatColor.DARK_GREEN + "You are now a cop! Don't let them escape!");
				int CopCount = VariableManager.copamount.get(game);
				VariableManager.copamount.put(game , (CopCount + 1));
				VariableManager.cop.put(p.getName() , true);
				List<String> names = VariableManager.cops.get(game);
				names.add(p.getName());
				VariableManager.cops.put(game , names);
				List<String> names2 = VariableManager.players.get(game);
				names2.remove(p.getName());
				VariableManager.players.put(game , names2);
                
				ItemStack helm = new ItemStack(Material.DIAMOND_HELMET, 1);
				helm.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
				helm.addEnchantment(Enchantment.DURABILITY, 3);
				ItemStack chestplate = new ItemStack(Material.DIAMOND_CHESTPLATE, 1);
				chestplate.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
				chestplate.addEnchantment(Enchantment.DURABILITY, 3);
            	ItemStack leggings = new ItemStack(Material.DIAMOND_LEGGINGS, 1);
            	leggings.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
            	leggings.addEnchantment(Enchantment.DURABILITY, 3);
            	ItemStack boots = new ItemStack(Material.DIAMOND_BOOTS, 1);
            	boots.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
            	boots.addEnchantment(Enchantment.DURABILITY, 3);
            	ItemStack sword = new ItemStack(Material.DIAMOND_SWORD, 1);
            	sword.addEnchantment(Enchantment.DAMAGE_ALL, 3);
            	ItemStack bow = new ItemStack(Material.BOW, 1);
            	bow.addEnchantment(Enchantment.ARROW_DAMAGE, 1);
            	bow.addEnchantment(Enchantment.ARROW_INFINITE, 1);
            	ItemStack stick = new ItemStack(Material.STICK, 1);
            	stick.addUnsafeEnchantment(Enchantment.KNOCKBACK, 2);
            	ItemStack arrow = new ItemStack(Material.ARROW, 1);
            	ItemStack food = new ItemStack(Material.COOKED_BEEF, 10);
            	p.getInventory().addItem(new ItemStack[] { food });
            	p.getInventory().setHelmet(helm);
            	p.getInventory().setChestplate(chestplate);
            	p.getInventory().setLeggings(leggings);
            	p.getInventory().setBoots(boots);
            	p.getInventory().addItem(new ItemStack[] { sword });
            	p.getInventory().addItem(new ItemStack[] { stick });
            	p.getInventory().addItem(new ItemStack[] { bow });
            	p.getInventory().addItem(new ItemStack[] { arrow });
                InGameGUI.IGWatch(p);
            	p.setPlayerListName(ChatColor.BLUE + p.getName());
            	VariableManager.copkickvote.put(p.getName(), 0);
	            List<String> reset = VariableManager.players.get("");
	        	reset = new ArrayList<String>();
	        	VariableManager.copkickvoters.put(p.getName(), reset);
                for (String playr : VariableManager.players.get(game)) {
                	Player pl = Bukkit.getPlayer(playr);
                	pl.sendMessage(ChatColor.DARK_RED + p.getName() + ChatColor.DARK_GREEN + ", is now the new cop in game " + game 
                			+ "! Let the game begin!!");
                	pl.sendMessage(ChatColor.DARK_BLUE + "Please listen to the cops orders! Do not abuse of anything and have fun!");
                }
		} else if (VariableManager.coptimer.get(game).equalsIgnoreCase("yes")) {
			if (!(VariableManager.copjoinlist.get(game).contains(p.getName())) || (VariableManager.copjoinlist.get(game).size() == 0)) {
    			VariableManager.copjoinlist.get(game).add(p.getName());
    			p.sendMessage(ChatColor.DARK_GREEN + "You name was added to the list to be Cop this round.");
    		} else {
    			p.sendMessage(ChatColor.DARK_GREEN + "You've already done this! Please wait.");
    		}
		} 
	}
}
