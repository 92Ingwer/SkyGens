package org.kim.freeBuild.listeners;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.kim.freeBuild.items.GeneratorItems;
import org.kim.freeBuild.objects.AutomaticChestObject;

public class OpenAutomaticChestListener implements Listener {

    //TODO: Fixen, dass wenn Spieler inv anklickt, dass nichts passiert (Zurzeit: Alles wird verdoppelt)
    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (!(e.getWhoClicked() instanceof Player p)) {
            return;
        }
        if (e.getInventory().getHolder() instanceof Chest chest) {
            Inventory inv = e.getInventory();
            if (e.getView().title().equals(Component.text("Automatic - Chest"))) {
                e.setCancelled(true);
                Integer[] slots = {9, 10, 11, 1, 2, 13, 14, 15, 16, 17};
                for (int slot : slots) {
                    if (slot == e.getSlot() && e.getCurrentItem() != null) {
                        if (inv.equals(p.getOpenInventory().getTopInventory())) {
                            ItemStack stack = e.getCurrentItem();
                            p.getInventory().addItem(stack);
                            inv.removeItem(stack);
                        }
                    }
                }
                if (e.getSlot() == 26) {
                    chest.getBlock().setType(Material.AIR);
                    p.getInventory().addItem(GeneratorItems.getChest());
                    AutomaticChestObject automaticChestObject = new AutomaticChestObject(-1.0, -1.0, -1.0, null);
                    AutomaticChestObject.automaticChestObjectMap.put(p, automaticChestObject);
                    p.closeInventory();
                }
            }
        }
    }
}