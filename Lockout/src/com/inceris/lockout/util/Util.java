package com.inceris.lockout.util;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.PortalType;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.inceris.lockout.Lockout;
import com.onarandombox.MultiverseCore.MultiverseCore;
import com.onarandombox.MultiverseCore.api.MVWorldManager;
import com.onarandombox.MultiverseNetherPortals.MultiverseNetherPortals;

import dev.skeens.nametagcolors.NameTagColors;

public class Util {

	public static Lockout pl = Lockout.getPlugin(Lockout.class);
	public static NameTagColors ntc = NameTagColors.getPlugin(NameTagColors.class);
	public static MultiverseCore mv = MultiverseCore.getPlugin(MultiverseCore.class);
	public static MVWorldManager worldManager = mv.getMVWorldManager();
	public static MultiverseNetherPortals np = (MultiverseNetherPortals) Bukkit.getServer().getPluginManager()
			.getPlugin("Multiverse-NetherPortals");
	public static ItemStack compass = compass();
	
	public static ItemStack compass() {
		ItemStack compass = new ItemStack(Material.COMPASS);
		ItemMeta meta = compass.getItemMeta();
		meta.setDisplayName(Util.colours("&fPlayer Tracker"));
		List<String> lore = new ArrayList<String>();
		lore.add(Util.colours("&7Right-click to change targets"));
		meta.setLore(lore);
		compass.setItemMeta(meta);
		return compass;
	}
	
	public static boolean enableHardMode(List<Player> teamB, List<Player> teamE) {
		for (Player p : pl.getHardModeEnabled()) {
			if (teamB.contains(p) || teamE.contains(p)) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean enableHardMode(List<Player> players) {
		for (Player p : pl.getHardModeEnabled()) {
			if (players.contains(p)) {
				return true;
			}
		}
		return false;
	}
	
	public static List<Player> teamB(List<Player> players) {
		boolean flip = true;
		List<Player> teamB = new ArrayList<Player>();
		for (Player p : players) {
			if (flip) {
				teamB.add(p);
			}
			flip = !flip;
		}
		return teamB;
	}
	
	public static List<Player> teamE(List<Player> players) {
		boolean flip = false;
		List<Player> teamE = new ArrayList<Player>();
		for (Player p : players) {
			if (flip) {
				teamE.add(p);
			}
			flip = !flip;
		}
		return teamE;
	}
	
	public static void refreshCompass(GameInstance gi, Player p) {
		for (Player pp : gi.getOpponents(p)) {
			if (gi.getCompassTracking().get(pp).equals(p)) {
				pp.setCompassTarget(p.getLocation());
			}
		}
	}

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

	public static void linkWorldPortals(World world, World nether, World end) {
		np.addWorldLink(world.getName(), nether.getName(), PortalType.NETHER);
		np.addWorldLink(nether.getName(), world.getName(), PortalType.NETHER);
		np.addWorldLink(world.getName(), end.getName(), PortalType.ENDER);
		np.addWorldLink(end.getName(), world.getName(), PortalType.ENDER);
	}

	public static void unlinkWorldPortals(World world, World nether, World end) {
		np.removeWorldLink(world.getName(), nether.getName(), PortalType.NETHER);
		np.removeWorldLink(nether.getName(), world.getName(), PortalType.NETHER);
		np.removeWorldLink(world.getName(), end.getName(), PortalType.ENDER);
		np.removeWorldLink(end.getName(), world.getName(), PortalType.ENDER);
	}

	public static String generateGameName() {
		for (int i = 1; i < 99; i++) {
			boolean nameAvailable = true;
			for (GameInstance gi : pl.getGameInstances()) {
				if (!gi.getGameName().equals("LockoutGameWorld" + i)) {
					nameAvailable = false;
				}
			}
			if (nameAvailable) {
				return "LockoutGameWorld" + i;
			}
		}
		return null;
	}

	public static void winner(List<Player> winningPlayers) {
		List<Player> allPlayers = GameInstance.get(winningPlayers.get(0)).getPlayers();
		for (Player p : allPlayers) {
			if (winningPlayers.contains(p)) {
				p.sendMessage(Util.format("You are the winner!"));
			} else {
				p.sendMessage(Util.format("You have been locked out!"));
			}
		}
	}

	public static void stopGameWithWinner(List<Player> players) {
		winner(players);
		Bukkit.getScheduler().runTaskLater(pl, new Runnable() {
			@Override
			public void run() {
				GameInstance.get(players.get(0)).reset();
			}
		}, 50);
	}

	public static String format(String message) {
		return colours("&8[&9&lLockout&8] &f" + message);
	}

	public static String colours(String message) {
		return ChatColor.translateAlternateColorCodes('&', message);
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

	public static void checkWearingArmorSet(GameInstance gi, Player p, List<Objective> objectives, Objective objective,
			ItemStack[] ac, Material m1, Material m2, Material m3, Material m4) {
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
