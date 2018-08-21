package me.ProSl3nderMan.Commands;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.ProSl3nderMan.Main.Main;
import me.ProSl3nderMan.Managers.SetObjectManager;
import net.md_5.bungee.api.ChatColor;

public class SetExecutor implements CommandExecutor {
	
	public SetExecutor(Main plugin) {}
	
	public static HashMap<String, List<Integer>> test = new HashMap<String , List<Integer>>();
	
	public static int getInt(String string) {
		try {
			return Integer.parseInt(string);
		} catch (NumberFormatException nFE) {
			return 0;
		}
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;
		if (p.hasPermission("ProCandr.adminperms")) {
			int ng = Main.plugin.getConfig().getInt("nextmap");
			if (args[0].equalsIgnoreCase("lobby")) {
				Main.plugin.getConfig().set("lobby.x" , p.getLocation().getX());
				Main.plugin.getConfig().set("lobby.y" , p.getLocation().getY());
				Main.plugin.getConfig().set("lobby.z" , p.getLocation().getZ());
				Main.plugin.getConfig().set("lobby.yaw" , p.getLocation().getYaw());
				Main.plugin.getConfig().set("lobby.pitch" , p.getLocation().getPitch());
				Main.plugin.getConfig().set("lobby.world", p.getWorld().getName());
				Main.plugin.saveConfig();
				Main.plugin.reloadConfig();
				p.sendMessage(ChatColor.DARK_GREEN + "Lobby has been set!");
			}
			if (args[0].equalsIgnoreCase("cell")) {
				if (getInt(args[1]) >= ng) {
					p.sendMessage(ChatColor.DARK_GREEN + "Incorrect map: " + args[1]);
				} 
				if (getInt(args[1]) < ng) {
					SetObjectManager.SetCell(p, args[1], args[2]);
				}
			}
			if (args[0].equalsIgnoreCase("cop")) {
				if (getInt(args[1]) >= ng) {
					p.sendMessage(ChatColor.DARK_GREEN + "Incorrect map: " + args[1]);
				} else if (getInt(args[1]) < ng) {
					SetObjectManager.SetCopSpawn(p, args[1]);
				}
			}
			if (args[0].equalsIgnoreCase("door")) {
				if (getInt(args[1]) >= ng) {
					p.sendMessage(ChatColor.DARK_GREEN + "Incorrect map: " + args[1]);
				} else if (getInt(args[1]) < ng) {
					SetObjectManager.SetDoor(p, args[1], args[2]);
				}
			}
			if (args[0].equalsIgnoreCase("pressure")) {
				if (getInt(args[1]) >= ng) {
					p.sendMessage(ChatColor.DARK_GREEN + "Incorrect map: " + args[1]);
				} else if (getInt(args[1]) < ng) {
					SetObjectManager.SetPressurePlate(p, args[1]);
				}
			}
			if (args[0].equalsIgnoreCase("cellamount")) {
				if (getInt(args[1]) >= ng ) {
					p.sendMessage(ChatColor.DARK_GREEN + "Incorrect map: " + args[1]);
				} else if (getInt(args[1]) < ng) {
					SetObjectManager.SetCellamount(p, args[1], args[2]);
				}
			}
			if (args[0].equalsIgnoreCase("rg")) {
				if (getInt(args[2]) >= ng ) {
					p.sendMessage(ChatColor.DARK_GREEN + "Incorrect map: " + args[1]);
					return true;
				}
				if (!args[3].equalsIgnoreCase("block1") && !args[3].equalsIgnoreCase("block2")) {
					p.sendMessage(ChatColor.DARK_GREEN + "Argument 4 needs to be either block1 or block2, not '" + args[3] + "'.");
					return true;
				}
				if (args[1].equalsIgnoreCase("win"))
					SetObjectManager.SetWinRg(p, args[2], args[3], p.getTargetBlock((Set<Material>) null, 100));
				if (args[1].equalsIgnoreCase("restrict"))
					SetObjectManager.SetRestrictRg(p, args[2], args[3], p.getTargetBlock((Set<Material>) null, 100));
			}
		} else {
			p.sendMessage(ChatColor.DARK_RED + "You do not have permission to this command!");
		}
		return true;
	}
}
