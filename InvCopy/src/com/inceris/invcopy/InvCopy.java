package com.inceris.invcopy;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
// import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class InvCopy extends JavaPlugin {

	public boolean debugMode = false;
	
	@Override
	public void onEnable() {
		Bukkit.getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
		// allow to send to BungeeCord
		Bukkit.getMessenger().registerIncomingPluginChannel(this, "BungeeCord", new PluginChannelListener());
		// gets a Message from Bungee
	}

	@Override
	public void onDisable() {
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (label.equalsIgnoreCase("invcopy")) {
			if (args[0].equals("debug")) {
				if (debugMode) {
					sender.sendMessage("[InvCopy] Debug Mode Off");
					debugMode = false;
				}
				else {
					sender.sendMessage("[InvCopy] Debug Mode On");
					debugMode = true;
				}
			} else {
				try {
					if (debugMode) Bukkit.getLogger().info("[InvCopy] Main command called");
					forwardPluginMessage("towny", "requestInventory",
							serialisePlayer(Bukkit.getServer().getPlayer(args[0])));
				} catch (Exception e) {
					sender.sendMessage(ChatColor.RED + "There was a problem!");
					e.printStackTrace();
				}
			}
		}
		return false;
	}

	public byte[] serialiseInventory(Player p) throws IOException {
		if (debugMode) Bukkit.getLogger().info("[InvCopy] Serialising an inventory belonging to: " + p.getName());
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(bos);
		dos.writeUTF(p.getUniqueId().toString());
		byte[][] serialisedInv = Serialisation.playerInventoryToBase64(p.getInventory());
		dos.writeShort(serialisedInv[0].length);
		bos.writeBytes(serialisedInv[0]);
		dos.writeShort(serialisedInv[1].length);
		bos.writeBytes(serialisedInv[1]);
		dos.writeShort(serialisedInv[2].length);
		bos.writeBytes(serialisedInv[2]);
		dos.flush();
		dos.close();
		bos.close();
		return bos.toByteArray();
	}

	public void unserialiseInventory(byte[] byteArray) throws IOException {
		if (debugMode) Bukkit.getLogger().info("[InvCopy] Unserialising an inventory");
		ByteArrayInputStream bis = new ByteArrayInputStream(byteArray);
		DataInputStream dis = new DataInputStream(bis);
		Player p = getServer().getPlayer(UUID.fromString(dis.readUTF()));
		if (debugMode) Bukkit.getLogger().info("[InvCopy] Inventory belongs to: " + p.getName());

		short contentsLength = dis.readShort();
		byte[] contents = new byte[contentsLength];
		for (int i = 0; i < contents.length; i++) {
			contents[i] = dis.readByte();
		}

		short armourLength = dis.readShort();
		byte[] armour = new byte[armourLength];
		for (int i = 0; i < armour.length; i++) {
			armour[i] = dis.readByte();
		}

		short offHandLength = dis.readShort();
		byte[] offHand = new byte[offHandLength];
		for (int i = 0; i < offHand.length; i++) {
			offHand[i] = dis.readByte();
		}
		dis.close();
		bis.close();
		p.getInventory().setContents(Serialisation.playerInventoryFromBase64(contents, armour, offHand, p.getInventory()).getContents());
	}

	public byte[] serialisePlayer(Player p) throws IOException {
		if (debugMode) Bukkit.getLogger().info("[InvCopy] Serialising player UUID of: " + p.getName());
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		DataOutputStream out = new DataOutputStream(bos);
		out.writeUTF(p.getUniqueId().toString());
		bos.close();
		return bos.toByteArray();
	}

	// public OfflinePlayer unserialisePlayer(DataInputStream dis) throws IOException {
	// 	UUID uuid = UUID.fromString(dis.readUTF());
	//	dis.close();
	//	return Bukkit.getServer().getOfflinePlayer(uuid);
	//}

	public void forwardPluginMessage(String server, String channel, byte[] byteArray) throws IOException {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(bos);
		dos.writeUTF("Forward");
		dos.writeUTF(server);
		dos.writeUTF(channel);
		dos.writeShort(byteArray.length);
		dos.write(byteArray);
		
		((Player) Bukkit.getOnlinePlayers().toArray()[0]).sendPluginMessage(this, "BungeeCord", bos.toByteArray());
		
		dos.close();
		bos.close();
	}
}
