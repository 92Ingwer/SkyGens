package org.kim.freeBuild.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.kim.freeBuild.FreeBuild;
import org.kim.freeBuild.objects.GenerationBaseObject;
import org.kim.freeBuild.objects.PlayerBaseObject;

public class OnQuitListener implements Listener {
    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        PlayerBaseObject playerBaseObject = PlayerBaseObject.playerBaseObjectMap.get(player);
        GenerationBaseObject generationBaseObject = GenerationBaseObject.generationBaseObjectMap.get(player);
        //playerbase
        int bankmoney = playerBaseObject.getBankmoney();
        String islandname = playerBaseObject.getIslandname();
        int islandid = playerBaseObject.getIslandid();
        String world = playerBaseObject.getWorld();
        double x = playerBaseObject.getX();
        double y = playerBaseObject.getY();
        double z = playerBaseObject.getZ();
        int amountofgens = playerBaseObject.getAmountofGens();
        //genbase
        double xg = generationBaseObject.getX();
        double yg = generationBaseObject.getY();
        double zg = generationBaseObject.getZ();
        int level = generationBaseObject.getLevel();
        //rest
        String uuid = player.getUniqueId().toString();
        updateDB(bankmoney,islandname,islandid,world,x,y,z,uuid,amountofgens);
        updateDB2(xg,yg,zg,level,uuid);
    }
    public static void updateDB(int bankmoney, String islandname, int islandid, String world, double x, double y, double z, String uuid, int amountofgens) {
        Bukkit.getScheduler().runTaskAsynchronously(FreeBuild.getInstance(), () -> {
            //playerbase
            FreeBuild.getSql().update("UPDATE playerbase set bankmoney = '" + bankmoney + "' WHERE uuid = '" + uuid + "'");
            FreeBuild.getSql().update("UPDATE playerbase set islandname = '" + islandname + "' WHERE uuid = '" + uuid + "'");
            FreeBuild.getSql().update("UPDATE playerbase set islandid = '" + islandid + "' WHERE uuid = '" + uuid + "'");
            FreeBuild.getSql().update("UPDATE playerbase set world = '" + world + "' WHERE uuid = '" + uuid + "'");
            FreeBuild.getSql().update("UPDATE playerbase set x = '" + x + "' WHERE uuid = '" + uuid + "'");
            FreeBuild.getSql().update("UPDATE playerbase set y = '" + y + "' WHERE uuid = '" + uuid + "'");
            FreeBuild.getSql().update("UPDATE playerbase set z = '" + z + "' WHERE uuid = '" + uuid + "'");
            FreeBuild.getSql().update("UPDATE playerbase set amountofgens = '" + amountofgens + "' WHERE uuid = '" + uuid + "'");
        });
    }
    public static void updateDB2(double xg, double yg, double zg, int level, String uuid) {
        Bukkit.getScheduler().runTaskAsynchronously(FreeBuild.getInstance(), () -> {
            //genbase
            FreeBuild.getSql().update("UPDATE genbase set x = '" + xg + "' WHERE uuid = '" + uuid + "'");
            FreeBuild.getSql().update("UPDATE genbase set y = '" + yg + "' WHERE uuid = '" + uuid + "'");
            FreeBuild.getSql().update("UPDATE genbase set z = '" + zg + "' WHERE uuid = '" + uuid + "'");
            FreeBuild.getSql().update("UPDATE genbase set level = '" + level + "' WHERE uuid = '" + uuid + "'");
        });
    }
}
