package com.inceris.runecraftitems.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import com.inceris.runecraftitems.Items;
import com.inceris.runecraftitems.Util;

public class ItemUseListener implements Listener {
	
	@EventHandler
	public void onItemUse(PlayerInteractEvent e) {
		EquipmentSlot slot = e.getHand();
		if (!(slot == null)) {
			if (slot.equals(EquipmentSlot.HAND)) {
				Player p = e.getPlayer();
				ItemStack itemInHand = p.getInventory().getItemInMainHand();
				if (!(itemInHand == null)) {
					if (Util.CheckItem(itemInHand, Items.flyToken)) {
						Util.SendCommand("lp user " + p.getName() + " permission settemp essentials.fly true 1d");
						itemInHand.setAmount(itemInHand.getAmount() - 1);

					} else if (Util.CheckItem(itemInHand, Items.rankToken)) {
						Util.SendCommand("lp user " + p.getName() + " promote donator");
						itemInHand.setAmount(itemInHand.getAmount() - 1);

					} else if (Util.CheckItem(itemInHand, Items.mobFreezer)) {
						Util.SendCommand("freeze " + p.getName());
					}
				}
			}
		}
	}
}
