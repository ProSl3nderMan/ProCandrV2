package me.ProSl3nderMan.Main;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;

import me.ProSl3nderMan.Commands.BuddysExecutor;
import me.ProSl3nderMan.Commands.ChestsExecutor;
import me.ProSl3nderMan.Commands.CopKickExecutor;
import me.ProSl3nderMan.Commands.CrExecutor;
import me.ProSl3nderMan.Commands.GameExecutor;
import me.ProSl3nderMan.Commands.GearsExecutor;
import me.ProSl3nderMan.Commands.HatsExecutor;
import me.ProSl3nderMan.Commands.ModExecutor;
import me.ProSl3nderMan.Commands.OpenCellExecutor;
import me.ProSl3nderMan.Commands.SetExecutor;
import me.ProSl3nderMan.Commands.ShopExecutor;
import me.ProSl3nderMan.Commands.StartExecutor;
import me.ProSl3nderMan.Commands.StatsExecutor;
import me.ProSl3nderMan.Commands.TrailsExecutor;
import me.ProSl3nderMan.Configs.Itemsconfig;
import me.ProSl3nderMan.Configs.Menuconfig;
import me.ProSl3nderMan.Configs.Playersconfig;
import me.ProSl3nderMan.Configs.Yaws;
import me.ProSl3nderMan.Events.OnDeath;
import me.ProSl3nderMan.Events.OnEntityInteract;
import me.ProSl3nderMan.Events.OnEntityTargetEvent;
import me.ProSl3nderMan.Events.OnItemDrop;
import me.ProSl3nderMan.Events.OnJoin;
import me.ProSl3nderMan.Events.OnLeave;
import me.ProSl3nderMan.Events.OnMobSpawn;
import me.ProSl3nderMan.Events.OnPlayerMovement;
import me.ProSl3nderMan.Events.OnPressure;
import me.ProSl3nderMan.Events.OnRightClick;
import me.ProSl3nderMan.Managers.CratesManager;
import me.ProSl3nderMan.Managers.JoinCopsManager;
import me.ProSl3nderMan.Managers.JoinGameManager;
import me.ProSl3nderMan.Managers.LeaveGameManager;
import me.ProSl3nderMan.Managers.LeaveServerManager;
import me.ProSl3nderMan.Managers.ModTpManager;
import me.ProSl3nderMan.Managers.NanoManager;
import me.ProSl3nderMan.Managers.OpenCellManager;
import me.ProSl3nderMan.Managers.PartManager;
import me.ProSl3nderMan.Managers.SetObjectManager;
import me.ProSl3nderMan.Managers.VariableManager;
import me.ProSl3nderMan.Managers.WinGameManager;
import me.ProSl3nderMan.Menus.EventGUIHandler;
import me.ProSl3nderMan.Menus.InGameGUI;
import me.ProSl3nderMan.Menus.ItemsGUI;
import me.ProSl3nderMan.Menus.LobbyGUI;
import net.milkbowl.vault.economy.Economy;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class Main extends JavaPlugin {
	public static Main plugin;
	public static Economy econ = null;
	protected MainLogger log;
	public static Playersconfig PlayersConfig = new Playersconfig();
	public static Menuconfig MenuConfig = new Menuconfig();
	public static Itemsconfig ItemsConfig = new Itemsconfig();
	public static VariableManager VM = new VariableManager();
	public static Yaws Yaw = new Yaws();
	public static NanoManager NM = new NanoManager();
	public static PartManager PM = new PartManager();
  
	public static void OpenCellNo() {
		Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
			@Override
			public void run() {
				for (int aaa = 1 ; aaa < Main.plugin.getConfig().getInt("nextmap"); aaa++) {
					VariableManager.opencell.put(aaa + "", "no");
				}
	        }
			}, 60L);
	}
  
  
  
	public void onEnable() {
		plugin = this;
		
    
		getCommand("cr").setExecutor(new CrExecutor(this));
		getCommand("set").setExecutor(new SetExecutor(this));
		getCommand("open").setExecutor(new OpenCellExecutor(this));
		getCommand("mod").setExecutor(new ModExecutor(this));
		getCommand("cop").setExecutor(new CopKickExecutor(this));
		getCommand("start").setExecutor(new StartExecutor(this));
		getCommand("game").setExecutor(new GameExecutor(this));
		getCommand("stats").setExecutor(new StatsExecutor(this));
		getCommand("hats").setExecutor(new HatsExecutor(this));
		getCommand("trails").setExecutor(new TrailsExecutor(this));
		getCommand("buddys").setExecutor(new BuddysExecutor(this));
		getCommand("shop").setExecutor(new ShopExecutor(this));
		getCommand("gears").setExecutor(new GearsExecutor(this));
		getCommand("chests").setExecutor(new ChestsExecutor(this));
    
		new CrExecutor(this);
		new SetExecutor(this);
		new OpenCellExecutor(this);
		new ModExecutor(this);
		new CopKickExecutor(this);
		new StartExecutor(this);
		new GameExecutor(this);	
		new StatsExecutor(this);
		new HatsExecutor(this);
		new TrailsExecutor(this);
		new BuddysExecutor(this);
		new ShopExecutor(this);
		new GearsExecutor(this);
		new ChestsExecutor(this);
		
		new JoinGameManager(this);
		new JoinCopsManager(this);
		new LeaveGameManager(this);
		new WinGameManager(this);
		new SetObjectManager(this);
		new ModTpManager(this);
		new OpenCellManager(this);
		new LeaveServerManager(this);
		new VariableManager(this);
		new CratesManager(this);
		
		new OnDeath(this);
		new OnPressure(this);
		new OnItemDrop(this);
		new OnJoin(this);
		new OnRightClick(this);
		new OnLeave(this);
		new OnPlayerMovement(this);
		new OnEntityTargetEvent(this);
		new OnMobSpawn(this);
		new OnEntityInteract(this);
		
		new EventGUIHandler(this);
		new LobbyGUI(this);
		new InGameGUI(this);
		new ItemsGUI(this);
		
		new Playersconfig();
		new Menuconfig();
		new Itemsconfig();
    
		File file = new File(getDataFolder() + File.separator + "config.yml");
		if (!file.exists()) {
			getLogger().info("Generating config.yml");
			getConfig().options().copyDefaults(true);
			saveDefaultConfig();
      
			getLogger().info("Config.yml has been created! Please add world names in config!");
		}
		
		getConfig().options().copyDefaults(true);
		saveDefaultConfig();
		PlayersConfig.saveDefaultPlayers();
		MenuConfig.saveDefaultMenu();
		ItemsConfig.saveDefaultItems();
		Yaw.saveDefaultYaws();
		
		PlayersConfig.reloadPlayers();
		MenuConfig.reloadMenu();
		ItemsConfig.reloadItems();
		Yaw.reloadYaws();

		for (int aaa = 1 ; aaa < getConfig().getInt("nextmap"); aaa++) {
			VariableManager.restarting.put("" + aaa , "no");
			VariableManager.playeramount.put("" + aaa , 0);
			VariableManager.opencell.put("" + aaa , "no");
			VariableManager.copamount.put("" + aaa , 0);
			VariableManager.coptimer.put("" + aaa , "no");
			List<String> game = VariableManager.players.get("");
			game = new ArrayList<String>();
			VariableManager.players.put("" + aaa, game);
			VariableManager.cops.put("" + aaa, game);
			VariableManager.copjoinlist.put("" + aaa, game);
			VM.resRG.put(aaa + "", VM.getRestrictRegion(aaa + ""));
		}
		
		if (!setupEconomy()) {
			getLogger().info(String.format("[%s] - Disabled due to no Vault dependency found!", new Object[] { getDescription().getName() }));
			getServer().getPluginManager().disablePlugin(this);
			return;
		}
		
		Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.plugin, new Runnable() {
			@Override
			public void run() {
				int ng = getConfig().getInt("nextmap");
    			OpenCellNo();
    			for (int aaa = 1; aaa < ng; aaa++) {
    				VariableManager.opencell.put(aaa + "" , "yes");
    				Bukkit.broadcastMessage(ChatColor.GOLD + "Do " + ChatColor.RED + "/open cell " + ChatColor.GOLD + "in game " + aaa + " to open the cells quickly!");
    			}
			}
		}, 6000L, 6000L);
		
		new BukkitRunnable() {
			public void run() {
				for (World w : Bukkit.getWorlds()) {
					for (Entity e : w.getEntities()) {
						if (!(e instanceof Player) && !VM.nano.containsValue(e))
							e.remove();
					}
				}
			}
		}.runTaskTimer(this, 0L, 600L);
		
		loadSkulls();
		
		if (!Yaw.getYaws().contains("cos") || !Yaw.getYaws().contains("sin")) {
			new BukkitRunnable() {
				public void run() {
					saveYaws();
				}
			}.runTaskLater(this, 200L);
		}
		
		if (!getConfig().contains("cops.banned.hours"))
			return;
		for (String s : getConfig().getConfigurationSection("cops.banned.hours").getKeys(false)) {
			hourKeeper(getConfig().getInt("cops.banned.hours." + s), s);
		}
		for (String s : getConfig().getConfigurationSection("cops.banned.days").getKeys(false)) {
			getConfig().set("cops.banned.days." + s, getConfig().getInt("cops.banned.days" + s) - 1);
			saveConfig();
			reloadConfig();
			if (getConfig().getInt("cops.banned.days." + s) == 0) {
				getConfig().set("cops.banned.days." + s, null);
				saveConfig();
				reloadConfig();
			}
		}
	}
	
	public void loadSkulls() {
		for (String s : Main.MenuConfig.getMenu().getStringList("menus.buddymenu.buddys")) {
			String[] parts = s.split("/");
			String skullOwner = parts[0]; //the skin owner's in game name.
			ItemStack itemSkull = new ItemStack(Material.SKULL_ITEM, 1, (short)3);
		    SkullMeta metaSkull = (SkullMeta)itemSkull.getItemMeta();
			metaSkull.setOwner(skullOwner);
			itemSkull.setItemMeta(metaSkull);
			VM.skull.put(s, itemSkull);
		}
	}
	
	private HashMap<String, Integer> dummy = new HashMap<String, Integer>();
	public void saveYaws() {
		Bukkit.broadcastMessage("start yaws");
		dummy.put("yaw", 1);
		new BukkitRunnable() {
			public void run() {
				if (dummy.get("yaw") == 3600) {
					Yaw.srYaws();
					cancel();
					return;
				}
				int yaw = dummy.get("yaw");
				
				Bukkit.getLogger().log(Level.SEVERE, yaw + "");
				double math = (yaw/10) - 180;
				Yaw.getYaws().set("cos." + yaw, Math.cos(Math.toRadians(math)));
				Yaw.getYaws().set("sin." + yaw, Math.sin(Math.toRadians(math)));
				dummy.put("yaw", yaw + 1);
			}
		}.runTaskTimer(this, 20L, 1L);
		Bukkit.broadcastMessage("end yaws");
	}
	
	public void hourKeeper(Integer t, final String s) {
		new BukkitRunnable() {
			public void run() {
				getConfig().set("cops.banned.hours." + s, null);
				saveConfig();
				reloadConfig();
			}
		}.runTaskLater(plugin, t);
	}
	
	private boolean setupEconomy() {
		if (getServer().getPluginManager().getPlugin("Vault") == null) {
			return false;
		}
		RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
		if (rsp == null) {
			return false;
		}
		econ = (Economy)rsp.getProvider();
		return econ != null;
	}
	
	public Location getSpawn() {
		int x = Main.plugin.getConfig().getInt("lobby.x");
        int y = Main.plugin.getConfig().getInt("lobby.y");
        int z = Main.plugin.getConfig().getInt("lobby.z");
        int yaw = Main.plugin.getConfig().getInt("lobby.yaw");
        int pitch = Main.plugin.getConfig().getInt("lobby.pitch");
		Location loc = new Location(Bukkit.getWorld(Main.plugin.getConfig().getString("lobby.world")), x, y, z, yaw, pitch);
		
		return loc;
	}
	
	public Location getLocFromString(String s) {
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
	
	public String getStringFromLoc(final Location l) {
	    if (l == null) {
	        return "";
	    }
	    return l.getWorld().getName() + ";" + l.getBlockX() + ";" + l.getBlockY() + ";" + l.getBlockZ();
	}
	
	public void sendGameMessage(String game, String message) {
		for (String player : VariableManager.players.get(game))
			Bukkit.getPlayer(player).sendMessage(message);
	}
  
	public void onDisable() {
		saveConfig();
		reloadConfig();
		if (VM.cooldown.size() != 0) {
			for (String s : Main.VM.cooldown.keySet()) {
				getConfig().set("cops.banned.hours." + s, VM.cooldown.get(s));
			}
			saveConfig();
			reloadConfig();
		}
	}
}
