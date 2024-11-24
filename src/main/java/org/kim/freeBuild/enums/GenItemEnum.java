package org.kim.freeBuild.enums;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.kim.freeBuild.utils.ItemBuilder;

import java.util.List;

public enum GenItemEnum {
    COAL(Material.COAL,1,1,"Kohle"),
    DEEPSLATE_COAL(Material.COAL, 2, 2, "Kohle"),
    IRON(Material.IRON_INGOT, 3, 3, "Eisen"),
    DEEPSLATE_IRON(Material.IRON_INGOT, 4, 4, "Eisen"),
    COPPER(Material.COPPER_INGOT, 5, 5, "Kupfer"),
    DEEPSLATE_COPPER(Material.COPPER_INGOT, 6, 6, "Kupfer"),
    GOLD(Material.GOLD_INGOT, 7, 7,"Gold"),
    DEEPSLATE_GOLD(Material.GOLD_INGOT, 8, 8,"Gold"),
    REDSTONE(Material.REDSTONE, 9, 9,"Redstone"),
    DEEPSLATE_REDSTONE(Material.REDSTONE, 10, 10,"Redstone"),
    LAPIS(Material.LAPIS_LAZULI, 11, 11,"Lapis"),
    DEEPSLATE_LAPIS(Material.LAPIS_LAZULI, 12, 12,"Lapis"),
    DIAMOND(Material.DIAMOND, 13, 13,"Diamond"),
    DEEPSLATE_DIAMOND(Material.DIAMOND, 14, 14,"Diamond"),
    EMERALD(Material.EMERALD, 15, 15,"Emerald"),
    DEEPSLATE_EMERALD(Material.EMERALD, 16, 16,"Emerald"),
    NETHER_QUARTZ(Material.QUARTZ, 17, 17,"Quartz"),
    NETHER_GOLD(Material.GOLD_NUGGET, 18, 18,"Gold-Nugget"),;





    private Material material;
    private int price;
    private int level;
    private String name;

    GenItemEnum(Material material, int level, int price, String name) {
        this.material = material;
        this.price = price;
        this.level = level;
        this.name = name;
    }

    public Material getMaterial() {
        return material;
    }
    public int getLevel() {
        return level;
    }
    public static Material getMaterial(int level) {
        for(GenItemEnum genItemEnum : GenItemEnum.values()) {
            if(genItemEnum.getLevel() == level) {
                return genItemEnum.getMaterial();
            }
        }
        return null;
    }
    public String getName() {
        return name;
    }
    public int getPrice() {
        return price;
    }
    public static String getName(int level) {
        for(GenItemEnum genItemEnum : GenItemEnum.values()) {
            if(genItemEnum.getLevel() == level) {
                return genItemEnum.getName();
            }
        }
        return null;
    }
    public static int getPrice(int level) {
        for(GenItemEnum genItemEnum : GenItemEnum.values()) {
            if(genItemEnum.getLevel() == level) {
                return genItemEnum.getPrice();
            }
        }
        return -1;
    }


    public static ItemStack getItem(int level) {
        Material material = getMaterial(level);
        String name = getName(level);
        int price = getPrice(level);
        return new ItemBuilder(material)
                .name(MiniMessage.miniMessage().deserialize("<gradient:#FFFFFF:#F57A7A>"+ getName(level)+ " (Level " +level +")</gradient>"))
                .lore(List.of(MiniMessage.miniMessage().deserialize("<gradient:#FFDD83:#F3BB28>Wert: " + price+"$ </gradient>")))
                .build();
    }

}
