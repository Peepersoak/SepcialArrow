package com.peepersoak.specialarrows.crafting;

import com.peepersoak.specialarrows.arrows.ArrowFactory;
import com.peepersoak.specialarrows.arrows.Types;
import com.peepersoak.specialarrows.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;

public class ArrowCraftingrecipe {

    public ArrowCraftingrecipe() {
        bundleOfArrow();
        diamondArrow();
        endCrystalArrow();
        fishArrow();
        explosiveArrow();
        lightingArrow();
        teleportArrow();
        rideArrow();
        gravityArrow();
        infinityArrow();
    }

    private final ArrowFactory arrowFactory = new ArrowFactory();

    private void bundleOfArrow() {
        ItemStack bundleOfArrow = arrowFactory.createArrow(Types.BUNDLE_OF, 32);

        ShapedRecipe recipe = new ShapedRecipe(Utils.getKey("BundleOfArrow"), bundleOfArrow);
        recipe.shape("xxx","xxx","xxx");
        recipe.setIngredient('x', Material.ARROW);

        Bukkit.addRecipe(recipe);
    }

    private void diamondArrow() {
        ItemStack diamondArrow = arrowFactory.createArrow(Types.DIAMOND, 16);
        ItemStack bundleOfArrow = arrowFactory.createArrow(Types.BUNDLE_OF, 1);

        ShapedRecipe recipe = new ShapedRecipe(Utils.getKey("DiamondArrow"), diamondArrow);
        recipe.shape("xxx","xzx","xxx");
        recipe.setIngredient('x', Material.DIAMOND);
        recipe.setIngredient('z', new RecipeChoice.ExactChoice(bundleOfArrow));

        Bukkit.addRecipe(recipe);
    }

    private void endCrystalArrow() {
        ItemStack endCrystalArrow = arrowFactory.createArrow(Types.END_CRYSTAL, 16);

        ShapelessRecipe recipe = new ShapelessRecipe(Utils.getKey("EndCrystalArrow"), endCrystalArrow);
        recipe.addIngredient(Material.ARROW);
        recipe.addIngredient(Material.END_CRYSTAL);

        Bukkit.addRecipe(recipe);
    }

    private void fishArrow() {
        ItemStack fishArrow = arrowFactory.createArrow(Types.FISH, 32);

        ShapedRecipe recipe = new ShapedRecipe(Utils.getKey("FishArrow"), fishArrow);
        recipe.shape("xyx","yzy","xyx");

        recipe.setIngredient('x', Material.SALMON);
        recipe.setIngredient('y', Material.COD);
        recipe.setIngredient('z', Material.ARROW);

        Bukkit.addRecipe(recipe);
    }

    private void explosiveArrow() {
        ItemStack explosiveArrow = arrowFactory.createArrow(Types.EXPLOSIVE, 16);

        ShapedRecipe recipe = new ShapedRecipe(Utils.getKey("ExplosiveArrow"), explosiveArrow);
        recipe.shape("xxx","xyx","xxx");

        recipe.setIngredient('x', Material.TNT);
        recipe.setIngredient('y', Material.ARROW);

        Bukkit.addRecipe(recipe);
    }

    private void lightingArrow() {
        ItemStack lightningArrow = arrowFactory.createArrow(Types.LIGHTNING, 8);

        ShapedRecipe recipe = new ShapedRecipe(Utils.getKey("LightningArrow"), lightningArrow);
        recipe.shape("xzx","xyx","xzx");

        recipe.setIngredient('z', Material.LIGHTNING_ROD);
        recipe.setIngredient('y', Material.ARROW);

        Bukkit.addRecipe(recipe);
    }

    private void teleportArrow() {
        ItemStack teleportArrow = arrowFactory.createArrow(Types.TELEPORT, 32);

        ShapedRecipe recipe = new ShapedRecipe(Utils.getKey("TeleportArrow"), teleportArrow);
        recipe.shape("xxx","xyx","xxx");

        recipe.setIngredient('x', Material.ENDER_PEARL);
        recipe.setIngredient('y', Material.ARROW);

        Bukkit.addRecipe(recipe);
    }

    private void rideArrow() {
        ItemStack rideArrow = arrowFactory.createArrow(Types.RIDE, 16);

        ShapelessRecipe recipe = new ShapelessRecipe(Utils.getKey("RideArrow"), rideArrow);
        recipe.addIngredient(Material.SADDLE);
        recipe.addIngredient(Material.ARROW);

        Bukkit.addRecipe(recipe);
    }

    private void gravityArrow() {
        ItemStack gravityArrow = arrowFactory.createArrow(Types.GRAVITY, 12);

        ShapedRecipe recipe = new ShapedRecipe(Utils.getKey("GravityArrow"), gravityArrow);
        recipe.shape("xxx","xyx","xxx");

        recipe.setIngredient('x', Material.NETHERITE_INGOT);
        recipe.setIngredient('y', Material.ARROW);

        Bukkit.addRecipe(recipe);
    }

    private void infinityArrow() {
        ItemStack infinityArrow = arrowFactory.createArrow(Types.INFINITY, 8);

        ShapedRecipe recipe = new ShapedRecipe(Utils.getKey("InfinityArrow"), infinityArrow);
        recipe.shape("abc","def","ghi");

        int count = 1;

        for (Types types : Types.values()) {
            ItemStack i = arrowFactory.createArrow(types, 1);
            switch (count) {
                case 1 -> recipe.setIngredient('a', new RecipeChoice.ExactChoice(i));
                case 2 -> recipe.setIngredient('b', new RecipeChoice.ExactChoice(i));
                case 3 -> recipe.setIngredient('c', new RecipeChoice.ExactChoice(i));
                case 4 -> recipe.setIngredient('d', new RecipeChoice.ExactChoice(i));
                case 5 -> recipe.setIngredient('f', new RecipeChoice.ExactChoice(i));
                case 6 -> recipe.setIngredient('g', new RecipeChoice.ExactChoice(i));
                case 7 -> recipe.setIngredient('h', new RecipeChoice.ExactChoice(i));
                case 8 -> recipe.setIngredient('i', new RecipeChoice.ExactChoice(i));
            }
            count++;
            if (count > 8) break;
        }
        recipe.setIngredient('e', Material.DIAMOND_BLOCK);

        Bukkit.addRecipe(recipe);
    }
}
