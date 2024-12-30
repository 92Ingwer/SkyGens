package org.kim.SkyGens.commands;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.kim.SkyGens.enums.GenItemEnum;
import org.kim.SkyGens.items.GeneratorItems;

import java.util.ArrayList;
import java.util.List;

public class GiveItemsCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (!(commandSender instanceof Player p)) {
            return false;
        }
        if (args.length != 3) {
            p.sendMessage("§cUsage: Verwende /giveitems <type> <level> <Amount> ");
            return false;
        }
        List<String> usage = new ArrayList<>();
        usage.add("genitems");
        usage.add("automaticchest");
        usage.add("drill");
        if (!usage.contains(args[0].toLowerCase())) {
            p.sendMessage("§cUsage: Es gibt: genitems, automaticchest & drill");
            return false;
        }
        int level;
        int amount;
        try {
            level = Integer.parseInt(args[1]);
            amount = Integer.parseInt(args[2]);
        } catch (NumberFormatException e) {
            p.sendMessage("§cUsage: Falscher Zahlenformat!");
            return false;
        }
        ItemStack item = new ItemStack(Material.BARRIER);
        if (args[0].equalsIgnoreCase("genitems")) {
            item = GenItemEnum.getItem(level);
        } else if (args[0].equalsIgnoreCase("automaticchest")) {
            item = GeneratorItems.getChest();
        } else if (args[0].equalsIgnoreCase("drill")) {
            item = GeneratorItems.getDrill();
        }
        item.setAmount(amount);
        p.getInventory().addItem(item);
        return false;
    }
}
