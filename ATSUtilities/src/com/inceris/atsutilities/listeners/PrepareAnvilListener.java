package com.inceris.atsutilities.listeners;

import java.util.List;
import java.util.Map;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;

import com.inceris.atsutilities.ATSUtilities;

public class PrepareAnvilListener implements Listener {
	
	private static ATSUtilities atsu = ATSUtilities.getPlugin(ATSUtilities.class);
	
	private Map<Enchantment, Integer> enchantedBook(ItemStack item) {
		EnchantmentStorageMeta meta = (EnchantmentStorageMeta) item.getItemMeta();
		return meta.getStoredEnchants();
	}
	
	private void generateResult(Enchantment enchantment, ItemStack base, ItemStack addition, ItemStack result, List<HumanEntity> viewers) {
		
		Map<Enchantment, Integer> baseEnchants = null;
		Map<Enchantment, Integer> additionEnchants = null;

		Material baseType = base.getType();
		if (base.getType().equals(Material.ENCHANTED_BOOK)) {
			baseEnchants = enchantedBook(base);
		} else {
			baseEnchants = base.getEnchantments();
		}
		
		Material additionType = addition.getType();
		if (additionType.equals(Material.ENCHANTED_BOOK)) {
			additionEnchants = enchantedBook(addition);
		} else {
			additionEnchants = addition.getEnchantments();
		}

		if (baseEnchants.containsKey(enchantment)
				&& additionEnchants.containsKey(enchantment)) {

			if (baseEnchants.get(enchantment).equals(additionEnchants.get(enchantment))) {

				if (atsu.debug)
					atsu.getLogger().info("Identified " + enchantment + " craft");

				if (result.getType().equals(Material.ENCHANTED_BOOK)) {
					EnchantmentStorageMeta meta = (EnchantmentStorageMeta) result.getItemMeta();
					meta.addStoredEnchant(enchantment, baseEnchants.get(enchantment) + 1, true);
					result.setItemMeta(meta);
				} else {
					result.addUnsafeEnchantment(enchantment, baseEnchants.get(enchantment) + 1);
				}
			}

		} else if (!baseType.equals(Material.ENCHANTED_BOOK) && additionType.equals(Material.ENCHANTED_BOOK)) {
			for (Map.Entry<Enchantment, Integer> e : additionEnchants.entrySet()) {
				if (e.getKey().canEnchantItem(base)) {
					result.addUnsafeEnchantment(e.getKey(), e.getValue());
				}
			}
		}
	}

	@EventHandler
	public void onSmithItem(PrepareAnvilEvent e) {

		if (atsu.debug)
			atsu.getLogger().info("PrepareAnvilEvent called");

		AnvilInventory inv = e.getInventory();
		ItemStack base = inv.getItem(0);
		ItemStack addition = inv.getItem(1);

		if (base != null && addition != null) {
			for (Enchantment enchantment : atsu.allowedEnchantments) {
				if (e.getResult() != null) {
					generateResult(enchantment, base, addition, e.getResult(), e.getViewers());
				}
			}
		}
	}
}
