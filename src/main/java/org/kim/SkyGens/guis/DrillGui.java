package org.kim.SkyGens.guis;

import net.kyori.adventure.text.Component;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.kim.SkyGens.items.GeneratorItems;
import org.kim.SkyGens.objects.AutomaticDrillObject;
import org.kim.SkyGens.utils.InventoryBuilder;

public class DrillGui implements Listener {
    @EventHandler
    public void onRightClick(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        Action action = e.getAction();
        Block b = e.getClickedBlock();
        if(b == null) {
            return;
        }
        Location blockLocation = b.getLocation();
        AutomaticDrillObject automaticDrillObject = AutomaticDrillObject.automaticDrillObject.get(p.getUniqueId());
        Location drillLocation = automaticDrillObject.getLocation();
        if(action == Action.RIGHT_CLICK_BLOCK && blockLocation.equals(drillLocation)) {
            e.setCancelled(true);
            DrillGui.openInventory(p);
        }
    }
    @EventHandler
    public void onClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        if(e.getView().title().equals(Component.text("Automatic - Drill"))) {
            e.setCancelled(true);
            if(e.getSlot() == 8) {
                AutomaticDrillObject automaticDrillObject = AutomaticDrillObject.automaticDrillObject.get(p.getUniqueId());
                Location location = new Location(p.getWorld(), automaticDrillObject.getLocation().getX(), automaticDrillObject.getLocation().getY(), automaticDrillObject.getLocation().getZ());
                Block b = location.getBlock();
                b.setType(Material.AIR);
                p.getInventory().addItem(GeneratorItems.getDrill());
                AutomaticDrillObject automaticDrillObject1 = new AutomaticDrillObject(-1.0,-1.0,-1.0,null,true,0);
                AutomaticDrillObject.automaticDrillObject.put(p.getUniqueId(), automaticDrillObject1);
                p.closeInventory();
            }
        }
    }
    public static void openInventory(Player p) {
        Inventory inventory = new InventoryBuilder("Automatic - Drill",9,1)
                .aItem(8,Material.BEDROCK,Component.text("§cAbbauen!"),null).build();
        p.openInventory(inventory);
    }
}
