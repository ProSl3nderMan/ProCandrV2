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

public class Itemsconfig {
	private FileConfiguration customConfig = null;
	private File customConfigFile = null;
	
	public void reloadItems() {
		if (customConfigFile == null) {
		    customConfigFile = new File(Main.plugin.getDataFolder(), "items.yml");
		}
		customConfig = YamlConfiguration.loadConfiguration(customConfigFile);
		Reader defConfigStream = null;
		try {
			defConfigStream = new InputStreamReader(Main.plugin.getResource("items.yml"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		if (defConfigStream != null) {
		    YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
		    customConfig.setDefaults(defConfig);
		}
	}
	public FileConfiguration getItems() {
		if (customConfig == null) {
	        reloadItems();
	    }
	    return customConfig;
	}
	public void saveItems() {
		if (customConfig == null || customConfigFile == null) {
	        return;
	    }
	    try {
	        getItems().save(customConfigFile);
	    } catch (IOException ex) {
	        Main.plugin.getLogger().log(Level.SEVERE, "Could not save config to " + customConfigFile, ex);
	    }
	}
	public void srItems() {
		saveItems();
		reloadItems();
	}
	public void saveDefaultItems() {
	    if (customConfigFile == null) {
	        customConfigFile = new File(Main.plugin.getDataFolder(), "items.yml");
	    	Main.plugin.getLogger().info("Generating items.yml");
	    	getItems().options().copyDefaults(true);
	    	saveDefaultItems();
	      
	    	Main.plugin.getLogger().info("items.yml has been created!");
	    }
	    if (!customConfigFile.exists()) {            
	         Main.plugin.saveResource("items.yml", false);
	     }
	}
}
