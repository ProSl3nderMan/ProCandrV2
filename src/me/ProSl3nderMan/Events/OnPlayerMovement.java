package me.ProSl3nderMan.Events;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.HashMap;

import me.ProSl3nderMan.Main.Main;
import me.ProSl3nderMan.Managers.VariableManager;
import net.md_5.bungee.api.ChatColor;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class OnPlayerMovement implements Listener {
	public OnPlayerMovement (Main main) {
		main.getServer().getPluginManager().registerEvents(this, main);
	}
	
	public boolean ifContains(String[] s, String k) {
		Boolean test = Boolean.valueOf(false);
	    for (int i = 0; i < s.length; i++) {
	    	if (s[i].equalsIgnoreCase(k)) {
	    		return true;
	    	}
	    }
	    return test.booleanValue();
	}
	
	
	public void changeBlock(final Player p, final Location loc, final Block block) {
		new BukkitRunnable() {
			@SuppressWarnings("deprecation")
			public void run() {
				p.sendBlockChange(loc, block.getType(), block.getData());
			}
		}.runTaskLater(Main.plugin, 40L);
	}
	
	@EventHandler
	public void whenInRestArea(PlayerMoveEvent e) {
		Player p = (Player) e.getPlayer();
		if (!VariableManager.cop.containsKey(p.getName()))
			return;
		if (VariableManager.cop.get(p.getName())) {
			if (Main.VM.resRG.get(VariableManager.game.get(p.getName())).contains(e.getTo())) {
				e.setCancelled(true);
				p.sendMessage(ChatColor.RED + "Cops are not allowed to go in this area!");
				return;
			}
		}
	}
	
	public HashMap<Location, Material> res = new HashMap<Location, Material>();
	@SuppressWarnings("deprecation")
	@EventHandler
    public void trails(PlayerMoveEvent e) {
        Player p = (Player) e.getPlayer();
        if(VariableManager.trail.containsKey(p.getName())) {
        	Material m = p.getLocation().subtract(0.0D, 1.0D, 0.0D).getBlock().getType();
            String numbers = "0,6,8,9,10,11,26,27,28,30,31,32,34,37,38,39,40,44,50,51,55,53,59,63,64,65,66,67,68,69,70,71,72,75,76,77,83,85,92,93,94,96,101,102,104,105,106,107,108,109,111,113,114,115,126,128,131,132,134,135,136,139,140,143,141,142,144,147,148,149,150,151,156,157,160,163,164,167,171,175,176,177,178,180,182,183,184,185,186,187,188,189,190,191,192,193,194,195,196,197,397";
            String[] numberlist = numbers.split(",");
            if (!ifContains(numberlist, String.valueOf(m.getId()))) {
            	ItemStack item = (ItemStack)VariableManager.trail.get(p.getName());
            	Byte data = Byte.valueOf(item.getData().getData());
            	Location loc = p.getLocation().subtract(0.0D, 1.0D, 0.0D);
            	Block block = loc.getBlock();
            	p.sendBlockChange(loc, item.getType(), data.byteValue());
            	changeBlock(p, loc, block);
            	for (Player k : Bukkit.getOnlinePlayers()) {
            		if (!VariableManager.hidetrail.contains(k.getName())) {
            			k.sendBlockChange(loc, item.getType(), data.byteValue());
            			changeBlock(k, loc, block);
            		}
            	}
            }
        }
	}
	

	@EventHandler
    public void nanos(PlayerMoveEvent e) {
		Player p = (Player) e.getPlayer();								
        if (!Main.VM.nano.containsKey(p.getName())) return;
        Location to = p.getLocation().clone();
        
        DecimalFormat df = new DecimalFormat("#.#");
		df.setRoundingMode(RoundingMode.CEILING);
        int yaw = Math.round(p.getLocation().getYaw() * 10);
        if (p.getLocation().getYaw() < 0) { //if the yaw is negative
        	yaw = 360-((int) Math.sqrt(p.getLocation().getYaw()*p.getLocation().getYaw()));
        	yaw = Math.round(yaw * 10);
        }
        double x = Main.Yaw.getYaws().getDouble("cos." + yaw) * 1;
        double z = Main.Yaw.getYaws().getDouble("sin." + yaw) * 1;
        /*
    	ArmorStand m = (ArmorStand) Main.VM.nano.get(p.getName());
        if (e.getFrom().getX() != e.getTo().getX() || e.getFrom().getZ() != e.getTo().getZ()) {
        	if (!Main.VM.flying.containsKey(m)) {
            	Main.VM.flying.put(m, true);
            	Main.NM.startFlying(p);
        	}
        	Main.VM.flying.put(m, true);
        } else
        	Main.VM.flying.put(m, false);
        */
        
        if (e.getFrom().getY() != e.getTo().getY()) {
        	Main.VM.floating.put(Main.VM.nano.get(p.getName()), false);
        	double y = p.getLocation().getY() + .8; 
        	to.setY(y);
        } else {
        	Main.VM.floating.put(Main.VM.nano.get(p.getName()), true);
        	double y = Main.VM.nano.get(p.getName()).getLocation().getY(); 
        	to.setY(y);
        }
        to.add(x, 0, z);
        to.setDirection(p.getLocation().getDirection());
        Main.VM.nano.get(p.getName()).teleport(to);
	}
}