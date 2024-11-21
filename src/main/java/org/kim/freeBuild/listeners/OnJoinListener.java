package org.kim.freeBuild.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.kim.freeBuild.FreeBuild;
import org.kim.freeBuild.objects.PlayerBaseObject;
import org.kim.freeBuild.sql.SQLCreate;


public class OnJoinListener implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Bukkit.getScheduler().runTaskAsynchronously(FreeBuild.getInstance(), () -> {
            Player player = event.getPlayer();
            boolean userExists = SQLCreate.userExists(player.getUniqueId());
            if (!PlayerBaseObject.playerBaseObjectMap.containsKey(player)) {
                PlayerBaseObject playerBaseObject = new PlayerBaseObject(0,"null",-1,"null",-1,-1,-1,0);
                PlayerBaseObject.playerBaseObjectMap.put(player, playerBaseObject);
            }
            if (!userExists) {
                SQLCreate.insertUser(player.getUniqueId());
            } else {
                PlayerBaseObject playerBaseObject = new PlayerBaseObject(SQLCreate.getBankMoney(player.getUniqueId()),SQLCreate.getIslandname(player.getUniqueId()),SQLCreate.getIslandid(player.getUniqueId()),SQLCreate.getWorldName(player.getUniqueId()),SQLCreate.getX(player.getUniqueId()),SQLCreate.getY(player.getUniqueId()),SQLCreate.getZ(player.getUniqueId()),SQLCreate.getAmountofGens(player.getUniqueId()));
                PlayerBaseObject.playerBaseObjectMap.put(player, playerBaseObject);
            }
        });
    }
}
