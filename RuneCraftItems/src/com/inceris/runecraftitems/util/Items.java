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

public class Items {
	public static ItemStack getItem(String name) {
		try {
			ItemStack item = null;
			switch (name.toLowerCase()) {
			case "stoneone":
				item = stoneOne;
				break;
			case "stonetwo":
				item = stoneTwo;
				break;
			case "stonethree":
				item = stoneThree;
				break;
			case "stonefour":
				item = stoneFour;
				break;
			case "stonefive":
				item = stoneFive;
				break;
			case "stonesix":
				item = stoneSix;
				break;
			case "stoneseven":
				item = stoneSeven;
				break;
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
			case "pokeball":
				item = pokeball;
				break;
			case "runecraftessence":
				item = runecraftEssence;
				break;
			case "doubleedgedsword":
				item = doubleEdgedSword;
				break;
			case "totemofrunecraft":
				item = totemOfRunecraft;
				break;
			case "invisibilitycloak":
				item = invisibilityCloak;
				break;
			case "thunderbow":
				item = thunderBow;
				break;
			case "thunderstone":
				item = thunderstone;
				break;
			case "areschestplate":
				item = aresChestplate;
				break;
			case "obsidianiksteacup":
				item = obsidianiksTeacup;
				break;
			case "brolysrage":
				item = brolysRage;
				break;
			case "smgsstaff":
				item = smgsStaff;
				break;
			case "thering":
				item = theRing;
				break;
			case "kfc":
				item = kfc;
				break;
			case "medusasrose":
				item = medusasRose;
				break;
			case "lokissceptre":
				item = lokisSceptre;
				break;
			case "escobarssalt":
				item = escobarsSalt;
				break;
			case "vaccine":
				item = vaccine;
				break;
			}
			return item;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static ItemStack stoneOne = Util.constructItem(Material.AMETHYST_SHARD, "stoneone");
	public static ItemStack stoneTwo = Util.constructItem(Material.AMETHYST_SHARD, "stonetwo");
	public static ItemStack stoneThree = Util.constructItem(Material.AMETHYST_SHARD, "stonethree");
	public static ItemStack stoneFour = Util.constructItem(Material.AMETHYST_SHARD, "stonefour");
	public static ItemStack stoneFive = Util.constructItem(Material.AMETHYST_SHARD, "stonefive");
	public static ItemStack stoneSix = Util.constructItem(Material.AMETHYST_SHARD, "stonesix");
	public static ItemStack stoneSeven = Util.constructItem(Material.AMETHYST_SHARD, "stoneseven");
	public static ItemStack thunderBow = Util.constructItem(Material.BOW, "thunderbow");
	public static ItemStack thunderstone = Util.constructItem(Material.YELLOW_DYE, "thunderstone");
	public static ItemStack aresChestplate = Util.constructItem(Material.NETHERITE_CHESTPLATE, "areschestplate");
	public static ItemStack brolysRage = Util.constructItem(Material.LEATHER_CHESTPLATE, "brolysrage");
	public static ItemStack smgsStaff = Util.constructItem(Material.BLAZE_ROD, "smgsstaff");
	public static ItemStack theRing = Util.constructItem(Material.GOLD_NUGGET, "thering");
	public static ItemStack kfc = Util.constructItem(Material.COOKED_CHICKEN, "kfc");
	public static ItemStack medusasRose = Util.constructItem(Material.WITHER_ROSE, "medusasrose");
	public static ItemStack lokisSceptre = Util.constructItem(Material.BLAZE_ROD, "lokissceptre");
	public static ItemStack vaccine = Util.constructItem(Material.SPECTRAL_ARROW, "vaccine");
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
	public static ItemStack pokeball = Util.constructItem(Material.MAGMA_CREAM, "pokeball");
	public static ItemStack doubleEdgedSword = Util.constructItem(Material.NETHERITE_SWORD, "doubleedgedsword");
	public static ItemStack runecraftEssence = Util.constructItem(Material.SNOWBALL, "runecraftessence");
	public static ItemStack totemOfRunecraft = Util.constructItem(Material.TOTEM_OF_UNDYING, "totemofrunecraft");
	public static ItemStack invisibilityCloak = Util.constructItem(Material.ELYTRA, "invisibilitycloak");

	public static ItemStack superpick = superpick();
	private static ItemStack superpick() {
		ItemStack item = Util.constructItem(Material.NETHERITE_PICKAXE, "superpick");
		Damageable meta = (Damageable) item.getItemMeta();
		meta.setDamage(Material.NETHERITE_PICKAXE.getMaxDurability() - 100);
		item.setItemMeta(meta);
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
	
	public static ItemStack obsidianiksTeacup = obsidianiksTeacup();
	private static ItemStack obsidianiksTeacup() {
		ItemStack item = Util.constructItem(Material.POTION, "obsidianiksteacup");
		
		ItemMeta meta = item.getItemMeta();
		PotionMeta pmeta = (PotionMeta) meta;
		PotionData pdata = new PotionData(PotionType.WATER);
		pmeta.setBasePotionData(pdata);
		item.setItemMeta(meta);
		
		return item;
	}
	
	public static ItemStack escobarsSalt = escobarsSalt();
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
