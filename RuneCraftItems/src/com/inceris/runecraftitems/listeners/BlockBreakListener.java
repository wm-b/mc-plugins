package com.inceris.runecraftitems.listeners;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import com.inceris.runecraftitems.util.Items;
import com.inceris.runecraftitems.RuneCraftItems;
import com.inceris.runecraftitems.util.Util;
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
		if (rci.debug)
			Bukkit.getLogger().info("Detected block break!");

		Player p = e.getPlayer();
		ItemStack itemInHand = p.getInventory().getItemInMainHand();
		if (Util.checkItem(itemInHand, Items.getItem("superpick"))) {

			if (rci.debug)
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

		if (rci.getConfig().getBoolean("stones.enabled")) {
			Material m = e.getBlock().getType();
			if (m.equals(Material.OAK_LOG) || m.equals(Material.SPRUCE_LOG) || m.equals(Material.JUNGLE_LOG)
					|| m.equals(Material.DARK_OAK_LOG) || m.equals(Material.BIRCH_LOG)
					|| m.equals(Material.ACACIA_LOG)) {
				if (Util.percentChance(rci.getConfig().getDouble("stones.droprates.break-log"))) {
					p.getInventory().addItem(Items.getItem("stoneone"));
				}
			} else if (m.equals(Material.STONE) || m.equals(Material.DEEPSLATE) || m.equals(Material.TUFF)
					|| m.equals(Material.ANDESITE) || m.equals(Material.DIORITE)
					|| m.equals(Material.CALCITE) || m.equals(Material.GRANITE)) {
				if (Util.percentChance(rci.getConfig().getDouble("stones.droprates.break-stone"))) {
					p.getInventory().addItem(Items.getItem("stonetwo"));
				}
			}
		}
	}

}