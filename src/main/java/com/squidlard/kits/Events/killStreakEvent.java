package com.squidlard.kits.Events;

import com.squidlard.kits.Core;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

/**
 * Created by Dalton on 4/17/2017.
 */
public class killStreakEvent implements Listener{

    @EventHandler
    public void on(PlayerDeathEvent e){
        Player p = (Player) e.getEntity().getKiller();
        if (e.getEntity() instanceof Player) {
            if (e.getEntity().getKiller() instanceof Player) {
                if (p.getLevel() == 5 || p.getLevel() == 10 || p.getLevel() == 15 || p.getLevel() == 20 || p.getLevel() == 25 || p.getLevel() == 30 || p.getLevel() == 35 || p.getLevel() == 40 || p.getLevel() == 45 || p.getLevel() == 50 || p.getLevel() == 55) {
                    Bukkit.broadcastMessage(Core.prefix + "§f" + p.getDisplayName() + "§3 is on a killstreak of §8[§6" + p.getLevel() + "§8]");
                }
            }
        }
    }

}
