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
	
	private World world;
	private World nether;
	private World end;
	private boolean active;
	private long startTime;
	private List<Objective> objectives;
	private Map<Player, Integer> playerScores;
	private Map<Player, Character> teams;

	private Scoreboard scoreboard;

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

	public Map<Player, Integer> getPlayerScores() {
		return playerScores;
	}

	public void setPlayerScores(Map<Player, Integer> scores) {
		this.playerScores = scores;
	}

	public Map<Player, Character> getTeams() {
		return teams;
	}

	public void setTeams(Map<Player, Character> teams) {
		this.teams = teams;
	}

	public Scoreboard getScoreboard() {
		return scoreboard;
	}

	public void setScoreboard(Scoreboard scoreboard) {
		this.scoreboard = scoreboard;
	}
	

	public GameInstance(Player p1, Player p2) {
		String worldName = Util.worldName(p1, p2);
		boolean generatedWorld = false;

		if (pl.getServer().getWorld(worldName) == null) {
			generatedWorld = true;
			Util.worldManager.addWorld(worldName, World.Environment.NORMAL, null, WorldType.NORMAL, true, null);
		}
		if (pl.getServer().getWorld(worldName + "_nether") == null) {
			generatedWorld = true;
			Util.worldManager.addWorld(worldName + "_nether", World.Environment.NETHER, null, WorldType.NORMAL, true, null);
		}
		if (pl.getServer().getWorld(worldName + "_the_end") == null) {
			generatedWorld = true;
			Util.worldManager.addWorld(worldName + "_the_end", World.Environment.THE_END, null, WorldType.NORMAL, true, null);
		}

		if (generatedWorld) {
			Bukkit.getScheduler().runTaskLater(pl, new Runnable() {
				@Override
				public void run() {
					world = pl.getServer().getWorld(worldName);
					nether = pl.getServer().getWorld(worldName + "_nether");
					end = pl.getServer().getWorld(worldName + "_the_end");
					world.getWorldBorder().setSize(4001);
					nether.getWorldBorder().setSize(1334);
					end.getWorldBorder().setSize(4001);
					active = true;
					startTime = System.currentTimeMillis();
					objectives = Objective.chooseObjectives(false);
					playerScores = new HashMap<Player, Integer>();
					getPlayerScores().put(p1, 0);
					getPlayerScores().put(p2, 0);
					teams = new HashMap<Player, Character>();
					getTeams().put(p1, 'b');
					getTeams().put(p2, 'e');
					scoreboard = scoreboard();
					p1.setScoreboard(scoreboard);
					p2.setScoreboard(scoreboard);
					p1.getInventory().setContents(new ItemStack[] {});
					p2.getInventory().setContents(new ItemStack[] {});
					p1.getInventory().addItem(compass(p2));
					p2.getInventory().addItem(compass(p1));
					p1.setHealth(20);
					p1.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, 5, 1));
					p2.setHealth(20);
					p2.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, 5, 1));
					p1.setExp(0);
					p2.setExp(0);
					pl.gameInstances.add(GameInstance.this);
					RTP.rtp(p1, world, 100);
					RTP.rtp(p2, world, 100);
				}
			}, 100);
		} else {
			world = pl.getServer().getWorld(worldName);
			nether = pl.getServer().getWorld(worldName + "_nether");
			end = pl.getServer().getWorld(worldName + "_the_end");
			active = true;
			startTime = System.currentTimeMillis();
			objectives = Objective.chooseObjectives(false);
			playerScores = new HashMap<Player, Integer>();
			getPlayerScores().put(p1, 0);
			getPlayerScores().put(p2, 0);
			teams = new HashMap<Player, Character>();
			getTeams().put(p1, 'b');
			getTeams().put(p2, 'e');
			scoreboard = scoreboard();
			p1.setScoreboard(scoreboard);
			p2.setScoreboard(scoreboard);
			p1.getInventory().setContents(new ItemStack[] {});
			p2.getInventory().setContents(new ItemStack[] {});
			p1.getInventory().addItem(compass(p2));
			p2.getInventory().addItem(compass(p1));
			p1.setHealth(20);
			p1.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, 5, 1));
			p2.setHealth(20);
			p2.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, 5, 1));
			p1.setExp(0);
			p2.setExp(0);
			pl.gameInstances.add(GameInstance.this);
			RTP.rtp(p1, world, 100);
			RTP.rtp(p2, world, 100);
		}

	}
	
	public void refreshScoreboard() {
		for (Player p : playerScores.keySet()) {
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
			if (gi.getPlayerScores().keySet().contains(p)) {
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
	
	public void reset() {
		if (this.isActive()) {
			for (Player p : playerScores.keySet()) {
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
					for (Player p : playerScores.keySet()) {
						p.getInventory().setContents(new ItemStack[] {});
						p.setExp(0);
					}
					active = false;
					startTime = 0;
					objectives = new ArrayList<Objective>();
					playerScores = new HashMap<Player, Integer>();
				}
			}, 150);
		}
	}
	
	public void messagePlayers(String message) {
		for (Player p : playerScores.keySet()) {
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
