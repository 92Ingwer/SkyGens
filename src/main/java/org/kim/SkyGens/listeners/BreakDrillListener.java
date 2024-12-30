package org.kim.SkyGens.listeners;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.kim.SkyGens.objects.AutomaticDrillObject;

public class BreakDrillListener implements Listener {
    @EventHandler
    public void onBreak(BlockBreakEvent e) {
        Player p = e.getPlayer();
        Block block = e.getBlock();
        AutomaticDrillObject automaticDrillObject = AutomaticDrillObject.automaticDrillObject.get(p.getUniqueId());
        if(automaticDrillObject == null) {
            return;
        }
        Location loc = block.getLocation();
        Location drillLoc = automaticDrillObject.getLocation();
        if(loc.equals(drillLoc)) {
            e.setCancelled(true);
        }
    }
}
