package com.inceris.atstcg;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class PackListener implements Listener {
	
	private ATSTCG tc = ATSTCG.getPlugin(ATSTCG.class);
	
	public void handlePack(PlayerInteractEvent event, ItemStack itemInHand, Player p, int set) {
		p.playSound(event.getPlayer().getLocation(), Sound.BLOCK_COMPOSTER_READY, 1f, 1f);
		for (int j = 0; j < 4; j++) p.getInventory().addItem(tc.randomCardOfEdFromSet(2, set));
		p.getInventory().getItemInMainHand().setAmount(itemInHand.getAmount() - 1);
	}
	
	@EventHandler
	public void onPlayerInteractEvent(PlayerInteractEvent event) {
		Player p = event.getPlayer();
		ItemStack itemInHand = p.getInventory().getItemInMainHand();
		if (itemInHand.getType() == Material.NETHERITE_SCRAP && itemInHand.getEnchantmentLevel(Enchantment.ARROW_INFINITE) == 10) {
			if (tc.getEmptySlots(p) >= 4) {
				if (itemInHand.getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', "&aSet 1 &6Second Edition &dCard Pack"))) {
					handlePack(event, itemInHand, p, 1);
				}
				else if (itemInHand.getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', "&aSet 2 &6Second Edition &dCard Pack"))) {
					handlePack(event, itemInHand, p, 2);
				}
			}
			else {
				p.sendMessage(ChatColor.RED + "Make some space in your inventory first!");
			}
		}
	}
}
