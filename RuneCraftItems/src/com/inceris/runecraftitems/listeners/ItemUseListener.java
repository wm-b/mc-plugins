package com.inceris.runecraftitems.listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import com.inceris.runecraftitems.Items;
import com.inceris.runecraftitems.RCIPlayer;
import com.inceris.runecraftitems.RuneCraftItems;
import com.inceris.runecraftitems.Util;

public class ItemUseListener implements Listener {
	
	private static RuneCraftItems rci = RuneCraftItems.getPlugin(RuneCraftItems.class);
	
	@EventHandler
	public void onItemUse(PlayerInteractEvent e) {
		EquipmentSlot slot = e.getHand();
		if (!(slot == null)) {
			if (slot.equals(EquipmentSlot.HAND)) {
				Player p = e.getPlayer();
				ItemStack itemInHand = p.getInventory().getItemInMainHand();
				if (!(itemInHand == null)) {
					if (Util.checkItem(itemInHand, Items.flyToken)) {
						Util.sendCommand("lp user " + p.getName() + " permission settemp essentials.fly true 1d");
						itemInHand.setAmount(itemInHand.getAmount() - 1);

					} else if (Util.checkItem(itemInHand, Items.rankToken)) {
						Util.sendCommand("lp user " + p.getName() + " promote donator");
						itemInHand.setAmount(itemInHand.getAmount() - 1);

					} else if (Util.checkItem(itemInHand, Items.mobFreezer)) {
						Util.sendCommand("freeze " + p.getName());
						
					} else if (Util.checkItem(itemInHand, Items.chocolateBar)) {
						RCIPlayer rcip = RCIPlayer.getRCIPlayer(p);
						if (!rcip.healOnCooldown) {
							p.setHealth(p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
							rcip.healOnCooldown = true;
							
							Bukkit.getScheduler().runTaskLater(rci, new Runnable() {
								@Override
								public void run() {
									rcip.healOnCooldown = false;
								}
							}, 6000);
						} else {
							p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&6R&5C&9I&8] &cThis item is on cooldown!"));
						}
					}
				}
			}
		}
	}
}
