package com.squidlard.kits.Events;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

/**
 * Created by Dalton on 4/17/2017.
 */
public class dropEvent implements Listener{

    @EventHandler
    public void on(PlayerDropItemEvent e) {
        Player p = e.getPlayer();
        if(p.getGameMode() != GameMode.CREATIVE) {
            e.setCancelled(true);
        }
    }

}
