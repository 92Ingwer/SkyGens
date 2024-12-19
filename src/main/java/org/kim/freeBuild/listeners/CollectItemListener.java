package org.kim.freeBuild.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.kim.freeBuild.services.PlayerService;

public class CollectItemListener implements Listener {

    @EventHandler
    public void onCollect(EntityPickupItemEvent e) {
        if(!(e.getEntity() instanceof Player p)) return;
        if(PlayerService.isPlayerOnOtherIsland(p)) e.setCancelled(true);
    }
}
