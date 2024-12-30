package org.kim.SkyGens.items;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.kim.SkyGens.utils.ItemBuilder;

import java.util.List;

public class GeneratorItems {

    public static ItemStack getGen() {
        return new ItemBuilder(Material.BEDROCK)
                .name(MiniMessage.miniMessage().deserialize("<b><gradient:#F5ECD6:#DB9BE9>Generator </gradient></b>"))
                .lore(List.of(Component.text("§fSetze deinen Generator!")))
                .build();
    }
    public static ItemStack getChest() {
        return new ItemBuilder(Material.CHEST)
                .name(MiniMessage.miniMessage().deserialize("Automatic - Chest"))
                .lore(List.of(Component.text("§fSetze deine Chest neben deinem Generator!")))
                .build();
    }
    public static ItemStack getDrill() {
        return new ItemBuilder(Material.DISPENSER)
                .name(MiniMessage.miniMessage().deserialize("Automatic - Drill"))
                .lore(List.of(Component.text("§fSetze deinen Drill neben deinem Generator!")))
                .build();
    }
}
