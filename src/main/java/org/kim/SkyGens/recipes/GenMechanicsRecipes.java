package org.kim.SkyGens.recipes;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.kim.SkyGens.SkyGens;
import org.kim.SkyGens.enums.GenItemEnum;
import org.kim.SkyGens.items.GeneratorItems;

public class GenMechanicsRecipes {

    public static void ChestRecipe() {
        NamespacedKey key = new NamespacedKey(SkyGens.getInstance(), "chest_level_0");
        ItemStack item = GeneratorItems.getChest();
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape("ABA", "ACA", "AAA");
        recipe.setIngredient('A', Material.IRON_INGOT);
        recipe.setIngredient('B', new RecipeChoice.ExactChoice(GenItemEnum.getItem(2)));
        recipe.setIngredient('C', Material.CHEST);
        SkyGens.getInstance().getServer().addRecipe(recipe);
    }

    public static void DrillRecipe() {
        NamespacedKey key = new NamespacedKey(SkyGens.getInstance(), "drill_level_0");
        ItemStack item = GeneratorItems.getDrill();
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape("ABA","ACA","AAA");
        recipe.setIngredient('A',Material.COBBLESTONE);
        recipe.setIngredient('B',new RecipeChoice.ExactChoice(GenItemEnum.getItem(9)));
        recipe.setIngredient('C',Material.LAVA_BUCKET);
        SkyGens.getInstance().getServer().addRecipe(recipe);
    }
}
