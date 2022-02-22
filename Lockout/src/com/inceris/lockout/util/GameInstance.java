package com.inceris.lockout.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.World;
import org.bukkit.entity.Player;

import com.inceris.lockout.Lockout;

public class GameInstance {

	private static Lockout pl = (Lockout) Lockout.getPlugin(Lockout.class);
	
	private World world;
	private boolean active;
	private long startTime;
	private List<Objective> objectives;
	private Map<Player, Integer> playerScores;

	public World getWorld() {
		return world;
	}

	public void setWorld(World world) {
		this.world = world;
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

	public GameInstance(World inWorld, boolean inActive, long inStartTime) {
		world = inWorld;
		active = inActive;
		startTime = inStartTime;
		objectives = new ArrayList<Objective>();
		playerScores = new HashMap<Player, Integer>();
	}
	
	public GameInstance() {
		world = null;
		active = false;
		startTime = 0;
		objectives = null;
		playerScores = null;
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
		this.setActive(false);
		this.setStartTime(0);
		this.setObjectives(new ArrayList<Objective>());
		this.setPlayerScores(new HashMap<Player, Integer>());
	}
	
	public void messagePlayers(String message) {
		for (Player p : playerScores.keySet()) {
			p.sendMessage(Util.format(message));
		}
	}
}
