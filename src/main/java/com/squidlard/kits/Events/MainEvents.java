package com.squidlard.kits.Events;

import com.squidlard.kits.Core;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class MainEvents implements Listener{

    @EventHandler
    public void onFirstJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        if (!(p.hasPlayedBefore())) {
            Core.getInstance().getConfig().addDefault("Stats." + p.getUniqueId() + ".Deaths", Integer.valueOf(0));
            Core.getInstance().getConfig().addDefault("Stats." + p.getUniqueId() + ".Kills", Integer.valueOf(0));
            Core.getInstance().saveConfig();
        }
    }

    @EventHandler
    public void on(EntityDamageEvent e) {
        if (e.getEntity() instanceof Player) {
            if (!(e.getCause() == EntityDamageEvent.DamageCause.FALL)) {
                return;
            }
        }
    }

}
