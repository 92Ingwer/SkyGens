package org.kim.SkyGens.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class OnIslandPvPEvent implements Listener {
    @EventHandler
    public void onHit(EntityDamageByEntityEvent e) {
        if(!(e.getDamager() instanceof Player || e.getEntity() instanceof Player)) return;
        e.setCancelled(true);
    }
}
