package com.inceris.lockout.commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.inceris.lockout.Lockout;
import com.inceris.lockout.util.GameInstance;
import com.inceris.lockout.util.Util;

public class LockoutCommand {

	public static Lockout pl = Lockout.getPlugin(Lockout.class);

	public static boolean cmd(CommandSender sender, String[] args) {
		if (args.length == 0) {
			sender.sendMessage(Util.format("Developed by &cInceris &ffor &9lockout.biz"));
			return true;

		} else if (args[0].equalsIgnoreCase("start") && sender.hasPermission("lockout.admin")) {

			if (args.length == 1) {
				if (pl.getPrepTeamB().size() > 0 && pl.getPrepTeamE().size() > 0) {
					if (Arrays.asList(args).contains("-h")) {
						new GameInstance(pl.getPrepTeamB(), pl.getPrepTeamE(), true);
					} else {
						new GameInstance(pl.getPrepTeamB(), pl.getPrepTeamE(),
								Util.enableHardMode(pl.getPrepTeamB(), pl.getPrepTeamE()));
					}
					pl.setPreventJoiningTeams(true);
					pl.setPrepTeamB(new ArrayList<Player>());
					pl.setPrepTeamE(new ArrayList<Player>());
				} else {
					sender.sendMessage(Util
							.format("One or both of the team prep lists are empty! Specify players to start a game."));
				}

			} else if (args[1].equalsIgnoreCase(args[2])) {

				sender.sendMessage(Util.format("You can't start a game with yourself!"));
				return true;

			} else {

				List<Player> players = new ArrayList<Player>();
				for (int i = 1; i < args.length; i++) {
					if (pl.getServer().getPlayer(args[i]) instanceof Player) {
						players.add(pl.getServer().getPlayer(args[i]));
					} else if (!args[i].equals("-h")) {
						sender.sendMessage(Util.format("One of those arguments doesn't look like a player!"));
						sender.sendMessage("/lockout start [player 1] [player 2] ... {-h}");
						return true;
					}
				}

				if (GameInstance.get(players.get(0)) == null && GameInstance.get(players.get(1)) == null) {
					if (Arrays.asList(args).contains("-h")) {
						new GameInstance(Util.teamB(players), Util.teamE(players), true);
					} else {
						new GameInstance(Util.teamB(players), Util.teamE(players), Util.enableHardMode(players));
					}
				} else {
					sender.sendMessage(Util.format("Existing game still active! Please wait and then try again."));
				}
			}

		} else if (args[0].equalsIgnoreCase("stop") && sender.hasPermission("lockout.admin")) {
			if (args.length == 1 && sender instanceof Player) {
				GameInstance.get((Player) sender).reset();
				return true;
			} else if (pl.getServer().getPlayer(args[1]) != null) {
				GameInstance.get(pl.getServer().getPlayer(args[1])).reset();
				return true;
			}

		} else if (args[0].equalsIgnoreCase("forcewin") && sender.hasPermission("lockout.admin")) {
			if (pl.getServer().getPlayer(args[1]) != null && args.length > 1) {
				Player p = pl.getServer().getPlayer(args[1]);
				Util.stopGameWithWinner(GameInstance.get(p).getTeam(p));
				return true;
			} else {
				sender.sendMessage(Util.format("&9/lockout forcewin [player]"));
				return true;
			}

		} else if (args[0].equalsIgnoreCase("list") && sender.hasPermission("lockout.admin")) {
			for (GameInstance gi : pl.getGameInstances()) {
				String players = "";
				for (Player p : gi.getPlayers()) {
					players += p.getName() + " ";
				}
				sender.sendMessage(Util.format("Game running in: " + gi.getWorld().getName() + " with " + players));
			}

		} else if (args[0].equalsIgnoreCase("scoreboard") && sender.hasPermission("lockout.admin")) {
			if (sender instanceof Player && pl.getServer().getPlayer(args[1]) != null) {
				Player p = (Player) sender;
				Player target = pl.getServer().getPlayer(args[1]);
				GameInstance gi = GameInstance.get(target);
				if (gi == null) {
					sender.sendMessage(Util.format("That player is not in a game of lockout!"));
					return true;
				} else {
					gi.getScoreboardViewers().add(p);
					p.setScoreboard(gi.getScoreboard());
					return true;
				}
			} else {
				sender.sendMessage(Util.format("&9/lockout scoreboard [player]"));
				return true;
			}

		} else if (args[0].equalsIgnoreCase("objectives") && sender.hasPermission("lockout.admin")) {
			if (sender instanceof Player) {
				GameInstance gi = GameInstance.get((Player) sender);
				if (gi == null) {
					sender.sendMessage(Util.format("You're not in a game of lockout!"));
				} else {
					sender.sendMessage(Util.format(gi.printObjectives()));
				}
			}

		} else if (args[0].equalsIgnoreCase("info") && sender.hasPermission("lockout.admin")) {
			if (args.length == 1) {
				if (sender instanceof Player) {
					GameInstance gi = GameInstance.get((Player) sender);
					if (gi == null) {
						sender.sendMessage(Util.format("You're not in a game of lockout!"));
					} else {
						sender.sendMessage(Util.format("World: " + gi.getWorld().getName()));
						sender.sendMessage(Util.format("Nether: " + gi.getNether().getName()));
						sender.sendMessage(Util.format("End: " + gi.getEnd().getName()));
						sender.sendMessage(Util.format("Active: " + gi.isActive()));
						sender.sendMessage(Util.format("Start Time: " + gi.getStartTime()));
					}
				}
			} else if (pl.getServer().getWorld(args[1]) != null) {
				GameInstance gi = GameInstance.get(pl.getServer().getWorld(args[1]));
				sender.sendMessage(Util.format("World: " + gi.getWorld().getName()));
				sender.sendMessage(Util.format("Active: " + gi.isActive()));
				sender.sendMessage(Util.format("Start Time: " + gi.getStartTime()));
			}

		} else if (args[0].equalsIgnoreCase("prep") && sender.hasPermission("lockout.admin")) {
			if (args[1] != null) {
				if (args[1].equalsIgnoreCase("add") && !pl.isPreventJoiningTeams()) {
					if (args[2] != null) {
						if (args[2].equalsIgnoreCase("b")) {
							if (args[3] != null && pl.getServer().getPlayer(args[3]) != null
									&& !pl.getPrepTeamE().contains(pl.getServer().getPlayer(args[3]))) {
								Player p = pl.getServer().getPlayer(args[3]);
								if (p.hasPermission("lockout.use")) {
									pl.getPrepTeamB().add(p);
									Util.ntc.setTemporaryColor(p, 'b');
									p.sendMessage(Util.format("You have been added to the &b&lBlue &fteam!"));
								} else {
									p.sendMessage(Util.format(
											"Before you can play, please vote for us on just one list! &9/vote"));
								}
							}
						} else if (args[2].equalsIgnoreCase("e")) {
							if (args[3] != null && pl.getServer().getPlayer(args[3]) != null
									&& !pl.getPrepTeamB().contains(pl.getServer().getPlayer(args[3]))) {
								Player p = pl.getServer().getPlayer(args[3]);
								if (p.hasPermission("lockout.use")) {
									pl.getPrepTeamE().add(p);
									Util.ntc.setTemporaryColor(p, 'e');
									p.sendMessage(Util.format("You have been added to the &e&lYellow &fteam!"));
								} else {
									p.sendMessage(Util.format(
											"Before you can play, please vote for us on just one list! &9/vote"));
								}
							}
						}
						if (pl.getPrepTeamB().size() > 0 && pl.getPrepTeamE().size() > 0
								&& pl.isGameStarting() == false) {
							pl.setGameStarting(true);
							for (Player p : pl.getLobby().getPlayers()) {
								p.sendMessage(Util
										.format("Both teams have players! A new game will start in &c30 seconds&f."));
							}

							// If both teams have players, do a 30 second countdown and then start a game
							Bukkit.getScheduler().runTaskLater(pl, new Runnable() {
								@Override
								public void run() {
									if (!(pl.getPrepTeamB().size() > 0 && pl.getPrepTeamE().size() > 0)) {
										pl.setGameStarting(false);
										for (Player p : pl.getLobby().getPlayers()) {
											p.sendMessage(Util.format("Too many players have left their teams!"));
										}
										pl.setPrepTeamB(new ArrayList<Player>());
										pl.setPrepTeamE(new ArrayList<Player>());
									}
									if (pl.isGameStarting()) {
										for (Player p : pl.getLobby().getPlayers()) {
											p.sendMessage(Util.format("A new game will start in &c20 seconds&f."));
										}
										Bukkit.getScheduler().runTaskLater(pl, new Runnable() {
											@Override
											public void run() {
												if (!(pl.getPrepTeamB().size() > 0 && pl.getPrepTeamE().size() > 0)) {
													pl.setGameStarting(false);
													for (Player p : pl.getLobby().getPlayers()) {
														p.sendMessage(
																Util.format("Too many players have left their teams!"));
													}
													pl.setPrepTeamB(new ArrayList<Player>());
													pl.setPrepTeamE(new ArrayList<Player>());
												}
												if (pl.isGameStarting()) {
													for (Player p : pl.getLobby().getPlayers()) {
														p.sendMessage(Util
																.format("A new game will start in &c10 seconds&f."));
													}
													Bukkit.getScheduler().runTaskLater(pl, new Runnable() {
														@Override
														public void run() {
															if (!(pl.getPrepTeamB().size() > 0
																	&& pl.getPrepTeamE().size() > 0)) {
																pl.setGameStarting(false);
																for (Player p : pl.getLobby().getPlayers()) {
																	p.sendMessage(Util.format(
																			"Too many players have left their teams!"));
																}
																pl.setPrepTeamB(new ArrayList<Player>());
																pl.setPrepTeamE(new ArrayList<Player>());
															}
															if (pl.isGameStarting()) {
																pl.setGameStarting(false);
																new GameInstance(pl.getPrepTeamB(), pl.getPrepTeamE(),
																		Util.enableHardMode(pl.getPrepTeamB(),
																				pl.getPrepTeamE()));
																pl.setPrepTeamB(new ArrayList<Player>());
																pl.setPrepTeamE(new ArrayList<Player>());
															}
														}
													}, 200);
												}
											}
										}, 200);
									}
								}
							}, 200);
						}
					}
					return true;
				} else if (args[1].equalsIgnoreCase("remove")) {
					if (args[2] != null) {
						if (args[2].equalsIgnoreCase("b")) {
							if (args[3] != null && pl.getServer().getPlayer(args[3]) != null
									&& pl.getPrepTeamB().contains(pl.getServer().getPlayer(args[3]))) {
								Player p = pl.getServer().getPlayer(args[3]);
								pl.getPrepTeamB().remove(p);
								Util.ntc.resetTempColor(p);
								p.sendMessage(Util.format("You have been removed from the &b&lBlue &fteam!"));
								return true;
							}
						} else if (args[2].equalsIgnoreCase("e")) {
							if (args[3] != null && pl.getServer().getPlayer(args[3]) != null
									&& pl.getPrepTeamE().contains(pl.getServer().getPlayer(args[3]))) {
								Player p = pl.getServer().getPlayer(args[3]);
								pl.getPrepTeamE().remove(p);
								Util.ntc.resetTempColor(p);
								p.sendMessage(Util.format("You have been removed from the &e&lYellow &fteam!"));
								return true;
							}
						}
					}

				} else if (args[1].equalsIgnoreCase("clear") && sender.hasPermission("lockout.hard")) {
					pl.setPrepTeamB(new ArrayList<Player>());
					pl.setPrepTeamE(new ArrayList<Player>());
					return true;

				} else if (args[1].equalsIgnoreCase("leave") && sender.hasPermission("lockout.use")) {

					return true;

				} else if (args[1].equalsIgnoreCase("stop")) {
					if (pl.isGameStarting()) {
						pl.setGameStarting(false);
						pl.setPrepTeamB(new ArrayList<Player>());
						pl.setPrepTeamE(new ArrayList<Player>());
						for (Player p : pl.getLobby().getPlayers()) {
							p.sendMessage(Util.format("An admin stopped the game that was starting!"));
							p.sendMessage(Util.format("Everyone has been removed from their teams."));
						}
					} else {
						sender.sendMessage(Util.format("There is no game starting."));
					}
					return true;
				}
			}

		} else if (args[0].equalsIgnoreCase("hard") && sender.hasPermission("lockout.hard")) {
			if (sender instanceof Player) {
				Player p = (Player) sender;
				if (pl.getHardModeEnabled().contains(p)) {
					pl.getHardModeEnabled().remove(p);
					p.sendMessage(Util.format("Hard mode objectives disabled."));
					return true;
				} else {
					pl.getHardModeEnabled().add(p);
					p.sendMessage(Util.format("Hard mode objectives enabled for you and those you're playing with!"));
				}
			}

		} else {
			sender.sendMessage(Util.format("Developed by &cInceris &ffor &9lockout.biz"));
			return true;
		}

		return false;
	}

}
