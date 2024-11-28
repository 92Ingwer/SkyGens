package org.kim.freeBuild.recipes;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.kim.freeBuild.FreeBuild;
import org.kim.freeBuild.enums.GenItemEnum;
import org.kim.freeBuild.items.GeneratorItems;

public class GenMechanicsRecipes {

    public static void ChestRecipe() {
        NamespacedKey key = new NamespacedKey(FreeBuild.getInstance(), "chest_level_0");
        ItemStack item = GeneratorItems.getChest();
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape("ABA", "ACA", "AAA");
        recipe.setIngredient('A', Material.IRON_INGOT);
        recipe.setIngredient('B', new RecipeChoice.ExactChoice(GenItemEnum.getItem(2)));
        recipe.setIngredient('C', Material.CHEST);
        FreeBuild.getInstance().getServer().addRecipe(recipe);
    }
}
