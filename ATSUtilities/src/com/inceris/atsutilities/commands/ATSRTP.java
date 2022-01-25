package com.inceris.atsutilities.commands;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import com.inceris.atsutilities.ATSUtilities;

public class ATSRTP {
	
	private static ATSUtilities atsu = ATSUtilities.getPlugin(ATSUtilities.class);

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

	public static void cmd(String[] args) {
		Bukkit.getScheduler().runTaskAsynchronously(atsu, new Runnable() {
			@Override
			public void run() {
				int x = 0;
				int z = 0;
				short i;
				Location destination;
				World world = atsu.getServer().getWorld(args[1]);
				Player player = atsu.getServer().getPlayer(args[0]);

				if (args[3].toLowerCase().equals("random")) {
					x = getRandomNumber(-Integer.parseInt(args[2]), Integer.parseInt(args[2]));
					z = getRandomNumber(-Integer.parseInt(args[2]), Integer.parseInt(args[2]));

					for (i = 0; i < 10000; i++) {

						x = getRandomNumber(-Integer.parseInt(args[2]), Integer.parseInt(args[2]));
						z = getRandomNumber(-Integer.parseInt(args[2]), Integer.parseInt(args[2]));

						destination = GetHighestBlock(new Location(world, x, 255, z, 0, 0));
						if (CheckLocation(destination)) {
							TPPlayer(atsu, player, destination);
							break;
						}
					}
					if (i == 10000)
						player.sendMessage("[ATSRTP] Could not find a spot.");
				} else {

					String biomes[] = args[3].toLowerCase().split(",");

					for (i = 0; i < 10000; i++) {
						x = getRandomNumber(-Integer.parseInt(args[2]), Integer.parseInt(args[2]));
						z = getRandomNumber(-Integer.parseInt(args[2]), Integer.parseInt(args[2]));
						destination = new Location(world, x, 255, z, 0, 0);
						if (Arrays.asList(biomes)
								.contains(destination.getBlock().getBiome().toString().toLowerCase())) {
							destination = GetHighestBlock(destination);
							if (CheckLocation(destination)) {
								TPPlayer(atsu, player, destination);
								break;
							}
						}
					}
					if (i == 10000)
						player.sendMessage("[ATSRTP] Could not find the biome.");
				}
			}
		});
	}
}
