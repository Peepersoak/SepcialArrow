package com.peepersoak.specialarrows.utils;

import com.peepersoak.specialarrows.GeneralEvents;
import com.peepersoak.specialarrows.arrows.ArrowEvent;
import com.peepersoak.specialarrows.SpecialArrows;
import org.bukkit.plugin.PluginManager;

public class EventHandlers {

    public EventHandlers(PluginManager pm, SpecialArrows instance) {
        pm.registerEvents(new ArrowEvent(), instance);
        pm.registerEvents(new GeneralEvents(), instance);
    }
}
