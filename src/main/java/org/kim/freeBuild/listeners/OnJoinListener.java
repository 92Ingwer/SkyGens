package org.kim.freeBuild.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Chest;
import org.bukkit.block.Dispenser;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.kim.freeBuild.FreeBuild;
import org.kim.freeBuild.objects.AutomaticChestObject;
import org.kim.freeBuild.objects.AutomaticDrillObject;
import org.kim.freeBuild.objects.GenerationBaseObject;
import org.kim.freeBuild.objects.PlayerBaseObject;
import org.kim.freeBuild.services.GenerationService;
import org.kim.freeBuild.services.PlayerService;
import org.kim.freeBuild.sql.SQLCreate;

import java.util.ArrayList;
import java.util.List;


public class OnJoinListener implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Bukkit.getScheduler().runTaskAsynchronously(FreeBuild.getInstance(), () -> {
            Player player = event.getPlayer();
            boolean userExists = SQLCreate.userExists(player.getUniqueId());
            if (!PlayerBaseObject.playerBaseObjectMap.containsKey(player)) {
                PlayerBaseObject playerBaseObject = new PlayerBaseObject(0, "null", -1, "null", -1, -1, -1, 0);
                PlayerBaseObject.playerBaseObjectMap.put(player, playerBaseObject);
            }
            if (!GenerationBaseObject.generationBaseObjectMap.containsKey(player)) {
                GenerationBaseObject generationBaseObject = new GenerationBaseObject(-1, -1, -1, 0, -1, 0);
                GenerationBaseObject.generationBaseObjectMap.put(player, generationBaseObject);
            }
            if (!AutomaticChestObject.automaticChestObjectMap.containsKey(player)) {
                AutomaticChestObject automaticChestObject = new AutomaticChestObject(-1.0, -1.0, -1.0, null,true);
                AutomaticChestObject.automaticChestObjectMap.put(player, automaticChestObject);
            }
            if(!AutomaticDrillObject.automaticDrillObject.containsKey(player)) {
                AutomaticDrillObject automaticDrillObject = new AutomaticDrillObject(-1.0,-1.0,-1.0,null,true,0);
                AutomaticDrillObject.automaticDrillObject.put(player, automaticDrillObject);
            }
            if (!userExists) {
                SQLCreate.insertUser(player.getUniqueId());
            } else {

                //PlayerBase
                PlayerBaseObject playerBaseObject = new PlayerBaseObject(SQLCreate.getBankMoney(player.getUniqueId()), SQLCreate.getIslandname(player.getUniqueId()), SQLCreate.getIslandid(player.getUniqueId()), SQLCreate.getWorldName(player.getUniqueId()), SQLCreate.getIslandX(player.getUniqueId()), SQLCreate.getIslandY(player.getUniqueId()), SQLCreate.getIslandZ(player.getUniqueId()), SQLCreate.getAmountofGens(player.getUniqueId()));
                PlayerBaseObject.playerBaseObjectMap.put(player, playerBaseObject);

                //Gen
                GenerationBaseObject generationBaseObject = new GenerationBaseObject(SQLCreate.getGenX(player.getUniqueId()), SQLCreate.getGenY(player.getUniqueId()), SQLCreate.getGenZ(player.getUniqueId()), SQLCreate.getGenLevel(player.getUniqueId()), SQLCreate.getGenUpgrade(player.getUniqueId()), SQLCreate.getGenFuel(player.getUniqueId()));
                GenerationBaseObject.generationBaseObjectMap.put(player, generationBaseObject);
                //chest
                double chestX = SQLCreate.getChestX(player.getUniqueId());
                double chestY = SQLCreate.getChestY(player.getUniqueId());
                double chestZ = SQLCreate.getChestZ(player.getUniqueId());
                double drillX = SQLCreate.getDrilLX(player.getUniqueId());
                double drillY = SQLCreate.getDrilLY(player.getUniqueId());
                double drillZ = SQLCreate.getDrilLZ(player.getUniqueId());
                Bukkit.getScheduler().runTask(FreeBuild.getInstance(), () -> {

                    Location chestLocation = new Location(Bukkit.getWorld("InselWelt"), chestX, chestY, chestZ);
                    Location drillLocation = new Location(Bukkit.getWorld("InselWelt"), drillX, drillY, drillZ);
                    Dispenser dispenser = null;
                    Chest chest = null;
                    if (chestLocation.getBlock().getState() instanceof Chest) {
                        chest = (Chest) chestLocation.getBlock().getState();
                    }
                    if(drillLocation.getBlock().getState() instanceof Dispenser) {
                        dispenser = (Dispenser) drillLocation.getBlock().getState();
                    }
                    AutomaticDrillObject automaticDrillObject = new AutomaticDrillObject(drillX,drillY,drillZ,dispenser,true,0);
                    AutomaticChestObject automaticChestObject = new AutomaticChestObject(chestX, chestY, chestZ, chest,true);
                    AutomaticChestObject.automaticChestObjectMap.put(player, automaticChestObject);
                    AutomaticDrillObject.automaticDrillObject.put(player, automaticDrillObject);
                    if(!PlayerService.wasPlayerOn.containsKey(event.getPlayer())) {
                        AutomaticChestObject automaticChestObject1 = AutomaticChestObject.automaticChestObjectMap.get(event.getPlayer());
                        AutomaticDrillObject automaticDrillObject1 = AutomaticDrillObject.automaticDrillObject.get(event.getPlayer());
                        if(automaticChestObject1.getChest() != null) {
                            List<Location> locationList = new ArrayList<>();
                            locationList.add(chestLocation.add(0.5,0.5,0.5));
                            locationList.add(generationBaseObject.getLocation().clone().add(0.5,0.5,0.5));
                            GenerationService.locationForChestParticle.put(event.getPlayer(), locationList);
                            PlaceUpgraderListener.drawParticleChest(event.getPlayer());
                        }
                        if(automaticDrillObject1.getDispenser() != null) {
                            List<Location> locationList = new ArrayList<>();
                            locationList.add(drillLocation.add(1, 1, 1));
                            locationList.add(generationBaseObject.getLocation().add(0.5, 0.5, 0.5));
                            GenerationService.locationForDrillParticle.put(event.getPlayer(), locationList);
                            PlaceUpgraderListener.spawnDrill(event.getPlayer());
                        }
                        PlayerService.wasPlayerOn.put(event.getPlayer(), true);
                    }
                    if(PlayerService.isPlayerOnOtherIsland(event.getPlayer())) {
                        player.teleport(playerBaseObject.getLocation());
                    }
                });
            }
        });
    }
}

