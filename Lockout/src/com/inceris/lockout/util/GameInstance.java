package com.inceris.lockout.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
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
	private Map<Player, Player> compassTracking;

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

	public Map<Player, Player> getCompassTracking() {
		return compassTracking;
	}

	public void setCompassTracking(Map<Player, Player> compassTracking) {
		this.compassTracking = compassTracking;
	}

	public GameInstance(List<Player> teamB, List<Player> teamE, boolean hard) {
		if (!pl.isPreventNewGame()) {
			pl.setPreventNewGame(true);
			gameName = Util.generateGameName();
			boolean generatedWorld = false;

			for (Player p : teamB) {
				p.sendMessage(Util.format("Your game is starting! Please wait..."));
			}
			for (Player p : teamE) {
				p.sendMessage(Util.format("Your game is starting! Please wait..."));
			}

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
				Util.worldManager.addWorld(gameName + "_the_end", World.Environment.THE_END, null, WorldType.NORMAL,
						true, null);
			}

			if (generatedWorld) {
				Bukkit.getScheduler().runTaskLater(pl, new Runnable() {
					@Override
					public void run() {
						initialiseGame(teamB, teamE, hard);
					}
				}, 100);
			} else {
				initialiseGame(teamB, teamE, hard);
			}
		} else {
			for (Player p : teamB) {
				p.sendMessage(Util.format("Your game could not start. Please try again."));
			}
			for (Player p : teamE) {
				p.sendMessage(Util.format("Your game could not start. Please try again."));
			}
			this.teamB = new ArrayList<Player>();
			this.teamE = new ArrayList<Player>();
		}

	}

	public void initialiseGame(List<Player> teamB, List<Player> teamE, boolean hard) {
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
		compassTracking = new HashMap<Player, Player>();
		this.teamB = new ArrayList<Player>();
		this.teamE = new ArrayList<Player>();
		scoreboardViewers = new ArrayList<Player>();
		scoreboard = scoreboard();
		for (Player p : teamB) {
			this.teamB.add(p);
			initialisePlayer(p);
		}
		for (Player p : teamE) {
			this.teamE.add(p);
			initialisePlayer(p);
		}
		for (Player p : getPlayers()) {
			compassTracking.put(p, getOpponents(p).get(0));
		}
		getPlayerScores().put(teamB, 0);
		getPlayerScores().put(teamE, 0);
		pl.getGameInstances().add(GameInstance.this);
	}

	public void refreshScoreboard() {
		for (Player p : scoreboardViewers) {
			if (!p.isOnline()) {
				scoreboardViewers.remove(p);
			}
			p.setScoreboard(scoreboard);
		}
	}

	public void initialisePlayer(Player p) {
		scoreboardViewers.add(p);
		p.setScoreboard(scoreboard);
		p.getInventory().setContents(new ItemStack[] {});
		p.setHealth(20);
		p.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, 5, 1));
		p.setExp(0);
		p.getInventory().addItem(Util.compass);

		Bukkit.getScheduler().runTaskAsynchronously(pl, new Runnable() {
			@Override
			public void run() {
				RTP.rtp(p, world, 10);
				pl.setPreventJoiningTeams(false);
				pl.setPreventNewGame(false);
			}
		});
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

	public String printObjectives() {
		String listObjectives = "";
		for (Objective o : this.getObjectives()) {
			listObjectives += o.getDescription();
			listObjectives += ", ";
		}
		return listObjectives.substring(0, listObjectives.length() - 2);
	}

	public static GameInstance get(Player p) {
		for (GameInstance gi : pl.getGameInstances()) {
			if (gi.getPlayers().contains(p)) {
				return gi;
			}
		}
		return null;
	}

	public static GameInstance get(World w) {
		for (GameInstance gi : pl.getGameInstances()) {
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
			pl.setPreventNewGame(true);
			for (Player p : getPlayers()) {
				p.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
				Location l = pl.getLobby().getSpawnLocation();
				l.setYaw(l.getYaw() + 180);
				RTP.TPPlayer(pl, p, l);
			}
			Bukkit.getScheduler().runTaskLater(pl, new Runnable() {
				@Override
				public void run() {
					Util.l("Deleteing world " + world.getName());
					Util.mv.deleteWorld(world.getName());
					Util.l("Deleteing world " + nether.getName());
					Util.mv.deleteWorld(nether.getName());
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
					pl.setPreventNewGame(false);
				}
			}, 20);
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

}
