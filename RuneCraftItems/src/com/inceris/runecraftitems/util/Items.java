package com.inceris.runecraftitems.util;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Items {
	public static ItemStack getItem(String name) {
		try {
			ItemStack item = null;
			switch (name.toLowerCase()) {
			case "stargazer":
				item = stargazer;
				break;
			case "grapefruit":
				item = grapefruit;
				break;
			case "flytoken":
				item = flyToken;
				break;
			case "loveisblind":
				item = loveIsBlind;
				break;
			case "disguisecap":
				item = disguiseCap;
				break;
			case "cupidsbow":
				item = cupidsBow;
				break;
			case "incssword":
				item = incsSword;
				break;
			case "eyeofthedragon":
				item = eyeOfTheDragon;
				break;
			case "chocolatebar":
				item = chocolateBar;
				break;
			case "grapplinghook":
				item = grapplingHook;
				break;
			case "ranktoken":
				item = rankToken;
				break;
			case "superpick":
				item = superpick;
				break;
			case "mobfreezer":
				item = mobFreezer;
				break;
			case "neverletgo":
				item = neverLetGo;
				break;
			case "brokenheart":
				item = brokenHeart;
				break;
			case "pulsingheart":
				item = pulsingHeart;
				break;
			case "cupidscrown":
				item = cupidsCrown;
				break;
			case "cupidswings":
				item = cupidsWings;
				break;
			case "ricksflowers":
				item = ricksFlowers;
				break;
			}
			return item;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static ItemStack superpick = superpick();

	private static ItemStack superpick() {
		ItemStack item = Util.constructItem(Material.NETHERITE_PICKAXE, "superpick");
		return item;
	}

	public static ItemStack neverLetGo = neverLetGo();

	private static ItemStack neverLetGo() {
		ItemStack item = Util.constructItem(Material.DIAMOND_CHESTPLATE, "neverletgo");
		item.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 8);
		item.addEnchantment(Enchantment.BINDING_CURSE, 1);
		return item;
	}

	public static ItemStack incsSword = incsSword();

	private static ItemStack incsSword() {
		ItemStack item = new ItemStack(Material.NETHERITE_SWORD);
		item.addUnsafeEnchantment(Enchantment.ARROW_INFINITE, 10);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&4&lInc's Sword"));
		item.setItemMeta(meta);
		return item;
	}

	public static ItemStack grapplingHook = Util.constructItem(Material.FISHING_ROD, "grapplinghook");
	public static ItemStack flyToken = Util.constructItem(Material.FEATHER, "flytoken");
	public static ItemStack rankToken = Util.constructItem(Material.NAME_TAG, "ranktoken");
	public static ItemStack mobFreezer = Util.constructItem(Material.CLOCK, "mobfreezer");
	public static ItemStack disguiseCap = Util.constructItem(Material.LEATHER_HELMET, "disguisecap");
	public static ItemStack eyeOfTheDragon = Util.constructItem(Material.ENDER_PEARL, "eyeofthedragon");
	public static ItemStack cupidsBow = Util.constructItem(Material.BOW, "cupidsbow");
	public static ItemStack chocolateBar = Util.constructItem(Material.NETHERITE_SCRAP, "chocolatebar");
	public static ItemStack pulsingHeart = Util.constructItem(Material.PINK_DYE, "pulsingheart");
	public static ItemStack cupidsWings = Util.constructItem(Material.ELYTRA, "cupidswings");
	public static ItemStack loveIsBlind = Util.constructItem(Material.STICK, "loveisblind");
	public static ItemStack brokenHeart = Util.constructItem(Material.DIAMOND_SWORD, "brokenheart");
	public static ItemStack stargazer = Util.constructItem(Material.NETHER_STAR, "stargazer");
	public static ItemStack grapefruit = Util.constructItem(Material.POPPED_CHORUS_FRUIT, "grapefruit");
	public static ItemStack ricksFlowers = Util.constructItem(Material.PEONY, "ricksflowers");
	public static ItemStack cupidsCrown = Util.constructItem(Material.AMETHYST_CLUSTER, "cupidscrown");
	
}
