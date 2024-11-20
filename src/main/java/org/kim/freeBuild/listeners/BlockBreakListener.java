package org.kim.freeBuild.listeners;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.kim.freeBuild.enums.MineralsEnum;
import org.kim.freeBuild.methods.BlockBreakMethods;
import org.kim.freeBuild.objects.BlockBreakObject;
import org.kim.freeBuild.services.BlockService;
import org.kim.freeBuild.utils.MiniMessageBuilder;

import java.util.Collection;

public class BlockBreakListener implements Listener {
    public String prefix = "<gradient:#FFC21E:#FFEE00>[BlockBreaker]</gradient>";
    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        Block b = e.getBlock();
        Player p = e.getPlayer();
        Location l = e.getBlock().getLocation();
        if (BlockBreakMethods.isBlockAnOre(b)) {
            e.setCancelled(true);
            if (BlockService.blockObjectHashMap.containsKey(l)) {
                BlockBreakObject bbo = BlockService.blockObjectHashMap.get(l);
                int heartsPre = bbo.getHearts();
                int heartsLeft = heartsPre - 1;
                bbo.setHearts(heartsLeft);
                if(heartsLeft <= 0) {
                    Collection<ItemStack> collection = b.getDrops();
                    int drops = (int) (Math.random()*3+1);
                    for (ItemStack item : collection) {
                        item.setAmount(item.getAmount()*drops-item.getAmount());
                        p.getWorld().dropItemNaturally(l, item);
                    }
                    b.breakNaturally();
                    BlockService.blockObjectHashMap.remove(l);
                    p.sendMessage(drops+"");
                }
            } else {
                BlockBreakObject bbo = new BlockBreakObject(b.getType(),l);
                BlockService.blockObjectHashMap.put(l, bbo);
            }
        }
    }
}
