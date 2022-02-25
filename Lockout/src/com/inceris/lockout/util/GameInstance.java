package com.inceris.lockout.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.WorldType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.CompassMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Scoreboard;

import com.inceris.lockout.Lockout;

public class GameInstance {

	public static Lockout pl = Lockout.getPlugin(Lockout.class);

	private String gameName;
	private World world;
	private World nether;
	private World end;
	private boolean active;
	private long startTime;
	private List<Objective> objectives;
	private Map<List<Player>, Integer> teamScores;
	private List<Player> teamB;
	private List<Player> teamE;
	private List<Player> scoreboardViewers;
	private Scoreboard scoreboard;

	public String getGameName() {
		return gameName;
	}

	public void setGameName(String gameName) {
		this.gameName = gameName;
	}

	public World getWorld() {
		return world;
	}

	public void setWorld(World world) {
		this.world = world;
	}

	public World getNether() {
		return nether;
	}

	public void setNether(World nether) {
		this.nether = nether;
	}

	public World getEnd() {
		return end;
	}

	public void setEnd(World end) {
		this.end = end;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public long getStartTime() {
		return startTime;
	}

	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	public List<Objective> getObjectives() {
		return objectives;
	}

	public void setObjectives(List<Objective> objectives) {
		this.objectives = objectives;
	}

	public Map<List<Player>, Integer> getPlayerScores() {
		return teamScores;
	}

	public void setPlayerScores(Map<List<Player>, Integer> teamScores) {
		this.teamScores = teamScores;
	}

	public List<Player> getTeamB() {
		return teamB;
	}

	public void setTeamB(List<Player> teamB) {
		this.teamB = teamB;
	}

	public List<Player> getTeamE() {
		return teamE;
	}

	public void setTeamE(List<Player> teamE) {
		this.teamE = teamE;
	}

	public Scoreboard getScoreboard() {
		return scoreboard;
	}

	public void setScoreboard(Scoreboard scoreboard) {
		this.scoreboard = scoreboard;
	}

	public List<Player> getScoreboardViewers() {
		return scoreboardViewers;
	}

	public void setScoreboardViewers(List<Player> scoreboardViewers) {
		this.scoreboardViewers = scoreboardViewers;
	}

	public GameInstance(List<Player> players, boolean hard) {

		gameName = Util.generateGameName();
		boolean generatedWorld = false;

		if (pl.getServer().getWorld(gameName) == null) {
			generatedWorld = true;
			Util.worldManager.addWorld(gameName, World.Environment.NORMAL, null, WorldType.NORMAL, true, null);
		}
		if (pl.getServer().getWorld(gameName + "_nether") == null) {
			generatedWorld = true;
			Util.worldManager.addWorld(gameName + "_nether", World.Environment.NETHER, null, WorldType.NORMAL, true,
					null);
		}
		if (pl.getServer().getWorld(gameName + "_the_end") == null) {
			generatedWorld = true;
			Util.worldManager.addWorld(gameName + "_the_end", World.Environment.THE_END, null, WorldType.NORMAL, true,
					null);
		}

		if (generatedWorld) {
			Bukkit.getScheduler().runTaskLater(pl, new Runnable() {
				@Override
				public void run() {
					initialiseGame(players, hard);
				}
			}, 100);
		} else {
			initialiseGame(players, hard);
		}

	}

	public void initialiseGame(List<Player> players, boolean hard) {
		world = pl.getServer().getWorld(gameName);
		nether = pl.getServer().getWorld(gameName + "_nether");
		end = pl.getServer().getWorld(gameName + "_the_end");
		world.getWorldBorder().setSize(4001);
		nether.getWorldBorder().setSize(1334);
		end.getWorldBorder().setSize(4001);
		active = true;
		startTime = System.currentTimeMillis();
		objectives = Objective.chooseObjectives(hard);
		teamScores = new HashMap<List<Player>, Integer>();
		teamB = new ArrayList<Player>();
		teamE = new ArrayList<Player>();
		scoreboardViewers = new ArrayList<Player>();
		scoreboard = scoreboard();
		boolean flip = true;
		for (Player p : players) {
			if (flip) {
				teamB.add(p);
			} else {
				teamE.add(p);
			}
			flip = !flip;
			scoreboardViewers.add(p);
			p.setScoreboard(scoreboard);
			p.getInventory().setContents(new ItemStack[] {});
			p.setHealth(20);
			p.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, 5, 1));
			p.setExp(0);
			giveCompasses(p);

			Bukkit.getScheduler().runTaskAsynchronously(pl, new Runnable() {
				@Override
				public void run() {
					RTP.rtp(p, world, 10);
				}
			});
		}
		getPlayerScores().put(teamB, 0);
		getPlayerScores().put(teamE, 0);
		pl.gameInstances.add(GameInstance.this);
	}

	public void refreshScoreboard() {
		for (Player p : scoreboardViewers) {
			if (!p.isOnline()) {
				scoreboardViewers.remove(p);
			}
			p.setScoreboard(scoreboard);
		}
	}

	private Scoreboard scoreboard() {
		Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();
		org.bukkit.scoreboard.Objective obj = board.registerNewObjective("Objectives", "Objectives", "Objectives");
		obj.setDisplaySlot(DisplaySlot.SIDEBAR);
		for (Objective o : objectives) {
			obj.getScore(ChatColor.GRAY + o.getDescription()).setScore(0);
		}
		return board;
	}

	public void giveCompasses(Player p) {
		for (Player pp : getPlayers()) {
			if (!getTeam(p).contains(pp)) {
				p.getInventory().addItem(compass(pp));
			}
		}
	}

	public String printObjectives() {
		String listObjectives = "";
		for (Objective o : this.getObjectives()) {
			listObjectives += o.getDescription();
			listObjectives += ", ";
		}
		return listObjectives.substring(0, listObjectives.length() - 2);
	}

	public static GameInstance get(Player p) {
		for (GameInstance gi : pl.gameInstances) {
			if (gi.getPlayers().contains(p)) {
				return gi;
			}
		}
		return null;
	}

	public static GameInstance get(World w) {
		for (GameInstance gi : pl.gameInstances) {
			if (gi.getWorld().equals(w)) {
				return gi;
			}
		}
		return null;
	}

	public List<Player> getPlayers() {
		List<Player> players = new ArrayList<Player>();
		players.addAll(teamB);
		players.addAll(teamE);
		return players;
	}

	public void reset() {
		if (this.isActive()) {
			for (Player p : getPlayers()) {
				p.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
			}
			Util.worldManager.unloadWorld(world.getName());
			Util.worldManager.unloadWorld(nether.getName());
			Util.worldManager.unloadWorld(end.getName());
			Bukkit.getScheduler().runTaskLater(pl, new Runnable() {
				@Override
				public void run() {
					Util.l("Deleteing world " + world.getName());
					Util.mv.deleteWorld(world.getName());
				}
			}, 50);
			Bukkit.getScheduler().runTaskLater(pl, new Runnable() {
				@Override
				public void run() {
					Util.l("Deleteing world " + nether.getName());
					Util.mv.deleteWorld(nether.getName());
				}
			}, 100);
			Bukkit.getScheduler().runTaskLater(pl, new Runnable() {
				@Override
				public void run() {
					Util.l("Deleteing world " + end.getName());
					Util.mv.deleteWorld(end.getName());
					Util.l("Finished deleting");
					for (Player p : getPlayers()) {
						p.getInventory().setContents(new ItemStack[] {});
						p.setExp(0);
					}
					active = false;
					startTime = 0;
					objectives = new ArrayList<Objective>();
					teamScores = new HashMap<List<Player>, Integer>();
					teamB = new ArrayList<Player>();
					teamE = new ArrayList<Player>();
				}
			}, 150);
		}
	}

	public List<Player> getOpponents(Player p) {
		if (getPlayers().contains(p)) {
			if (teamB.contains(p)) {
				return teamE;
			} else {
				return teamB;
			}
		}
		return null;
	}

	public List<Player> getTeam(Player p) {
		if (getPlayers().contains(p)) {
			if (teamB.contains(p)) {
				return teamB;
			} else {
				return teamE;
			}
		}
		return null;
	}

	public List<List<Player>> getTeams() {
		List<List<Player>> teams = new ArrayList<List<Player>>();
		teams.add(teamB);
		teams.add(teamE);
		return teams;
	}

	public char getTeamCharacter(Player p) {
		if (getPlayers().contains(p)) {
			if (teamB.contains(p)) {
				return 'b';
			} else {
				return 'e';
			}
		}
		return '4';
	}

	public void messagePlayers(String message) {
		for (Player p : getPlayers()) {
			p.sendMessage(Util.format(message));
		}
	}

	public ItemStack compass(Player p) {
		ItemStack compass = new ItemStack(Material.COMPASS);
		CompassMeta meta = (CompassMeta) compass.getItemMeta();
		meta.setDisplayName(p.getName() + " Tracker");
		meta.setLodestoneTracked(true);
		meta.setLodestone(p.getLocation());
		compass.setItemMeta(meta);
		return compass;
	}

}
