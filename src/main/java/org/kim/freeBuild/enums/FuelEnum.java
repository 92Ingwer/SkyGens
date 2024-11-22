package org.kim.freeBuild.enums;

import org.bukkit.Material;

public enum FuelEnum {
    COAL(Material.COAL, 5),
    CHARCOAL(Material.CHARCOAL, 6),
    COAL_BLOCK(Material.COAL_BLOCK, 40),
    LAVA(Material.LAVA_BUCKET, 30),
    NETHERSTAR(Material.NETHER_STAR, 300),
    REDSTONE(Material.REDSTONE, 10),
    REDSTONE_BLOCK(Material.REDSTONE_BLOCK, 80),
    BLAZE_ROD(Material.BLAZE_ROD, 50),
    BLAZE_POWDER(Material.BLAZE_POWDER, 60);


    final Material material;
     final double fuel;

    FuelEnum(Material material, double fuel) {
        this.material = material;
        this.fuel = fuel;
    }

    public Material getMaterial() {
        return material;
    }
    public double getFuel() {
        return fuel;
    }

    public static double getFuel(Material material) {
        for (FuelEnum fuelEnum : FuelEnum.values()) {
            if (fuelEnum.getMaterial().equals(material)) {
                return fuelEnum.getFuel();
            }
        }
        return 0;
    }

    public static boolean isItemFuel(Material material) {
        for (FuelEnum fuelEnum : FuelEnum.values()) {
            if (fuelEnum.getMaterial().equals(material)) {
                return true;
            }
        }
        return false;
    }
}
