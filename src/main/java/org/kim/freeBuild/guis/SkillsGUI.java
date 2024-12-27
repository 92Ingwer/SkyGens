package org.kim.freeBuild.guis;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.kim.freeBuild.skills.FarmingSkill;
import org.kim.freeBuild.skills.HuntingSkill;
import org.kim.freeBuild.utils.InventoryBuilder;

import java.awt.*;
import java.util.List;

public class SkillsGUI implements Listener {
    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        String title = PlainTextComponentSerializer.plainText().serialize(e.getView().title());
        if (title.equals("Skills von " + p.getName())) {
            e.setCancelled(true);
            int slot = e.getSlot();
            FarmingSkill farmingSkill = FarmingSkill.farmingSkillMap.get(p.getUniqueId());
            switch (slot) {
                case 11:
                    p.closeInventory();
                    SpecificSkillGUI.openInventory(p,farmingSkill);
                    break;
                case 16:
                    break;
            }
        }
    }

    public static void openInventory(Player player) {
        Inventory inventory = new InventoryBuilder("<gradient:#C8E0C7:#F1F1F1>Skills von " + player.getName() + "</gradient>", 6 * 9, 1)
                .aItem(11, Material.IRON_HOE, Component.text("§aFarming"), List.of(Component.text(Color.GRAY + "Öffne die Farming-Übersicht")))
                .aItem(15, Material.IRON_SWORD, Component.text("§cHunting"), List.of(Component.text(Color.GRAY + "Öffne die Hunting-Übersicht")))
                .build();
        player.openInventory(inventory);
    }
}
