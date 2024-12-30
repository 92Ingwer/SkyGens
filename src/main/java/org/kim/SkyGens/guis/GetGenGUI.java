package org.kim.SkyGens.guis;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.kim.SkyGens.items.GeneratorItems;
import org.kim.SkyGens.utils.InventoryBuilder;

import java.util.List;

public class GetGenGUI implements Listener {
    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        if (e.getView().title().equals(Component.text("Get-Generator - Inventory")) && e.getRawSlot() < e.getView().getTopInventory().getSize()) {
            e.setCancelled(true);
            if (e.getSlot() == 13) {
                p.getInventory().addItem(GeneratorItems.getGen());
                p.closeInventory();
            }
        }
    }

    public static void openInventory(Player p) {
        Inventory inventory = new InventoryBuilder("Get-Generator - Inventory", 3 * 9, 1)
                .aItem(13, Material.BEDROCK, MiniMessage.miniMessage().deserialize("<b><gradient:#F5ECD6:#DB9BE9>Generator </gradient></b>"), List.of(Component.text("§fSetze deinen Generator!")))
                .build();
        p.openInventory(inventory);
    }
}
