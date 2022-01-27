package com.inceris.runecraftitems;
import java.util.ArrayList;

import org.bukkit.entity.Player;

public class RCIPlayer {
	
	public static ArrayList<RCIPlayer> players = new ArrayList<RCIPlayer>();
	
	public boolean healOnCooldown = false;

	Player player;
	
	public RCIPlayer(Player Player) {
		player = Player;
	}
	
	public static RCIPlayer getRCIPlayer(Player p) {
		for (RCIPlayer rcip : players) {
			if (rcip.player.getUniqueId().equals(p.getUniqueId())) {
				return rcip;
			}
		}
		return null;
	}
	
}
