package org.kim.freeBuild.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.kim.freeBuild.enums.MineralsEnum;
import org.kim.freeBuild.objects.AutomaticChestObject;
import org.kim.freeBuild.objects.BrokenGenObject;
import org.kim.freeBuild.objects.GenerationBaseObject;
import org.kim.freeBuild.services.GenerationService;

public class BreakGenListener implements Listener {
    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        Player p = e.getPlayer();
        Location l = e.getBlock().getLocation();
        AutomaticChestObject automaticChestObject = AutomaticChestObject.automaticChestObjectMap.get(p.getUniqueId());
        GenerationBaseObject generationBaseObject = GenerationBaseObject.generationBaseObjectMap.get(p.getUniqueId());
        int level = generationBaseObject.getLevel();
        Location genLoc = new Location(Bukkit.getWorld("InselWelt"),generationBaseObject.getX(),generationBaseObject.getY(),generationBaseObject.getZ());
        int hearts = MineralsEnum.getHeart(level);
        if(genLoc.equals(l)) {
            e.setCancelled(true);
            breakGenMethod(generationBaseObject, automaticChestObject, p, genLoc, hearts);
        }
    }
    public static void breakGen(GenerationBaseObject generationBaseObject,AutomaticChestObject automaticChestObject,Player p) {
        int level = generationBaseObject.getLevel();
        Location genLoc = new Location(Bukkit.getWorld("InselWelt"),generationBaseObject.getX(),generationBaseObject.getY(),generationBaseObject.getZ());
        int hearts = MineralsEnum.getHeart(level);
        breakGenMethod(generationBaseObject, automaticChestObject, p, genLoc, hearts);
    }

    public static void breakGenMethod(GenerationBaseObject generationBaseObject, AutomaticChestObject automaticChestObject, Player p, Location genLoc, int hearts) {
        if(!GenerationService.brokenGenObjects.containsKey(p.getUniqueId())) {
            BrokenGenObject brokenGenObject = new BrokenGenObject(hearts,genLoc,p,generationBaseObject,automaticChestObject);
            GenerationService.brokenGenObjects.put(p.getUniqueId(),brokenGenObject);
            int heartsleft = brokenGenObject.getHearts()-1;
            brokenGenObject.setHearts(heartsleft);
            GenerationService.brokenGenObjects.put(p.getUniqueId(),brokenGenObject);
        } else {
            BrokenGenObject brokenGenObject = GenerationService.brokenGenObjects.get(p.getUniqueId());
            int heartsleft = brokenGenObject.getHearts()-1;
            brokenGenObject.setHearts(heartsleft);
            if(heartsleft <= 0) {
                GenerationService.brokenGenObjects.remove(p.getUniqueId());
                return;
            }
            GenerationService.brokenGenObjects.put(p.getUniqueId(),brokenGenObject);
        }
    }

    public static void useFuel(GenerationBaseObject generationBaseObject, Player p) {
        double fuel = generationBaseObject.getFuel();
        int level = generationBaseObject.getLevel();
        double usage = MineralsEnum.getUsage(level);
        if(usage > fuel) {
            return;
        }
        generationBaseObject.setFuel(fuel-usage);
        GenerationBaseObject.generationBaseObjectMap.put(p.getUniqueId(),generationBaseObject);
    }
}
