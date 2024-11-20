package org.kim.freeBuild.enums;

import org.bukkit.Material;

public enum MineralsEnum {
    COAL(Material.COAL_ORE, 1),
    DEEPSLATE_COAL(Material.DEEPSLATE_COAL_ORE, 2),
    IRON(Material.IRON_ORE, 2),
    DEEPSLATE_IRON(Material.DEEPSLATE_IRON_ORE, 3),
    COPPER(Material.COPPER_ORE, 2),
    DEEPSLATE_COPPER(Material.DEEPSLATE_COPPER_ORE, 3),
    GOLD(Material.GOLD_ORE, 3),
    DEEPSLATE_GOLD(Material.DEEPSLATE_GOLD_ORE, 4),
    REDSTONE(Material.REDSTONE_ORE, 2),
    DEEPSLATE_REDSTONE(Material.DEEPSLATE_REDSTONE_ORE, 3),
    LAPIS(Material.LAPIS_ORE, 2),
    DEEPSLATE_LAPIS(Material.DEEPSLATE_LAPIS_ORE, 3),
    DIAMOND(Material.DIAMOND_ORE, 5),
    DEEPSLATE_DIAMOND(Material.DEEPSLATE_DIAMOND_ORE, 6),
    EMERALD(Material.EMERALD_ORE, 4),
    DEEPSLATE_EMERALD(Material.DEEPSLATE_EMERALD_ORE, 5),
    NETHER_QUARTZ(Material.NETHER_QUARTZ_ORE, 1),
    NETHER_GOLD(Material.NETHER_GOLD_ORE, 3);


    final Material material;
    final int hearts;

    MineralsEnum(Material material, int hearts) {
        this.material = material;
        this.hearts = hearts;
    }

    public Material getMaterial() {
        return material;
    }
    public int getHearts3() {
        return hearts;
    }
    public static int getHeart(Material material) {
        for (MineralsEnum mineral : values()) {
            if (mineral.getMaterial() == material) {
                return mineral.getHearts3();
            }
        }
        return -1;
    }
}
