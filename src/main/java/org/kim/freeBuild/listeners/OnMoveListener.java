package org.kim.freeBuild.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.kim.freeBuild.objects.PlayerBaseObject;
import org.kim.freeBuild.services.IslandService;


public class OnMoveListener implements Listener {
    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        Player p = e.getPlayer();
        if (!p.isOp()) {
            if (!IslandService.islandBeAllowedToMove.containsKey(p)) {
                Location l = e.getFrom();
                PlayerBaseObject playerBaseObject = PlayerBaseObject.playerBaseObjectMap.get(p);
                cancelMove(e, p, playerBaseObject, l);
            } else {
                Player t = IslandService.islandBeAllowedToMove.get(p);
                PlayerBaseObject playerBaseObject = PlayerBaseObject.playerBaseObjectMap.get(t);
                Location l = e.getFrom();
                cancelMove(e, p, playerBaseObject, l);

            }
        }
    }


    private void cancelMove(PlayerMoveEvent e, Player p, PlayerBaseObject playerBaseObject, Location l) {
        if (p.getWorld().equals(Bukkit.getWorld("InselWelt"))) {
            if (l.getX() >= (playerBaseObject.getX() + 1000) || l.getZ() >= (playerBaseObject.getZ() + 1000) || l.getX() <= (playerBaseObject.getX() - 1000) || l.getZ() <= (playerBaseObject.getZ() - 1000)) {
                e.setCancelled(true);
                p.sendMessage("Du darfst diese Grenzen nicht überschreiten!");
            }
        }
    }
}
