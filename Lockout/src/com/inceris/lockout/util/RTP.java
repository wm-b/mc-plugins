package com.inceris.lockout.util;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import com.inceris.lockout.Lockout;

public class RTP {

	public static Lockout pl = Lockout.getPlugin(Lockout.class);

	public static int getRandomNumber(int min, int max) {
		return (int) ((Math.random() * (max - min)) + min);
	}

	public static Location GetHighestBlock(Location l) {
		for (int y = 255; y >= -64; y--) {
			l.setY(y);

			if (l.getBlock().getType().isSolid()) {
				l.setY(y + 1);
				return l;
			}
		}
		return l;
	}

	public static void TPPlayer(Plugin plugin, Player player, Location l) {
		Bukkit.getScheduler().runTask(plugin, new Runnable() {
			@Override
			public void run() {
				l.setX(l.getX() + 0.5);
				l.setZ(l.getZ() + 0.5);
				player.teleport(l);
			}
		});
	}

	public static boolean CheckLocation(Location l) {
		if (!l.getBlock().getType().toString().toLowerCase().equals("water")
				&& !l.getBlock().getType().toString().toLowerCase().equals("lava")
				&& !l.getBlock().getType().toString().toLowerCase().equals("kelp")
				&& !l.getBlock().getType().toString().toLowerCase().equals("kelp_plant")
				&& !l.getBlock().getType().toString().toLowerCase().equals("seagrass")
				&& !l.getBlock().getType().toString().toLowerCase().equals("tall_seagrass")) {
			return true;
		}
		return false;
	}

	// Call this async, you fool
	public static void rtp(Player player, World world, int radius) {
		int x = 0;
		int z = 0;
		short i;
		Location destination;

		x = getRandomNumber(-radius, radius);
		z = getRandomNumber(-radius, radius);

		for (i = 0; i < 10000; i++) {

			x = getRandomNumber(-radius, radius);
			z = getRandomNumber(-radius, radius);

			destination = GetHighestBlock(new Location(world, x, 255, z, 0, 0));
			if (CheckLocation(destination)) {
				TPPlayer(pl, player, destination);
				break;
			}
		}
		if (i == 10000)
			player.sendMessage(Util.format("Could not find a safe location to teleport you to!"));
	}
}
