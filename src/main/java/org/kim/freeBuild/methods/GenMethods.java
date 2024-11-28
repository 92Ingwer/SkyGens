package org.kim.freeBuild.methods;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Explosive;
import org.bukkit.entity.Panda;
import org.bukkit.entity.Player;
import org.kim.freeBuild.enums.MineralsEnum;
import org.kim.freeBuild.items.GeneratorItems;
import org.kim.freeBuild.objects.GenerationBaseObject;
import org.kim.freeBuild.services.GenerationService;

public class GenMethods {
    public static boolean isBlockAnOre(Block block) {
        Material material = block.getType();
        for (MineralsEnum mineralsEnum : MineralsEnum.values()) {
            if (mineralsEnum.getMaterial3() == material) {
                return true;
            }
        }
        return false;
    }

    public static void breakGen(Player p) {
        GenerationBaseObject generationBaseObject = GenerationBaseObject.generationBaseObjectMap.get(p);
        Location location = new Location(Bukkit.getWorld("InselWelt"),generationBaseObject.getX(), generationBaseObject.getY(), generationBaseObject.getZ());
        Block b = location.getBlock();
        generationBaseObject.setX(-1);
        generationBaseObject.setY(-1);
        generationBaseObject.setZ(-1);
        GenerationBaseObject.generationBaseObjectMap.put(p, generationBaseObject);
        b.setType(Material.AIR);
        p.getInventory().addItem(GeneratorItems.getGen());
        createExplosion(b);
        GenerationService.brokenGenObjects.remove(p);
    }

    public static void createExplosion(Block b) {
        Location l = b.getLocation();
        World world = b.getWorld();
        world.createExplosion(l,2.0F,false,false);
    }
}
