package com.inceris.runecraftitems.util;

import java.util.ArrayList;
import org.bukkit.entity.Player;

public class RCIPlayer {
	public static ArrayList<RCIPlayer> players = new ArrayList<>();

	public boolean healOnCooldown = false;

	public boolean grapefruitOnCooldown = false;

	Player player;

	public RCIPlayer(Player player) {
		this.player = player;
	}

	public static RCIPlayer getRCIPlayer(Player p) {
		for (RCIPlayer rcip : players) {
			if (rcip.player.getUniqueId().equals(p.getUniqueId()))
				return rcip;
		}
		return null;
	}
}
