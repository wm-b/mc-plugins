package com.inceris.runecraftitems.listeners;

import java.util.concurrent.ThreadLocalRandom;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType.SlotType;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.inceris.runecraftitems.RuneCraftItems;
import com.inceris.runecraftitems.util.Items;
import com.inceris.runecraftitems.util.Util;

public class InventoryClickListener implements Listener {

	private static RuneCraftItems pl = RuneCraftItems.getPlugin(RuneCraftItems.class);

	@EventHandler
	public void onInventoryClick(InventoryClickEvent e) {

		if (pl.debug)
			Bukkit.getLogger().info("Detected inventory click!");

		if (!e.isCancelled()) {
			HumanEntity human = e.getWhoClicked();

			if (human instanceof Player) {
				Player p = (Player) human;
				ItemStack currentItem = e.getCurrentItem();
				ItemStack cursorItem = e.getCursor();
				SlotType slotType = e.getSlotType();
				ClickType clickType = e.getClick();
				int slot = e.getSlot();
				Inventory inv = e.getInventory();

				if (inv instanceof AnvilInventory) {
					if (slotType.equals(SlotType.RESULT)) {

						if (pl.debug)
							Bukkit.getLogger().info("Detected anvil/grindstone inventory click!");
						AnvilInventory anvil = (AnvilInventory) inv;

						for (ItemStack i : anvil.getContents()) {
							if (i.getEnchantmentLevel(Enchantment.ARROW_INFINITE) == 10
									&& !Util.checkItem(i, Items.getItem("grailsword"))) {
								if (pl.debug) {
									Bukkit.getLogger().info("Detected RCItem click!");
								}
								e.setCancelled(true);
								p.sendMessage(ChatColor.translateAlternateColorCodes('&',
										"&8[&6R&5C&9I&8] &cYou can't alter this item!"));
								break;
							}
						}
					}
				}

				if (cursorItem != null && !clickType.equals(ClickType.SHIFT_LEFT)
						&& !clickType.equals(ClickType.SHIFT_RIGHT)) {
					if (slot == 39 && Util.checkItem(cursorItem, Items.getItem("disguiseCap"))) {
						String randomPeacefulMob = null;
						switch (ThreadLocalRandom.current().nextInt(1, 6)) {
						case 1:
							randomPeacefulMob = "cow";
							break;
						case 2:
							randomPeacefulMob = "sheep";
							break;
						case 3:
							randomPeacefulMob = "pig";
							break;
						case 4:
							randomPeacefulMob = "rabbit";
							break;
						case 5:
							randomPeacefulMob = "chicken";
							break;
						}
						Util.sendCommand("disguiseplayer " + p.getName() + " " + randomPeacefulMob);

					} else if (slot == 38 && Util.checkItem(cursorItem, Items.getItem("cupidsWings"))) {
						Util.sendCommand("trailsid HEART " + p.getName());

					} else if (slot == 38 && Util.checkItem(cursorItem, Items.getItem("neverLetGo"))) {
						Util.sendCommand("trailsid ENDROD " + p.getName());

					} else if (slot == 38 && Util.checkItem(cursorItem, Items.getItem("devilsgreencoat"))) {
						pl.wearingDevilsGreenCoat.add(p);

					} else if (slot == 38 && Util.checkItem(cursorItem, Items.getItem("invisibilityCloak"))) {
						p.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 60000000, 0));

					} else if (slot == 38 && Util.checkItem(cursorItem, Items.getItem("aresChestplate"))) {
						p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 60000000, 0));
						p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 60000000, 0));
						Util.sendCommand("trailsid PINKCONFETTI " + p.getName());

					} else if (slot == 38 && Util.checkItem(cursorItem, Items.getItem("brolysRage"))) {
						p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 60000000, 2));
						p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 60000000, 6));
						p.setHealth(6);

					} else if (slot == 36 && Util.checkItem(cursorItem, Items.getItem("hermesboots"))) {
						p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 60000000, 2));

					} else if (slot == 39 && Util.checkItem(cursorItem, Items.getItem("crownoftwelvestars"))) {
						Util.sendCommand("trailsid FUN " + p.getName());

					} else if (slot == 39 && Util.checkItem(cursorItem, Items.getItem("sunwukongsheadband"))) {
						Util.sendCommand("lp user " + p.getName() + " parent add sunwukongsheadband");

					}
				}

				if (currentItem != null) {
					Util.checkRemoveItemEffects(p, currentItem);
				}
			}
		}
	}
}
