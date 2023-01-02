package com.inceris.atsutilities.listeners;

import com.inceris.atsutilities.ATSUtilities;
import com.sk89q.worldedit.util.Location;
import com.sk89q.worldguard.LocalPlayer;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.flags.Flags;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import com.sk89q.worldguard.protection.regions.RegionQuery;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class EntityDamageByEntityListener implements Listener {

    private static final ATSUtilities pl = ATSUtilities.getPlugin(ATSUtilities.class);

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent e) {

        Projectile projectile = null;
        if (e.getDamager() instanceof Projectile) {
            projectile = (Projectile) e.getDamager();
        } else {
            return;
        }

        Player p = null;
        if (projectile.getShooter() instanceof Player) {
            p = (Player) projectile.getShooter();
        } else {
            return;
        }

        ItemFrame i = null;
        if (e.getEntity() instanceof ItemFrame) {
            i = (ItemFrame) e.getEntity();
        } else {
            return;
        }

        if (pl.debug) pl.getLogger().info("[ATSUtilities] Detected itemframe being hit!");

        LocalPlayer localPlayer = WorldGuardPlugin.inst().wrapPlayer(p);
        Location loc = new Location(localPlayer.getWorld(), i.getLocation().getX(), i.getLocation().getY(), i.getLocation().getZ());
        RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
        RegionQuery query = container.createQuery();

        if (!query.testState(loc, localPlayer, Flags.BUILD)) {
            if (pl.debug) pl.getLogger().info("[ATSUtilities] Itemframe being hit inside protected region!");
            e.setCancelled(true);
        }
    }
}
