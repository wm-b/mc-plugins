package com.inceris.lockout.listeners;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPortalEvent;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;

import com.inceris.lockout.util.GameInstance;

public class PlayerPortalListener implements Listener {
	
	@EventHandler
	public void onPortal(PlayerPortalEvent event) {
		Player player = event.getPlayer();

		if (GameInstance.get(player) != null) {
			GameInstance gi = GameInstance.get(player);
			World world = gi.getWorld();
			World nether = gi.getNether();
			World end = gi.getEnd();
			if (event.getCause().equals(TeleportCause.NETHER_PORTAL)) {
				Location location;
				if (player.getWorld().equals(world)) {
					location = new Location(nether, event.getFrom().getBlockX() / 3, event.getFrom().getBlockY(),
							event.getFrom().getBlockZ() / 3);
				} else {
					location = new Location(world, event.getFrom().getBlockX() * 3, event.getFrom().getBlockY(),
							event.getFrom().getBlockZ() * 3);
				}
				event.setTo(location);
			}

			if (event.getCause().equals(TeleportCause.END_PORTAL)) {
				if (player.getWorld().equals(world)) {
					Location loc = new Location(end, 100, 50, 0);
					event.setTo(loc);
					Block block = loc.getBlock();
					for (int x = block.getX() - 2; x <= block.getX() + 2; x++) {
						for (int z = block.getZ() - 2; z <= block.getZ() + 2; z++) {
							Block platformBlock = loc.getWorld().getBlockAt(x, block.getY() - 1, z);
							if (platformBlock.getType() != Material.OBSIDIAN) {
								platformBlock.setType(Material.OBSIDIAN);
							}
							for (int yMod = 1; yMod <= 3; yMod++) {
								Block b = platformBlock.getRelative(BlockFace.UP, yMod);
								if (b.getType() != Material.AIR) {
									b.setType(Material.AIR);
								}
							}
						}
					}
				} else if (player.getWorld().equals(end)) {
					event.setTo(world.getSpawnLocation());
				}
			}
        }
    }

}
