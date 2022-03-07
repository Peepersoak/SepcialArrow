package com.peepersoak.specialarrows.arrows;

import com.peepersoak.specialarrows.utils.StringPath;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class Arrows {

    public Arrows(Player player) {
        this.player = player;
        setArrow();
    }

    private final Player player;

    private ItemStack arrow = null;
    private Types types = null;

    private void setArrow() {
        ItemStack itemInOffHand = player.getInventory().getItemInOffHand();
        if (itemInOffHand.getType() != Material.ARROW) return;
        arrow = itemInOffHand;
        initializedArrow();
    }

    private void initializedArrow() {
        ItemMeta meta = arrow.getItemMeta();
        assert meta != null;
        PersistentDataContainer pdc = meta.getPersistentDataContainer();
        if (pdc.has(StringPath.EFFECT_KEY, PersistentDataType.INTEGER)) {
            Integer key = pdc.get(StringPath.EFFECT_KEY, PersistentDataType.INTEGER);
            if (key == null) return;
            switch (key) {
                case 0 -> types = Types.EXPLOSIVE;
                case 1 -> types = Types.LIGHTNING;
                case 2 -> types = Types.TELEPORT;
                case 3 -> types = Types.RIDE;
                case 4 -> types = Types.GRAVITY;
                case 5 -> types = Types.BUNDLE_OF;
                case 6 -> types = Types.DIAMOND;
                case 7 -> types = Types.END_CRYSTAL;
                case 8 -> types = Types.FISH;
                case 9 -> types = Types.INFINITY;
            }
        }
    }

    public Types getTypes(ItemStack arrow) {
        ItemMeta meta = arrow.getItemMeta();
        assert meta != null;
        PersistentDataContainer pdc = meta.getPersistentDataContainer();
        Types types = null;
        if (pdc.has(StringPath.EFFECT_KEY, PersistentDataType.INTEGER)) {
            Integer key = pdc.get(StringPath.EFFECT_KEY, PersistentDataType.INTEGER);
            if (key == null) return null;
            switch (key) {
                case 0 -> types = Types.EXPLOSIVE;
                case 1 -> types = Types.LIGHTNING;
                case 2 -> types = Types.TELEPORT;
                case 3 -> types = Types.RIDE;
                case 4 -> types = Types.GRAVITY;
                case 5 -> types = Types.BUNDLE_OF;
                case 6 -> types = Types.DIAMOND;
                case 7 -> types = Types.END_CRYSTAL;
                case 8 -> types = Types.FISH;
                case 9 -> types = Types.INFINITY;
            }
        }
        return types;
    }

    public Types getTypes() {
        return types;
    }

    public ItemStack getArrow() {
        return arrow;
    }
}
