package com.squidlard.kits.Signs;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

/**
 * Created by Dalton on 4/17/2017.
 */
public class IronSign implements Listener{

    @EventHandler
    public void onSignChange(SignChangeEvent e) {
        if (e.getLine(0).equalsIgnoreCase("[KitIron]")) {
            e.setLine(0, "§8§m-----------------");
            e.setLine(1, "§7Kit");
            e.setLine(2, "§8Iron");
            e.setLine(3, "§8§m-----------------");
        }
    }

}
