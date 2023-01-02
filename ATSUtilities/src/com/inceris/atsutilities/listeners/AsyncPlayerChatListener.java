package com.inceris.atsutilities.listeners;

import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import com.inceris.atsutilities.ATSUtilities;

public class AsyncPlayerChatListener implements Listener {

	private static final ATSUtilities atsu = ATSUtilities.getPlugin(ATSUtilities.class);

	public void sendMessageToPlayers(Set<Player> players, String message) {
		Bukkit.getScheduler().runTaskLater(atsu, () -> {
			for (Player p : players) {
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
			}
		}, 1);
	}

	public void sendMessageToPlayer(Player player, String message) {
		Bukkit.getScheduler().runTaskLater(atsu, () -> player.sendMessage(ChatColor.translateAlternateColorCodes('&', message)), 1);
	}

	@EventHandler(priority = EventPriority.MONITOR)
	public void onPlayerChat(AsyncPlayerChatEvent e) {

		if (!e.isCancelled()) {

			String message = e.getMessage();
			Player p = e.getPlayer();

			if ((message.contains("how".toLowerCase()) && message.contains("town".toLowerCase()))
					&& (message.contains("make".toLowerCase()) || message.contains("create".toLowerCase()))) {
				sendMessageToPlayer(p, "");
				sendMessageToPlayers(e.getRecipients(), "&8[&4Server&8] &7To make a town, type &9/t new [name]&7!");
				sendMessageToPlayer(p, "");
				sendMessageToPlayer(p, "&7For more tips on how to use towny, check &9/townyhelp&7!");

			} else if (((message.contains("i".toLowerCase()) && message.contains("grief".toLowerCase()))
					&& (message.contains("got".toLowerCase()) || message.contains("was".toLowerCase())))
					|| ((message.contains("stole".toLowerCase()) || message.contains("gone".toLowerCase()))
							&& (message.contains("item".toLowerCase()) || message.contains("stuff".toLowerCase())
									|| message.contains("things".toLowerCase()))
							&& (message.contains("my".toLowerCase()) || message.contains("someone".toLowerCase())))) {
				sendMessageToPlayer(p, "");
				sendMessageToPlayers(e.getRecipients(), "&8[&4Server&8] &7If you think you were griefed "
						+ "or stolen from, please report this incident on our &9/discord &7server by "
						+ "creating a ticket in the &b#create-ticket &7channel and our staff team will "
						+ "be with you as soon as they can!");
				sendMessageToPlayer(p, "");
				sendMessageToPlayer(p, "&7To review our rules to see if you think this is the case, use &9/rules&7!");
				
			} else if ((message.contains("how".toLowerCase()) && message.contains("claim".toLowerCase()))
					&& (message.contains("do".toLowerCase()) || message.contains("land".toLowerCase()))) {
				sendMessageToPlayer(p, "");
				sendMessageToPlayers(e.getRecipients(), "&8[&4Server&8] &7To claim land, type &9/t claim&7!");
				sendMessageToPlayer(p, "");
				sendMessageToPlayer(p, "&7For more detail, check &9/townybasics&7!");
				
			}
		}
	}
}
