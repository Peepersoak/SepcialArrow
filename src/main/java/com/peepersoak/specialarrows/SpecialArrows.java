package com.peepersoak.specialarrows;

import com.peepersoak.specialarrows.commands.CommandCompleter;
import com.peepersoak.specialarrows.commands.MainCommand;
import com.peepersoak.specialarrows.crafting.ArrowCraftingrecipe;
import com.peepersoak.specialarrows.utils.EventHandlers;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class SpecialArrows extends JavaPlugin {

    private static SpecialArrows instance;

    @Override
    public void onEnable() {
        instance = this;
        new EventHandlers(Bukkit.getPluginManager(), this);
        Objects.requireNonNull(this.getCommand("specialarrow")).setExecutor(new MainCommand());
        Objects.requireNonNull(this.getCommand("specialarrow")).setTabCompleter(new CommandCompleter());
        new ArrowCraftingrecipe();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static SpecialArrows getInstance() {
        return instance;
    }
}
