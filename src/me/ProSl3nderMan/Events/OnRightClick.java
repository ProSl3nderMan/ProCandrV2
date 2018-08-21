						package me.ProSl3nderMan.Events;

import java.util.Arrays;

import me.ProSl3nderMan.Main.Main;
import me.ProSl3nderMan.Managers.VariableManager;
import me.ProSl3nderMan.Menus.InGameGUI;
import me.ProSl3nderMan.Menus.ItemsGUI;
import me.ProSl3nderMan.Menus.LobbyGUI;
import me.ProSl3nderMan.Menus.ModGUI;
import net.md_5.bungee.api.ChatColor;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class OnRightClick implements Listener {
	
	public OnRightClick(Main plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onInteract(PlayerInteractEvent event) {
		Player p = event.getPlayer();
	    if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
	    	Block block = event.getClickedBlock();
	    	if (block.getType() == Material.CHEST) {
	    		return;
	    	}
	    	if (block.getType() == Material.SPONGE) {
	    		String game = VariableManager.game.get(p.getName());
	    		if (VariableManager.cop.get(p.getName())) {
	    			p.sendMessage(ChatColor.DARK_RED + "You are a cop! You cannot escape!");
	    			event.setCancelled(false);
	    		} else if (!game.equalsIgnoreCase("0")){
	    			Main.PM.managePart(game, Main.VM.part.get(game));
	    		}
	    	}
	    }
	    if(event.getPlayer().getInventory().getItemInHand().getType() == Material.WATCH && (event.getPlayer().getInventory().getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase("Lobby Commands"))) {
			if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK)
				LobbyGUI.openLobbyGUI(event.getPlayer());
		} else if(event.getPlayer().getInventory().getItemInHand().getType() == Material.WATCH && (event.getPlayer().getInventory().getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase("In Game Commands"))) {
			if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK)
				InGameGUI.openIGGUI(event.getPlayer());
		} else if(event.getPlayer().getInventory().getItemInHand().getType() == Material.WATCH && (event.getPlayer().getInventory().getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase("Mod Commands"))) {
			if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK)
				ModGUI.openModGUI(event.getPlayer());
		} else if(event.getPlayer().getInventory().getItemInHand().getType() == Material.EMERALD && (event.getPlayer().getInventory().getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase("Items"))) {
			if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK)
				ItemsGUI.openIGUI(event.getPlayer());
		}
	}
	
	@EventHandler
	public void onChest(PlayerInteractEvent e) {
		if (e.getAction() != Action.RIGHT_CLICK_BLOCK)
			return;
		if (e.getClickedBlock().getType() != Material.CHEST)
			return;
		Player p = e.getPlayer();
		if (VariableManager.game.containsKey(p.getName()))
			return;
		e.setCancelled(true);
		Inventory inv = Bukkit.createInventory(null, 9, ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "Achievements");
		
		ItemStack perm = new ItemStack(Material.ENDER_CHEST, 1);
		ItemMeta permm = perm.getItemMeta();
		permm.setDisplayName(ChatColor.DARK_RED + "Permanent Achievements");
		permm.setLore(Arrays.asList(ChatColor.RED + "Achievements that'll always be here."));
		perm.setItemMeta(permm);
		inv.setItem(2, perm);
		
		ItemStack weekly = new ItemStack(Material.CHEST, 1);
		ItemMeta weeklym = weekly.getItemMeta();
		weeklym.setDisplayName(ChatColor.DARK_GREEN + "Rare Achievements");
		weeklym.setLore(Arrays.asList(ChatColor.GREEN + "Achievements added by an admin at random times."));
		weekly.setItemMeta(weeklym);
		inv.setItem(6, weekly);
		
		p.openInventory(inv);
	}
	
	@EventHandler(priority = EventPriority.MONITOR)
	public void onSignClick(PlayerInteractEvent e) {
		if (e.getAction() != Action.RIGHT_CLICK_BLOCK)
			return;
		if (e.getClickedBlock().getType() != Material.SIGN && e.getClickedBlock().getType() != Material.WALL_SIGN && e.getClickedBlock().getType() != Material.SIGN_POST)
			return;
		Player p = e.getPlayer();
		Sign sign = (Sign) e.getClickedBlock().getState();
		if (!sign.getLine(0).equalsIgnoreCase("Vip") && !sign.getLine(0).equalsIgnoreCase("Vip+") && !sign.getLine(0).equalsIgnoreCase("Member"))
			return;
		if (sign.getLine(0).equalsIgnoreCase("Vip") && !p.hasPermission("ProCandr.vip")) {
			p.sendMessage(ChatColor.RED + "You must be a Vip to select a cell. Do /donate to become a Vip.");
			return;
		}
		if (sign.getLine(0).equalsIgnoreCase("Vip+") && !p.hasPermission("ProCandr.vip+")) {
			p.sendMessage(ChatColor.RED + "You must be a Vip+ to select a cell. Do /donate to become a Vip+.");
			return;
		}
		if (sign.getLine(0).equalsIgnoreCase("Member")) {
			if (!Main.PlayersConfig.getPlayers().contains("players." + p.getUniqueId() + ".map")) {
				p.sendMessage(ChatColor.RED + "You already have this cell selected!");
				return;
			}
			p.sendMessage(ChatColor.GREEN + "You have selected the cell Member");
			String s = sign.getLine(1).replace("Map ", "");
			Main.PlayersConfig.getPlayers().set("players." + p.getUniqueId() + ".map" + s, null);
			Main.PlayersConfig.srPlayers();
			return;
		}
		String s = sign.getLine(1).replace("Map ", "");
		Main.PlayersConfig.getPlayers().set("players." + p.getUniqueId() + ".map" + s, sign.getLine(0).toLowerCase());
		Main.PlayersConfig.srPlayers();
		p.sendMessage(ChatColor.GREEN + "You have selected the cell " + sign.getLine(0) + ".");
	}
}
