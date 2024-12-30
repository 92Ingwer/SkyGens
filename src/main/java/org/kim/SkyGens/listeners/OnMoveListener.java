package org.kim.SkyGens.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.kim.SkyGens.objects.PlayerBaseObject;
import org.kim.SkyGens.services.IslandService;

import java.util.UUID;


public class OnMoveListener implements Listener {
    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        Player p = e.getPlayer();
        if (!p.isOp()) {
            if (!IslandService.islandBeAllowedToMove.containsKey(p.getUniqueId())) {
                Location l = e.getFrom();
                PlayerBaseObject playerBaseObject = PlayerBaseObject.playerBaseObjectMap.get(p.getUniqueId());
                cancelMove(e, p, playerBaseObject, l);
            } else {
                UUID uuid = IslandService.islandBeAllowedToMove.get(p.getUniqueId());
                Player t = Bukkit.getPlayer(uuid);
                assert t != null;
                PlayerBaseObject playerBaseObject = PlayerBaseObject.playerBaseObjectMap.get(t.getUniqueId());
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
