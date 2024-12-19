package org.kim.freeBuild.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.kim.freeBuild.FreeBuild;
import org.kim.freeBuild.objects.AutomaticChestObject;
import org.kim.freeBuild.objects.AutomaticDrillObject;
import org.kim.freeBuild.objects.GenerationBaseObject;
import org.kim.freeBuild.objects.PlayerBaseObject;

public class OnQuitListener implements Listener {
    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        PlayerBaseObject playerBaseObject = PlayerBaseObject.playerBaseObjectMap.get(player);
        GenerationBaseObject generationBaseObject = GenerationBaseObject.generationBaseObjectMap.get(player);
        AutomaticChestObject automaticChestObject = AutomaticChestObject.automaticChestObjectMap.get(player);
        AutomaticDrillObject automaticDrillObject = AutomaticDrillObject.automaticDrillObject.get(player);
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
        int upgrade = generationBaseObject.getUpgrade();
        double fuel = generationBaseObject.getFuel();
        //automaticchest
        double xc = automaticChestObject.getX();
        double yc = automaticChestObject.getY();
        double zc = automaticChestObject.getZ();
        //drill
        double xd = automaticDrillObject.getX();
        double yd = automaticDrillObject.getY();
        double zd = automaticDrillObject.getZ();
        //rest
        String uuid = player.getUniqueId().toString();
        updatePlayerBase(bankmoney,islandname,islandid,world,x,y,z,uuid,amountofgens);
        updateGenBase(xg,yg,zg,level,uuid,upgrade,fuel);
        updateAutomatiCChest(xc,yc,zc,uuid);
        updateAutomaticDrill(xd,yg,zd,uuid);
    }
    public static void updatePlayerBase(int bankmoney, String islandname, int islandid, String world, double x, double y, double z, String uuid, int amountofgens) {
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
    public static void updateGenBase(double xg, double yg, double zg, int level, String uuid,int upgrade, double fuel) {
        Bukkit.getScheduler().runTaskAsynchronously(FreeBuild.getInstance(), () -> {
            //genbase
            FreeBuild.getSql().update("UPDATE genbase set x = '" + xg + "' WHERE uuid = '" + uuid + "'");
            FreeBuild.getSql().update("UPDATE genbase set y = '" + yg + "' WHERE uuid = '" + uuid + "'");
            FreeBuild.getSql().update("UPDATE genbase set z = '" + zg + "' WHERE uuid = '" + uuid + "'");
            FreeBuild.getSql().update("UPDATE genbase set level = '" + level + "' WHERE uuid = '" + uuid + "'");
            FreeBuild.getSql().update("UPDATE genbase set upgrade = '" + upgrade  + "' WHERE uuid = '" + uuid + "'");
            FreeBuild.getSql().update("UPDATE genbase set fuel = '" + fuel  + "' WHERE uuid = '" + uuid + "'");
        });
    }
    public static void updateAutomatiCChest(double xc,double yc, double zc, String uuid) {
        Bukkit.getScheduler().runTaskAsynchronously(FreeBuild.getInstance(), () -> {
            FreeBuild.getSql().update("UPDATE upgrades set x = '" + xc + "' WHERE uuid = '" + uuid + "' AND upgradetype = 'chest'");
            FreeBuild.getSql().update("UPDATE upgrades set y = '" + yc + "' WHERE uuid = '" + uuid + "' AND upgradetype = 'chest'");
            FreeBuild.getSql().update("UPDATE upgrades set z = '" + zc + "' WHERE uuid = '" + uuid + "' AND upgradetype = 'chest'");
        });
    }
    public static void updateAutomaticDrill(double xd,double yd, double zd, String uuid) {
        Bukkit.getScheduler().runTaskAsynchronously(FreeBuild.getInstance(), () -> {
            FreeBuild.getSql().update("UPDATE upgrades set x = '" + xd + "' WHERE uuid = '" + uuid + "' AND upgradetype = 'drill'");
            FreeBuild.getSql().update("UPDATE upgrades set y = '" + yd + "' WHERE uuid = '" + uuid + "' AND upgradetype = 'drill'");
            FreeBuild.getSql().update("UPDATE upgrades set z = '" + zd + "' WHERE uuid = '" + uuid + "' AND upgradetype = 'drill'");
        });
    }
}
