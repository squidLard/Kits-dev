package com.squidlard.kits.Events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

/**
 * Created by Dalton on 4/17/2017.
 */
public class mobSpawnEvent implements Listener{

    @EventHandler
    public void on(CreatureSpawnEvent e) {
        e.setCancelled(true);
    }
}
