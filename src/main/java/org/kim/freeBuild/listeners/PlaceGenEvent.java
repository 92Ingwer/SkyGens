package org.kim.freeBuild.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.kim.freeBuild.enums.MineralsEnum;
import org.kim.freeBuild.items.GeneratorItem;
import org.kim.freeBuild.objects.GenerationBaseObject;

public class PlaceGenEvent implements Listener {
    @EventHandler
    public void placeGenEvent(PlayerInteractEvent event) {
        Player p = event.getPlayer();
        Action action = event.getAction();
        Block b = event.getClickedBlock();
        if (action == Action.RIGHT_CLICK_BLOCK && p.getItemInHand().equals(GeneratorItem.getItem())) {
            event.setCancelled(true);
            Location loc = b.getLocation().add(0, 1, 0);
            Block b2 = loc.getBlock();
            GenerationBaseObject generationBaseObject = GenerationBaseObject.generationBaseObjectMap.get(p);
            int level = generationBaseObject.getLevel();
            b2.setType(MineralsEnum.getMaterial(level));
            GenerationBaseObject generationBaseObject1 = new GenerationBaseObject(loc.getX(),loc.getY(),loc.getZ(),level);
            GenerationBaseObject.generationBaseObjectMap.put(p, generationBaseObject1);
        }
        if(action == Action.RIGHT_CLICK_BLOCK) {
            GenerationBaseObject generationBaseObject = GenerationBaseObject.generationBaseObjectMap.get(p);
            double x = generationBaseObject.getX();
            double y = generationBaseObject.getY();
            double z = generationBaseObject.getZ();

            Location location = new Location(p.getWorld(), x, y, z);
            if(location.equals(b.getLocation()) && location.getWorld().equals(Bukkit.getWorld("InselWelt"))) {
                p.sendMessage("ALLES KLAPPT ABI");
            }
        }
    }

}
