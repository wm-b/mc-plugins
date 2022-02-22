package com.inceris.lockout.util;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;

public class Objective {
	
	// Normal Objectives
	public final static Objective wearLeatherPiece = new Objective("Wear a piece of leather armor", false, false);
	public final static Objective wearLeatherSet = new Objective("Wear a set of leather armor", false, false);
	public final static Objective wearChainmailPiece = new Objective("Wear a piece of chainmail armor", false, false);
	public final static Objective wearChainmailSet = new Objective("Wear a set of chainmail armor", false, false);
	public final static Objective wearIronPiece = new Objective("Wear a piece of iron armor", false, false);
	public final static Objective wearIronSet = new Objective("Wear a set of iron armor", false, false);
	public final static Objective wearGoldPiece = new Objective("Wear a piece of gold armor", false, false);
	public final static Objective wearGoldSet = new Objective("Wear a set of gold armor", false, false);
	public final static Objective wearDiamondPiece = new Objective("Wear a piece of diamond armor", false, false);
	
	public final static Objective useAnvil = new Objective("Use an anvil", false, false);
	public final static Objective useComposter = new Objective("Use a composter", false, false);
	public final static Objective useFurnace = new Objective("Use a furnace", false, false);
	public final static Objective useLoom = new Objective("Use a loom", false, false);
	public final static Objective useGrindstone = new Objective("Use a grindstone", false, false);
	public final static Objective useFletchingTable = new Objective("Use a fletching table", false, false);
	public final static Objective useCartographyTable = new Objective("Use a cartography table", false, false);
	public final static Objective useBlastFurnaces = new Objective("Use a blast furnace", false, false);
	public final static Objective useSmoker = new Objective("Use a smoker", false, false);
	public final static Objective useCampfire = new Objective("Use a campfire", false, false);

	public final static Objective breedSheep = new Objective("Breed a sheep", false, false);
	public final static Objective breedCow = new Objective("Breed a cow", false, false);
	public final static Objective breedPig = new Objective("Breed a pig", false, false);
	public final static Objective breedRabbit = new Objective("Breed a rabbit", false, false);
	public final static Objective breedWolf = new Objective("Breed a wolf", false, false);
	public final static Objective breedChicken = new Objective("Breed a chicken", false, false);
	public final static Objective breedBee = new Objective("Breed a bee", false, false);
	public final static Objective breedCat = new Objective("Breed a cat", false, false);
	
	public final static Objective killOpponent = new Objective("Kill your opponent", false, false);
	public final static Objective killZombie = new Objective("Kill a zombie", false, false);
	public final static Objective killSpider = new Objective("Kill a spider", false, false);
	public final static Objective killCreeper = new Objective("Kill a creeper", false, false);
	public final static Objective killZombieVillager = new Objective("Kill a zombie villager", false, false);
	public final static Objective killSkeleton = new Objective("Kill a skeleton", false, false);
	public final static Objective killSilverfish = new Objective("Kill a silverfish", false, false);
	public final static Objective killIronGolem = new Objective("Kill an iron golem", false, false);
	public final static Objective killWitch = new Objective("Kill a witch", false, false);
	public final static Objective killWitherSkeleton = new Objective("Kill a wither skeleton", false, false);
	public final static Objective killBlaze = new Objective("Kill a blaze", false, false);
	public final static Objective killPhantom = new Objective("Kill a phantom", false, false);

	public final static Objective obtainDiamonds = new Objective("Obtain diamonds", false, false);
	public final static Objective obtainRedstone = new Objective("Obtain redstone", false, false);
	public final static Objective obtainLapis = new Objective("Obtain lapis", false, false);
	public final static Objective obtainCoal = new Objective("Obtain coal", false, false);
	public final static Objective obtainRawIron = new Objective("Obtain raw iron", false, false);
	public final static Objective obtainRawGold = new Objective("Obtain raw gold", false, false);
	public final static Objective obtainIronIngot = new Objective("Obtain an iron ingot", false, false);
	public final static Objective obtainGoldIngot = new Objective("Obtain a gold ingot", false, false);
	public final static Objective obtainStone = new Objective("Obtain stone", false, false);
	public final static Objective obtainTuff = new Objective("Obtain tuff", false, false);
	public final static Objective obtainGranite = new Objective("Obtain granite", false, false);
	public final static Objective obtainAndesite = new Objective("Obtain andesite", false, false);
	public final static Objective obtainDiorite = new Objective("Obtain diorite", false, false);
	public final static Objective obtainDeepslate = new Objective("Obtain deepslate", false, false);
	
	public final static Objective eatApple = new Objective("Eat an apple", false, false);
	public final static Objective eatMushroomSoup = new Objective("Eat mushroom soup", false, false);
	public final static Objective eatBread = new Objective("Eat bread", false, false);
	public final static Objective eatPork = new Objective("Eat pork", false, false);
	public final static Objective eatChicken = new Objective("Eat chicken", false, false);
	public final static Objective eatBeef = new Objective("Eat beef", false, false);
	public final static Objective eatMutton = new Objective("Eat mutton", false, false);
	public final static Objective eatRabbit = new Objective("Eat rabbit", false, false);
	public final static Objective eatRabbitStew = new Objective("Eat rabbit stew", false, false);
	public final static Objective eatGlowBerries = new Objective("Eat glow berries", false, false);
	public final static Objective eatBeetrootSoup = new Objective("Eat beetroot soup", false, false);
	public final static Objective eatSweetBerries = new Objective("Eat sweet berries", false, false);
	public final static Objective eatCarrot = new Objective("Eat a carrot", false, false);
	public final static Objective eatPotato = new Objective("Eat a potato", false, false);
	public final static Objective eatCod = new Objective("Eat cod", false, false);
	public final static Objective eatSalmon = new Objective("Eat salmon", false, false);
	public final static Objective eatPufferfish = new Objective("Eat a pufferfish", false, false);
	public final static Objective eatSpiderEye = new Objective("Eat a spider eye", false, false);
	public final static Objective eatMelonSlice = new Objective("Eat a melon slice", false, false);
	public final static Objective eatDriedKelp = new Objective("Eat dried kelp", false, false);
	public final static Objective eatCookie = new Objective("Eat a cookie", false, false);
	public final static Objective eatRottenFlesh = new Objective("Eat rotten flesh", false, false);
	public final static Objective eatPumpkinPie = new Objective("Eat pumpkin pie", false, false);
	public final static Objective eatBeetroot = new Objective("Eat beetroot", false, false);
	
	public final static Objective dontDie = new Objective("Don't die", false, false);
	public final static Objective dontTakeDamage = new Objective("Don't take damage", false, false);
	public final static Objective dontCatchFire = new Objective("Don't catch fire", false, false);
	public final static Objective dontFall = new Objective("Don't fall", false, false);
	public final static Objective dontPickUpObsidian = new Objective("Don't pick up obsidian", false, false);
	public final static Objective dont5Hearts = new Objective("Don't go below 5 hearts", false, false);
	
	public final static Objective tradeWithPiglin = new Objective("Trade with a piglin", false, false);
	public final static Objective reachBedrock = new Objective("Reach bedrock", false, false);
	public final static Objective swimWithDolphins = new Objective("Swim with dolphins", false, false);
	public final static Objective brewPotion = new Objective("Brew a potion", false, false);
	public final static Objective enchantItem = new Objective("Enchant an item", false, false);
	public final static Objective goToSleep = new Objective("Go to sleep", false, false);
	public final static Objective gainAbsorption = new Objective("Gain absorption", false, false);
	public final static Objective catchFish = new Objective("Catch a fish", false, false);
	public final static Objective rideMinecart = new Objective("Ride a minecart", false, false);
	public final static Objective sailBoat = new Objective("Sail a boat", false, false);
	public final static Objective tradeWithVillager = new Objective("Trade with a villager", false, false);
	public final static Objective buildSnowman = new Objective("Build a snowman", false, false);
	public final static Objective createIronGolem = new Objective("Create an iron golem", false, false);
	public final static Objective dieToDripstone = new Objective("Die to falling dripstone", false, false);
	public final static Objective dieToAnvil = new Objective("Die to a falling anvil", false, false);
	public final static Objective dieToTNT = new Objective("Die to TNT", false, false);
	public final static Objective suffocate = new Objective("Suffocate", false, false);
	public final static Objective goOutWithABang = new Objective("Go out with a bang", false, false);
	public final static Objective getMiningFatigue = new Objective("Get mining fatigue", false, false);
	
	// Hard Objectives
	public final static Objective wearDiamondSet = new Objective("Wear a set of diamond armor", false, true);
	public final static Objective wearTurtleHelmet = new Objective("Wear a turtle helmet", false, true);
	
	public final static Objective breedAxolotl = new Objective("Breed an axolotl", false, true);
	public final static Objective breedFox = new Objective("Breed a fox", false, true);
	public final static Objective breedHorse = new Objective("Breed a horse", false, true);
	public final static Objective breedPolarBear = new Objective("Breed a polar bear", false, true);
	public final static Objective breedPanda = new Objective("Breed a panda", false, true);

	public final static Objective killIllager = new Objective("Kill an illager", false, true);
	public final static Objective killEnderDragon = new Objective("Kill the ender dragon", false, true);
	public final static Objective killWither = new Objective("Kill the wither", false, true);
	public final static Objective killElderGuardian = new Objective("Kill an elder guardian", false, true);

	public final static Objective obtainAncientDebris = new Objective("Obtain ancient debris", false, true);
	public final static Objective obtainNetheriteIngot = new Objective("Obtain netherite ingot", false, true);
	public final static Objective obtainBeacon = new Objective("Obtain a beacon", false, true);
	
	public final static Objective eatPoisonousPotato = new Objective("Eat a poisonous potato", false, true);
	public final static Objective eatTropicalFish = new Objective("Eat a tropical fish", false, true);
	public final static Objective eatEnchantedGoldenApple = new Objective("Eat an enchanted golden apple", false, true);
	
	public final static Objective dontGetSlowed = new Objective("Don't get slowed", false, true);
	public final static Objective dontGetPoisoned = new Objective("Don't get poisoned", false, true);
	public final static Objective dontDieByBed = new Objective("Don't die to intention game design", false, true);
	
	public final static Objective getResurrected = new Objective("Get resurrected by a totem of undying", false, true);
	public final static Objective catchBook = new Objective("Fish up an enchanted book", false, true);
	public final static Objective sleepAlone = new Objective("Sleep alone in the overworld", false, true);
	public final static Objective levitate = new Objective("Get the levitation effect", false, true);
	
	private String description;
	private boolean complete;
	private boolean hard;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isComplete() {
		return complete;
	}

	public void setComplete(boolean complete) {
		this.complete = complete;
	}
	
	public boolean isHard() {
		return hard;
	}

	public void setHard(boolean hard) {
		this.hard = hard;
	}

	
	public Objective(String inDescription, boolean inComplete, boolean inHard) {
		description = inDescription;
		complete = inComplete;
		hard = inHard;
	}
	
	public static void complete(GameInstance gi, Objective objective, Player p) {
		
		for (Objective o : gi.getObjectives()) {
			if (o.equals(objective)) {
				o.setComplete(true);
			}
		}
		
		gi.getPlayerScores().put(p, gi.getPlayerScores().get(p) + 1);
		gi.messagePlayers(p.getName() + " completed: " + objective.getDescription());
	}
	
	public static List<Objective> chooseObjectives(boolean hard) {
		List<Objective> objectives = new ArrayList<Objective>();
		Objective[] fullList = buildObjectiveArray();
		
		while (objectives.size() <= 15) {
			
			int r1 = Util.randomNumberBetween(1, 100);
			
			if (r1 <= 10) {
				int r2;
				if (hard) r2 = Util.randomNumberBetween(0, 10);
				else r2 = Util.randomNumberBetween(0, 8);
				if (!objectives.contains(fullList[r2])) {
					objectives.add(fullList[r2]);
				}
				
			} else if (r1 <= 30) {
				int r2 = Util.randomNumberBetween(11, 20);
				if (!objectives.contains(fullList[r2])) {
					objectives.add(fullList[r2]);
				}
				
			} else if (r1 <= 35) {
				int r2;
				if (hard) r2 = Util.randomNumberBetween(21, 33);
				else r2 = Util.randomNumberBetween(21, 28);
				if (!objectives.contains(fullList[r2])) {
					objectives.add(fullList[r2]);
				}
				
			} else if (r1 <= 45) {
				int r2;
				if (hard) r2 = Util.randomNumberBetween(34, 49);
				else r2 = Util.randomNumberBetween(34, 45);
				if (!objectives.contains(fullList[r2])) {
					objectives.add(fullList[r2]);
				}
				
			} else if (r1 <= 55) {
				int r2;
				if (hard) r2 = Util.randomNumberBetween(50, 66);
				else r2 = Util.randomNumberBetween(50, 63);
				if (!objectives.contains(fullList[r2])) {
					objectives.add(fullList[r2]);
				}
				
			} else if (r1 <= 65) {
				int r2;
				if (hard) r2 = Util.randomNumberBetween(67, 96);
				else r2 = Util.randomNumberBetween(67, 90);
				if (!objectives.contains(fullList[r2])) {
					objectives.add(fullList[r2]);
				}
				
			} else if (r1 <= 75) {
				int r2;
				if (hard) r2 = Util.randomNumberBetween(94, 102);
				else r2 = Util.randomNumberBetween(94, 99);
				if (!objectives.contains(fullList[r2])) {
					objectives.add(fullList[r2]);
				}
				
			} else {
				int r2;
				if (hard) r2 = Util.randomNumberBetween(103, 125);
				else r2 = Util.randomNumberBetween(103, 121);
				if (!objectives.contains(fullList[r2])) {
					objectives.add(fullList[r2]);
				}
				
			}
			
		}
		
		return objectives;
	}
	
	public static Objective[] buildObjectiveArray() {
		Objective[] objectives = new Objective[] { wearLeatherPiece, wearLeatherSet, wearChainmailPiece,
				wearChainmailSet, wearIronPiece, wearIronSet, wearGoldPiece, wearGoldSet, wearDiamondPiece,
				wearDiamondSet, wearTurtleHelmet, useAnvil, useComposter, useFurnace, useLoom, useGrindstone,
				useFletchingTable, useCartographyTable, useBlastFurnaces, useSmoker, useCampfire, breedSheep, breedCow,
				breedPig, breedRabbit, breedWolf, breedChicken, breedBee, breedCat, breedAxolotl, breedFox, breedHorse,
				breedPolarBear, breedPanda, killOpponent, killZombie, killSpider, killCreeper, killZombieVillager,
				killSkeleton, killSilverfish, killIronGolem, killWitch, killWitherSkeleton, killBlaze, killPhantom,
				killIllager, killEnderDragon, killWither, killElderGuardian, obtainDiamonds, obtainRedstone,
				obtainLapis, obtainCoal, obtainRawIron, obtainRawGold, obtainIronIngot, obtainGoldIngot, obtainStone,
				obtainTuff, obtainGranite, obtainAndesite, obtainDiorite, obtainDeepslate, obtainAncientDebris,
				obtainNetheriteIngot, obtainBeacon, eatApple, eatMushroomSoup, eatBread, eatPork, eatChicken, eatBeef,
				eatMutton, eatRabbit, eatRabbitStew, eatGlowBerries, eatBeetrootSoup, eatSweetBerries, eatCarrot,
				eatPotato, eatCod, eatSalmon, eatPufferfish, eatSpiderEye, eatMelonSlice, eatDriedKelp, eatCookie,
				eatRottenFlesh, eatPumpkinPie, eatBeetroot, eatPoisonousPotato, eatTropicalFish,
				eatEnchantedGoldenApple, dontDie, dontTakeDamage, dontFall, dontCatchFire, dontPickUpObsidian,
				dont5Hearts, dontGetSlowed, dontGetPoisoned, dontDieByBed, tradeWithPiglin, reachBedrock,
				swimWithDolphins, brewPotion, enchantItem, goToSleep, gainAbsorption, catchFish, rideMinecart, sailBoat,
				tradeWithVillager, buildSnowman, createIronGolem, dieToDripstone, dieToAnvil, dieToTNT, suffocate,
				goOutWithABang, getMiningFatigue, getResurrected, catchBook, sleepAlone, levitate };
		return objectives;
	}
	
}
