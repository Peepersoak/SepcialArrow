package com.peepersoak.specialarrows;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class GeneralEvents implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        e.getPlayer().setResourcePack("https://www.dropbox.com/s/pbzngsii86yzlkv/CustomArrows_RP_1.zip?dl=1");
    }
}
