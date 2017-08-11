package com.squidlard.kits.Events;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

/**
 * Created by Dalton on 4/17/2017.
 */
public class AntiBuildEvent implements Listener{

    @EventHandler
    public void on(BlockBreakEvent e) {
        Player p = e.getPlayer();
        if(p.getGameMode() != GameMode.CREATIVE) {
            e.setCancelled(true);
        }

    }

    @EventHandler
    public void on(BlockPlaceEvent e) {
        Player p = e.getPlayer();
        if (p.getGameMode() != GameMode.CREATIVE) {
            e.setCancelled(true);
        }
    }

}
