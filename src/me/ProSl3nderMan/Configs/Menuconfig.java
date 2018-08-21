package me.ProSl3nderMan.Configs;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import me.ProSl3nderMan.Main.Main;

public class Menuconfig {
	private FileConfiguration customConfig = null;
	private File customConfigFile = null;
	
	public void reloadMenu() {
		if (customConfigFile == null) {
		    customConfigFile = new File(Main.plugin.getDataFolder(), "menu.yml");
		}
		customConfig = YamlConfiguration.loadConfiguration(customConfigFile);
		Reader defConfigStream = null;
		try {
			defConfigStream = new InputStreamReader(Main.plugin.getResource("menu.yml"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		if (defConfigStream != null) {
		    YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
		    customConfig.setDefaults(defConfig);
		}
	}
	public FileConfiguration getMenu() {
		if (customConfig == null) {
	        reloadMenu();
	    }
	    return customConfig;
	}
	public void saveMenu() {
		if (customConfig == null || customConfigFile == null) {
	        return;
	    }
	    try {
	        getMenu().save(customConfigFile);
	    } catch (IOException ex) {
	        Main.plugin.getLogger().log(Level.SEVERE, "Could not save config to " + customConfigFile, ex);
	    }
	}
	public void srMenu() {
		saveMenu();
		reloadMenu();
	}
	public void saveDefaultMenu() {
	    if (customConfigFile == null) {
	        customConfigFile = new File(Main.plugin.getDataFolder(), "menu.yml");
	    	Main.plugin.getLogger().info("Generating config.yml");
	    	getMenu().options().copyDefaults(true);
	    	saveDefaultMenu();
	      
	    	Main.plugin.getLogger().info("menu.yml has been created!");
	    }
	    if (!customConfigFile.exists()) {            
	         Main.plugin.saveResource("menu.yml", false);
	     }
	}
}
