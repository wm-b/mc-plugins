package com.inceris.runecraftitems;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class Items {
	
	public static ItemStack getItem(String name) {
		ItemStack item = null;
		
		switch (name.toLowerCase()) {
		case "superpick":
			item = superpick;
			break;
		case "grapplinghook":
			item = grapplingHook;
			break;
		case "flytoken":
			item = flyToken;
			break;
		case "ranktoken":
			item = rankToken;
			break;
		case "mobfreezer":
			item = mobFreezer;
			break;
		case "disguisecap":
			item = disguiseCap;
			break;
		case "cupidsbow":
			item = cupidsBow;
			break;
		case "chocolatebar":
			item = chocolateBar;
			break;
		case "pulsingheart":
			item = pulsingHeart;
			break;
		case "cupidswings":
			item = cupidsWings;
			break;
		case "loveisblind":
			item = loveIsBlind;
			break;
		case "brokenheart":
			item = brokenHeart;
			break;
		case "stargazer":
			item = stargazer;
			break;
		}
		
		return item;
	}
	
	public static ItemStack superpick = superpick();
	private static ItemStack superpick() {
		ItemStack item = Util.constructItem(Material.NETHERITE_PICKAXE, "superpick");
//		item.addEnchantment(Enchantment.DIG_SPEED, 5);
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
	
}
