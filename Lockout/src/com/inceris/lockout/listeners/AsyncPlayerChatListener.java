package com.inceris.lockout.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import com.inceris.lockout.Lockout;
import com.inceris.lockout.util.GameInstance;
import com.inceris.lockout.util.Util;

public class AsyncPlayerChatListener implements Listener {

	public static Lockout pl = Lockout.getPlugin(Lockout.class);

	public String teamBChatFormat(String s, Player p) {
		return Util.colours("&8[&3TeamChat&8] &b" + p.getName() + " &8&l> &7" + s);
	}

	public String teamEChatFormat(String s, Player p) {
		return Util.colours("&8[&6TeamChat&8] &e" + p.getName() + " &8&l> &7" + s);
	}

	@EventHandler
	public void onAsyncPlayerChat(AsyncPlayerChatEvent e) {

		Player player = e.getPlayer();
		GameInstance gi = GameInstance.get(player);

		if (gi != null) {
			if (gi.getTeamB().contains(player)) {
				e.setCancelled(true);
				String message = teamBChatFormat(e.getMessage(), player);
				for (Player p : gi.getTeamB()) {
					p.sendMessage(message);
					Util.l.info(message);
				}

			} else if (gi.getTeamE().contains(player)) {
				e.setCancelled(true);
				String message = teamEChatFormat(e.getMessage(), player);
				for (Player p : gi.getTeamE()) {
					p.sendMessage(message);
					Util.l.info(message);
				}

			}
		}
	}
}
