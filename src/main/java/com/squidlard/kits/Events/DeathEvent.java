package com.squidlard.kits.Events;

import com.squidlard.kits.Core;
import net.minecraft.server.v1_7_R4.EntityLightning;
import net.minecraft.server.v1_7_R4.PacketPlayOutSpawnEntityWeather;
import net.minecraft.server.v1_7_R4.WorldServer;
import org.bukkit.*;
import org.bukkit.craftbukkit.v1_7_R4.CraftWorld;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

public class DeathEvent implements Listener {

    public String translate(String input) {
        return ChatColor.translateAlternateColorCodes('&', input);
    }

    @EventHandler(priority= EventPriority.HIGH)
    public void onPlayerDeath(PlayerDeathEvent event) {
        EntityDamageEvent.DamageCause c = event.getEntity().getLastDamageCause().getCause();
        Player p = event.getEntity();
        Player r = p.getKiller();
        Location location = p.getLocation();
        PacketPlayOutSpawnEntityWeather packet;
        WorldServer worldServer = ((CraftWorld)location.getWorld()).getHandle();
        EntityLightning entityLightning = new EntityLightning(worldServer, location.getX(), location.getY(), location.getZ(), false);
        packet = new PacketPlayOutSpawnEntityWeather(entityLightning);
        for (Player target : Bukkit.getOnlinePlayers()) {
            if (p instanceof Player) {
                ((CraftPlayer)target).getHandle().playerConnection.sendPacket(packet);
                target.playSound(target.getLocation(), Sound.AMBIENCE_THUNDER, 1.0F, 1.0F);
            }
        }
        int kills = Core.getInstance().getConfig().getInt("Stats." + p.getUniqueId() + ".Kills");
        int deaths = Core.getInstance().getConfig().getInt("Stats." + p.getUniqueId() + ".Deaths");
        if (c.equals(EntityDamageEvent.DamageCause.DROWNING)) {
            String message = translate("&c" + p.getName() + "&4[" + kills + "]" + "&e" + " Drowned.");
            Core.getInstance().getConfig().set("Stats." + p.getUniqueId() + ".Deaths", deaths + 1);
            p.setLevel(0);
            event.setDeathMessage(message);
        } else if (c.equals(EntityDamageEvent.DamageCause.FALL)) {
            String message = translate("&c" + p.getName() + "&4[" + kills + "]" + " &ehit the ground to hard.");
            Core.getInstance().getConfig().set("Stats." + p.getUniqueId() + ".Deaths", deaths + 1);
            event.setDeathMessage(message);
        } else if (c.equals(EntityDamageEvent.DamageCause.VOID)) {
            String message = translate("&c" + p.getName() + "&4[" + kills + "]" + "&e" + " fell into the void.");
            Core.getInstance().getConfig().set("Stats." + p.getUniqueId() + ".Deaths", deaths + 1);
            p.setLevel(0);
            event.setDeathMessage(message);
        } else if (c.equals(EntityDamageEvent.DamageCause.LAVA)) {
            String message = translate("&c" + p.getName() + "&4[" + kills + "]" + "&e" + " tried to swim in lava.");
            Core.getInstance().getConfig().set("Stats." + p.getUniqueId() + ".Deaths", deaths + 1);
            p.setLevel(0);
            event.setDeathMessage(message);
        } else if ((c.equals(EntityDamageEvent.DamageCause.FIRE)) || (c.equals(EntityDamageEvent.DamageCause.FIRE_TICK))) {
            String message = translate("&c" + p.getName() + "&4[" + kills + "]" + "&e" + " burnt to a crisp.");
            Core.getInstance().getConfig().set("Stats." + p.getUniqueId() + ".Deaths", deaths + 1);
            p.setLevel(0);
            event.setDeathMessage(message);
        } else if ((c.equals(EntityDamageEvent.DamageCause.ENTITY_EXPLOSION)) || (c.equals(EntityDamageEvent.DamageCause.BLOCK_EXPLOSION))) {
            String message = translate("&c" + p.getName() + "&4[" + kills + "]" + "&e" + " was bombed to death.");
            Core.getInstance().getConfig().set("Stats." + p.getUniqueId() + ".Deaths", deaths + 1);
            p.setLevel(0);
            event.setDeathMessage(message);
        } else if (c.equals(EntityDamageEvent.DamageCause.MAGIC)) {
            String message = translate("&c" + p.getName() + "&4[" + kills + "]" + " &ewas killed by magic.");
            Core.getInstance().getConfig().set("Stats." + p.getUniqueId() + ".Deaths", deaths + 1);
            p.setLevel(0);
            event.setDeathMessage(message);
        } else if (c.equals(EntityDamageEvent.DamageCause.SUFFOCATION)) {
            String message = translate("&c" + p.getName() + "&4[" + kills + "]" + "&e" + " Suffocated.");
            Core.getInstance().getConfig().set("Stats." + p.getUniqueId() + ".Deaths", deaths + 1);
            p.setLevel(0);
            event.setDeathMessage(message);
        } else if (c.equals(EntityDamageEvent.DamageCause.PROJECTILE)) {
            String message = translate("&c" + p.getName() + "&4[" + kills + "]" + " &ewas shot to death.");
            Core.getInstance().getConfig().set("Stats." + p.getUniqueId() + ".Deaths", deaths + 1);
            p.setLevel(0);
            event.setDeathMessage(message);
        } else if (c.equals(EntityDamageEvent.DamageCause.STARVATION)) {
            String message = translate("&c" + p.getName() + "&4[" + kills + "]" + " &estarved.");
            Core.getInstance().getConfig().set("Stats." + p.getUniqueId() + ".Deaths", deaths + 1);
            p.setLevel(0);
            event.setDeathMessage(message);
        } else if (c.equals(EntityDamageEvent.DamageCause.WITHER)) {
            String message = translate("&c" + p.getName() + "&4[" + kills + "]" + " &ewithered away.");
            Core.getInstance().getConfig().set("Stats." + p.getUniqueId() + ".Deaths", deaths + 1);
            p.setLevel(0);
            event.setDeathMessage(message);
        } else if ((c.equals(EntityDamageEvent.DamageCause.ENTITY_ATTACK)) &&
                ((event.getEntity().getLastDamageCause() instanceof EntityDamageByEntityEvent))) {
            EntityDamageByEntityEvent f = (EntityDamageByEntityEvent) event.getEntity().getLastDamageCause();
            Entity d = f.getDamager();
            if ((d instanceof Player)) {
                Player k = (Player) d;
                int kills2 = Core.getInstance().getConfig().getInt("Stats." + k.getUniqueId() + ".Kills");
                ItemStack it = ((Player)r).getItemInHand();
                String itemName;
                if ((it == null) || (it.getType() == Material.AIR)) {
                    return;
                } else {
                    itemName = it.getItemMeta().getDisplayName();
                }
                String message = translate("&c" + p.getName() + "&4[" + kills + "]" + "&e" + " was slain by " + "&c" + k.getName() + "&4[" + kills2 + "]" + " &eusing " + itemName);
                String message2 = translate("&c" + p.getName() + "&4[" + kills + "]" + "&e" + " was slain by " + "&c" + k.getName() + "&4[" + kills2 + "]");
                Core.getInstance().getConfig().set("Stats." + p.getUniqueId() + ".Deaths", deaths + 1);
                Core.getInstance().getConfig().set("Stats." + r.getUniqueId() + ".Kills", kills2 + 1);
                r.setLevel(r.getLevel() + 1);
                p.setLevel(0);
                if (itemName == null) {
                    event.setDeathMessage(message2);
                } else {
                    event.setDeathMessage(message);
                }
            } else if (d.getType().equals(EntityType.PIG_ZOMBIE)) {
                String message = translate("&c" + p.getName() + "&4[" + kills + "]" + "&e" + " was slain by Zombie Pigman");
                Core.getInstance().getConfig().set("Stats." + p.getUniqueId() + ".Deaths", deaths + 1);
                p.setLevel(0);
                event.setDeathMessage(message);
            } else if (d.getType().equals(EntityType.ZOMBIE)) {
                String message = translate("&c" + p.getName() + "&4[" + kills + "]" + "&e" + " was slain by Zombie");
                Core.getInstance().getConfig().set("Stats." + p.getUniqueId() + ".Deaths", deaths + 1);
                p.setLevel(0);
                event.setDeathMessage(message);
            } else if (d.getType().equals(EntityType.SPIDER)) {
                String message = translate("&c" + p.getName() + "&4[" + kills + "]" + "&e" + " was slain by Spider");
                Core.getInstance().getConfig().set("Stats." + p.getUniqueId() + ".Deaths", deaths + 1);
                p.setLevel(0);
                event.setDeathMessage(message);
            } else if (d.getType().equals(EntityType.SILVERFISH)) {
                String message = translate("&c" + p.getName() + "&4[" + kills + "]" + "&e" + " was slain by Silverfish");
                Core.getInstance().getConfig().set("Stats." + p.getUniqueId() + ".Deaths", deaths + 1);
                p.setLevel(0);
                event.setDeathMessage(message);
            } else if (d.getType().equals(EntityType.SLIME)) {
                String message = translate("&c" + p.getName() + "&4[" + kills + "]" + "&e" + " was slain by Slime");
                Core.getInstance().getConfig().set("Stats." + p.getUniqueId() + ".Deaths", deaths + 1);
                p.setLevel(0);
                event.setDeathMessage(message);
            } else if (d.getType().equals(EntityType.CREEPER)) {
                String message = translate("&c" + p.getName() + "&4[" + kills + "]" + "&e" + " was blown up by a Creeper");
                Core.getInstance().getConfig().set("Stats." + p.getUniqueId() + ".Deaths", deaths + 1);
                p.setLevel(0);
                event.setDeathMessage(message);
            } else if (d.getType().equals(EntityType.MAGMA_CUBE)) {
                String message = translate("&c" + p.getName() + "&4[" + kills + "]" + "&e" + " was slain by Magma Cube");
                Core.getInstance().getConfig().set("Stats." + p.getUniqueId() + ".Deaths", deaths + 1);
                p.setLevel(0);
                event.setDeathMessage(message);
            } else if (d.getType().equals(EntityType.ENDERMAN)) {
                String message = translate("&c" + p.getName() + "&4[" + kills + "]" + "&e" + " was slain by Enderman");
                Core.getInstance().getConfig().set("Stats." + p.getUniqueId() + ".Deaths", deaths + 1);
                p.setLevel(0);
                event.setDeathMessage(message);
            } else if (d.getType().equals(EntityType.ENDER_DRAGON)) {
                String message = translate("&c" + p.getName() + "&4[" + kills + "]" + "&e" + " was slain by Ender Dragon");
                Core.getInstance().getConfig().set("Stats." + p.getUniqueId() + ".Deaths", deaths + 1);
                p.setLevel(0);
                event.setDeathMessage(message);
            } else if (d.getType().equals(EntityType.CAVE_SPIDER)) {
                String message = translate("&c" + p.getName() + "&4[" + kills + "]" + "&e" + " was slain by Cave Spider");
                Core.getInstance().getConfig().set("Stats." + p.getUniqueId() + ".Deaths", deaths + 1);
                p.setLevel(0);
                event.setDeathMessage(message);
            } else if (d.getType().equals(EntityType.IRON_GOLEM)) {
                String message = translate("&c" + p.getName() + "&4[" + kills + "]" + "&e" + " was slain by Iron Golem");
                Core.getInstance().getConfig().set("Stats." + p.getUniqueId() + ".Deaths", deaths + 1);
                p.setLevel(0);
                event.setDeathMessage(message);
            } else if (d.getType().equals(EntityType.WOLF)) {
                String message = translate("&c" + p.getName() + "&4[" + kills + "]" + "&e" + " was slain by Wolf");
                Core.getInstance().getConfig().set("Stats." + p.getUniqueId() + ".Deaths", deaths + 1);
                p.setLevel(0);
                event.setDeathMessage(message);
            } else if (d.getType().equals(EntityType.GIANT)) {
                String message = translate("&c" + p.getName() + "&4[" + kills + "]" + "&e" + " was slain by Giant");
                Core.getInstance().getConfig().set("Stats." + p.getUniqueId() + ".Deaths", deaths + 1);
                p.setLevel(0);
                event.setDeathMessage(message);
            } else if (d.getType().equals(EntityType.WITHER)) {
                String message = translate("&c" + p.getName() + "&4[" + kills + "]" + "&e" + " was slain by Wither");
                Core.getInstance().getConfig().set("Stats." + p.getUniqueId() + ".Deaths", deaths + 1);
                p.setLevel(0);
                event.setDeathMessage(message);
            } else if ((d.getType().equals(EntityType.SKELETON)) && (((Skeleton) d).getSkeletonType().equals(Skeleton.SkeletonType.NORMAL))) {
                String message = translate("&c" + p.getName() + "&4[" + kills + "]" + "&e" + " was slain by Skeleton");
                Core.getInstance().getConfig().set("Stats." + p.getUniqueId() + ".Deaths", deaths + 1);
                p.setLevel(0);
                event.setDeathMessage(message);
            } else if (d.getType().equals(EntityType.BLAZE)) {
                String message = translate("&c" + p.getName() + "&4[" + kills + "]" + "&e" + " was fireballed by Blaze");
                Core.getInstance().getConfig().set("Stats." + p.getUniqueId() + ".Deaths", deaths + 1);
                p.setLevel(0);
                event.setDeathMessage(message);
            } else if (d.getType().equals(EntityType.GHAST)) {
                String message = translate("&c" + p.getName() + "&4[" + kills + "]" + "&e" + " was fireballed by Ghast");
                Core.getInstance().getConfig().set("Stats." + p.getUniqueId() + ".Deaths", deaths + 1);
                p.setLevel(0);
                event.setDeathMessage(message);
            } else if (d.getType().equals(EntityType.WITCH)) {
                String message = translate("&c" + p.getName() + "&4[" + kills + "]" + "&e" + " was slain by Witch");
                Core.getInstance().getConfig().set("Stats." + p.getUniqueId() + ".Deaths", deaths + 1);
                p.setLevel(0);
                event.setDeathMessage(message);
            } else if ((d.getType().equals(EntityType.SKELETON)) && (((Skeleton) d).getSkeletonType().equals(Skeleton.SkeletonType.WITHER))) {
                String message = translate("&c" + p.getName() + "&4[" + kills + "]" + "&e" + " was slain by Wither Skeleton");
                Core.getInstance().getConfig().set("Stats." + p.getUniqueId() + ".Deaths", deaths + 1);
                p.setLevel(0);
                event.setDeathMessage(message);
            }
        } else {
            event.setDeathMessage(null);
        }
        Core.getInstance().saveConfig();
    }
}
