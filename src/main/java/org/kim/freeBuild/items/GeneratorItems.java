package org.kim.freeBuild.items;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.kim.freeBuild.utils.ItemBuilder;

import java.util.List;

public class GeneratorItems {

    public static ItemStack getGen() {
        ItemStack item = new ItemBuilder(Material.BEDROCK)
                .name(MiniMessage.miniMessage().deserialize("<b><gradient:#F5ECD6:#DB9BE9>Generator </gradient></b>"))
                .lore(List.of(Component.text("§fSetze deinen Generator!")))
                .build();
        return item;
    }
    public static ItemStack getChest() {
        ItemStack item = new ItemBuilder(Material.CHEST)
                .name(MiniMessage.miniMessage().deserialize("Automatic - Chest"))
                .lore(List.of(Component.text("§fSetze deine Chest neben deinem Generator!")))
                .build();
        return item;
    }
}
