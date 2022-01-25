package com.inceris.runecraftitems.listeners;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import com.inceris.runecraftitems.ItemList;
import com.inceris.runecraftitems.Util;

public class ItemUseListener implements Listener {
	
	@EventHandler
	public void onItemUse(PlayerInteractEvent e) {

		if (e.getHand().equals(EquipmentSlot.HAND)) {
			Player p = e.getPlayer();
			ItemStack itemInHand = p.getInventory().getItemInMainHand();
			if (itemInHand.getEnchantmentLevel(Enchantment.ARROW_INFINITE) == 10 && itemInHand.getItemMeta()
					.getDisplayName().equals(ItemList.flyToken.getItemMeta().getDisplayName())) {
				Util.SendCommand("lp user " + p.getName() + " permission settemp essentials.fly true 1d");
				itemInHand.setAmount(itemInHand.getAmount() - 1);
				
			} else if (itemInHand.getEnchantmentLevel(Enchantment.ARROW_INFINITE) == 10 && itemInHand.getItemMeta()
					.getDisplayName().equals(ItemList.rankToken.getItemMeta().getDisplayName())) {
				Util.SendCommand("lp user " + p.getName() + " promote donator");
				itemInHand.setAmount(itemInHand.getAmount() - 1);
				
			} else if (itemInHand.getEnchantmentLevel(Enchantment.ARROW_INFINITE) == 10 && itemInHand.getItemMeta()
					.getDisplayName().equals(ItemList.mobFreezer.getItemMeta().getDisplayName())) {
				Util.SendCommand("freeze " + p.getName());
			}
		}
	}
}
