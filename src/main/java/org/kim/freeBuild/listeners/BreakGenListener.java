package org.kim.freeBuild.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.kim.freeBuild.objects.GenerationBaseObject;

public class BreakGenListener implements Listener {
    public String prefix = "<gradient:#FFC21E:#FFEE00>[BlockBreaker]</gradient>";
    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        Player p = e.getPlayer();
        Location l = e.getBlock().getLocation();
        GenerationBaseObject generationBaseObject = GenerationBaseObject.generationBaseObjectMap.get(p);
        Location genLoc = new Location(Bukkit.getWorld("InselWelt"),generationBaseObject.getX(),generationBaseObject.getY(),generationBaseObject.getZ());
        if(genLoc.equals(l)) {
            e.setCancelled(true);
        }

    }
}
