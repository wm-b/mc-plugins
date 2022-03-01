package com.inceris.lockout.util;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Objective {

	// Normal Objectives
	public final static Objective findMineshaft = new Objective("Find a mineshaft", false);
	public final static Objective findPyramid = new Objective("Find a desert pyramid", false);
	public final static Objective findVillage = new Objective("Find a village", false);
	public final static Objective findRuinedPortal = new Objective("Find a ruined portal", false);
	public final static Objective findOceanRuins = new Objective("Find ocean ruins", false);
	public final static Objective findShipwreck = new Objective("Find a shipwreck", false);
	public final static Objective findFortress = new Objective("Find a nether fortress", false);
	public final static Objective findBastion = new Objective("Find a bastion remnant", false);
	public final static Objective findNetherFossil = new Objective("Find nether fossils", false);

	public final static Objective wearLeatherPiece = new Objective("Wear a piece of leather armor", false);
	public final static Objective wearLeatherSet = new Objective("Wear a set of leather armor", false);
	public final static Objective wearChainmailPiece = new Objective("Wear a piece of chainmail armor", false);
	public final static Objective wearChainmailSet = new Objective("Wear a set of chainmail armor", false);
	public final static Objective wearIronPiece = new Objective("Wear a piece of iron armor", false);
	public final static Objective wearIronSet = new Objective("Wear a set of iron armor", false);
	public final static Objective wearGoldPiece = new Objective("Wear a piece of gold armor", false);
	public final static Objective wearGoldSet = new Objective("Wear a set of gold armor", false);
	public final static Objective wearDiamondPiece = new Objective("Wear a piece of diamond armor", false);

	public final static Objective useAnvil = new Objective("Use an anvil", false);
	public final static Objective useComposter = new Objective("Use a composter", false);
	public final static Objective useFurnace = new Objective("Use a furnace", false);
	public final static Objective useLoom = new Objective("Use a loom", false);
	public final static Objective useGrindstone = new Objective("Use a grindstone", false);
	public final static Objective useFletchingTable = new Objective("Use a fletching table", false);
	public final static Objective useCartographyTable = new Objective("Use a cartography table", false);
	public final static Objective useBlastFurnace = new Objective("Use a blast furnace", false);
	public final static Objective useSmoker = new Objective("Use a smoker", false);
	public final static Objective useCampfire = new Objective("Use a campfire", false);
	public final static Objective useJukebox = new Objective("Use a jukebox", false);

	public final static Objective breedSheep = new Objective("Breed a sheep", false);
	public final static Objective breedCow = new Objective("Breed a cow", false);
	public final static Objective breedPig = new Objective("Breed a pig", false);
	public final static Objective breedRabbit = new Objective("Breed a rabbit", false);
	public final static Objective breedWolf = new Objective("Breed a wolf", false);
	public final static Objective breedChicken = new Objective("Breed a chicken", false);
	public final static Objective breedBee = new Objective("Breed a bee", false);
	public final static Objective breedCat = new Objective("Breed a cat", false);

	public final static Objective killOpponent = new Objective("Kill your opponent", false);
	public final static Objective killZombie = new Objective("Kill a zombie", false);
	public final static Objective killSpider = new Objective("Kill a spider", false);
	public final static Objective killCreeper = new Objective("Kill a creeper", false);
	public final static Objective killZombieVillager = new Objective("Kill a zombie villager", false);
	public final static Objective killSkeleton = new Objective("Kill a skeleton", false);
	public final static Objective killSilverfish = new Objective("Kill a silverfish", false);
	public final static Objective killIronGolem = new Objective("Kill an iron golem", false);
	public final static Objective killWitch = new Objective("Kill a witch", false);
	public final static Objective killWitherSkeleton = new Objective("Kill a wither skeleton", false);
	public final static Objective killBlaze = new Objective("Kill a blaze", false);
	public final static Objective killPhantom = new Objective("Kill a phantom", false);

	public final static Objective obtainDiamond = new Objective("Obtain a diamond", false);
	public final static Objective obtainRedstone = new Objective("Obtain redstone", false);
	public final static Objective obtainLapis = new Objective("Obtain lapis", false);
	public final static Objective obtainCoal = new Objective("Obtain coal", false);
	public final static Objective obtainRawIron = new Objective("Obtain raw iron", false);
	public final static Objective obtainRawGold = new Objective("Obtain raw gold", false);
	public final static Objective obtainIronIngot = new Objective("Obtain an iron ingot", false);
	public final static Objective obtainGoldIngot = new Objective("Obtain a gold ingot", false);
	public final static Objective obtainStone = new Objective("Obtain stone", false);
	public final static Objective obtainTuff = new Objective("Obtain tuff", false);
	public final static Objective obtainGranite = new Objective("Obtain granite", false);
	public final static Objective obtainAndesite = new Objective("Obtain andesite", false);
	public final static Objective obtainDiorite = new Objective("Obtain diorite", false);
	public final static Objective obtainDeepslate = new Objective("Obtain deepslate", false);

	public final static Objective eatApple = new Objective("Eat an apple", false);
	public final static Objective eatMushroomSoup = new Objective("Eat mushroom soup", false);
	public final static Objective eatBread = new Objective("Eat bread", false);
	public final static Objective eatPork = new Objective("Eat pork", false);
	public final static Objective eatChicken = new Objective("Eat chicken", false);
	public final static Objective eatBeef = new Objective("Eat beef", false);
	public final static Objective eatMutton = new Objective("Eat mutton", false);
	public final static Objective eatRabbit = new Objective("Eat rabbit", false);
	public final static Objective eatRabbitStew = new Objective("Eat rabbit stew", false);
	public final static Objective eatGlowBerries = new Objective("Eat glow berries", false);
	public final static Objective eatBeetrootSoup = new Objective("Eat beetroot soup", false);
	public final static Objective eatSweetBerries = new Objective("Eat sweet berries", false);
	public final static Objective eatCarrot = new Objective("Eat a carrot", false);
	public final static Objective eatPotato = new Objective("Eat a potato", false);
	public final static Objective eatCod = new Objective("Eat cod", false);
	public final static Objective eatSalmon = new Objective("Eat salmon", false);
	public final static Objective eatPufferfish = new Objective("Eat a pufferfish", false);
	public final static Objective eatSpiderEye = new Objective("Eat a spider eye", false);
	public final static Objective eatMelonSlice = new Objective("Eat a melon slice", false);
	public final static Objective eatDriedKelp = new Objective("Eat dried kelp", false);
	public final static Objective eatCookie = new Objective("Eat a cookie", false);
	public final static Objective eatRottenFlesh = new Objective("Eat rotten flesh", false);
	public final static Objective eatPumpkinPie = new Objective("Eat pumpkin pie", false);
	public final static Objective eatBeetroot = new Objective("Eat beetroot", false);

	public final static Objective dontDie = new Objective("Don't die", false);
	public final static Objective dontTakeDamage = new Objective("Don't take damage", false);
	public final static Objective dontCatchFire = new Objective("Don't catch fire", false);
	public final static Objective dontFall = new Objective("Don't fall", false);
	public final static Objective dontPickUpWood = new Objective("Don't pick up wood", false);
	public final static Objective dontPickUpObsidian = new Objective("Don't pick up obsidian", false);
	public final static Objective dont5Hearts = new Objective("Don't go below 5 hearts", false);

	public final static Objective tradeWithPiglin = new Objective("Trade with a piglin", false);
	public final static Objective reachBedrock = new Objective("Reach bedrock", false);
	public final static Objective swimWithDolphins = new Objective("Swim with dolphins", false);
	public final static Objective brewPotion = new Objective("Brew a potion", false);
	public final static Objective enchantItem = new Objective("Enchant an item", false);
	public final static Objective goToSleep = new Objective("Go to sleep", false);
	public final static Objective gainAbsorption = new Objective("Gain absorption", false);
	public final static Objective catchFish = new Objective("Catch a fish", false);
	public final static Objective rideMinecart = new Objective("Ride a minecart", false);
	public final static Objective sailBoat = new Objective("Sail a boat", false);
	public final static Objective teachVillagerTrade = new Objective("Teach a villager a new trade", false);
	public final static Objective buildSnowman = new Objective("Build a snowman", false);
	public final static Objective createIronGolem = new Objective("Create an iron golem", false);
	public final static Objective dieToDripstone = new Objective("Die to falling dripstone", false);
	public final static Objective dieToAnvil = new Objective("Die to a falling anvil", false);
	public final static Objective dieToTNT = new Objective("Die to TNT", false);
	public final static Objective suffocate = new Objective("Suffocate", false);
	public final static Objective goOutWithABang = new Objective("Go out with a bang", false);
	public final static Objective returnToSender = new Objective("Hit a ghast with a fireball", false);
	public final static Objective reachHeightLimit = new Objective("Reach the height limit", false);
	public final static Objective tameHorse = new Objective("Tame a horse", false);
	public final static Objective tameWolf = new Objective("Tame a wolf", false);
	public final static Objective tameCat = new Objective("Tame a cat", false);
	public final static Objective bucketAxolotl = new Objective("Bucket an axolotl", false);
	public final static Objective blockArrowWithShield = new Objective("Block an arrow with a shield", false);
	public final static Objective freeze = new Objective("Freeze in powdered snow", false);

	// Hard Objectives
	public final static Objective wearDiamondSet = new Objective("Wear a set of diamond armor", true);
	public final static Objective wearTurtleHelmet = new Objective("Wear a turtle helmet", true);

	public final static Objective breedAxolotl = new Objective("Breed an axolotl", true);
	public final static Objective breedFox = new Objective("Breed a fox", true);
	public final static Objective breedHorse = new Objective("Breed a horse", true);
	public final static Objective breedPolarBear = new Objective("Breed a polar bear", true);
	public final static Objective breedPanda = new Objective("Breed a panda", true);

	public final static Objective killIllager = new Objective("Kill an illager", true);
	public final static Objective killEnderDragon = new Objective("Kill the ender dragon", true);
	public final static Objective killWither = new Objective("Kill the wither", true);
	public final static Objective killElderGuardian = new Objective("Kill an elder guardian", true);

	public final static Objective obtainAncientDebris = new Objective("Obtain ancient debris", true);
	public final static Objective obtainNetheriteIngot = new Objective("Obtain a netherite ingot", true);
	public final static Objective obtainBeacon = new Objective("Obtain a beacon", true);

	public final static Objective eatPoisonousPotato = new Objective("Eat a poisonous potato", true);
	public final static Objective eatTropicalFish = new Objective("Eat a tropical fish", true);
	public final static Objective eatEnchantedGoldenApple = new Objective("Eat an enchanted golden apple", true);

	public final static Objective dontGetSlowed = new Objective("Don't get slowed", true);
	public final static Objective dontGetPoisoned = new Objective("Don't get poisoned", true);
	public final static Objective dontDieByBed = new Objective("Don't die to intentional game design", true);

	public final static Objective getMiningFatigue = new Objective("Get mining fatigue", true);
	public final static Objective getResurrected = new Objective("Get resurrected", true);
	public final static Objective catchBook = new Objective("Fish up an enchanted book", true);
	public final static Objective sleepAlone = new Objective("Sleep alone in the overworld", true);
	public final static Objective levitate = new Objective("Get the levitation effect", true);

	private String description;
	private boolean hard;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isHard() {
		return hard;
	}

	public void setHard(boolean hard) {
		this.hard = hard;
	}

	public Objective(String inDescription, boolean inHard) {
		description = inDescription;
		hard = inHard;
	}

	public static void complete(GameInstance gi, Objective objective, Player p) {

		List<Objective> objectives = gi.getObjectives();
		for (int i = 0; i < objectives.size(); i++) {
			Objective o = objectives.get(i);
			if (o.equals(objective)) {
				gi.getObjectives().remove(o);

				gi.getScoreboard().resetScores(ChatColor.GRAY + objective.getDescription());
				gi.getScoreboard().getObjective("Objectives").getScore(ChatColor.translateAlternateColorCodes('&',
						"&" + gi.getTeamCharacter(p) + objective.getDescription())).setScore(0);
				gi.refreshScoreboard();

				gi.getPlayerScores().put(gi.getTeam(p), gi.getPlayerScores().get(gi.getTeam(p)) + 1);
				gi.messagePlayers(
						"&" + gi.getTeamCharacter(p) + p.getName() + "&7 completed&8: &f" + objective.getDescription());

				for (List<Player> team : gi.getTeams()) {
					if (gi.getPlayerScores().get(team) >= 8) {
						Util.stopGameWithWinner(gi.getTeam(p));
					}
				}
			}
		}
	}

	public static Objective chooseRandomObjective(List<Objective> objectives, Objective[] choices, boolean hard) {
		if (!hard) {
			int count = 0;
			for (Objective o : choices) {
				if (o.isHard() == false) {
					count++;
				}
			}
			for (int i = 0; i < 100; i++) {
				Objective choice = choices[Util.randomNumberBetween(0, count - 1)];
				if (!objectives.contains(choice)) {
					return choice;
				}
			}
			return null;
		} else {
			for (int i = 0; i < 100; i++) {
				Objective choice = choices[Util.randomNumberBetween(0, choices.length - 1)];;
				if (!objectives.contains(choice)) {
					return choice;
				}
			}
			return null;
		}
	}

	public static List<Objective> chooseObjectives(boolean hard) {
		List<Objective> objectives = new ArrayList<Objective>();
		int count = 0;
		while (objectives.size() < 15 && count < 100) {
			int r1 = Util.randomNumberBetween(1, 100);

			if (r1 <= 10) {
				objectives.add(chooseRandomObjective(objectives, wearObjectives, hard));
				// use
			} else if (r1 <= 20) {
				objectives.add(chooseRandomObjective(objectives, useObjectives, hard));
				// breed
			} else if (r1 <= 25) {
				objectives.add(chooseRandomObjective(objectives, breedObjectives, hard));
				// kill
			} else if (r1 <= 35) {
				objectives.add(chooseRandomObjective(objectives, killObjectives, hard));
				// obtain
			} else if (r1 <= 47) {
				objectives.add(chooseRandomObjective(objectives, obtainObjectives, hard));
				// eat
			} else if (r1 <= 57) {
				objectives.add(chooseRandomObjective(objectives, eatObjectives, hard));
				// find
			} else if (r1 <= 63) {
				objectives.add(chooseRandomObjective(objectives, findObjectives, hard));
				// dont
			} else if (r1 <= 75) {
				objectives.add(chooseRandomObjective(objectives, dontObjectives, hard));
				// other
			} else {
				objectives.add(chooseRandomObjective(objectives, otherObjectives, hard));
			}
			count++;
		}
		return objectives;
	}

	public static Objective[] wearObjectives = new Objective[] { wearLeatherPiece, wearLeatherSet, wearChainmailPiece,
			wearChainmailSet, wearIronPiece, wearIronSet, wearGoldPiece, wearGoldSet, wearDiamondPiece, wearDiamondSet,
			wearTurtleHelmet };

	public static Objective[] useObjectives = new Objective[] { useAnvil, useComposter, useFurnace, useLoom,
			useGrindstone, useFletchingTable, useCartographyTable, useBlastFurnace, useSmoker, useCampfire,
			useJukebox };

	public static Objective[] breedObjectives = new Objective[] { breedSheep, breedCow, breedPig, breedRabbit,
			breedWolf, breedChicken, breedBee, breedCat, breedAxolotl, breedFox, breedHorse, breedPolarBear,
			breedPanda };

	public static Objective[] killObjectives = new Objective[] { killOpponent, killZombie, killSpider, killCreeper,
			killZombieVillager, killSkeleton, killSilverfish, killIronGolem, killWitch, killWitherSkeleton, killBlaze,
			killPhantom, killIllager, killEnderDragon, killWither, killElderGuardian };

	public static Objective[] obtainObjectives = new Objective[] { obtainDiamond, obtainRedstone, obtainLapis,
			obtainCoal, obtainRawIron, obtainRawGold, obtainIronIngot, obtainGoldIngot, obtainStone, obtainTuff,
			obtainGranite, obtainAndesite, obtainDiorite, obtainDeepslate, obtainAncientDebris, obtainNetheriteIngot,
			obtainBeacon };

	public static Objective[] eatObjectives = new Objective[] { eatApple, eatMushroomSoup, eatBread, eatPork,
			eatChicken, eatBeef, eatMutton, eatRabbit, eatRabbitStew, eatGlowBerries, eatBeetrootSoup, eatSweetBerries,
			eatCarrot, eatPotato, eatCod, eatSalmon, eatPufferfish, eatSpiderEye, eatMelonSlice, eatDriedKelp,
			eatCookie, eatRottenFlesh, eatPumpkinPie, eatBeetroot, eatPoisonousPotato, eatTropicalFish,
			eatEnchantedGoldenApple };

	public static Objective[] findObjectives = new Objective[] { findMineshaft, findPyramid, findVillage,
			findRuinedPortal, findOceanRuins, findShipwreck, findFortress, findBastion, findNetherFossil };

	public static Objective[] dontObjectives = new Objective[] { dontDie, dontPickUpWood, dontTakeDamage, dontFall,
			dontCatchFire, dontPickUpObsidian, dont5Hearts, dontGetSlowed, dontGetPoisoned, dontDieByBed };

	public static Objective[] otherObjectives = new Objective[] { tradeWithPiglin, reachBedrock, swimWithDolphins,
			brewPotion, enchantItem, goToSleep, gainAbsorption, catchFish, rideMinecart, sailBoat, teachVillagerTrade,
			buildSnowman, createIronGolem, dieToDripstone, dieToAnvil, dieToTNT, suffocate, goOutWithABang,
			returnToSender, reachHeightLimit, tameHorse, tameCat, tameWolf, bucketAxolotl, blockArrowWithShield, freeze,
			getMiningFatigue, getResurrected, catchBook, sleepAlone, levitate };

}
