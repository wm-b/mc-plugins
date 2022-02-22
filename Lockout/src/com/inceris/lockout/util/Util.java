package com.inceris.lockout.util;

import java.util.List;

import org.bukkit.ChatColor;

import com.inceris.lockout.Lockout;

public class Util {

	private static Lockout pl = (Lockout) Lockout.getPlugin(Lockout.class);
	
	public static void initialise() {

		List<String> WorldsStrings = pl.getConfig().getStringList("Worlds");
		
		for (String s : WorldsStrings) {
			if (pl.getServer().getWorld(s) != null) {
				pl.gameInstances.add(new GameInstance(pl.getServer().getWorld(s), false, 0));
			}
		}
		
	}
	
	public static int randomNumberBetween(int min, int max) {
		return min + (int)(Math.random() * ((max - min) + 1));
	}
	
	public static boolean percentChance(double percent) {
		double r = Math.random();
		if (r <= (percent / 100)) {
			return true;
		} else {
			return false;
		}
	}
	
	public static GameInstance chooseWorld() {
		int i = 0;
		while (true) {
			int r = randomNumberBetween(1, pl.gameInstances.size());
			int j = 1;
			for (GameInstance gw : pl.gameInstances) {
				if (r == j && gw.isActive() == false) {
					return gw;
				}
				j++;
			}
			if (i > 100) {
				break;
			}
			i++;
		}
		return null;
	}
	
	public static String format(String message) {
		return ChatColor.translateAlternateColorCodes('&',
				"&8[&9&lLockout&8] &f" + message);
	}
	
}
