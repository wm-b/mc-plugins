package com.inceris.atsutilities.listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import com.inceris.atsutilities.ATSUtilities;
import com.inceris.atsutilities.util.Items;

public class PlayerInteractListener implements Listener {
	
	private static ATSUtilities atsu = ATSUtilities.getPlugin(ATSUtilities.class);
	
	@EventHandler
	public void onItemUse(PlayerInteractEvent e) {
		EquipmentSlot slot = e.getHand();
		if (!(slot == null)) {
			if (slot.equals(EquipmentSlot.HAND)) {
				Player p = e.getPlayer();
				PlayerInventory inv = p.getInventory();
				ItemStack item = inv.getItemInMainHand();
				if (!(item == null)) {
					if (Items.checkItem(item, Items.prestigeRankupToken)) {
						ConsoleCommandSender console = atsu.getServer().getConsoleSender();
						Bukkit.dispatchCommand((CommandSender) console, "prestigeRankup " + p.getName());
						inv.setItemInMainHand(new ItemStack(Material.AIR));
						
					} else if (atsu.denyTallGrass) {
						if (item.getType().equals(Material.TALL_GRASS) && e.getClickedBlock() != null) {
							p.sendMessage(ChatColor.translateAlternateColorCodes('&',
									"&4&lThou shalt not placeth the grass of sin."));
							e.setCancelled(true);

						}
					}
				}
			}
		}
	}
}
