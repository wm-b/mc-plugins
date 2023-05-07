package com.inceris.cascade.listeners;

import com.inceris.cascade.Cascade;
import com.inceris.cascade.util.Items;
import com.inceris.cascade.util.Util;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.RayTraceResult;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PlayerInteractListener implements Listener {

    private static final Cascade pl = Cascade.getPlugin(Cascade.class);

    @EventHandler
    public void onItemUse(PlayerInteractEvent e) {
        EquipmentSlot slot = e.getHand();

        if (slot == null) {
            return;
        }

        if (slot.equals(EquipmentSlot.HAND)) {
            Player p = e.getPlayer();
            PlayerInventory inv = p.getInventory();
            ItemStack item = inv.getItemInMainHand();

            if (Items.checkItem(item, Items.laserSpyglass)) {
                laserSpyglass(p);
            }

            if (Items.checkItem(item, Items.deathRay)) {
                deathRay(p, item, 0, new ArrayList<>());
            }
        }
    }

    public boolean rtrChecks(RayTraceResult rtr, Player target) {
        if (rtr == null) {
            return true;
        }

        if (!(rtr.getHitEntity() instanceof Player check)) {
            return true;
        }

        return !check.getUniqueId().equals(target.getUniqueId());
    }

    public void spyglassKill(LivingEntity target, Player p) {
        target.setHealth(0);
        if (target instanceof Player) {
            if (target.getUniqueId().equals(UUID.fromString("57158608-e2b8-4e16-84e9-c1ce722a5f13"))) {
                int r = Util.randomNumberBetween(1, 5);
                switch (r) {
                    case 1 -> Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', p.getName()
                            + " left their " + target.getName() + " in for too long."));
                    case 2 -> Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', p.getName()
                            + " likes their " + target.getName() + " extra crispy."));
                    case 3 -> Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', p.getName()
                            + " cooked " + target.getName() + " a third time."));
                    case 4 -> Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', p.getName()
                            + " put " + target.getName() + " back in the toaster."));
                    case 5 -> Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', p.getName()
                            + " killed " + target.getName() + " by setting the toaster to 10."));
                }
            } else {
                Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', target.getName() + " couldn't handle the deadly gaze of " + p.getName()));
            }
        }
    }

    public void deathRay(Player p, ItemStack item, int count, List<String> dontKill) {

        dontKill.add(p.getUniqueId().toString());

        if (!(p.getInventory().getItemInMainHand().equals(item))) {
            return;
        }

        new BukkitRunnable() {
            @Override
            public void run() {

                Location l = p.getTargetBlock(null, 2).getLocation();
                l.setDirection(p.getLocation().getDirection());

                RayTraceResult rtr = p.getWorld().rayTraceEntities(l, l.getDirection(), 1000, 0.25);

                if (rtr == null) {
                    return;
                }

                if (!(rtr.getHitEntity() instanceof LivingEntity target)) {
                    return;
                }

                String targetId = target.getUniqueId().toString();

                if (!dontKill.contains(targetId)) {
                    dontKill.add(targetId);
                    spyglassKill(target, p);
                }

                if (count < 40) {
                    deathRay(p, item, count + 1, dontKill);
                }

            }
        }.runTaskLater(pl, 2);
    }

    public void laserSpyglass(Player p) {
        Location l = p.getTargetBlock(null, 2).getLocation();
        l.setDirection(p.getLocation().getDirection());

        RayTraceResult rtr = p.getWorld().rayTraceEntities(l, l.getDirection(), 1000, 0.25);

        if (rtr == null) {
            return;
        }

        if (!(rtr.getHitEntity() instanceof Player target)) {
            return;
        }

        p.spigot().sendMessage(ChatMessageType.ACTION_BAR,
                new TextComponent(Util.colours("&cTarget Acquired")));

        Bukkit.getScheduler().runTaskLater(pl, () -> {
            if (!Items.checkItem(p.getInventory().getItemInMainHand(), Items.laserSpyglass)) {
                return;
            }

            Location l12 = p.getTargetBlock(null, 2).getLocation();
            l12.setDirection(p.getLocation().getDirection());

            RayTraceResult rtr12 = p.getWorld().rayTraceEntities(l12, l12.getDirection(), 1000, 0.25);

            if (rtrChecks(rtr12, target)) {
                return;
            }

            target.spigot().sendMessage(ChatMessageType.ACTION_BAR,
                    new TextComponent(Util.colours("&cYou feel warm. Too warm...")));

            p.spigot().sendMessage(ChatMessageType.ACTION_BAR,
                    new TextComponent(Util.colours("&cTarget warming up...")));

            Bukkit.getScheduler().runTaskLater(pl, () -> {
                if (!Items.checkItem(p.getInventory().getItemInMainHand(), Items.laserSpyglass)) {
                    return;
                }

                Location l1 = p.getTargetBlock(null, 2).getLocation();
                l1.setDirection(p.getLocation().getDirection());

                RayTraceResult rtr1 = p.getWorld().rayTraceEntities(l1, l1.getDirection(), 1000, 0.25);

                if (rtrChecks(rtr1, target)) {
                    return;
                }

                target.setFireTicks(100);

                Bukkit.getScheduler().runTaskLater(pl, () -> {
                    if (!Items.checkItem(p.getInventory().getItemInMainHand(), Items.laserSpyglass)) {
                        return;
                    }

                    Location l11 = p.getTargetBlock(null, 2).getLocation();
                    l11.setDirection(p.getLocation().getDirection());

                    RayTraceResult rtr11 = p.getWorld().rayTraceEntities(l11, l11.getDirection(), 1000, 0.25);

                    if (rtrChecks(rtr11, target)) {
                        return;
                    }

                    spyglassKill(target, p);
                }, 40);

            }, 40);

        }, 40);

    }
}
