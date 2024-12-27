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
import org.kim.freeBuild.guis.GenGUI;
import org.kim.freeBuild.items.GeneratorItems;
import org.kim.freeBuild.objects.GenerationBaseObject;

public class PlaceGenEvent implements Listener {
    @EventHandler
    public void placeGenEvent(PlayerInteractEvent event) {
        Player p = event.getPlayer();
        Action action = event.getAction();
        Block b = event.getClickedBlock();
        if (action == Action.RIGHT_CLICK_BLOCK && p.getItemInHand().equals(GeneratorItems.getGen())) {
            event.setCancelled(true);
            if(b == null) {
                return;
            }
            Location loc = b.getLocation().add(0, 1, 0);
            Block b2 = loc.getBlock();
            GenerationBaseObject generationBaseObject = GenerationBaseObject.generationBaseObjectMap.get(p.getUniqueId());
            int level = generationBaseObject.getLevel();
            int upgrade = generationBaseObject.getUpgrade();
            double fuel = generationBaseObject.getFuel();
            if (generationBaseObject.getX() == -1 && generationBaseObject.getY() == -1 && generationBaseObject.getZ() == -1) {
                b2.setType(MineralsEnum.getMaterial(level));
                GenerationBaseObject generationBaseObject1 = new GenerationBaseObject(loc.getX(), loc.getY(), loc.getZ(), level, upgrade, fuel);
                GenerationBaseObject.generationBaseObjectMap.put(p.getUniqueId(), generationBaseObject1);
            } else {
                p.sendMessage("Du kannst keinen Gen placen!");
            }
        }
        if (action == Action.RIGHT_CLICK_BLOCK) {
            GenerationBaseObject generationBaseObject = GenerationBaseObject.generationBaseObjectMap.get(p.getUniqueId());
            double x = generationBaseObject.getX();
            double y = generationBaseObject.getY();
            double z = generationBaseObject.getZ();
            if(b == null) {
                return;
            }
            Location location = new Location(p.getWorld(), x, y, z);
            if (location.equals(b.getLocation()) && location.getWorld().equals(Bukkit.getWorld("InselWelt"))) {
                GenGUI.openInventory(p);
            }
        }
    }

}
