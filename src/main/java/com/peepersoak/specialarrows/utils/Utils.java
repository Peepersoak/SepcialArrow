package com.peepersoak.specialarrows.utils;

import com.peepersoak.specialarrows.SpecialArrows;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;

public class Utils {

    public static String color(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public static NamespacedKey getKey(String key) {
        return new NamespacedKey(SpecialArrows.getInstance(), key);
    }
}
