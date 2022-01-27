package com.inceris.runecraftitems.listeners;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import com.inceris.runecraftitems.Items;
import com.inceris.runecraftitems.RuneCraftItems;
import com.inceris.runecraftitems.Util;
import com.sk89q.worldedit.util.Location;
import com.sk89q.worldguard.LocalPlayer;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.flags.Flags;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import com.sk89q.worldguard.protection.regions.RegionQuery;

public class BlockBreakListener implements Listener {
	
	private static RuneCraftItems rci = RuneCraftItems.getPlugin(RuneCraftItems.class);

	public List<Block> blockRadius(Block b) {
		List<Block> blocks = new ArrayList<Block>();

		for (int i = -1; i < 2; i++) {
			for (int j = -1; j < 2; j++) {
				for (int k = -1; k < 2; k++) {
					if (!(i == 0 && j == 0 && k == 0))
						blocks.add(b.getRelative(i, j, k));
				}
			}
		}

		return blocks;
	}

	@EventHandler
	public void onBlockBreak(BlockBreakEvent e) {
		if (RuneCraftItems.debug)
			Bukkit.getLogger().info("Detected block break!");

		Player p = e.getPlayer();
		ItemStack itemInHand = p.getInventory().getItemInMainHand();
		if (Util.CheckItem(itemInHand, Items.superpick)) {

			if (RuneCraftItems.debug)
				Bukkit.getLogger().info("Detected superpick break!");

			List<Block> blocks = blockRadius(e.getBlock());

			Bukkit.getScheduler().runTask(rci, new Runnable() {
				@Override
				public void run() {

					for (Block b : blocks) {

						LocalPlayer localPlayer = WorldGuardPlugin.inst().wrapPlayer(p);
						Location loc = new Location(localPlayer.getWorld(), b.getX(), b.getY(), b.getZ());
						RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
						RegionQuery query = container.createQuery();

						if (query.testState(loc, localPlayer, Flags.BUILD)) {
							b.breakNaturally(itemInHand);
						} else {
							p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&6R&5C&b9I&8] &cYou can't use that here!"));
							break;
						}

					}
				}
			});

		}
	}

}