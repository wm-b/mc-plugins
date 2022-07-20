package com.inceris.lockout.util;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Objective {

	// Normal Objectives
	public final static Objective findMineshaft = new Objective("Find a mineshaft", false, false);
	public final static Objective findPyramid = new Objective("Find a desert pyramid", false, false);
	public final static Objective findVillage = new Objective("Find a village", true, false);
	public final static Objective findRuinedPortal = new Objective("Find a ruined portal", true, false);
	public final static Objective findOceanRuins = new Objective("Find ocean ruins", true, false);
	public final static Objective findShipwreck = new Objective("Find a shipwreck", true, false);
	public final static Objective findFortress = new Objective("Find a nether fortress", false, false);
	public final static Objective findBastion = new Objective("Find a bastion remnant", false, false);
	public final static Objective findNetherFossil = new Objective("Find nether fossils", false, false);

	public final static Objective wearLeatherPiece = new Objective("Wear a piece of leather armor", true, false);
	public final static Objective wearLeatherSet = new Objective("Wear a set of leather armor", false, false);
	public final static Objective wearChainmailPiece = new Objective("Wear a piece of chainmail armor", true, false);
	public final static Objective wearChainmailSet = new Objective("Wear a set of chainmail armor", false, false);
	public final static Objective wearIronPiece = new Objective("Wear a piece of iron armor", true, false);
	public final static Objective wearIronSet = new Objective("Wear a set of iron armor", false, false);
	public final static Objective wearGoldPiece = new Objective("Wear a piece of gold armor", true, false);
	public final static Objective wearGoldSet = new Objective("Wear a set of gold armor", false, false);
	public final static Objective wearDiamondPiece = new Objective("Wear a piece of diamond armor", true, false);

	public final static Objective useAnvil = new Objective("Use an anvil", true, false);
	public final static Objective useComposter = new Objective("Use a composter", true, false);
	public final static Objective useFurnace = new Objective("Use a furnace", true, false);
	public final static Objective useLoom = new Objective("Use a loom", true, false);
	public final static Objective useGrindstone = new Objective("Use a grindstone", true, false);
	public final static Objective useFletchingTable = new Objective("Use a fletching table", true, false);
	public final static Objective useCartographyTable = new Objective("Use a cartography table", true, false);
	public final static Objective useBlastFurnace = new Objective("Use a blast furnace", true, false);
	public final static Objective useSmoker = new Objective("Use a smoker", true, false);
	public final static Objective useCampfire = new Objective("Use a campfire", true, false);
	public final static Objective useJukebox = new Objective("Use a jukebox", true, false);

	public final static Objective breedSheep = new Objective("Breed a sheep", true, false);
	public final static Objective breedCow = new Objective("Breed a cow", true, false);
	public final static Objective breedPig = new Objective("Breed a pig", true, false);
	public final static Objective breedRabbit = new Objective("Breed a rabbit", true, false);
	public final static Objective breedWolf = new Objective("Breed a wolf", true, false);
	public final static Objective breedChicken = new Objective("Breed a chicken", true, false);
	public final static Objective breedBee = new Objective("Breed a bee", true, false);
	public final static Objective breedCat = new Objective("Breed a cat", false, false);

	public final static Objective killOpponent = new Objective("Kill your opponent", false, false);
	public final static Objective killZombie = new Objective("Kill a zombie", true, false);
	public final static Objective killSpider = new Objective("Kill a spider", true, false);
	public final static Objective killCreeper = new Objective("Kill a creeper", true, false);
	public final static Objective killZombieVillager = new Objective("Kill a zombie villager", true, false);
	public final static Objective killSkeleton = new Objective("Kill a skeleton", true, false);
	public final static Objective killIronGolem = new Objective("Kill an iron golem", true, false);
	public final static Objective killWitch = new Objective("Kill a witch", true, false);
	public final static Objective killWitherSkeleton = new Objective("Kill a wither skeleton", false, false);
	public final static Objective killBlaze = new Objective("Kill a blaze", false, false);

	public final static Objective obtainDiamond = new Objective("Obtain a diamond", true, false);
	public final static Objective obtainRedstone = new Objective("Obtain redstone", true, false);
	public final static Objective obtainLapis = new Objective("Obtain lapis", true, false);
	public final static Objective obtainCoal = new Objective("Obtain coal", true, false);
	public final static Objective obtainRawIron = new Objective("Obtain raw iron", true, false);
	public final static Objective obtainRawGold = new Objective("Obtain raw gold", true, false);
	public final static Objective obtainIronIngot = new Objective("Obtain an iron ingot", true, false);
	public final static Objective obtainGoldIngot = new Objective("Obtain a gold ingot", true, false);
	public final static Objective obtainStone = new Objective("Obtain stone", true, false);
	public final static Objective obtainTuff = new Objective("Obtain tuff", true, false);
	public final static Objective obtainGranite = new Objective("Obtain granite", true, false);
	public final static Objective obtainAndesite = new Objective("Obtain andesite", true, false);
	public final static Objective obtainDiorite = new Objective("Obtain diorite", true, false);
	public final static Objective obtainDeepslate = new Objective("Obtain deepslate", true, false);

	public final static Objective eatApple = new Objective("Eat an apple", true, false);
	public final static Objective eatMushroomSoup = new Objective("Eat mushroom soup", false, false);
	public final static Objective eatBread = new Objective("Eat bread", true, false);
	public final static Objective eatPork = new Objective("Eat pork", true, false);
	public final static Objective eatChicken = new Objective("Eat chicken", true, false);
	public final static Objective eatBeef = new Objective("Eat beef", true, false);
	public final static Objective eatMutton = new Objective("Eat mutton", true, false);
	public final static Objective eatRabbit = new Objective("Eat rabbit", true, false);
	public final static Objective eatRabbitStew = new Objective("Eat rabbit stew", false, false);
	public final static Objective eatGlowBerries = new Objective("Eat glow berries", false, false);
	public final static Objective eatBeetrootSoup = new Objective("Eat beetroot soup", false, false);
	public final static Objective eatSweetBerries = new Objective("Eat sweet berries", true, false);
	public final static Objective eatCarrot = new Objective("Eat a carrot", true, false);
	public final static Objective eatPotato = new Objective("Eat a potato", true, false);
	public final static Objective eatCod = new Objective("Eat cod", true, false);
	public final static Objective eatSalmon = new Objective("Eat salmon", true, false);
	public final static Objective eatPufferfish = new Objective("Eat a pufferfish", true, false);
	public final static Objective eatSpiderEye = new Objective("Eat a spider eye", true, false);
	public final static Objective eatMelonSlice = new Objective("Eat a melon slice", true, false);
	public final static Objective eatDriedKelp = new Objective("Eat dried kelp", true, false);
	public final static Objective eatCookie = new Objective("Eat a cookie", true, false);
	public final static Objective eatRottenFlesh = new Objective("Eat rotten flesh", true, false);
	public final static Objective eatPumpkinPie = new Objective("Eat pumpkin pie", false, false);
	public final static Objective eatBeetroot = new Objective("Eat beetroot", true, false);

	public final static Objective dontDie = new Objective("Don't die", false, false);
	public final static Objective dontTakeDamage = new Objective("Don't take damage", true, false);
	public final static Objective dontCatchFire = new Objective("Don't catch fire", true, false);
	public final static Objective dontFall = new Objective("Don't fall", false, false);
	public final static Objective dontPickUpWood = new Objective("Don't pick up wood", true, false);
	public final static Objective dontPickUpObsidian = new Objective("Don't pick up obsidian", false, false);
	public final static Objective dont5Hearts = new Objective("Don't go below 5 hearts", false, false);

	public final static Objective tradeWithPiglin = new Objective("Trade with a piglin", false, false);
	public final static Objective reachBedrock = new Objective("Reach bedrock", true, false);
	public final static Objective swimWithDolphins = new Objective("Swim with dolphins", true, false);
	public final static Objective brewPotion = new Objective("Brew a potion", false, false);
	public final static Objective enchantItem = new Objective("Enchant an item", false, false);
	public final static Objective goToSleep = new Objective("Go to sleep", true, false);
	public final static Objective gainAbsorption = new Objective("Gain absorption", false, false);
	public final static Objective catchFish = new Objective("Catch a fish", true, false);
	public final static Objective rideMinecart = new Objective("Ride a minecart", true, false);
	public final static Objective sailBoat = new Objective("Sail a boat", true, false);
	public final static Objective teachVillagerTrade = new Objective("Teach a villager a new trade", true, false);
	public final static Objective buildSnowman = new Objective("Build a snowman", true, false);
	public final static Objective createIronGolem = new Objective("Create an iron golem", true, false);
	public final static Objective dieToDripstone = new Objective("Die to falling dripstone", false, false);
	public final static Objective dieToAnvil = new Objective("Die to a falling anvil", true, false);
	public final static Objective dieToTNT = new Objective("Die to TNT", true, false);
	public final static Objective suffocate = new Objective("Suffocate", true, false);
	public final static Objective goOutWithABang = new Objective("Go out with a bang", false, false);
	public final static Objective returnToSender = new Objective("Hit a ghast with a fireball", false, false);
	public final static Objective reachHeightLimit = new Objective("Reach the height limit", true, false);
	public final static Objective tameHorse = new Objective("Tame a horse", false, false);
	public final static Objective tameWolf = new Objective("Tame a wolf", true, false);
	public final static Objective tameCat = new Objective("Tame a cat", true, false);
	public final static Objective bucketAxolotl = new Objective("Bucket an axolotl", false, false);
	public final static Objective blockArrowWithShield = new Objective("Block an arrow with a shield", true, false);
	public final static Objective freeze = new Objective("Freeze in powdered snow", true, false);

	// Hard Objectives
	public final static Objective wearDiamondSet = new Objective("Wear a set of diamond armor", false, true);
	public final static Objective wearTurtleHelmet = new Objective("Wear a turtle helmet", false, true);

	public final static Objective breedAxolotl = new Objective("Breed an axolotl", false, true);
	public final static Objective breedFox = new Objective("Breed a fox", false, true);
	public final static Objective breedHorse = new Objective("Breed a horse", false, true);
	public final static Objective breedPolarBear = new Objective("Breed a polar bear", false, true);
	public final static Objective breedPanda = new Objective("Breed a panda", false, true);

	public final static Objective killSilverfish = new Objective("Kill a silverfish", false, true);
	public final static Objective killIllager = new Objective("Kill an illager", false, true);
	public final static Objective killEnderDragon = new Objective("Kill the ender dragon", false, true);
	public final static Objective killWither = new Objective("Kill the wither", false, true);
	public final static Objective killElderGuardian = new Objective("Kill an elder guardian", false, true);

	public final static Objective obtainAncientDebris = new Objective("Obtain ancient debris", false, true);
	public final static Objective obtainNetheriteIngot = new Objective("Obtain a netherite ingot", false, true);
	public final static Objective obtainBeacon = new Objective("Obtain a beacon", false, true);

	public final static Objective eatPoisonousPotato = new Objective("Eat a poisonous potato", false, true);
	public final static Objective eatTropicalFish = new Objective("Eat a tropical fish", false, true);
	public final static Objective eatEnchantedGoldenApple = new Objective("Eat an enchanted golden apple", false, true);

	public final static Objective dontGetSlowed = new Objective("Don't get slowed", false, true);
	public final static Objective dontGetPoisoned = new Objective("Don't get poisoned", false, true);
	public final static Objective dontDieByBed = new Objective("Don't die to intentional game design", false, true);

	public final static Objective getMiningFatigue = new Objective("Get mining fatigue", false, true);
	public final static Objective getResurrected = new Objective("Get resurrected", false, true);
	public final static Objective catchBook = new Objective("Fish up an enchanted book", false, true);
	public final static Objective sleepAlone = new Objective("Sleep alone in the overworld", false, true);
	public final static Objective levitate = new Objective("Get the levitation effect", false, true);

	private String description;
	private boolean hard;
	private boolean easy;

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

	public boolean isEasy() {
		return easy;
	}

	public void setEasy(boolean easy) {
		this.easy = easy;
	}

	public Objective(String inDescription, boolean inHard, boolean inEasy) {
		description = inDescription;
		hard = inHard;
		easy = inEasy;
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
						Util.stopGameWithWinner(team);
					}
				}
			}
		}
	}

	public static Objective chooseRandomObjective(List<Objective> objectives, Objective[] choices, boolean easy,
			boolean hard) {
		if (hard) {
			for (int i = 0; i < 100; i++) {
				Objective choice = choices[Util.randomNumberBetween(0, choices.length - 1)];
				;
				if (!objectives.contains(choice)) {
					return choice;
				}
			}
			return null;

		} else if (!easy) {
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
		}
	}

	public static List<Objective> chooseObjectives(boolean easy, boolean hard) {
		List<Objective> objectives = new ArrayList<Objective>();
		int count = 0;
		while (objectives.size() < 15 && count < 100) {
			int r1 = Util.randomNumberBetween(1, 100);

			if (r1 <= 10) {
				Objective o = chooseRandomObjective(objectives, wearObjectives, easy, hard);
				if (o != null) {
					objectives.add(o);
				}
				// use
			} else if (r1 <= 20) {
				Objective o = chooseRandomObjective(objectives, useObjectives, easy, hard);
				if (o != null) {
					objectives.add(o);
				}
				// breed
			} else if (r1 <= 25) {
				Objective o = chooseRandomObjective(objectives, breedObjectives, easy, hard);
				if (o != null) {
					objectives.add(o);
				}
				// kill
			} else if (r1 <= 33) {
				Objective o = chooseRandomObjective(objectives, killObjectives, easy, hard);
				if (o != null) {
					objectives.add(o);
				}
				// obtain
			} else if (r1 <= 46) {
				Objective o = chooseRandomObjective(objectives, obtainObjectives, easy, hard);
				if (o != null) {
					objectives.add(o);
				}
				// eat
			} else if (r1 <= 57) {
				Objective o = chooseRandomObjective(objectives, eatObjectives, easy, hard);
				if (o != null) {
					objectives.add(o);
				}
				// find
			} else if (r1 <= 63) {
				Objective o = chooseRandomObjective(objectives, findObjectives, easy, hard);
				if (o != null) {
					objectives.add(o);
				}
				// dont
			} else if (r1 <= 75) {
				Objective o = chooseRandomObjective(objectives, dontObjectives, easy, hard);
				if (o != null) {
					objectives.add(o);
				}
				// other
			} else {
				Objective o = chooseRandomObjective(objectives, otherObjectives, easy, hard);
				if (o != null) {
					objectives.add(o);
				}
			}
			count++;
		}
		return objectives;
	}

	public static Objective[] wearObjectives = new Objective[] { wearLeatherPiece, wearChainmailPiece, wearIronPiece,
			wearGoldPiece, wearDiamondPiece, wearLeatherSet, wearChainmailSet, wearIronSet, wearGoldSet, wearDiamondSet,
			wearTurtleHelmet };

	public static Objective[] useObjectives = new Objective[] { useAnvil, useComposter, useFurnace, useLoom,
			useGrindstone, useFletchingTable, useCartographyTable, useBlastFurnace, useSmoker, useCampfire,
			useJukebox };

	public static Objective[] breedObjectives = new Objective[] { breedSheep, breedCow, breedPig, breedRabbit,
			breedWolf, breedChicken, breedBee, breedCat, breedAxolotl, breedFox, breedHorse, breedPolarBear,
			breedPanda };

	public static Objective[] killObjectives = new Objective[] { killZombie, killSpider, killCreeper,
			killZombieVillager, killSkeleton, killIronGolem, killSilverfish, killWitch, killOpponent,
			killWitherSkeleton, killBlaze, killIllager, killEnderDragon, killWither, killElderGuardian };

	public static Objective[] obtainObjectives = new Objective[] { obtainDiamond, obtainRedstone, obtainLapis,
			obtainCoal, obtainRawIron, obtainRawGold, obtainIronIngot, obtainGoldIngot, obtainStone, obtainTuff,
			obtainGranite, obtainAndesite, obtainDiorite, obtainDeepslate, obtainAncientDebris, obtainNetheriteIngot,
			obtainBeacon };

	public static Objective[] eatObjectives = new Objective[] { eatApple, eatBread, eatPork, eatChicken, eatBeef,
			eatMutton, eatRabbit, eatSweetBerries, eatCarrot, eatPotato, eatCod, eatSalmon, eatPufferfish, eatSpiderEye,
			eatMelonSlice, eatDriedKelp, eatCookie, eatRottenFlesh, eatPumpkinPie, eatBeetroot, eatGlowBerries,
			eatBeetrootSoup, eatRabbitStew, eatMushroomSoup, eatPoisonousPotato, eatTropicalFish,
			eatEnchantedGoldenApple };

	public static Objective[] findObjectives = new Objective[] { findVillage, findRuinedPortal, findOceanRuins,
			findShipwreck, findMineshaft, findPyramid, findFortress, findBastion, findNetherFossil };

	public static Objective[] dontObjectives = new Objective[] { dontPickUpWood, dontTakeDamage, dontCatchFire, dontDie,
			dontFall, dontPickUpObsidian, dont5Hearts, dontGetSlowed, dontGetPoisoned, dontDieByBed };

	public static Objective[] otherObjectives = new Objective[] { reachBedrock, swimWithDolphins, goToSleep, catchFish,
			rideMinecart, sailBoat, teachVillagerTrade, buildSnowman, createIronGolem, dieToAnvil, suffocate,
			reachHeightLimit, tameCat, tameWolf, tameHorse, enchantItem, tradeWithPiglin, goOutWithABang,
			gainAbsorption, returnToSender, dieToDripstone, dieToTNT, brewPotion, bucketAxolotl, blockArrowWithShield,
			freeze, getMiningFatigue, getResurrected, catchBook, sleepAlone, levitate };

}
