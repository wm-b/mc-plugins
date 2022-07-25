package com.inceris.invcopy;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;

import com.lishid.openinv.OpenInv;

public class PluginChannelListener implements PluginMessageListener {

	private InvCopy ic = InvCopy.getPlugin(InvCopy.class);
	private OpenInv oi = OpenInv.getPlugin(OpenInv.class);

	@Override
	public synchronized void onPluginMessageReceived(String channel, Player player, byte[] message) {
		try {
			DataInputStream in = new DataInputStream(new ByteArrayInputStream(message));
			String subchannel = in.readUTF();
			if (subchannel.equals("inventoryPayload")) {
				if (ic.debugMode) Bukkit.getLogger().info("[InvCopy] Recieved inventoryPayload message.");
				if (ic.noResponse) ic.noResponse = false;
				in.readShort();
				ic.unserialiseInventory(in.readAllBytes());
			}
			if (subchannel.equals("requestInventory")) {
				if (ic.debugMode) Bukkit.getLogger().info("[InvCopy] Recieved requestInventory message.");
				in.readShort();
				String uuidString = in.readUTF();
				UUID uuid = UUID.fromString(uuidString);
				if (ic.debugMode) Bukkit.getLogger().info("[InvCopy] Sending inventoryPayload message for: " + uuidString);
				ic.forwardPluginMessage(ic.serverTo, "inventoryPayload",
						ic.serialiseInventory(oi.loadPlayer(Bukkit.getOfflinePlayer(uuid))));
				in.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}