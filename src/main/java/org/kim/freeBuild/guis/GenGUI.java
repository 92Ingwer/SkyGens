package org.kim.freeBuild.guis;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.kim.freeBuild.enums.MineralsEnum;
import org.kim.freeBuild.methods.GenMethods;
import org.kim.freeBuild.objects.GenerationBaseObject;
import org.kim.freeBuild.objects.PlayerBaseObject;
import org.kim.freeBuild.utils.InventoryBuilder;

import java.util.List;

public class GenGUI implements Listener {
    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        GenerationBaseObject generationBaseObject = GenerationBaseObject.generationBaseObjectMap.get(p);
        PlayerBaseObject playerBaseObject = PlayerBaseObject.playerBaseObjectMap.get(p);
        if (e.getView().title().equals(Component.text("Gen - Menü"))) {
            e.setCancelled(true);
            if (e.getSlot() == 42) {
                GenMethods.breakGen(p);
                p.closeInventory();
                return;
            }
            if(e.getSlot() == 15) {
                p.closeInventory();
                EnergyGenGUI.openInventory(p);
                return;
            }
            if(e.getSlot() == 11) {
                upgradeUpgrade(generationBaseObject,playerBaseObject,p);
                return;
            }
            if(e.getSlot() == 22) {
                upgradeLevel(generationBaseObject,playerBaseObject,p);
                return;
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
                .aItem(11, Material.EMERALD, m("<gradient:#38FF4E:#038521>Gen - Upgrade</gradient>"), List.of(upgradeUnder10(generationBaseObject), m("<gradient:#38FF4E:#038521>Preis: " + priceUpgrade + "$</gradient>")))
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
    private static Component upgradeUnder10(GenerationBaseObject generationBaseObject) {
        int upgrade = generationBaseObject.getUpgrade();
        if(upgrade == 10) {
            return MiniMessage.miniMessage().deserialize("<gradient:#38FF4E:#038521>Max. Upgrade</gradient>");
        } else {
            return MiniMessage.miniMessage().deserialize("<gradient:#38FF4E:#038521>Upgrade: " + upgrade + " --> " + (upgrade + 1) + " </gradient>");
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

    private static void upgradeUpgrade(GenerationBaseObject generationBaseObject, PlayerBaseObject playerBaseObject, Player p) {
        int bankmoney = playerBaseObject.getBankmoney();
        int upgrade = generationBaseObject.getUpgrade();
        int level = generationBaseObject.getLevel();
        int priceUpgrade = (int) (200 * (Math.pow(level, 3)) * (1 + 0.15 * upgrade));
        if(upgrade == 10) {
            p.sendMessage("Hast schon max Level!");
            return;
        }
        if(bankmoney > priceUpgrade) {
            playerBaseObject.setBankmoney(bankmoney - priceUpgrade);
            generationBaseObject.setUpgrade(upgrade+1);
            p.sendMessage("Erfolgreichs Upgrade!");
            PlayerBaseObject.playerBaseObjectMap.put(p,playerBaseObject);
            GenerationBaseObject.generationBaseObjectMap.put(p,generationBaseObject);
            p.closeInventory();
        } else {
            p.sendMessage("Nicht genug Geld!");
        }
    }
    private static void upgradeLevel(GenerationBaseObject generationBaseObject, PlayerBaseObject playerBaseObject, Player p) {
        Location loc = new Location(Bukkit.getWorld("InselWelt"),generationBaseObject.getX(),generationBaseObject.getY(),generationBaseObject.getZ());
        Block block = loc.getBlock();
        int bankmoney = playerBaseObject.getBankmoney();
        int upgrade = generationBaseObject.getUpgrade();
        int level = generationBaseObject.getLevel();
        int priceLevel = (int) (200 * (Math.pow(level + 1, 4)));
        if(bankmoney < priceLevel) {
            p.sendMessage("Hast nicht genug Geld");
            return;
        }
        if(upgrade == 10 && level < 19) {
            playerBaseObject.setBankmoney(bankmoney - priceLevel);
            generationBaseObject.setLevel(level+1);
            generationBaseObject.setUpgrade(0);
            p.sendMessage("Erfolgreichs Level!");
            PlayerBaseObject.playerBaseObjectMap.put(p,playerBaseObject);
            GenerationBaseObject.generationBaseObjectMap.put(p,generationBaseObject);
            block.setType(MineralsEnum.getMaterial(level+1));
            GenMethods.createExplosion(block);
            p.closeInventory();
        } else {
            p.sendMessage("Nicht genug Geld!");
        }
    }
}
