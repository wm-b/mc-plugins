package com.inceris.runecraftitems;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

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

		blocks.add(b.getRelative(1, 1, 1));
		blocks.add(b.getRelative(0, 1, 1));
		blocks.add(b.getRelative(-1, 1, 1));
		blocks.add(b.getRelative(1, 1, 0));
		blocks.add(b.getRelative(0, 1, 0));
		blocks.add(b.getRelative(-1, 1, 0));
		blocks.add(b.getRelative(1, 1, -1));
		blocks.add(b.getRelative(0, 1, -1));
		blocks.add(b.getRelative(-1, 1, -1));

		blocks.add(b.getRelative(1, 0, 1));
		blocks.add(b.getRelative(0, 0, 1));
		blocks.add(b.getRelative(-1, 0, 1));
		blocks.add(b.getRelative(1, 0, 0));
		blocks.add(b.getRelative(-1, 0, 0));
		blocks.add(b.getRelative(1, 0, -1));
		blocks.add(b.getRelative(0, 0, -1));
		blocks.add(b.getRelative(-1, 0, -1));

		blocks.add(b.getRelative(1, -1, 1));
		blocks.add(b.getRelative(0, -1, 1));
		blocks.add(b.getRelative(-1, -1, 1));
		blocks.add(b.getRelative(1, -1, 0));
		blocks.add(b.getRelative(0, -1, 0));
		blocks.add(b.getRelative(-1, -1, 0));
		blocks.add(b.getRelative(1, -1, -1));
		blocks.add(b.getRelative(0, -1, -1));
		blocks.add(b.getRelative(-1, -1, -1));

		return blocks;
	}

	@EventHandler
	public void onBlockBreak(BlockBreakEvent e) {
		if (RuneCraftItems.debug)
			Bukkit.getLogger().info("Detected block break!");

		Player p = e.getPlayer();
		ItemStack itemInHand = p.getInventory().getItemInMainHand();
		if (itemInHand.getEnchantmentLevel(Enchantment.ARROW_INFINITE) == 10 && itemInHand.getItemMeta()
				.getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', rci.getConfig().getString("items.superpick.name")))) {

			if (RuneCraftItems.debug) Bukkit.getLogger().info("Detected superpick break!");

			List<Block> blocks = blockRadius(e.getBlock());

			for (Block b : blocks) {
				
				LocalPlayer localPlayer = WorldGuardPlugin.inst().wrapPlayer(p);
				Location loc = new Location(localPlayer.getWorld(), b.getX(), b.getY(), b.getZ());
				RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
				RegionQuery query = container.createQuery();

				if (query.testState(loc, localPlayer, Flags.BUILD)) {
					b.breakNaturally(itemInHand);
				} else {
					p.sendMessage("[RCI] You can't use that here!");
					break;
				}
			}

		}
	}

}