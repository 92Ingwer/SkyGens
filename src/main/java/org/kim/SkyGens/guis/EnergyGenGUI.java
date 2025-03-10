package org.kim.SkyGens.guis;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.kim.SkyGens.enums.FuelEnum;
import org.kim.SkyGens.objects.GenerationBaseObject;
import org.kim.SkyGens.utils.InventoryBuilder;

public class EnergyGenGUI implements Listener {
    /**
     * Handles the player's interaction with a custom inventory GUI titled "Energie - Menü".
     * This method ensures proper functionality of the energy management system by processing the
     * placement and retrieval of fuel items in the designated inventory slots and updating the
     * player's corresponding fuel levels accordingly.
     *
     * @param e the InventoryClickEvent triggered when a player interacts with an inventory
     */
    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        Inventory clickedInventory = e.getClickedInventory();
        if (e.getView().title().equals(Component.text("Energie - Menü"))) {
            e.setCancelled(true);
            if(clickedInventory == null) return;
            if (clickedInventory.equals(e.getView().getBottomInventory())) {
                ItemStack clickedItem = e.getCurrentItem();
                if (clickedItem == null || clickedItem.getType() == Material.AIR) {
                    return;
                }
                Material mat = clickedItem.getType();
                if (!FuelEnum.isItemFuel(mat)) {
                    return;
                }
                Inventory topInventory = e.getView().getTopInventory();
                int freeSlot = getNextFreeSlot(topInventory);
                if (freeSlot == -1) {
                    return;
                }
                clickedInventory.setItem(e.getSlot(), null);
                topInventory.setItem(freeSlot, clickedItem);
            }
            if((e.getSlot() == 34 && clickedInventory.equals(e.getView().getTopInventory()))) {
                e.setCancelled(true);
                double fuel = 0;
                for(int i = 0; i < clickedInventory.getSize(); i++ ) {
                    ItemStack clickedItem = clickedInventory.getItem(i);
                    if (!(clickedItem == null || clickedItem.getType() == Material.GRAY_STAINED_GLASS_PANE || clickedItem.getType() == Material.EMERALD_BLOCK)) {
                        fuel = fuel+ (FuelEnum.getFuel(clickedItem.getType())*clickedItem.getAmount());
                    }
                }
                GenerationBaseObject generationBaseObject = GenerationBaseObject.generationBaseObjectMap.get(p.getUniqueId());
                generationBaseObject.setFuel(fuel+generationBaseObject.getFuel());
                p.closeInventory();
                p.sendMessage("Du hast nun " + fuel + "J mehr!");
            }
        }
    }


    public static void openInventory(Player p) {
        Inventory inventory = new InventoryBuilder("Energie - Menü", 5 * 9, 1).aItem(34, Material.EMERALD_BLOCK, Component.text("§aFertig?"), null).build();
        p.openInventory(inventory);
    }

    public int getNextFreeSlot(Inventory inventory) {
        for (int i = 0; i < inventory.getSize(); i++) {
            if ((inventory.getItem(i) == null)) {
                return i;
            }
        }
        return -1;
    }
}
