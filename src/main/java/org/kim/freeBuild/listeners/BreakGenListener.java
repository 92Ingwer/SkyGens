package org.kim.freeBuild.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.kim.freeBuild.enums.MineralsEnum;
import org.kim.freeBuild.methods.GenMethods;
import org.kim.freeBuild.objects.BrokenGenObject;
import org.kim.freeBuild.objects.GenerationBaseObject;
import org.kim.freeBuild.services.GenerationService;

public class BreakGenListener implements Listener {
    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        Player p = e.getPlayer();
        Location l = e.getBlock().getLocation();
        GenerationBaseObject generationBaseObject = GenerationBaseObject.generationBaseObjectMap.get(p);
        int level = generationBaseObject.getLevel();
        Location genLoc = new Location(Bukkit.getWorld("InselWelt"),generationBaseObject.getX(),generationBaseObject.getY(),generationBaseObject.getZ());
        int hearts = MineralsEnum.getHeart(level);
        if(genLoc.equals(l)) {
            e.setCancelled(true);
            if(!GenerationService.brokenGenObjects.containsKey(p)) {
                BrokenGenObject brokenGenObject = new BrokenGenObject(hearts,genLoc,p,generationBaseObject);
                GenerationService.brokenGenObjects.put(p,brokenGenObject);
                int heartsleft = brokenGenObject.getHearts()-1;
                brokenGenObject.setHearts(heartsleft);
                GenerationService.brokenGenObjects.put(p,brokenGenObject);
            } else {
                BrokenGenObject brokenGenObject = GenerationService.brokenGenObjects.get(p);
                int heartsleft = brokenGenObject.getHearts()-1;
                brokenGenObject.setHearts(heartsleft);
                if(heartsleft <= 0) {
                    GenerationService.brokenGenObjects.remove(p);
                    return;
                }
                GenerationService.brokenGenObjects.put(p,brokenGenObject);
            }
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
        GenerationBaseObject.generationBaseObjectMap.put(p,generationBaseObject);
    }
}
