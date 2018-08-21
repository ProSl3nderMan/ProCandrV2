package me.ProSl3nderMan.Managers;

import java.util.HashMap;

import me.ProSl3nderMan.Main.Main;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.scheduler.BukkitRunnable;

public class NanoManager {
	
	public HashMap<String, Integer> times = new HashMap<String, Integer>();
	public HashMap<String, String> dirs = new HashMap<String, String>();
	
	public void nanoSendOut(final Player p, ItemStack skull) {
		Location loc = p.getLocation();
		loc.add(0, .8, 0);
		ArmorStand m = (ArmorStand) loc.getWorld().spawn(loc, ArmorStand.class);
		Bukkit.broadcast(m.toString(), "asdfg");
		Main.VM.nano.put(p.getName(), m);
		m.setSmall(true);
		m.setGravity(false);
		m.setBasePlate(false);
		m.setArms(true);
		ItemStack itemSkull = skull;
		m.setHelmet(itemSkull);
		ItemStack leather = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
		LeatherArmorMeta leatherm = (LeatherArmorMeta)leather.getItemMeta();
		leatherm.setColor(Color.fromRGB(170, 170, 170));
		leather.setItemMeta(leatherm);
		m.setChestplate(leather);
		leather = new ItemStack(Material.LEATHER_LEGGINGS, 1);
		leatherm = (LeatherArmorMeta)leather.getItemMeta();
		leatherm.setColor(Color.fromRGB(170, 170, 170));
		leather.setItemMeta(leatherm);
		m.setLeggings(leather);
		leather = new ItemStack(Material.LEATHER_BOOTS, 1);
		leatherm = (LeatherArmorMeta)leather.getItemMeta();
		leatherm.setColor(Color.fromRGB(170, 170, 170));
		leather.setItemMeta(leatherm);
		m.setLeggings(leather);
		Main.VM.floating.put(m, true);
		times.put(p.getName(), 0);
		dirs.put(p.getName(), "up");
		final ArmorStand mm = m;
		new BukkitRunnable() {
			public void run() {
				if (!Main.VM.nano.containsKey(p.getName())) {
					times.remove(p.getName());
					dirs.remove(p.getName());
					Main.VM.floating.remove(mm);
					cancel();
					return;
				}
				if (!Main.VM.floating.get(mm)) {
					times.put(p.getName(), 0);
					dirs.put(p.getName(), "up");
				}
				if (Main.VM.floating.get(mm)) {
					int time = times.get(p.getName());
					String dir = dirs.get(p.getName());
					if (dir.equalsIgnoreCase("up")) {
						times.put(p.getName(), time+1);
						mm.teleport(mm.getLocation().add(0, .1, 0));
					} else if (dir.equalsIgnoreCase("down")) {
						times.put(p.getName(), time-1);
						mm.teleport(mm.getLocation().subtract(0, .1, 0));
					}
					if (time == 0)
						dirs.put(p.getName(), "up");
					else if (time == 6)
						dirs.put(p.getName(), "down");
				}
			}
		}.runTaskTimer(Main.plugin, 40L, 2L);
	}
	
	public void startFlying(final Player p) {
		final ArmorStand m = (ArmorStand) Main.VM.nano.get(p.getName());
		new BukkitRunnable() {
			public void run() {
				if (!Main.VM.nano.containsKey(p.getName())) {
					times.remove(p.getName());
					dirs.remove(p.getName());
					Main.VM.flying.remove(m);
					cancel();
					return;
				}
				if (Main.VM.flying.get(m) && m.getBodyPose().getX() < 1.39) {
					m.setBodyPose(m.getBodyPose().add(.01, 0, 0));
					p.sendMessage(m.getBodyPose().getX() + "");
				} else if (!Main.VM.flying.get(m) && m.getBodyPose().getX() > 0){
					m.setBodyPose(m.getBodyPose().subtract(.01, 0, 0));
					p.sendMessage(m.getBodyPose().getX() + "");
				} else if (m.getBodyPose().getX() < 0.01) {
					cancel();
					Main.VM.flying.remove(m);
					return;
				}
			}
		}.runTaskTimer(Main.plugin, 0L, 2L);
	}
}
