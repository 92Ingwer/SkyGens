package org.kim.freeBuild.listeners;

import net.kyori.adventure.text.Component;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;
import org.kim.freeBuild.FreeBuild;
import org.kim.freeBuild.items.GeneratorItems;
import org.kim.freeBuild.objects.AutomaticChestObject;
import org.kim.freeBuild.objects.GenerationBaseObject;
import org.kim.freeBuild.utils.ItemBuilder;

public class PlaceUpgraderListener implements Listener {
    //TODO: Wenn man abbaut und neu placed, soll es nur 1 Strahl geben. Dieser Strahl soll von der Mitte beider Blöcke gehen (Zurzeit: Es gibt dann mehrere Strähle, bis man rejoined)
    @EventHandler
    public void onPlaceUpgrader(BlockPlaceEvent e) {
        Player p = e.getPlayer();
        ItemStack item = e.getItemInHand();
        Block b = e.getBlockPlaced();
        AutomaticChestObject automaticChestObject = AutomaticChestObject.automaticChestObjectMap.get(p);
        GenerationBaseObject generationBaseObject = GenerationBaseObject.generationBaseObjectMap.get(p);

        //Chest placen
        if (item.equals(GeneratorItems.getChest())) {
            if (automaticChestObject.getX() != -1.0) {
                e.setCancelled(true);
                p.sendMessage("Du hast bereits eine Automatische Chest geplaced!");
                return;
            }
            Location loc = b.getLocation();
            if((generationBaseObject.getLocation().distance(loc) > 10) || generationBaseObject.getLocation().distance(loc) < 2) {
                e.setCancelled(true);
                p.sendMessage("Zu weit weg/nah von/an deinem Generator!");
                return;
            }
            Chest chest = (Chest) b.getState();
            setItemSlots(chest);
            AutomaticChestObject automaticChestObjectNew = new AutomaticChestObject(loc.getX(), loc.getY(), loc.getZ(),chest);
            AutomaticChestObject.automaticChestObjectMap.put(p, automaticChestObjectNew);
            drawParticleLine(loc.add(0,0.5,0),generationBaseObject.getLocation().add(0,0.5,0),p);
        }
    }


    public static void drawParticleLine(Location start, Location end, Player p) {
        Bukkit.getScheduler().runTaskTimer(FreeBuild.getInstance(), () -> {
            AutomaticChestObject automaticChestObject = AutomaticChestObject.automaticChestObjectMap.get(p);
            if(!Bukkit.getOnlinePlayers().contains(p) || automaticChestObject.getChest() == null) {
                return;
            }
            double distanz = start.distance(end);
            double schritt = 0.2;
            Vector direction = end.toVector().subtract(start.toVector()).normalize().multiply(schritt);
            Location current = start.clone();
            for (double d = 0; d < distanz; d += schritt) {
                current.getWorld().spawnParticle(Particle.DUST, current, 1, new Particle.DustOptions(Color.RED, 1));
                current.add(direction);
            }
        }, 0L, 5L);
    }

    public void setItemSlots(Chest chest) {
        int[] slots = {0, 1, 2, 3, 4, 5, 6, 7, 8, 18, 19, 20, 21, 22, 23, 24, 25};
        for (int slot : slots) {
            chest.getInventory().setItem(slot, new ItemStack(Material.GRAY_STAINED_GLASS_PANE));
        }
        ItemStack item = new ItemBuilder(Material.BEDROCK).name(Component.text("§cKiste abbauen")).build();
        chest.getInventory().setItem(26, item);
    }
}
