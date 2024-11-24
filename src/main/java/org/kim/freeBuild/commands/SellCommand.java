package org.kim.freeBuild.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.kim.freeBuild.enums.GenItemEnum;
import org.kim.freeBuild.objects.PlayerBaseObject;

public class SellCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(!(commandSender instanceof Player)) {
            return false;
        }
        Player p = (Player) commandSender;
        if(strings.length != 1) {
            p.sendMessage("§cUsage: Verwende /sell (hand/all)");
            return false;
        }
        PlayerBaseObject playerBaseObject = PlayerBaseObject.playerBaseObjectMap.get(p);
        int bankmoney = playerBaseObject.getBankmoney();
        int value = 0;
        if(strings[0].equalsIgnoreCase("hand")) {
            ItemStack item = p.getInventory().getItemInMainHand();
            int amount = item.getAmount();
            item.setAmount(1);
            for(int i = 1; i < 19;i++) {
                if(item.equals(GenItemEnum.getItem(i))) {
                    value = value+ GenItemEnum.getPrice(i)*amount;
                    playerBaseObject.setBankmoney(bankmoney + (value));
                    p.getInventory().setItemInMainHand(null);
                    PlayerBaseObject.playerBaseObjectMap.put(p, playerBaseObject);
                }
            }
            item.setAmount(amount);
            return true;
        }else if(strings[0].equalsIgnoreCase("all")) {
            for(int i = 0; p.getInventory().getSize() > i;i++) {
                ItemStack item = p.getInventory().getItem(i);
                if(item != null) {
                    int amount = item.getAmount();
                    item.setAmount(1);
                    for (int ii = 1; ii < 19; ii++) {
                        if (item.equals(GenItemEnum.getItem(ii))) {
                            value =value + GenItemEnum.getPrice(ii) * amount;
                            playerBaseObject.setBankmoney(bankmoney + (value));
                            PlayerBaseObject.playerBaseObjectMap.put(p, playerBaseObject);
                            p.getInventory().setItem(i,null);
                        }
                    }
                    item.setAmount(amount);
                }
            }
            return true;
        } else {
            p.sendMessage("§cUsage: Verwende /sell (hand/all)");
        }
        return false;
    }
}
