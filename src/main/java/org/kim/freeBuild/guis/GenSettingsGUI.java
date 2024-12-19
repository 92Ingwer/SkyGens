package org.kim.freeBuild.guis;


import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.block.Chest;
import org.bukkit.entity.ItemDisplay;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.kim.freeBuild.objects.AutomaticChestObject;
import org.kim.freeBuild.objects.AutomaticDrillObject;
import org.kim.freeBuild.utils.InventoryBuilder;

import java.util.List;

public class GenSettingsGUI implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        AutomaticChestObject automaticChestObject = AutomaticChestObject.automaticChestObjectMap.get(player);
        AutomaticDrillObject automaticDrillObject = AutomaticDrillObject.automaticDrillObject.get(player);
        if (event.getView().title().equals(Component.text("Gen - Einstellungen"))) {
            event.setCancelled(true);
            switch (event.getSlot()) {
                case 20:
                    automaticChestObject.changeSetting();
                    player.closeInventory();
                    break;
                case 24:
                    automaticDrillObject.changeSetting();
                    player.closeInventory();
                    break;
            }
        }
    }

    public static void openInventory(Player p) {
        AutomaticChestObject automaticChestObject = AutomaticChestObject.automaticChestObjectMap.get(p);
        AutomaticDrillObject automaticDrillObject = AutomaticDrillObject.automaticDrillObject.get(p);
        Chest chest = automaticChestObject.getChest();
        Component chestType;
        Component drillType;
        if (chest == null) {
            chestType = Component.text("§cNicht vorhanden");
        } else if (automaticChestObject.getSetting()) {
            chestType = Component.text("§aAngeschaltet!");
        } else {
            chestType = Component.text("§cAusgeschaltet!");
        }
        if(automaticDrillObject.getX() == -1.0) {
            drillType = Component.text("§cNicht vorhanden");
        } else if (automaticDrillObject.isSetting()) {
            drillType = Component.text("§aAngeschaltet!");
        } else {
            drillType = Component.text("§cAusgeschaltet!");
        }
        Inventory inventory = new InventoryBuilder("Gen - Einstellungen", 6 * 9, 2)
                .aItem(20, Material.CHEST, Component.text("§cAutomatic-Chest"), List.of(chestType))
                .aItem(24,Material.DISPENSER, Component.text("§cAutomatic-Drill"),List.of(drillType))
                .build();
        p.openInventory(inventory);
    }
}
