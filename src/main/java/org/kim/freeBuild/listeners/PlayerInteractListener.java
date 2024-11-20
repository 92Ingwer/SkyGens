package org.kim.freeBuild.listeners;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.kim.freeBuild.objects.PlayerBaseObject;


public class PlayerInteractListener implements Listener {
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        Location l = p.getLocation();
        PlayerBaseObject playerBaseObject = PlayerBaseObject.playerBaseObjectMap.get(p);
        if (l.getX() >= (playerBaseObject.getX() + 1000) || l.getZ() >= (playerBaseObject.getZ() + 1000) || l.getX() <= (playerBaseObject.getX() - 1000) || l.getZ() <= (playerBaseObject.getZ() - 1000)) {
            e.setCancelled(true);
            p.sendMessage("Hier kannst du nichts machen.");
        }
    }
}
