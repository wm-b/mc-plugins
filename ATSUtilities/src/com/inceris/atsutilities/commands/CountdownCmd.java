package com.inceris.atsutilities.commands;

import com.google.common.collect.Lists;
import com.inceris.atsutilities.ATSUtilities;
import com.inceris.atsutilities.util.Util;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.scheduler.BukkitRunnable;

import javax.annotation.Nonnull;
import java.util.*;

public class CountdownCmd implements TabCompleter, Listener {
    private static final HashMap<UUID, CounterRunnable> tasks = new HashMap<>();

    public static boolean cmd(CommandSender sender, String[] args) {

        if (!(sender instanceof Player player)) {
            return false;
        }

        if (args.length != 1) {
            player.sendMessage(Util.colours("&8[&aATS&9Timer&8]&c Invalid syntax! Use /countdown <seconds>"));
            return true;
        }

        CounterRunnable previouslyRunning = tasks.get(player.getUniqueId());
        boolean cancelled = previouslyRunning != null && !previouslyRunning.isCancelled();
        if (cancelled) previouslyRunning.clock = 0;

        if (args[0].equalsIgnoreCase("stop")) {
            if (cancelled) {
                player.sendMessage(Util.colours("&8[&aATS&9Timer&8] Countdown stopped!"));
            } else {
                player.sendMessage(Util.colours("&8[&aATS&9Timer&8]&c You have no previously running countdown!"));
            }
            return true;
        }

        int cd;
        try {
            cd = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            player.sendMessage(Util.colours("&8[&aATS&9Timer&8]&c '" + args[0] + "' is not a valid number of seconds!"));
            return true;
        }

        if (cd > 600) {
            player.sendMessage(Util.colours("&8[&aATS&9Timer&8]&c Please put at most 600 seconds!"));
            return true;
        }

        player.sendMessage(Util.colours("&8[&aATS&9Timer&8]&c " + cd + "&f seconds on the clock!"));
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(Util.colours("&c" + cd + "&f seconds left")));

        new CounterRunnable(player, cd);
        return true;

    }

    private static class CounterRunnable extends BukkitRunnable {
        private final Player origin;
        private final int startingClock;
        private int clock;

        public CounterRunnable(Player origin, int clock) {
            this.origin = origin;
            this.startingClock = this.clock = clock;
            this.clock++;
            runTaskTimer(ATSUtilities.getInstance(), 0, 20);
            tasks.put(origin.getUniqueId(), this);
        }

        @Override
        public void run() {
            if (--clock < 0 || isCancelled()) {
                tasks.values().removeIf(task -> task.equals(this));
                cancel();
                return;
            }

            sendCountdown(origin);
            for (Entity e : origin.getNearbyEntities(50, 50, 50)) {
                if (e instanceof Player p) sendCountdown(p);
            }
        }

        private void sendCountdown(Player to) {
            String title = "";
            String subtitle = "";
            if (clock <= 0) {
                title = Util.colours("&cTime's up!");
            } else if (clock == 69) {
                subtitle = Util.colours("&c" + clock + "&f seconds left, &anice");
            } else if (clock == 420) {
                subtitle = Util.colours("&c" + clock + "&f seconds left, &ablaze it");
            } else {
                subtitle = Util.colours("&c" + clock + "&f seconds left");
            }
            boolean first3 = clock > startingClock - 3;
            if (first3) title = "§a" + origin.getName() + " started a countdown!";

            if (clock <= 5 || clock % 10 == 0 || first3) {
                to.sendTitle(title, subtitle, 0, 25, 5);
                to.playSound(to.getLocation(), Sound.BLOCK_NOTE_BLOCK_BIT, 1, clock <= 0 ? 2 : 1);
            } else {
                to.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(subtitle));
            }
        }
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onTeleport(PlayerTeleportEvent e) {
        if (e.getTo() != null && Objects.equals(e.getTo().getWorld(), e.getFrom().getWorld())) {
            if (e.getTo().distance(e.getFrom()) < 30) return;
        }
        CounterRunnable task = tasks.get(e.getPlayer().getUniqueId());
        if (task != null) {
            tasks.remove(e.getPlayer().getUniqueId());
            task.clock = 0;
            e.getPlayer().sendMessage(Util.colours("&8[&aATS&9Timer&8]&c Your timer was cancelled because you teleported!"));
        }
    }

    @Override
    public List<String> onTabComplete(@Nonnull CommandSender sender, @Nonnull Command command, @Nonnull String s, @Nonnull String[] args) {
        if (args.length == 1) return Lists.newArrayList("<seconds>", "stop");
        return new ArrayList<>();
    }
}
