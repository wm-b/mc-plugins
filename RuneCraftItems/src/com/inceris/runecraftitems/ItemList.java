package com.inceris.runecraftitems;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class ItemList {
	
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
		}
		
		return item;
	}
	
	public static ItemStack superpick = superpick();
	private static ItemStack superpick() {
		ItemStack item = Util.ConstructItem(Material.NETHERITE_PICKAXE, "superpick");
//		item.addEnchantment(Enchantment.DIG_SPEED, 5);
		return item;
	}
	
	public static ItemStack grapplingHook = Util.ConstructItem(Material.FISHING_ROD, "grapplinghook");
	
	public static ItemStack flyToken = Util.ConstructItem(Material.FEATHER, "flytoken");
	
	public static ItemStack rankToken = Util.ConstructItem(Material.NAME_TAG, "ranktoken");
	
	public static ItemStack mobFreezer = Util.ConstructItem(Material.CLOCK, "mobfreezer");
	
}
