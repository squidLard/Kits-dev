package com.squidlard.kits.Events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;

/**
 * Created by Dalton on 4/17/2017.
 */
public class weatherEvent implements Listener{

    @EventHandler
    public void on(WeatherChangeEvent e) {

        e.setCancelled(true);

    }

}
