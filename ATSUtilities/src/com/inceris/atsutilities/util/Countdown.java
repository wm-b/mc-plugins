package com.inceris.atsutilities.util;

import com.inceris.atsutilities.ATSUtilities;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class Countdown {

    private static final ATSUtilities atsu = ATSUtilities.getPlugin(ATSUtilities.class);

    private static final List<Countdown> cdList = new ArrayList<>();

    private Player p;
    private int cd;
    private boolean cont;

    public Countdown(Player p, int cd) {

        stop(p);

        this.p = p;
        this.cd = cd;
        cont = true;

        cdList.add(this);

        countdown(this);
    }

    public static void stop(Player p) {

        List<Countdown> toRemove = new ArrayList<Countdown>();

        for (Countdown countdown : cdList) {
            if (countdown.getPlayer().equals(p)) {
                countdown.setContinue(false);
                toRemove.add(countdown);
            }
        }

        cdList.removeAll(toRemove);

    }

    private static void countdown(Countdown countdown) {

        Player p = countdown.getPlayer();

        boolean cont = countdown.isContinue();

        if (!cont) {
            p.spigot().sendMessage(ChatMessageType.ACTION_BAR,
                    new TextComponent(Util.colours("&cCountdown cancelled!")));
            return;
        }

        int cd = countdown.getCountdown() - 1;
        countdown.setCountdown(cd);

        final int cdf = cd;

        Bukkit.getScheduler().runTaskLater(atsu, new Runnable() {
            @Override
            public void run() {
                if (cdf <= 0) {
                    p.spigot().sendMessage(ChatMessageType.ACTION_BAR,
                            new TextComponent(Util.colours("&cTime's up!")));
                    return;
                }

                if (cdf == 69) {
                    p.spigot().sendMessage(ChatMessageType.ACTION_BAR,
                            new TextComponent(Util.colours("&c" + cdf + "&f seconds left, &anice")));
                } else if (cdf == 420) {
                    p.spigot().sendMessage(ChatMessageType.ACTION_BAR,
                            new TextComponent(Util.colours("&c" + cdf + "&f seconds left, &ablaze it")));
                } else {
                    p.spigot().sendMessage(ChatMessageType.ACTION_BAR,
                            new TextComponent(Util.colours("&c" + cdf + "&f seconds left")));
                }

                countdown(countdown);
            }
        }, 20);
    }

    public Player getPlayer() {
        return p;
    }

    public void setPlayer(Player p) {
        this.p = p;
    }

    public int getCountdown() {
        return cd;
    }

    public void setCountdown(int cd) {
        this.cd = cd;
    }

    public boolean isContinue() {
        return cont;
    }

    public void setContinue(boolean cont) {
        this.cont = cont;
    }
}
