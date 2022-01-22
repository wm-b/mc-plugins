package com.inceris.runecraftutilities;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import com.sk89q.worldedit.util.Location;
import com.sk89q.worldguard.LocalPlayer;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.flags.Flags;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import com.sk89q.worldguard.protection.regions.RegionQuery;

public class ItemUseListener implements Listener {

	@EventHandler
	public void onItemUse(PlayerInteractEvent e) {

		Block b = e.getClickedBlock();
		Player p = e.getPlayer();

		if (b.getType().equals(Material.GRASS_BLOCK)
				|| p.getInventory().getItemInMainHand().getType().equals(Material.POWDER_SNOW_BUCKET)) {

			LocalPlayer localPlayer = WorldGuardPlugin.inst().wrapPlayer(p);
			Location loc = new Location(localPlayer.getWorld(), b.getX(), b.getY(), b.getZ());
			RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
			RegionQuery query = container.createQuery();

			if (!query.testState(loc, localPlayer, Flags.BUILD) && !p.hasPermission("runecraftutilities.bypasspowdersnowplace")) {
				e.setCancelled(true);
				p.sendMessage(
						ChatColor.translateAlternateColorCodes('&', "&8[&6R&5C&bU&8] &cYou can't use that here!"));
			}

		}
	}
}
