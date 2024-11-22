package org.kim.freeBuild.guis;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.kim.freeBuild.methods.GenMethods;
import org.kim.freeBuild.objects.GenerationBaseObject;
import org.kim.freeBuild.utils.InventoryBuilder;

import java.util.List;

public class GenGUI implements Listener {
    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        if (e.getView().title().equals(Component.text("Gen - Menü"))) {
            e.setCancelled(true);
            if (e.getSlot() == 42) {
                GenMethods.breakGen(p);
                p.closeInventory();
            }
            if(e.getSlot() == 15) {
                p.closeInventory();
                EnergyGenGUI.openInventory(p);
            }
        }
    }

    public static void openInventory(Player p) {
        GenerationBaseObject generationBaseObject = GenerationBaseObject.generationBaseObjectMap.get(p);
        int upgrade = generationBaseObject.getUpgrade();
        int level = generationBaseObject.getLevel();
        double fuel = generationBaseObject.getFuel();
        int priceUpgrade = (int) (200 * (Math.pow(level, 3)) * (1 + 0.15 * upgrade));
        int priceLevel = (int) (200 * (Math.pow(level + 1, 4)));
        Inventory inventory = new InventoryBuilder("Gen - Menü", 6 * 9, 1)
                .aItem(11, Material.EMERALD, m("<gradient:#38FF4E:#038521>Gen - Upgrade</gradient>"), List.of(m("<gradient:#38FF4E:#038521>Upgrade: " + upgrade + " --> " + (upgrade + 1) + " </gradient>"), m("<gradient:#38FF4E:#038521>Preis: " + priceUpgrade + "$</gradient>")))
                .aItem(15, Material.FURNACE, m("<gradient:#4C4F4D:#C4C4C4>Energie</gradient>"), List.of(m("<gradient:#9BA99F:#C4C4C4>Energie: " + fuel + "J</gradient>"),m("<gradient:#9BA99F:#C4C4C4>Öffne das Energie-Menü</gradient>")))
                .aItem(22, Material.NETHER_STAR, m("<gradient:#FAE1FF:#CB90C5>Level - Upgrade</gradient>"), List.of(m("<gradient:#FAE1FF:#CB90C5>Level: " + level + " --> " + (level + 1) + "</gradient>"), m("<gradient:#FAE1FF:#CB90C5>Preis: " + priceLevel + "$</gradient>"), readyForUpgrade(generationBaseObject)))
                .aItem(38, Material.BEACON, m("<gradient:#8EEDFF:#7198FF>BOOSTER</gradient>"), List.of(m("<gradient:#8EEDFF:#7198FF>Öffne das Booster-Menü</gradient>")))
                .aItem(42, Material.BARRIER, m("<gradient:#FF8E8E:#FF71C2>Generatoren Zerstörung</gradient>"), List.of(m("<gradient:#FF8E8E:#FF71C2>Zerstöre deinen Generator</gradient>")))
                .build();
        setWhiteGlas(inventory);
        p.openInventory(inventory);
    }

    private static Component m(String text) {
        return MiniMessage.miniMessage().deserialize(text);
    }

    private static Component readyForUpgrade(GenerationBaseObject generationBaseObject) {
        if (generationBaseObject.getUpgrade() == 10) {
            return MiniMessage.miniMessage().deserialize(("<gradient:#3ACD5C:#1F8D1E>DU BIST BEREIT!</gradient>"));
        } else {
            return MiniMessage.miniMessage().deserialize(("<gradient:#CD3A3A:#CB2020>NOCH NICHT BEREIT!</gradient>"));
        }
    }

    private static void setWhiteGlas(Inventory inventory) {
        ItemStack item = new ItemStack(Material.WHITE_STAINED_GLASS_PANE);
        for (int i = 0; i < inventory.getSize(); i++) {
            if (inventory.getItem(i) == null) {
                inventory.setItem(i, item);
            }
        }
    }
}
