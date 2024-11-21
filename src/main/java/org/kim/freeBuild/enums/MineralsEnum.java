package org.kim.freeBuild.enums;

import org.bukkit.Material;

public enum MineralsEnum {
    COAL(Material.COAL_ORE, 1,1),
    DEEPSLATE_COAL(Material.DEEPSLATE_COAL_ORE, 2,2),
    IRON(Material.IRON_ORE, 2,3),
    DEEPSLATE_IRON(Material.DEEPSLATE_IRON_ORE, 3,4),
    COPPER(Material.COPPER_ORE, 2,5),
    DEEPSLATE_COPPER(Material.DEEPSLATE_COPPER_ORE, 3,6),
    GOLD(Material.GOLD_ORE, 3,7),
    DEEPSLATE_GOLD(Material.DEEPSLATE_GOLD_ORE, 4,8),
    REDSTONE(Material.REDSTONE_ORE, 2,9),
    DEEPSLATE_REDSTONE(Material.DEEPSLATE_REDSTONE_ORE, 3,10),
    LAPIS(Material.LAPIS_ORE, 2,11),
    DEEPSLATE_LAPIS(Material.DEEPSLATE_LAPIS_ORE, 3,12),
    DIAMOND(Material.DIAMOND_ORE, 5,13),
    DEEPSLATE_DIAMOND(Material.DEEPSLATE_DIAMOND_ORE, 6,14),
    EMERALD(Material.EMERALD_ORE, 4,15),
    DEEPSLATE_EMERALD(Material.DEEPSLATE_EMERALD_ORE, 5,16),
    NETHER_QUARTZ(Material.NETHER_QUARTZ_ORE, 1,17),
    NETHER_GOLD(Material.NETHER_GOLD_ORE, 3,18);


    final Material material;
    final int hearts;
    final int level;

    MineralsEnum(Material material, int hearts, int level) {
        this.material = material;
        this.hearts = hearts;
        this.level = level;
    }

    public Material getMaterial3() {
        return material;
    }
    public int getHearts3() {
        return hearts;
    }
    public static int getHeart(Material material) {
        for (MineralsEnum mineral : values()) {
            if (mineral.getMaterial3() == material) {
                return mineral.getHearts3();
            }
        }
        return -1;
    }
    public int getLevel() {
        return level;
    }
    public static Material getMaterial(int level) {
        for(MineralsEnum mineral : values()) {
            if(mineral.getLevel() == level) {
                return mineral.getMaterial3();
            }
        }
        return Material.AIR;
    }
}
