package com.peepersoak.specialarrows.arrows;

import com.peepersoak.specialarrows.utils.StringPath;
import com.peepersoak.specialarrows.utils.Utils;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.List;

public class ArrowFactory {

    public ItemStack createArrow(Types types) {
        ItemStack arrow = new ItemStack(Material.ARROW);
        arrow.addUnsafeEnchantment(Enchantment.DURABILITY, 0);
        ItemMeta meta = arrow.getItemMeta();
        assert meta != null;
        PersistentDataContainer pdc = meta.getPersistentDataContainer();

        int key;

        switch (types) {
            case EXPLOSIVE -> {
                key = 0;
                meta.setCustomModelData(800);
            }
            case LIGHTNING -> {
                key = 1;
                meta.setCustomModelData(1000);
            }
            case TELEPORT -> {
                key = 2;
                meta.setCustomModelData(300);
            }
            case RIDE -> {
                key = 3;
                meta.setCustomModelData(1100);
            }
            case GRAVITY -> {
                key = 4;
                meta.setCustomModelData(1200);
            }
            case BUNDLE_OF -> {
                key = 5;
                meta.setCustomModelData(100);
            }
            case DIAMOND -> {
                key = 6;
                meta.setCustomModelData(200);
            }
            case END_CRYSTAL -> {
                key = 7;
                meta.setCustomModelData(400);
            }
            case FISH -> {
                key = 8;
                meta.setCustomModelData(500);
            }
            case INFINITY -> {
                key = 9;
                meta.setCustomModelData(600);
            }
            default -> key = -1;
        }

        if (key == -1) return null;

        pdc.set(StringPath.EFFECT_KEY, PersistentDataType.INTEGER, key);
        meta.setDisplayName(Utils.color("&e" + types.name().replace("_", " ") + " ARROW"));
        meta.setLore(getLores(types));
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        arrow.setItemMeta(meta);
        return arrow;
    }

    public ItemStack createArrow(Types types, int ammount) {
        ItemStack arrow = new ItemStack(Material.ARROW);
        arrow.addUnsafeEnchantment(Enchantment.DURABILITY, 0);
        ItemMeta meta = arrow.getItemMeta();
        assert meta != null;
        PersistentDataContainer pdc = meta.getPersistentDataContainer();

        int key;

        switch (types) {
            case EXPLOSIVE -> {
                key = 0;
                meta.setCustomModelData(800);
            }
            case LIGHTNING -> {
                key = 1;
                meta.setCustomModelData(1000);
            }
            case TELEPORT -> {
                key = 2;
                meta.setCustomModelData(300);
            }
            case RIDE -> {
                key = 3;
                meta.setCustomModelData(1100);
            }
            case GRAVITY -> {
                key = 4;
                meta.setCustomModelData(1200);
            }
            case BUNDLE_OF -> {
                key = 5;
                meta.setCustomModelData(100);
            }
            case DIAMOND -> {
                key = 6;
                meta.setCustomModelData(200);
            }
            case END_CRYSTAL -> {
                key = 7;
                meta.setCustomModelData(400);
            }
            case FISH -> {
                key = 8;
                meta.setCustomModelData(500);
            }
            case INFINITY -> {
                key = 9;
                meta.setCustomModelData(600);
            }
            default -> key = -1;
        }

        if (key == -1) return null;

        pdc.set(StringPath.EFFECT_KEY, PersistentDataType.INTEGER, key);
        meta.setDisplayName(Utils.color("&e" + types.name().replace("_", " ") + " ARROW"));
        meta.setLore(getLores(types));
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        arrow.setItemMeta(meta);
        arrow.setAmount(ammount);
        return arrow;
    }

    private List<String> getLores(Types types) {
        List<String> lores = new ArrayList<>();
        switch (types) {
            case EXPLOSIVE -> {
                lores.add(Utils.color("&6This arrow will make"));
                lores.add(Utils.color("&6the hit entity explode"));
            }
            case LIGHTNING -> {
                lores.add(Utils.color("&6A powerful lightning"));
                lores.add(Utils.color("&6will strike on the entities"));
                lores.add(Utils.color("&6location."));
            }
            case TELEPORT -> {
                lores.add(Utils.color("&6Teleport yourself on the"));
                lores.add(Utils.color("&6target location. Will only"));
                lores.add(Utils.color("&6work if you hit a valid"));
                lores.add(Utils.color("&6entity"));
            }
            case RIDE -> {
                lores.add(Utils.color("&6You will fly and ride"));
                lores.add(Utils.color("&6the arrow that you shot"));
            }
            case GRAVITY -> {
                lores.add(Utils.color("&6Pull every living mobs"));
                lores.add(Utils.color("&6on a big radius"));
            }
            case BUNDLE_OF -> lores.add(Utils.color("&6Shoot 5 arrows at once"));
            case DIAMOND -> {
                lores.add(Utils.color("&6When this arrow killed an"));
                lores.add(Utils.color("&6entity, they will drop a"));
                lores.add(Utils.color("&6random loot"));
            }
            case END_CRYSTAL -> {
                lores.add(Utils.color("&6Shoot a very powerful"));
                lores.add(Utils.color("&6projectile that won't be"));
                lores.add(Utils.color("&6affected by gravity"));
            }
            case FISH -> {
                lores.add(Utils.color("&6Convert the hit entity"));
                lores.add(Utils.color("&6to a random fish"));
            }
            case INFINITY -> {
                lores.add(Utils.color("&6After a certain delay"));
                lores.add(Utils.color("&6the arrow will return to you"));
            }
        }
        return lores;
    }
}
