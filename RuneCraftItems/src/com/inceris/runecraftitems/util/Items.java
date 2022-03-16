package com.inceris.runecraftitems.util;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionType;

import com.inceris.runecraftitems.RuneCraftItems;

public class Items {

	private static RuneCraftItems pl = RuneCraftItems.getPlugin(RuneCraftItems.class);

	private static ItemStack[] items = new ItemStack[] { superpick(), neverLetGo(), incsSword(), obsidianiksTeacup(), 
			grailsword(), escobarsSalt(), 
			Util.constructItem(Material.AMETHYST_SHARD, "stoneseven"),
			Util.constructItem(Material.BOW, "thunderbow"), 
			Util.constructItem(Material.YELLOW_DYE, "thunderstone"),
			Util.constructItem(Material.NETHERITE_CHESTPLATE, "areschestplate"),
			Util.constructItem(Material.LEATHER_CHESTPLATE, "brolysrage"),
			Util.constructItem(Material.BLAZE_ROD, "smgsstaff"), 
			Util.constructItem(Material.GOLD_NUGGET, "thering"),
			Util.constructItem(Material.COOKED_CHICKEN, "kfc"), 
			Util.constructItem(Material.WITHER_ROSE, "medusasrose"),
			Util.constructItem(Material.BLAZE_ROD, "lokissceptre"),
			Util.constructItem(Material.SPECTRAL_ARROW, "vaccine"),
			Util.constructItem(Material.FISHING_ROD, "grapplinghook"), 
			Util.constructItem(Material.FEATHER, "flytoken"),
			Util.constructItem(Material.NAME_TAG, "ranktoken"), 
			Util.constructItem(Material.CLOCK, "mobfreezer"),
			Util.constructItem(Material.LEATHER_HELMET, "disguisecap"),
			Util.constructItem(Material.ENDER_PEARL, "eyeofthedragon"), 
			Util.constructItem(Material.BOW, "cupidsbow"),
			Util.constructItem(Material.NETHERITE_SCRAP, "chocolatebar"),
			Util.constructItem(Material.PINK_DYE, "pulsingheart"), 
			Util.constructItem(Material.ELYTRA, "cupidswings"),
			Util.constructItem(Material.STICK, "loveisblind"),
			Util.constructItem(Material.DIAMOND_SWORD, "brokenheart"),
			Util.constructItem(Material.NETHER_STAR, "stargazer"),
			Util.constructItem(Material.POPPED_CHORUS_FRUIT, "grapefruit"),
			Util.constructItem(Material.PEONY, "ricksflowers"),
			Util.constructItem(Material.AMETHYST_CLUSTER, "cupidscrown"),
			Util.constructItem(Material.MAGMA_CREAM, "pokeball"),
			Util.constructItem(Material.NETHERITE_SWORD, "doubleedgedsword"),
			Util.constructItem(Material.SNOWBALL, "runecraftessence"),
			Util.constructItem(Material.TOTEM_OF_UNDYING, "totemofrunecraft"),
			Util.constructItem(Material.ELYTRA, "invisibilitycloak"),
			Util.constructItem(Material.GOLDEN_AXE, "mjolnir"),
			Util.constructItem(Material.LEATHER_BOOTS, "hermesboots"),
			Util.constructItem(Material.TRIDENT, "poseidonstrident"),
			Util.constructItem(Material.IRON_SWORD, "theflamingsword"),
			Util.constructItem(Material.LEATHER_HELMET, "sunwukongsheadband"),
			Util.constructItem(Material.NETHERITE_CHESTPLATE, "kappasplatebody"),
			Util.constructItem(Material.LEATHER_HELMET, "crownoftwelvestars"),
			Util.constructItem(Material.ROSE_BUSH, "aphroditesroses"),
			Util.constructItem(Material.LEATHER_CHESTPLATE, "devilsgreencoat"),
			Util.constructItem(Material.LEATHER_BOOTS, "sandalsofchrist"),
			Util.constructItem(Material.NETHERITE_SWORD, "swordofvictory"),
			Util.constructItem(Material.NETHERITE_AXE, "axofpangu"),
			Util.constructItem(Material.GOLDEN_SWORD, "excalibur"), 
			Util.constructItem(Material.STICK, "brahmastra"),
			Util.constructItem(Material.AMETHYST_SHARD, "stoneone"),
			Util.constructItem(Material.AMETHYST_SHARD, "stonetwo"),
			Util.constructItem(Material.AMETHYST_SHARD, "stonethree"),
			Util.constructItem(Material.AMETHYST_SHARD, "stonefour"),
			Util.constructItem(Material.AMETHYST_SHARD, "stonefive"),
			Util.constructItem(Material.AMETHYST_SHARD, "stonesix") };

	public static ItemStack getItem(String name) {
		name = name.toLowerCase();
		try {
			for (ItemStack i : items) {
				if (pl.getConfig().getString("items." + name + ".name") != null && i.getItemMeta().getDisplayName()
						.equals(Util.colours(pl.getConfig().getString("items." + name + ".name")))) {
					return i;
				}
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private static ItemStack grailsword() {
		ItemStack item = Util.constructItem(Material.NETHERITE_SWORD, "grailsword");
		Damageable meta = (Damageable) item.getItemMeta();
		meta.setDamage(Material.NETHERITE_SWORD.getMaxDurability() - pl.getConfig().getInt("items.grailsword.durability"));
		item.setItemMeta(meta);
		return item;
	}

	private static ItemStack superpick() {
		ItemStack item = Util.constructItem(Material.NETHERITE_PICKAXE, "superpick");
		Damageable meta = (Damageable) item.getItemMeta();
		meta.setDamage(Material.NETHERITE_PICKAXE.getMaxDurability() - 100);
		item.setItemMeta(meta);
		return item;
	}

	private static ItemStack neverLetGo() {
		ItemStack item = Util.constructItem(Material.DIAMOND_CHESTPLATE, "neverletgo");
		item.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 8);
		item.addEnchantment(Enchantment.BINDING_CURSE, 1);
		return item;
	}

	private static ItemStack incsSword() {
		ItemStack item = new ItemStack(Material.NETHERITE_SWORD);
		item.addUnsafeEnchantment(Enchantment.ARROW_INFINITE, 10);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&4&lInc's Sword"));
		item.setItemMeta(meta);
		return item;
	}

	private static ItemStack obsidianiksTeacup() {
		ItemStack item = Util.constructItem(Material.POTION, "obsidianiksteacup");

		ItemMeta meta = item.getItemMeta();
		PotionMeta pmeta = (PotionMeta) meta;
		PotionData pdata = new PotionData(PotionType.WATER);
		pmeta.setBasePotionData(pdata);
		item.setItemMeta(meta);

		return item;
	}

	private static ItemStack escobarsSalt() {
		ItemStack item = Util.constructItem(Material.SUGAR, "escobarssalt");

		item.addUnsafeEnchantment(Enchantment.DIG_SPEED, 10);
		item.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 10);
		item.addUnsafeEnchantment(Enchantment.DURABILITY, 20);
		item.addUnsafeEnchantment(Enchantment.MENDING, 10);
		item.addUnsafeEnchantment(Enchantment.KNOCKBACK, 5);

		return item;
	}

}
