package org.kim.freeBuild.methods;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.kim.freeBuild.enums.MineralsEnum;

public class BlockBreakMethods {
    public static boolean isBlockAnOre(Block block) {
        Material material = block.getType();
        for(MineralsEnum mineralsEnum : MineralsEnum.values()) {
            if(mineralsEnum.getMaterial() == material) {
                return true;
            }
        }

        return false;
    }
}
