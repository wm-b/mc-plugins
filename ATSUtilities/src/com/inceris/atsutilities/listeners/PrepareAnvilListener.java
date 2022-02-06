package com.inceris.atsutilities.listeners;

import java.util.List;
import java.util.Map;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
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
	
	private ItemStack putEnchantmentOnBook(ItemStack item, Enchantment enchantment, int level) {
		EnchantmentStorageMeta meta = (EnchantmentStorageMeta) item.getItemMeta();
		meta.addStoredEnchant(enchantment, level, true);
		item.setItemMeta(meta);
		return item;
	}
	
	private boolean allowedPlayerViewing(List<HumanEntity> viewers, Enchantment enchantment, int level) {
		Player p = (Player) viewers.get(0);
		int prestigeLevelRequired = level - enchantment.getMaxLevel();
		if (prestigeLevelRequired > 0) {
			if (p.hasPermission("atsutilities.prestige." + prestigeLevelRequired)) {
				return true;
			}
		} else {
			return true;
		}
		return false;
	}
	
	private void generateResult(Enchantment enchantment, ItemStack base, ItemStack addition, ItemStack result, List<HumanEntity> viewers) {
		
		Map<Enchantment, Integer> baseEnchants = null;
		Map<Enchantment, Integer> additionEnchants = null;

		Material baseType = base.getType();
		if (baseType.equals(Material.ENCHANTED_BOOK)) {
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
				|| additionEnchants.containsKey(enchantment)) {

			int additionEnchantLevel = 0;
			if (additionEnchants.get(enchantment) != null) {
				additionEnchantLevel = additionEnchants.get(enchantment);
			}
			
			int baseEnchantLevel = 0;
			if (baseEnchants.get(enchantment) != null) {
				baseEnchantLevel = baseEnchants.get(enchantment);
			}
			
			if (baseEnchantLevel == additionEnchantLevel) {
				
				int resultLevel = baseEnchantLevel + 1;
				if (allowedPlayerViewing(viewers, enchantment, resultLevel)) {
					if (result.getType().equals(Material.ENCHANTED_BOOK)) {
						putEnchantmentOnBook(result, enchantment, resultLevel);
					} else {
						result.addUnsafeEnchantment(enchantment, resultLevel);
					}
				} else {
					if (result.getType().equals(Material.ENCHANTED_BOOK)) {
						putEnchantmentOnBook(result, enchantment, baseEnchantLevel);
					} else {
						result.addUnsafeEnchantment(enchantment, baseEnchantLevel);
					}
				}

			} else if (baseEnchantLevel < additionEnchantLevel) {

				if (allowedPlayerViewing(viewers, enchantment, additionEnchantLevel)) {
					if (result.getType().equals(Material.ENCHANTED_BOOK)) {
						putEnchantmentOnBook(result, enchantment, additionEnchantLevel);
					} else {
						result.addUnsafeEnchantment(enchantment, additionEnchantLevel);
					}
				} else {
					if (result.getType().equals(Material.ENCHANTED_BOOK)) {
						putEnchantmentOnBook(result, enchantment, baseEnchantLevel);
					} else {
						result.addUnsafeEnchantment(enchantment, baseEnchantLevel);
					}
				}
			} else {
					if (result.getType().equals(Material.ENCHANTED_BOOK)) {
						putEnchantmentOnBook(result, enchantment, baseEnchantLevel);
					} else {
						result.addUnsafeEnchantment(enchantment, baseEnchantLevel);
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
