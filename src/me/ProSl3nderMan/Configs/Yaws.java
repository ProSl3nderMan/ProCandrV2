package me.ProSl3nderMan.Configs;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;

import me.ProSl3nderMan.Main.Main;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class Yaws {
	private FileConfiguration customConfig = null;
	private File customConfigFile = null;
	
	public void reloadYaws() {
		if (customConfigFile == null) {
		    customConfigFile = new File(Main.plugin.getDataFolder(), "yaws.yml");
		}
		customConfig = YamlConfiguration.loadConfiguration(customConfigFile);
		Reader defConfigStream = null;
		try {
			defConfigStream = new InputStreamReader(Main.plugin.getResource("yaws.yml"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		if (defConfigStream != null) {
		    YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
		    customConfig.setDefaults(defConfig);
		}
	}
	public FileConfiguration getYaws() {
		if (customConfig == null) {
	        reloadYaws();
	    }
	    return customConfig;
	}
	public void saveYaws() {
		if (customConfig == null || customConfigFile == null) {
	        return;
	    }
	    try {
	        getYaws().save(customConfigFile);
	    } catch (IOException ex) {
	        Main.plugin.getLogger().log(Level.SEVERE, "Could not save config to " + customConfigFile, ex);
	    }
	}
	public void srYaws() {
		saveYaws();
		reloadYaws();
	}
	public void saveDefaultYaws() {
	    if (customConfigFile == null) {
	        customConfigFile = new File(Main.plugin.getDataFolder(), "yaws.yml");
	    	Main.plugin.getLogger().info("Generating config.yml");
	    	getYaws().options().copyDefaults(true);
	    	saveDefaultYaws();
	      
	    	Main.plugin.getLogger().info("yaws.yml has been created!");
	    }
	    if (!customConfigFile.exists()) {            
	         Main.plugin.saveResource("yaws.yml", false);
	     }
	}
}
