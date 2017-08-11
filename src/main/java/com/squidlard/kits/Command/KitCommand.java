package com.squidlard.kits.Command;

import com.squidlard.kits.Kits.*;
import com.squidlard.kits.TimeUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static com.squidlard.kits.TimeUtils.LongCountdown.setFormat;

/**
 * Created by Dalton on 5/5/2017.
 */
public class KitCommand implements CommandExecutor{

    public static Map<UUID, Long> cdtime = new HashMap();

    public void setCooldown(Player player, long value)
    {
        this.cdtime.put(player.getUniqueId(), Long.valueOf(System.currentTimeMillis() + value));
    }

    public boolean isCooldownActive(Player player) {
        if (!this.cdtime.containsKey(player.getUniqueId())) {
            return false;
        }
        return ((Long)this.cdtime.get(player.getUniqueId())).longValue() > System.currentTimeMillis();
    }

    public long getMillisecondLeft(Player player) {
        if (!isCooldownActive(player)) {
            return -1L;
        }
        return ((Long)this.cdtime.get(player.getUniqueId())).longValue() - System.currentTimeMillis();
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        Player p = (Player) sender;
        if (isCooldownActive(p)) {
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou are on cooldown for " + setFormat(getMillisecondLeft(p)) + "&c."));
            return true;
        }
        if (args.length == 1) {
            if (p.hasPermission("kits.use")) {
                if (args[0].equalsIgnoreCase("archer")) {
                    setCooldown(p, TimeUtils.parse("5s"));
                    for (PotionEffect effect : p.getActivePotionEffects())
                        p.removePotionEffect(effect.getType());
                    p.getInventory().clear();
                    p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 2));
                    p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, Integer.MAX_VALUE, 1));
                    ArcherKit.giveItems(p);
                    p.sendMessage(ChatColor.RED + "Archer Kit recieved.");
                    return true;
                }
                if (args[0].equalsIgnoreCase("gold")) {
                    setCooldown(p, TimeUtils.parse("5s"));
                    for (PotionEffect effect : p.getActivePotionEffects())
                        p.removePotionEffect(effect.getType());
                    p.getInventory().clear();
                    p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 1));
                    p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, Integer.MAX_VALUE, 0));
                    p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, Integer.MAX_VALUE, 1));
                    GoldKit.giveItems(p);
                    p.sendMessage(ChatColor.RED + "Gold Kit Recieved.");
                    return true;
                }
                if (args[0].equalsIgnoreCase("Diamond")) {
                    setCooldown(p, TimeUtils.parse("5s"));
                    p.getInventory().clear();
                    for (PotionEffect effect : p.getActivePotionEffects())
                        p.removePotionEffect(effect.getType());
                    p.getInventory().clear();
                    DiamondKit.giveItems(p);
                    p.sendMessage(ChatColor.RED + "Diamond Kit Recieved.");
                    return true;
                }
                if (args[0].equalsIgnoreCase("Iron")) {
                    setCooldown(p, TimeUtils.parse("5s"));
                    p.getInventory().clear();
                    for (PotionEffect effect : p.getActivePotionEffects())
                        p.removePotionEffect(effect.getType());
                    IronKit.giveItems(p);
                    p.sendMessage(ChatColor.RED + "Iron Kit Recieved.");
                    return true;
                }
                if (args[0].equalsIgnoreCase("Builder")) {
                    setCooldown(p, TimeUtils.parse("5s"));
                    p.getInventory().clear();
                    for (PotionEffect effect : p.getActivePotionEffects())
                        p.removePotionEffect(effect.getType());
                    p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 0));
                    p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, Integer.MAX_VALUE, 0));
                    p.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, Integer.MAX_VALUE, 0));
                    p.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, Integer.MAX_VALUE, 1));
                    p.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, Integer.MAX_VALUE, 0));
                    p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, Integer.MAX_VALUE, 0));
                    KitBuilder.giveItems(p);
                    p.sendMessage(ChatColor.RED + "Builder Kit Recieved");
                    return true;
                }
            } else {
                p.sendMessage(ChatColor.RED + "No Permissions.");
            }
        } else {
            p.sendMessage(ChatColor.GREEN + "Kits: " + ChatColor.GRAY + "Diamond Archer Gold Iron Builder");
        }
        return false;
    }

}
