package me.ProSl3nderMan.Managers;

import java.util.HashMap;

import me.ProSl3nderMan.Main.Main;
import net.md_5.bungee.api.ChatColor;

import org.bukkit.scheduler.BukkitRunnable;

public class PartManager {
	
	private HashMap<String, Integer> timer = new HashMap<String, Integer>();

	public void managePart(String game, Integer part) {
		if (part == 1)
			doPart1(game);
	}

	private void doPart1(final String game) {
		Main.plugin.sendGameMessage(game, ChatColor.DARK_RED + "A player has started the chopper's engines! The chopper will leave in 1 minute.");
		timer.put(game, 60);
		new BukkitRunnable() {
			public void run() {
				if (timer.get(game) == 0) {
					WinGameManager.processEndGame(game, 1, null);
					cancel();
					return;
				}
				timer.put(game, timer.get(game) -1);
			}
		}.runTaskTimer(Main.plugin, 20L, 20L);
	}
}
