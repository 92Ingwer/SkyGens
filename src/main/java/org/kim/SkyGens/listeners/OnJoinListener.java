package org.kim.SkyGens.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Chest;
import org.bukkit.block.Dispenser;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.kim.SkyGens.SkyGens;
import org.kim.SkyGens.objects.AutomaticChestObject;
import org.kim.SkyGens.objects.AutomaticDrillObject;
import org.kim.SkyGens.objects.GenerationBaseObject;
import org.kim.SkyGens.objects.PlayerBaseObject;
import org.kim.SkyGens.services.GenerationService;
import org.kim.SkyGens.services.PlayerService;
import org.kim.SkyGens.skills.FarmingSkill;
import org.kim.SkyGens.sql.SQLCreate;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class OnJoinListener implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();
        Bukkit.getScheduler().runTaskAsynchronously(SkyGens.getInstance(), () -> {
            boolean userExists = SQLCreate.userExists(uuid);
            if (!PlayerBaseObject.playerBaseObjectMap.containsKey(uuid)) {
                PlayerBaseObject playerBaseObject = new PlayerBaseObject(0, "null", -1, "null", -1, -1, -1, 0);
                PlayerBaseObject.playerBaseObjectMap.put(uuid, playerBaseObject);
            }
            if (!GenerationBaseObject.generationBaseObjectMap.containsKey(uuid)) {
                GenerationBaseObject generationBaseObject = new GenerationBaseObject(-1, -1, -1, 0, -1, 0);
                GenerationBaseObject.generationBaseObjectMap.put(uuid, generationBaseObject);
            }
            if (!AutomaticChestObject.automaticChestObjectMap.containsKey(uuid)) {
                AutomaticChestObject automaticChestObject = new AutomaticChestObject(-1.0, -1.0, -1.0, null, true);
                AutomaticChestObject.automaticChestObjectMap.put(uuid, automaticChestObject);
            }
            if (!AutomaticDrillObject.automaticDrillObject.containsKey(uuid)) {
                AutomaticDrillObject automaticDrillObject = new AutomaticDrillObject(-1.0, -1.0, -1.0, null, true, 0);
                AutomaticDrillObject.automaticDrillObject.put(uuid, automaticDrillObject);
            }
            if (!FarmingSkill.farmingSkillMap.containsKey(uuid)) {
                FarmingSkill farmingSkill = new FarmingSkill(uuid, "Farming", 0, 0,0);
                FarmingSkill.farmingSkillMap.put(uuid, farmingSkill);
            }
            if (!userExists) {
                SQLCreate.insertUser(player.getUniqueId());
            } else {
                //PlayerBase
                PlayerBaseObject playerBaseObject = new PlayerBaseObject(SQLCreate.getBankMoney(uuid), SQLCreate.getIslandname(uuid), SQLCreate.getIslandid(uuid), SQLCreate.getWorldName(uuid), SQLCreate.getIslandX(uuid), SQLCreate.getIslandY(uuid), SQLCreate.getIslandZ(uuid), SQLCreate.getAmountofGens(uuid));
                PlayerBaseObject.playerBaseObjectMap.put(uuid, playerBaseObject);

                //Gen
                GenerationBaseObject generationBaseObject = new GenerationBaseObject(SQLCreate.getGenX(uuid), SQLCreate.getGenY(uuid), SQLCreate.getGenZ(uuid), SQLCreate.getGenLevel(uuid), SQLCreate.getGenUpgrade(uuid), SQLCreate.getGenFuel(uuid));
                GenerationBaseObject.generationBaseObjectMap.put(uuid, generationBaseObject);
                //Farming Skill
                FarmingSkill farmingSkill = new FarmingSkill(uuid, "Farming", SQLCreate.getFarmingLevel(uuid), SQLCreate.getFarmingXP(uuid),0);
                FarmingSkill.farmingSkillMap.put(uuid, farmingSkill);
                //chest
                double chestX = SQLCreate.getChestX(uuid);
                double chestY = SQLCreate.getChestY(uuid);
                double chestZ = SQLCreate.getChestZ(uuid);
                double drillX = SQLCreate.getDrilLX(uuid);
                double drillY = SQLCreate.getDrilLY(uuid);
                double drillZ = SQLCreate.getDrilLZ(uuid);
                Bukkit.getScheduler().runTask(SkyGens.getInstance(), () -> {

                    Location chestLocation = new Location(Bukkit.getWorld("InselWelt"), chestX, chestY, chestZ);
                    Location drillLocation = new Location(Bukkit.getWorld("InselWelt"), drillX, drillY, drillZ);
                    Dispenser dispenser = null;
                    Chest chest = null;
                    if (chestLocation.getBlock().getState() instanceof Chest) {
                        chest = (Chest) chestLocation.getBlock().getState();
                    }
                    if (drillLocation.getBlock().getState() instanceof Dispenser) {
                        dispenser = (Dispenser) drillLocation.getBlock().getState();
                    }
                    AutomaticDrillObject automaticDrillObject = new AutomaticDrillObject(drillX, drillY, drillZ, dispenser, true, 0);
                    AutomaticChestObject automaticChestObject = new AutomaticChestObject(chestX, chestY, chestZ, chest, true);
                    AutomaticChestObject.automaticChestObjectMap.put(uuid, automaticChestObject);
                    AutomaticDrillObject.automaticDrillObject.put(uuid, automaticDrillObject);
                    AutomaticChestObject automaticChestObject1 = AutomaticChestObject.automaticChestObjectMap.get(uuid);
                    AutomaticDrillObject automaticDrillObject1 = AutomaticDrillObject.automaticDrillObject.get(uuid);
                    if (automaticChestObject1.getChest() != null) {
                        List<Location> locationList = new ArrayList<>();
                        locationList.add(chestLocation.add(0.5, 0.5, 0.5));
                        locationList.add(generationBaseObject.getLocation().clone().add(0.5, 0.5, 0.5));
                        GenerationService.locationForChestParticle.put(uuid, locationList);
                        PlaceUpgraderListener.drawParticleChest(event.getPlayer());
                    }
                    if (automaticDrillObject1.getDispenser() != null) {
                        List<Location> locationList = new ArrayList<>();
                        locationList.add(drillLocation.add(1, 1, 1));
                        locationList.add(generationBaseObject.getLocation().add(0.5, 0.5, 0.5));
                        GenerationService.locationForDrillParticle.put(uuid, locationList);
                    }
                    if (!PlayerService.wasPlayerOn.containsKey(uuid)) {
                        PlayerService.wasPlayerOn.put(uuid, true);
                    }
                    PlaceUpgraderListener.spawnDrill(event.getPlayer());
                    if (PlayerService.isPlayerOnOtherIsland(event.getPlayer())) {
                        player.teleport(playerBaseObject.getLocation());
                    }
                });
            }
        });
    }
}

