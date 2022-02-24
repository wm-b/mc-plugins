package com.inceris.lockout.util;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.inceris.lockout.Lockout;
import com.onarandombox.MultiverseCore.MultiverseCore;
import com.onarandombox.MultiverseCore.api.MVWorldManager;

public class Util {

	public static Lockout pl = Lockout.getPlugin(Lockout.class);
	public static MultiverseCore mv = MultiverseCore.getPlugin(MultiverseCore.class);
	public static MVWorldManager worldManager = mv.getMVWorldManager();

	public static void l(String s) {
		pl.getLogger().info(Util.format(s));
	}

	public static int randomNumberBetween(int min, int max) {
		return min + (int) (Math.random() * ((max - min) + 1));
	}

	public static boolean percentChance(double percent) {
		double r = Math.random();
		if (r <= (percent / 100)) {
			return true;
		} else {
			return false;
		}
	}

	public static String worldName(Player p1, Player p2) {
		return p1.getName() + "vs" + p2.getName();
	}

	public static void winner(Player p) {
		p.sendMessage(Util.format("You are the winner!"));
	}

	public static void stopGameWithWinner(Player p) {
		winner(p);
		Bukkit.getScheduler().runTaskLater(pl, new Runnable() {
			@Override
			public void run() {
				GameInstance.get(p).reset();
			}
		}, 50);
	}

	public static String format(String message) {
		return ChatColor.translateAlternateColorCodes('&', "&8[&9&lLockout&8] &f" + message);
	}

	public static void checkWearingArmorPiece(GameInstance gi, Player p, List<Objective> objectives,
			Objective objective, ItemStack[] ac, Material m1, Material m2, Material m3, Material m4) {
		if (objectives.contains(objective)) {
			boolean found = false;
			for (ItemStack item : ac) {
				if (item != null) {
					if (item.getType().equals(m1) || item.getType().equals(m2) || item.getType().equals(m3)
							|| item.getType().equals(m4)) {
						found = true;
					}
				}
			}
			if (found) {
				Objective.complete(gi, objective, p);
			}
		}
	}

	public static void checkWearingArmorSet(GameInstance gi, Player p, List<Objective> objectives,
			Objective objective, ItemStack[] ac, Material m1, Material m2, Material m3, Material m4) {
		if (objectives.contains(objective)) {
			boolean found = true;
			for (ItemStack item : ac) {
				if (item != null) {
					if (!(item.getType().equals(m1) || item.getType().equals(m2) || item.getType().equals(m3)
							|| item.getType().equals(m4))) {
						found = false;
					}
				} else {
					found = false;
				}
			}
			if (found) {
				Objective.complete(gi, objective, p);
			}
		}
	}

}
