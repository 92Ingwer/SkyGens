package org.kim.SkyGens.enums;

import lombok.Getter;
import org.bukkit.Material;

public enum MineralsEnum {
    COAL(Material.COAL_ORE, 20, 1,2),
    DEEPSLATE_COAL(Material.DEEPSLATE_COAL_ORE, 40, 2,4),
    IRON(Material.IRON_ORE, 50, 3,8),
    DEEPSLATE_IRON(Material.DEEPSLATE_IRON_ORE, 60, 4,16),
    COPPER(Material.COPPER_ORE, 70, 5,20),
    DEEPSLATE_COPPER(Material.DEEPSLATE_COPPER_ORE, 80, 6,22),
    GOLD(Material.GOLD_ORE, 100, 7,24),
    DEEPSLATE_GOLD(Material.DEEPSLATE_GOLD_ORE, 120, 8,26),
    REDSTONE(Material.REDSTONE_ORE, 150, 9,28),
    DEEPSLATE_REDSTONE(Material.DEEPSLATE_REDSTONE_ORE, 180, 10,30),
    LAPIS(Material.LAPIS_ORE, 200, 11,32),
    DEEPSLATE_LAPIS(Material.DEEPSLATE_LAPIS_ORE, 240, 12,34),
    DIAMOND(Material.DIAMOND_ORE, 300, 13,36),
    DEEPSLATE_DIAMOND(Material.DEEPSLATE_DIAMOND_ORE, 350, 14,38),
    EMERALD(Material.EMERALD_ORE, 400, 15,40),
    DEEPSLATE_EMERALD(Material.DEEPSLATE_EMERALD_ORE, 450, 16,42),
    NETHER_QUARTZ(Material.NETHER_QUARTZ_ORE, 250, 17,44),
    NETHER_GOLD(Material.NETHER_GOLD_ORE, 300, 18,46);


    final Material material;
    final int hearts;
    @Getter
    final int level;
    @Getter
    final double usage;

    MineralsEnum(Material material, int hearts, int level, double usage) {
        this.material = material;
        this.hearts = hearts;
        this.level = level;
        this.usage = usage;
    }

    public Material getMaterial3() {
        return material;
    }

    public int getHearts3() {
        return hearts;
    }
    public static int getHeart(int level) {
        for (MineralsEnum mineral : values()) {
            if (mineral.getLevel() == level) {
                return mineral.getHearts3();
            }
        }
        return -1;
    }

    public static Material getMaterial(int level) {
        for (MineralsEnum mineral : values()) {
            if (mineral.getLevel() == level) {
                return mineral.getMaterial3();
            }
        }
        return Material.AIR;
    }
    public static double getUsage(int level) {
        for (MineralsEnum mineral : values()) {
            if (mineral.getLevel() == level) {
                return mineral.getUsage();
            }
        }
        return 0;
    }
}
