package com.inceris.runecraftitems.util;

import com.inceris.runecraftitems.RuneCraftItems;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class RTP {
	private static RuneCraftItems rci = (RuneCraftItems) RuneCraftItems.getPlugin(RuneCraftItems.class);

	private static int getRandomNumber(int min, int max) {
		return (int) (Math.random() * (max - min) + min);
	}

	private static Location GetNextHighestBlock(Location l, int radius) {
		for (int y = (int) Math.round(l.getY()) - radius; y <= 255; y++) {
			l.setY(y);
			if (l.getBlock().getType().isSolid()) {
				l.setY(l.getY() + 1.0D);
				if (!l.getBlock().getType().isSolid()) {
					l.setY(l.getY() + 1.0D);
					if (!l.getBlock().getType().isSolid())
						return l;
				}
			}
		}
		return l;
	}

	private static void TPPlayer(Plugin plugin, final Player player, final Location l) {
		Bukkit.getScheduler().runTask(plugin, new Runnable() {
			public void run() {
				l.setX(l.getX() + 0.5D);
				l.setZ(l.getZ() + 0.5D);
				player.teleport(l);
			}
		});
	}

	public static void rtp(Player p, int radius) {
		Location l = p.getLocation();
		Location destination = p.getLocation();
		for (int i = 0; i < 100; i++) {
			destination.setX(destination.getX() + getRandomNumber(-radius, radius));
			destination.setZ(destination.getZ() + getRandomNumber(-radius, radius));
			destination = GetNextHighestBlock(destination, radius);
			if (destination.getY() - l.getY() <= radius || destination.getY() + l.getY() <= radius) {
				TPPlayer((Plugin) rci, p, destination);
				break;
			}
		}
	}
}