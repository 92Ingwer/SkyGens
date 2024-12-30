package org.kim.SkyGens.guis;


import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.kim.SkyGens.services.PlayerService;
import org.kim.SkyGens.skills.Skills;
import org.kim.SkyGens.utils.InventoryBuilder;
import org.kim.SkyGens.utils.ItemBuilder;

import java.util.List;

public class SkillLevelGUI implements Listener {
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        Skills skills =PlayerService.skillGUIWhichSkill.get(player.getUniqueId());
        if(skills == null) {
            return;
        }
        if (event.getView().title().equals(Component.text("Level Übersicht - " + skills.getName()))) {
            event.setCancelled(true);
            int site = PlayerService.skillGUIWhichSite.get(player.getUniqueId());
            switch (event.getSlot()) {
                case 53:
                    PlayerService.skillGUIWhichSite.put(player.getUniqueId(), site+1);
                    site++;
                    player.closeInventory();
                    openInventory(player,skills,site);
                    openInventory(player,skills,site);
                    break;
                case 8:
                    if (PlayerService.skillGUIWhichSite.get(player.getUniqueId()) != 0) {
                        PlayerService.skillGUIWhichSite.put(player.getUniqueId(), site-1);
                        site--;
                        player.closeInventory();
                        openInventory(player,skills,site);
                        break;
                    }
            }
        }
    }

    public static<T extends Skills> void openInventory(Player p, T skills, int site) {
        Inventory inventory = new InventoryBuilder("Level Übersicht - " + skills.getName(),6*9,1)
                .aItem(53, Material.NETHER_STAR, Component.text("§eWeiter!"), null)
                .aItem(8, Material.NETHER_STAR, Component.text("§eZurück!"), null)
                .build();
        redGlas(inventory,skills,site);
        p.openInventory(inventory);
    }
    private static <T extends Skills> void redGlas(Inventory inventory, T skills, int site) {
        int glassLevel = site*16;
        Integer[] redglas = {10, 11, 12, 13, 14, 15, 16, 25, 34, 33, 32, 31, 30, 29, 28, 37};
        for (int i : redglas) {
            inventory.setItem(i, new ItemBuilder(Material.RED_STAINED_GLASS_PANE).name(Component.text("§cLevel: " + glassLevel)).lore(List.of(Component.text("§cDu erhältst " + glassLevel * 4 + "% mehr Drops!"))).build());
            if (skills.getLevel() >= glassLevel) {
                inventory.setItem(i, new ItemBuilder(Material.GREEN_STAINED_GLASS_PANE).name(Component.text("§aLevel: " + glassLevel)).lore(List.of(Component.text("§aDu erhältst " + glassLevel * 4 + "% mehr Drops!"))).build());
            }
            glassLevel++;
        }
    }
}
