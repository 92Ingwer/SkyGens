package org.kim.freeBuild.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockExplodeEvent;
import org.kim.freeBuild.objects.AutomaticChestObject;
import org.kim.freeBuild.objects.AutomaticDrillObject;
import org.kim.freeBuild.objects.GenerationBaseObject;

public class BlockExplosiveEvent implements Listener {
    /**
     * Handles the BlockExplodeEvent to prevent specific blocks associated with players'
     * AutomaticDrillObject, AutomaticChestObject, and GenerationBaseObject instances
     * from being removed during an explosion.
     *
     * @param e The BlockExplodeEvent triggered by the explosion of a block.
     */
    @EventHandler
    public void onBlockExplosive(BlockExplodeEvent e) {
        for (Player p : Bukkit.getOnlinePlayers()) {
            AutomaticDrillObject automaticDrillObject = AutomaticDrillObject.automaticDrillObject.get(p);
            AutomaticChestObject automaticChestObject = AutomaticChestObject.automaticChestObjectMap.get(p);
            GenerationBaseObject generationBaseObject = GenerationBaseObject.generationBaseObjectMap.get(p);
            e.blockList().removeIf(block -> block.getLocation().equals(automaticDrillObject.getLocation()) || block.getLocation().equals(automaticChestObject.getLocation()) || block.getLocation().equals(generationBaseObject.getLocation()));
        }
    }
}
