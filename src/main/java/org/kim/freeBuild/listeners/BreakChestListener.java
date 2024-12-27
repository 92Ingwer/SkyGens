package org.kim.freeBuild.listeners;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.kim.freeBuild.objects.AutomaticChestObject;

public class BreakChestListener implements Listener {

    @EventHandler
    public void onBreak(BlockBreakEvent e) {
        Block b = e.getBlock();
        Player p = e.getPlayer();
        AutomaticChestObject automaticChestObject = AutomaticChestObject.automaticChestObjectMap.get(p.getUniqueId());
        if(automaticChestObject == null) {
            return;
        }
        Location loc = b.getLocation();
        Location chestLoc = automaticChestObject.getLocation();
        if(loc.equals(chestLoc)) {
            e.setCancelled(true);
        }
    }
}
