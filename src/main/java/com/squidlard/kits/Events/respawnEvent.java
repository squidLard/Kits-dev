package com.squidlard.kits.Events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

/**
 * Created by Dalton on 4/17/2017.
 */
public class respawnEvent implements Listener {

    @EventHandler
    public void on(PlayerRespawnEvent e) {
        Player p = e.getPlayer();
        p.setLevel(0);
        p.teleport(p.getWorld().getSpawnLocation());
    }

}
