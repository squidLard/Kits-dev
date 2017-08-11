package com.squidlard.kits.Events;

import com.squidlard.kits.Core;
import com.squidlard.kits.Signs.SignEvents;
import com.squidlard.kits.TimeUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.UUID;

/**
 * Created by Dalton on 4/17/2017.
 */
public class joinLeaveEvent implements Listener{

    public void setCooldown(Player player, long value)
    {
        SignEvents.cdtime.put(player.getUniqueId(), Long.valueOf(System.currentTimeMillis() + value));
    }

    public boolean isCooldownActive(Player player) {
        if (!SignEvents.cdtime.containsKey(player.getUniqueId())) {
            return false;
        }
        return ((Long)SignEvents.cdtime.get(player.getUniqueId())).longValue() > System.currentTimeMillis();
    }

    public long getMillisecondLeft(Player player) {
        if (!isCooldownActive(player)) {
            return -1L;
        }
        return ((Long)SignEvents.cdtime.get(player.getUniqueId())).longValue() - System.currentTimeMillis();
    }

    public void removeCooldown(Player player)
    {
        if (isCooldownActive(player)) {
            SignEvents.cdtime.remove(player.getUniqueId());
        }
    }

    private Core plugin = Core.getPlugin(Core.class);

    @EventHandler
    public void on(PlayerJoinEvent e) {
        e.setJoinMessage(null);
        Player player = e.getPlayer();
        UUID uuid = player.getUniqueId();
        int cdtime = plugin.getConfig().getInt(uuid + ".CoolDown_Left");
        if (cdtime == 0) {
            return;
        } else {
            setCooldown(player, cdtime);
        }
    }


    @EventHandler
    public void on(PlayerQuitEvent e) {
        e.setQuitMessage(null);
        Player p = e.getPlayer();
        p.getInventory().clear();
        Player player = e.getPlayer();
        UUID uuid = player.getUniqueId();
        plugin.getConfig().set(uuid + ".CoolDown_Left", getMillisecondLeft(player));
        plugin.saveConfig();
        removeCooldown(player);
    }

}
