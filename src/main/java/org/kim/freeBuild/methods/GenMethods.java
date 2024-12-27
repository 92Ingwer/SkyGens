package org.kim.freeBuild.methods;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.entity.*;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Transformation;
import org.bukkit.util.Vector;
import org.joml.AxisAngle4f;
import org.joml.Vector3f;
import org.kim.freeBuild.FreeBuild;
import org.kim.freeBuild.enums.GenItemEnum;
import org.kim.freeBuild.items.GeneratorItems;
import org.kim.freeBuild.objects.AutomaticChestObject;
import org.kim.freeBuild.objects.GenerationBaseObject;
import org.kim.freeBuild.services.GenerationService;
import java.util.List;

public class GenMethods {

    public static void breakGen(Player p) {
        GenerationBaseObject generationBaseObject = GenerationBaseObject.generationBaseObjectMap.get(p.getUniqueId());
        Location location = new Location(Bukkit.getWorld("InselWelt"), generationBaseObject.getX(), generationBaseObject.getY(), generationBaseObject.getZ());
        Block b = location.getBlock();
        generationBaseObject.setX(-1);
        generationBaseObject.setY(-1);
        generationBaseObject.setZ(-1);
        GenerationBaseObject.generationBaseObjectMap.put(p.getUniqueId(), generationBaseObject);
        b.setType(Material.AIR);
        p.getInventory().addItem(GeneratorItems.getGen());
        createExplosion(b);
        GenerationService.brokenGenObjects.remove(p.getUniqueId());
    }

    public static void createExplosion(Block b) {
        Location l = b.getLocation();
        World world = b.getWorld();
        world.createExplosion(l, 2.0F, false, false);
    }

    public static void moveItem(Player p) {
        AutomaticChestObject automaticChestObject = AutomaticChestObject.automaticChestObjectMap.get(p.getUniqueId());
        GenerationBaseObject generationBaseObject = GenerationBaseObject.generationBaseObjectMap.get(p.getUniqueId());
        int level = generationBaseObject.getLevel();
        List<Location> locationList = GenerationService.locationForChestParticle.get(p.getUniqueId());
        Location start = locationList.getFirst().clone();
        Location end = locationList.getLast().clone();
        start.add(0,0.35,0);
        end.add(0,0.35,0);
        if (!automaticChestObject.getSetting()) {
            return;
        }
        if (!Bukkit.getOnlinePlayers().contains(p) || automaticChestObject.getChest() == null) {
            return;
        }
        Chest chest = automaticChestObject.getChest();
        chest.open();
        double distance = start.distance(end);
        double step = 0.1;
        Vector direction = start.toVector().subtract(end.toVector()).normalize().multiply(step);
        ItemDisplay itemDisplay = start.getWorld().spawn(end, ItemDisplay.class, entity -> {
            entity.setItemStack(GenItemEnum.getItem(level));
            entity.setTransformation(
                    new Transformation(
                            new Vector3f(),
                            new AxisAngle4f(),
                            new Vector3f(0.5F, 0.5F, 0.5F),
                            new AxisAngle4f()
                    )
            );
        });
        new BukkitRunnable() {
            final Location current = end.clone();
            double traveled = 0;

            @Override
            public void run() {
                if (traveled >= distance) {
                    itemDisplay.remove();
                    chest.close();
                    cancel();
                    return;
                }
                current.add(direction);
                itemDisplay.teleport(current);
                traveled += step;
            }
        }.runTaskTimer(FreeBuild.getInstance(), 0L, 1L);
    }
}
