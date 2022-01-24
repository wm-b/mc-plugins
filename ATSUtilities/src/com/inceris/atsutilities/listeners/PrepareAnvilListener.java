package com.inceris.atsutilities.listeners;

import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.ItemStack;

import com.inceris.atsutilities.ATSUtilities;

public class PrepareAnvilListener implements Listener {
	
	private ItemStack generateResult(Enchantment enchantment, ItemStack base, ItemStack addition, ItemStack result, List<HumanEntity> viewers) {
		Map<Enchantment, Integer> baseEnchants = base.getEnchantments();
		Map<Enchantment, Integer> additionEnchants = addition.getEnchantments();

		if (baseEnchants.containsKey(enchantment)
				&& additionEnchants.containsKey(enchantment)) {

			if (baseEnchants.get(enchantment) == additionEnchants.get(enchantment)) {

				if (ATSUtilities.debug)
					Bukkit.getLogger().info("[ATSUtilities] Identified efficiency craft");

				int resultLevel = baseEnchants.get(enchantment) + 1;

				boolean allowedPlayerViewing = false;

				for (HumanEntity h : viewers) {

					if (h.hasPermission("atsutilities.prestige." + (resultLevel - enchantment.getMaxLevel())))
						allowedPlayerViewing = true;

				}

				if (allowedPlayerViewing) {
					result.addUnsafeEnchantment(enchantment, resultLevel);
				}
			}
		}
		return result;
	}

	@EventHandler
	public void onSmithItem(PrepareAnvilEvent e) {

		if (ATSUtilities.debug)
			Bukkit.getLogger().info("[ATSUtilities] PrepareAnvilEvent called");

		AnvilInventory inv = e.getInventory();
		ItemStack base = inv.getItem(0);
		ItemStack addition = inv.getItem(1);

		if (base != null && addition != null) {
			generateResult(Enchantment.DIG_SPEED, base, addition, e.getResult(), e.getViewers());
			generateResult(Enchantment.DAMAGE_ALL, base, addition, e.getResult(), e.getViewers());
			generateResult(Enchantment.DAMAGE_ARTHROPODS, base, addition, e.getResult(), e.getViewers());
			generateResult(Enchantment.DAMAGE_UNDEAD, base, addition, e.getResult(), e.getViewers());
			generateResult(Enchantment.ARROW_DAMAGE, base, addition, e.getResult(), e.getViewers());
			generateResult(Enchantment.IMPALING, base, addition, e.getResult(), e.getViewers());
			generateResult(Enchantment.PROTECTION_ENVIRONMENTAL, base, addition, e.getResult(), e.getViewers());
			generateResult(Enchantment.PROTECTION_FIRE, base, addition, e.getResult(), e.getViewers());
			generateResult(Enchantment.PROTECTION_EXPLOSIONS, base, addition, e.getResult(), e.getViewers());
			generateResult(Enchantment.PROTECTION_PROJECTILE, base, addition, e.getResult(), e.getViewers());
			generateResult(Enchantment.DURABILITY, base, addition, e.getResult(), e.getViewers());
		}
	}
}
