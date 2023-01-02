package com.inceris.atsutilities.commands;

import com.inceris.atsutilities.ATSUtilities;
import com.inceris.atsutilities.util.Util;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class Transform {

    private static final ATSUtilities atsu = ATSUtilities.getPlugin(ATSUtilities.class);

    public static boolean cmd(String[] args) {

        if (Bukkit.getServer().getPlayer(args[0]) == null) return false;

        EntityType entityType = switch (args[1].toUpperCase()) {
            case "ALLAY" -> EntityType.ALLAY;
            case "AXOLOTL" -> EntityType.AXOLOTL;
            case "BAT" -> EntityType.BAT;
            case "CAT" -> EntityType.CAT;
            case "CHICKEN" -> EntityType.CHICKEN;
            case "COD" -> EntityType.COD;
            case "COW" -> EntityType.COW;
            case "DONKEY" -> EntityType.DONKEY;
            case "FOX" -> EntityType.FOX;
            case "FROG" -> EntityType.FROG;
            case "GLOW_SQUID" -> EntityType.GLOW_SQUID;
            case "HORSE" -> EntityType.HORSE;
            case "MULE" -> EntityType.MULE;
            case "OCELOT" -> EntityType.OCELOT;
            case "PIG" -> EntityType.PIG;
            case "PUFFERFISH" -> EntityType.PUFFERFISH;
            case "RABBIT" -> EntityType.RABBIT;
            case "SALMON" -> EntityType.SALMON;
            case "SHEEP" -> EntityType.SHEEP;
            case "SNOWMAN" -> EntityType.SNOWMAN;
            case "SQUID" -> EntityType.SQUID;
            case "STRIDER" -> EntityType.STRIDER;
            case "TADPOLE" -> EntityType.TADPOLE;
            case "TROPICAL_FISH" -> EntityType.TROPICAL_FISH;
            case "TURTLE" -> EntityType.TURTLE;
            case "VILLAGER" -> EntityType.VILLAGER;
            case "WANDERING_TRADER" -> EntityType.WANDERING_TRADER;
            case "ZOMBIE_HORSE" -> EntityType.ZOMBIE_HORSE;
            case "SKELETON_HORSE" -> EntityType.SKELETON_HORSE;
            case "LLAMA" -> EntityType.LLAMA;
            case "BEE" -> EntityType.BEE;
            case "WOLF" -> EntityType.WOLF;
            case "WITHER" -> EntityType.WITHER;
            case "WARDEN" -> EntityType.WARDEN;
            default -> null;
        };

        if (entityType == null) {
            return false;
        }

        int transformForSeconds = 5;
        if (args.length >= 3 && Util.regex(args[2], "\\A[0-9]+\\Z")) {
            transformForSeconds = Integer.parseInt(args[2]);
        }

        Player p = Bukkit.getServer().getPlayer(args[0]);
        if (p == null) return true;
        ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
        Bukkit.dispatchCommand(console,
                "execute at " + p.getName() + " run particle minecraft:smoke ~ ~1 ~ 0.25 0.5 0.25 0 100");
        Bukkit.dispatchCommand(console,
                "execute at " + p.getName() + " run particle minecraft:large_smoke ~ ~1 ~ 0.25 0.5 0.25 0 20");
        Bukkit.dispatchCommand(console,
                "execute at " + p.getName() + " run playsound minecraft:entity.enderman.teleport master @a ~ ~ ~ 1 1");
        LivingEntity entity = (LivingEntity) p.getWorld().spawnEntity(p.getLocation(), entityType);
        atsu.transformMobs.add(entity.getUniqueId());
        entity.setCustomName(p.getName());
        entity.setCustomNameVisible(true);
        entity.setRemoveWhenFarAway(false);
        p.setGameMode(GameMode.SPECTATOR);
        p.setSpectatorTarget(entity);
        if (p.getSpectatorTarget() == null || !(p.getSpectatorTarget().getUniqueId().equals(entity.getUniqueId()))) {
            p.setGameMode(GameMode.SURVIVAL);
            atsu.transformMobs.remove(entity.getUniqueId());
            entity.remove();
        } else {
            transformRecursion(p, entity, 0, transformForSeconds * 5);
        }
        return true;
    }

    public static void transformRecursion(Player p, LivingEntity entity, int count, int countTo) {

        final int pass = count + 1;

        if (pass >= countTo) {
            atsu.transformMobs.remove(entity.getUniqueId());
            entity.remove();
            if (p.getGameMode().equals(GameMode.SPECTATOR)) {
                ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
                Bukkit.dispatchCommand(console,
                        "execute at " + p.getName() + " run particle minecraft:smoke ~ ~1 ~ 0.25 0.5 0.25 0 100");
                Bukkit.dispatchCommand(console,
                        "execute at " + p.getName() + " run particle minecraft:large_smoke ~ ~1 ~ 0.25 0.5 0.25 0 20");
                Bukkit.dispatchCommand(console, "execute at " + p.getName()
                        + " run playsound minecraft:entity.enderman.teleport master @a ~ ~ ~ 1 1");
                p.setGameMode(GameMode.SURVIVAL);
            }
            return;
        }

        if (!p.getGameMode().equals(GameMode.SPECTATOR)) {
            ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
            Bukkit.dispatchCommand(console,
                    "execute at " + p.getName() + " run particle minecraft:smoke ~ ~1 ~ 0.25 0.5 0.25 0 100");
            Bukkit.dispatchCommand(console,
                    "execute at " + p.getName() + " run particle minecraft:large_smoke ~ ~1 ~ 0.25 0.5 0.25 0 20");
            Bukkit.dispatchCommand(console,
                    "execute at " + p.getName() + " run playsound minecraft:entity.enderman.teleport master @a ~ ~ ~ 1 1");
            atsu.transformMobs.remove(entity.getUniqueId());
            entity.remove();
            return;
        }

        if (entity.isDead()) {
            atsu.transformMobs.remove(entity.getUniqueId());
            p.setGameMode(GameMode.SURVIVAL);
            ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
            Bukkit.dispatchCommand(console,
                    "execute at " + p.getName() + " run particle minecraft:smoke ~ ~1 ~ 0.25 0.5 0.25 0 100");
            Bukkit.dispatchCommand(console,
                    "execute at " + p.getName() + " run particle minecraft:large_smoke ~ ~1 ~ 0.25 0.5 0.25 0 20");
            Bukkit.dispatchCommand(console, "execute at " + p.getName()
                    + " run playsound minecraft:entity.enderman.teleport master @a ~ ~ ~ 1 1");
            entity.remove();
            return;
        }

        if (p.getSpectatorTarget() == null || !(p.getSpectatorTarget().getUniqueId().equals(entity.getUniqueId()))) {
            p.setSpectatorTarget(entity);
        }

        Bukkit.getScheduler().runTaskLater(atsu, new Runnable() {
            @Override
            public void run() {
                transformRecursion(p, entity, pass, countTo);
            }
        }, 4);
    }
}
