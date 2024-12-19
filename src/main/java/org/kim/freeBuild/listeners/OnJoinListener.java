package org.kim.freeBuild.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Chest;
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


        //AutomaticChestDustRequest
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
                AutomaticDrillObject automaticDrillObject = new AutomaticDrillObject(-1.0,-1.0,-1.0,true,0);
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
                Bukkit.getScheduler().runTask(FreeBuild.getInstance(), () -> {
                    Location location = new Location(Bukkit.getWorld("InselWelt"), chestX, chestY, chestZ);
                    Chest chest = null;
                    if (location.getBlock().getState() instanceof Chest) {
                        chest = (Chest) location.getBlock().getState();
                    }
                    AutomaticChestObject automaticChestObject = new AutomaticChestObject(chestX, chestY, chestZ, chest,true);
                    PlayerBaseObject.playerBaseObjectMap.put(player, playerBaseObject);
                    GenerationBaseObject.generationBaseObjectMap.put(player, generationBaseObject);
                    AutomaticChestObject.automaticChestObjectMap.put(player, automaticChestObject);
                    if(!PlayerService.wasPlayerOn.containsKey(event.getPlayer())) {
                        AutomaticChestObject automaticChestObject1 = AutomaticChestObject.automaticChestObjectMap.get(event.getPlayer());
                        if(automaticChestObject1.getChest() != null) {
                            List<Location> locationList = new ArrayList<>();
                            locationList.add(location.add(0.5,0.5,0.5));
                            locationList.add(generationBaseObject.getLocation().add(0.5,0.5,0.5));
                            GenerationService.locationForChestParticle.put(event.getPlayer(), locationList);
                            PlaceUpgraderListener.drawParticleLine(event.getPlayer());
                            PlayerService.wasPlayerOn.put(event.getPlayer(), true);
                        }
                    }
                });
                //Drill
                double drillX = SQLCreate.getDrilLX(player.getUniqueId());
                double drillY = SQLCreate.getDrilLY(player.getUniqueId());
                double drillZ = SQLCreate.getDrilLY(player.getUniqueId());

                AutomaticDrillObject automaticDrillObject = new AutomaticDrillObject(drillX,drillY,drillZ,true,0);
                AutomaticDrillObject.automaticDrillObject.put(player, automaticDrillObject);
            }
        });
    }
}

