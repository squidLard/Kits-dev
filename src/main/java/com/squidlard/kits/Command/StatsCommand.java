package com.squidlard.kits.Command;

import com.squidlard.kits.Core;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import static com.squidlard.kits.Core.econ;

public class StatsCommand implements CommandExecutor, Listener{

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        Player p = (Player) sender;

        String balance = econ.format(econ.getBalance(p.getName())).replace("$", "");
        double newBalance = Double.parseDouble(balance);
        int finalBalance = (int) newBalance;

        if (args.length < 1) {
            int kills = Core.getInstance().getConfig().getInt("Stats." + p.getUniqueId() + ".Kills");
            int deaths = Core.getInstance().getConfig().getInt("Stats." + p.getUniqueId() + ".Deaths");
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7&m------------------------"));
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cPlayer: " + "&7" + p.getName()));
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7&m------------------------"));
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cHealth: " + "&7" + p.getHealth()));
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cBalance: " + "&7$" + finalBalance));
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cKills: " + "&7" + kills));
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cDeaths: " + "&7" + deaths));
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7&m------------------------"));
            return true;
        }

        Player t = Bukkit.getServer().getPlayer(args[0]);

        if (t == null) {
            p.sendMessage(ChatColor.RED + "Could not find player.");
            return true;
        }
        if (args.length == 1) {
            int kills = Core.getInstance().getConfig().getInt("Stats." + t.getUniqueId() + ".Kills");
            int deaths = Core.getInstance().getConfig().getInt("Stats." + t.getUniqueId() + ".Deaths");
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7&m------------------------"));
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cPlayer: " + "&7" + t.getName()));
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7&m------------------------"));
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cHealth: " + "&7" + t.getHealth()));
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cBalance: " + "&7$" + finalBalance));
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cKills: " + "&7" + kills));
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cDeaths: " + "&7" + deaths));
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7&m------------------------"));
            return true;
        }

        return false;
    }

}
