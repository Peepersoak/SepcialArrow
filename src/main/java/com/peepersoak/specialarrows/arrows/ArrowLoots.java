package com.peepersoak.specialarrows.arrows;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class ArrowLoots {

    public ArrowLoots() {
        materials.add(Material.DIAMOND);
        materials.add(Material.ENCHANTED_GOLDEN_APPLE);
        materials.add(Material.NETHERITE_SCRAP);
        materials.add(Material.NETHERITE_INGOT);
        materials.add(Material.IRON_INGOT);
        materials.add(Material.GOLD_INGOT);
        materials.add(Material.EMERALD);
        materials.add(Material.ARROW);
        materials.add(Material.GOLDEN_APPLE);
    }

    private final List<Material> materials = new ArrayList<>();

    public ItemStack getRandomLoot() {
        Collections.shuffle(materials);
        Random rand = new Random();
        int count = rand.nextInt(64) + 1;
        return new ItemStack(materials.get(0), count);
    }
}
