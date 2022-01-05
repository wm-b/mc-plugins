package com.inceris.joinmessage;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class FirstJoinListener implements Listener {

	private JoinMessage jm = JoinMessage.getPlugin(JoinMessage.class);

	@EventHandler
	public void onEntityDeathEvent(PlayerJoinEvent event) {
		if (!event.getPlayer().hasPlayedBefore()) {
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			DataOutputStream dos = new DataOutputStream(bos);
			try {
				dos.writeUTF("Message");
				dos.writeUTF("ALL");
				dos.writeUTF(ChatColor.translateAlternateColorCodes('&',
						"&8[&4Server&8] &fWelcome, " + event.getPlayer().getName() + "&f, to &a&lA Towny Server&f!"));

				((Player) Bukkit.getOnlinePlayers().toArray()[0]).sendPluginMessage(jm, "BungeeCord",
						bos.toByteArray());

				dos.close();
				bos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
