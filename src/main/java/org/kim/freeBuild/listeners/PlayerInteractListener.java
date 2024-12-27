package org.kim.freeBuild.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.eclipse.sisu.Priority;
import org.kim.freeBuild.services.PlayerService;


public class PlayerInteractListener implements Listener {
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerInteract(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        if (PlayerService.isPlayerOnOtherIsland(p)) {
            e.setCancelled(true);
            p.sendMessage("Hier kannst du nichts machen.");
        }
    }
}
