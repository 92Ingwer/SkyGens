package org.kim.SkyGens.skills;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.Ageable;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.kim.SkyGens.enums.SkillMineralsEnum;
import org.kim.SkyGens.services.PlayerService;

public class BreakBlockListener implements Listener {
    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Block block = event.getBlock();
        Player p = event.getPlayer();
        Material material = block.getType();
        //Farming
        if (SkillMineralsEnum.isItemFarming(material)) {
                if(SkillMineralsEnum.isAgeable(material) && !(block.getBlockData() instanceof Ageable ageable && ageable.getAge() == ageable.getMaximumAge())) {
                    return;
                }
                double xp = SkillMineralsEnum.getXP(material);
                FarmingSkill farmingSkill = FarmingSkill.farmingSkillMap.get(p.getUniqueId());
                farmingSkill.applyXP(xp);
        }
    }
}
