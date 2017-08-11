package com.squidlard.kits.Signs;

import com.squidlard.kits.Core;
import com.squidlard.kits.Kits.*;
import com.squidlard.kits.TimeUtils;
import org.bukkit.ChatColor;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static com.squidlard.kits.Core.prefix2;
import static com.squidlard.kits.TimeUtils.LongCountdown.setFormat;

/**
 * Created by Dalton on 4/17/2017.
 */
public class SignEvents implements Listener{

    private Core plugin = Core.getPlugin(Core.class);

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

    @EventHandler
    public void onPlayerInteract (PlayerInteractEvent e) {
        Player p = e.getPlayer();
        if (!(e.getAction() == Action.RIGHT_CLICK_BLOCK)) return;
        if (e.getClickedBlock().getState() instanceof Sign) {
            Sign s = (Sign) e.getClickedBlock().getState();
            if (!isCooldownActive(p)) {
                if (s.getLine(2).equalsIgnoreCase("§bDiamond")) {
                    for (PotionEffect effect : p.getActivePotionEffects())
                        p.removePotionEffect(effect.getType());
                    p.getInventory().clear();
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix2 + "&aDiamond Kit has been equiped."));
                    DiamondKit.giveItems(p);
                    setCooldown(p, TimeUtils.parse("10s"));
                    return;
                }
            } else if (s.getLine(2).equalsIgnoreCase("§bDiamond")) {
                    e.setCancelled(true);
                    p.sendMessage(ChatColor.RED + "You are on cooldown for " + setFormat(getMillisecondLeft(p)) + ".");
                    return;
                }
            if (!isCooldownActive(p)) {
                if (s.getLine(2).equalsIgnoreCase("§8Iron")) {
                    for (PotionEffect effect : p.getActivePotionEffects())
                        p.removePotionEffect(effect.getType());
                    p.getInventory().clear();
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix2 + "&aIron Kit has been equiped."));
                    IronKit.giveItems(p);
                    setCooldown(p, TimeUtils.parse("10s"));
                    return;
                }
            } else if (s.getLine(2).equalsIgnoreCase("§8Iron")) {
                e.setCancelled(true);
                p.sendMessage(ChatColor.RED + "You are on cooldown for "+ setFormat(getMillisecondLeft(p)) + ".");
                return;
            }
            if (!isCooldownActive(p)) {
                if (s.getLine(2).equalsIgnoreCase("§eGold")) {
                    for (PotionEffect effect : p.getActivePotionEffects())
                        p.removePotionEffect(effect.getType());
                    p.getInventory().clear();
                    p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 1));
                    p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, Integer.MAX_VALUE, 0));
                    p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, Integer.MAX_VALUE, 1));
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix2 + "&aGold kit has been equiped."));
                    GoldKit.giveItems(p);
                    setCooldown(p, TimeUtils.parse("10s"));
                    return;
                }
            } else if (s.getLine(2).equalsIgnoreCase("§eGold")) {
                e.setCancelled(true);
                p.sendMessage(ChatColor.RED + "You are on cooldown for "+ setFormat(getMillisecondLeft(p)) + ".");
                return;
            }
            if (!isCooldownActive(p)) {
                if (s.getLine(2).equalsIgnoreCase("§4Archer")) {
                    for (PotionEffect effect : p.getActivePotionEffects())
                        p.removePotionEffect(effect.getType());
                    p.getInventory().clear();
                    p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 2));
                    p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, Integer.MAX_VALUE, 1));
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix2 + "&aArcher kit has been equiped."));
                    ArcherKit.giveItems(p);
                    setCooldown(p, TimeUtils.parse("10s"));
                    return;
                }
            } else if (s.getLine(2).equalsIgnoreCase("§4Archer")) {
                e.setCancelled(true);
                p.sendMessage(ChatColor.RED + "You are on cooldown for "+ setFormat(getMillisecondLeft(p)) + ".");
                return;
            }
            if (!isCooldownActive(p)) {
            if (s.getLine(2).equalsIgnoreCase("§aBuilder")) {
                for (PotionEffect effect : p.getActivePotionEffects())
                    p.removePotionEffect(effect.getType());
                p.getInventory().clear();
                p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 0));
                p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, Integer.MAX_VALUE, 0));
                p.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, Integer.MAX_VALUE, 0));
                p.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, Integer.MAX_VALUE, 1));
                p.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, Integer.MAX_VALUE, 0));
                p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, Integer.MAX_VALUE, 0));
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix2 + "&aBuilder kit has been equiped."));
                KitBuilder.giveItems(p);
                setCooldown(p, TimeUtils.parse("10s"));
                return;
            }
            } else if (s.getLine(2).equalsIgnoreCase("§aBuilder")) {
                e.setCancelled(true);
                p.sendMessage(ChatColor.RED + "You are on cooldown for "+ setFormat(getMillisecondLeft(p)) + ".");
                return;
            }
        }
    }
}
