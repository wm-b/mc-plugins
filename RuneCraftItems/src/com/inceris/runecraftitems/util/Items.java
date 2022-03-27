package com.inceris.runecraftitems.util;

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

	public final static ItemStack superpick = superpick();
	public final static ItemStack neverletgo = neverletgo();
	public final static ItemStack obsidianiksteacup = obsidianiksteacup();
	public final static ItemStack grailsword = grailsword();
	public final static ItemStack escobarssalt = escobarssalt();
	public final static ItemStack thunderbow = Util.constructItem(Material.BOW, "thunderbow");
	public final static ItemStack thunderstone = Util.constructItem(Material.YELLOW_DYE, "thunderstone");
	public final static ItemStack areschestplate = Util.constructItem(Material.NETHERITE_CHESTPLATE, "areschestplate");
	public final static ItemStack brolysrage = Util.constructItem(Material.LEATHER_CHESTPLATE, "brolysrage");
	public final static ItemStack smgsstaff = Util.constructItem(Material.BLAZE_ROD, "smgsstaff");
	public final static ItemStack thering = Util.constructItem(Material.GOLD_NUGGET, "thering");
	public final static ItemStack kfc = Util.constructItem(Material.COOKED_CHICKEN, "kfc");
	public final static ItemStack medusasrose = Util.constructItem(Material.WITHER_ROSE, "medusasrose");
	public final static ItemStack lokissceptre = Util.constructItem(Material.BLAZE_ROD, "lokissceptre");
	public final static ItemStack vaccine = Util.constructItem(Material.SPECTRAL_ARROW, "vaccine");
	public final static ItemStack grapplinghook = Util.constructItem(Material.FISHING_ROD, "grapplinghook");
	public final static ItemStack flytoken = Util.constructItem(Material.FEATHER, "flytoken");
	public final static ItemStack ranktoken = Util.constructItem(Material.NAME_TAG, "ranktoken");
	public final static ItemStack mobfreezer = Util.constructItem(Material.CLOCK, "mobfreezer");
	public final static ItemStack disguisecap = Util.constructItem(Material.LEATHER_HELMET, "disguisecap");
	public final static ItemStack eyeofthedragon = Util.constructItem(Material.ENDER_PEARL, "eyeofthedragon");
	public final static ItemStack cupidsbow = Util.constructItem(Material.BOW, "cupidsbow");
	public final static ItemStack chocolatebar = Util.constructItem(Material.NETHERITE_SCRAP, "chocolatebar");
	public final static ItemStack pulsingheart = Util.constructItem(Material.PINK_DYE, "pulsingheart");
	public final static ItemStack cupidswings = Util.constructItem(Material.ELYTRA, "cupidswings");
	public final static ItemStack loveisblind = Util.constructItem(Material.STICK, "loveisblind");
	public final static ItemStack brokenheart = Util.constructItem(Material.DIAMOND_SWORD, "brokenheart");
	public final static ItemStack stargazer = Util.constructItem(Material.NETHER_STAR, "stargazer");
	public final static ItemStack grapefruit = Util.constructItem(Material.POPPED_CHORUS_FRUIT, "grapefruit");
	public final static ItemStack ricksflowers = Util.constructItem(Material.PEONY, "ricksflowers");
	public final static ItemStack cupidscrown = Util.constructItem(Material.AMETHYST_CLUSTER, "cupidscrown");
	public final static ItemStack pokeball = Util.constructItem(Material.MAGMA_CREAM, "pokeball");
	public final static ItemStack doubleedgedsword = Util.constructItem(Material.NETHERITE_SWORD, "doubleedgedsword");
	public final static ItemStack runecraftessence = Util.constructItem(Material.SNOWBALL, "runecraftessence");
	public final static ItemStack totemofrunecraft = Util.constructItem(Material.TOTEM_OF_UNDYING, "totemofrunecraft");
	public final static ItemStack invisibilitycloak = Util.constructItem(Material.ELYTRA, "invisibilitycloak");
	public final static ItemStack mjolnir = Util.constructItem(Material.GOLDEN_AXE, "mjolnir");
	public final static ItemStack hermesboots = Util.constructItem(Material.LEATHER_BOOTS, "hermesboots");
	public final static ItemStack poseidonstrident = Util.constructItem(Material.TRIDENT, "poseidonstrident");
	public final static ItemStack theflamingsword = Util.constructItem(Material.IRON_SWORD, "theflamingsword");
	public final static ItemStack sunwukongsheadband = Util.constructItem(Material.LEATHER_HELMET,
			"sunwukongsheadband");
	public final static ItemStack kappasplatebody = Util.constructItem(Material.NETHERITE_CHESTPLATE,
			"kappasplatebody");
	public final static ItemStack crownoftwelvestars = Util.constructItem(Material.LEATHER_HELMET,
			"crownoftwelvestars");
	public final static ItemStack aphroditesroses = Util.constructItem(Material.ROSE_BUSH, "aphroditesroses");
	public final static ItemStack devilsgreencoat = Util.constructItem(Material.LEATHER_CHESTPLATE, "devilsgreencoat");
	public final static ItemStack sandalsofchrist = Util.constructItem(Material.LEATHER_BOOTS, "sandalsofchrist");
	public final static ItemStack swordofvictory = Util.constructItem(Material.NETHERITE_SWORD, "swordofvictory");
	public final static ItemStack axofpangu = Util.constructItem(Material.NETHERITE_AXE, "axofpangu");
	public final static ItemStack excalibur = Util.constructItem(Material.GOLDEN_SWORD, "excalibur");
	public final static ItemStack brahmastra = Util.constructItem(Material.STICK, "brahmastra");
	public final static ItemStack stoneone = Util.constructItem(Material.AMETHYST_SHARD, "stoneone");
	public final static ItemStack stonetwo = Util.constructItem(Material.AMETHYST_SHARD, "stonetwo");
	public final static ItemStack stonethree = Util.constructItem(Material.AMETHYST_SHARD, "stonethree");
	public final static ItemStack stonefour = Util.constructItem(Material.AMETHYST_SHARD, "stonefour");
	public final static ItemStack stonefive = Util.constructItem(Material.AMETHYST_SHARD, "stonefive");
	public final static ItemStack stonesix = Util.constructItem(Material.AMETHYST_SHARD, "stonesix");
	public final static ItemStack stoneseven = Util.constructItem(Material.AMETHYST_SHARD, "stoneseven");

	public static ItemStack getItem(String name) {
		name = name.toLowerCase();
		try {
			switch (name) {
			case "superpick":
				return superpick;
			case "grapplinghook":
				return grapplinghook;
			case "flytoken":
				return flytoken;
			case "ranktoken":
				return ranktoken;
			case "mobfreezer":
				return mobfreezer;
			case "disguisecap":
				return disguisecap;
			case "eyeofthedragon":
				return eyeofthedragon;
			case "cupidsbow":
				return cupidsbow;
			case "chocolatebar":
				return chocolatebar;
			case "pulsingheart":
				return pulsingheart;
			case "cupidswings":
				return cupidswings;
			case "loveisblind":
				return loveisblind;
			case "brokenheart":
				return brokenheart;
			case "stargazer":
				return stargazer;
			case "grapefruit":
				return grapefruit;
			case "neverletgo":
				return neverletgo;
			case "ricksflowers":
				return ricksflowers;
			case "cupidscrown":
				return cupidscrown;
			case "pokeball":
				return pokeball;
			case "doubleedgedsword":
				return doubleedgedsword;
			case "runecraftessence":
				return runecraftessence;
			case "totemofrunecraft":
				return totemofrunecraft;
			case "invisibilitycloak":
				return invisibilitycloak;
			case "thunderbow":
				return thunderbow;
			case "thunderstone":
				return thunderstone;
			case "areschestplate":
				return areschestplate;
			case "obsidianiksteacup":
				return obsidianiksteacup;
			case "brolysrage":
				return brolysrage;
			case "smgsstaff":
				return smgsstaff;
			case "thering":
				return thering;
			case "kfc":
				return kfc;
			case "medusasrose":
				return medusasrose;
			case "lokissceptre":
				return lokissceptre;
			case "escobarssalt":
				return escobarssalt;
			case "vaccine":
				return vaccine;
			case "mjolnir":
				return mjolnir;
			case "hermesboots":
				return hermesboots;
			case "poseidonstrident":
				return poseidonstrident;
			case "theflamingsword":
				return theflamingsword;
			case "sunwukongsheadband":
				return sunwukongsheadband;
			case "grailsword":
				return grailsword;
			case "kappasplatebody":
				return kappasplatebody;
			case "crownoftwelvestars":
				return crownoftwelvestars;
			case "aphroditesroses":
				return aphroditesroses;
			case "devilsgreencoat":
				return devilsgreencoat;
			case "sandalsofchrist":
				return sandalsofchrist;
			case "axofpangu":
				return axofpangu;
			case "excalibur":
				return excalibur;
			case "brahmastra":
				return brahmastra;
			case "swordofvictory":
				return swordofvictory;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private static ItemStack grailsword() {
		ItemStack item = Util.constructItem(Material.NETHERITE_SWORD, "grailsword");
		Damageable meta = (Damageable) item.getItemMeta();
		meta.setDamage(
				Material.NETHERITE_SWORD.getMaxDurability() - pl.getConfig().getInt("items.grailsword.durability"));
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

	private static ItemStack neverletgo() {
		ItemStack item = Util.constructItem(Material.DIAMOND_CHESTPLATE, "neverletgo");
		item.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 8);
		item.addEnchantment(Enchantment.BINDING_CURSE, 1);
		return item;
	}

	private static ItemStack obsidianiksteacup() {
		ItemStack item = Util.constructItem(Material.POTION, "obsidianiksteacup");

		ItemMeta meta = item.getItemMeta();
		PotionMeta pmeta = (PotionMeta) meta;
		PotionData pdata = new PotionData(PotionType.WATER);
		pmeta.setBasePotionData(pdata);
		item.setItemMeta(meta);

		return item;
	}

	private static ItemStack escobarssalt() {
		ItemStack item = Util.constructItem(Material.SUGAR, "escobarssalt");

		item.addUnsafeEnchantment(Enchantment.DIG_SPEED, 10);
		item.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 10);
		item.addUnsafeEnchantment(Enchantment.DURABILITY, 20);
		item.addUnsafeEnchantment(Enchantment.MENDING, 10);
		item.addUnsafeEnchantment(Enchantment.KNOCKBACK, 5);

		return item;
	}

}
