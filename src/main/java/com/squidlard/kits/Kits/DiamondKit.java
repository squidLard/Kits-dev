package com.squidlard.kits.Kits;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionType;

/**
 * Created by Dalton on 4/17/2017.
 */
public class DiamondKit {

    public static void giveItems(Player p) {
        ItemStack ih = new ItemStack(Material.DIAMOND_HELMET);
        ih.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
        ih.addEnchantment(Enchantment.DURABILITY, 3);
        ItemMeta ihmeta = ih.getItemMeta();
        ih.setItemMeta(ihmeta);

        ItemStack ic = new ItemStack(Material.DIAMOND_CHESTPLATE);
        ic.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
        ic.addEnchantment(Enchantment.DURABILITY, 3);
        ItemMeta icmeta = ic.getItemMeta();
        ic.setItemMeta(icmeta);

        ItemStack il = new ItemStack(Material.DIAMOND_LEGGINGS);
        il.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
        il.addEnchantment(Enchantment.DURABILITY, 3);
        ItemMeta ilmeta = il.getItemMeta();
        il.setItemMeta(ilmeta);

        ItemStack ib = new ItemStack(Material.DIAMOND_BOOTS);
        ib.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
        ib.addEnchantment(Enchantment.DURABILITY, 3);
        ItemMeta ibmeta = ib.getItemMeta();
        ib.setItemMeta(ibmeta);

        ItemStack is = new ItemStack(Material.DIAMOND_SWORD);
        is.addEnchantment(Enchantment.DAMAGE_ALL, 1);
        is.addEnchantment(Enchantment.DURABILITY, 3);
        ItemMeta ismeta = is.getItemMeta();
        is.setItemMeta(ismeta);

        ItemStack ir = new ItemStack(Material.ENDER_PEARL, 16);
        ItemMeta irmeta = ir.getItemMeta();
        ir.setItemMeta(irmeta);

        Potion a = new Potion(PotionType.SPEED, 2);

        Potion r = new Potion(PotionType.INSTANT_HEAL, 2);
        r.setSplash(true);

        ItemStack iw = new ItemStack(a.toItemStack(1));
        ItemMeta iwmeta = iw.getItemMeta();
        iw.setItemMeta(iwmeta);

        ItemStack s = new ItemStack(r.toItemStack(1));
        ItemMeta smeta = s.getItemMeta();
        s.setItemMeta(smeta);

        ItemStack f = new ItemStack(Material.COOKED_BEEF, 64);
        ItemMeta fmeta = f.getItemMeta();
        f.setItemMeta(fmeta);

        p.getInventory().setHelmet(ih);
        p.getInventory().setChestplate(ic);
        p.getInventory().setLeggings(il);
        p.getInventory().setBoots(ib);

        p.getInventory().setItem(0, is);
        p.getInventory().setItem(1, ir);
        p.getInventory().setItem(7, iw);
        p.getInventory().setItem(4, s);
        p.getInventory().setItem(5, s);
        p.getInventory().setItem(6, s);
        p.getInventory().setItem(8, f);
        p.getInventory().setItem(2, s);
        p.getInventory().setItem(3, s);
        p.getInventory().setItem(9, s);
        p.getInventory().setItem(10, s);
        p.getInventory().setItem(11, s);
        p.getInventory().setItem(12, s);
        p.getInventory().setItem(13, s);
        p.getInventory().setItem(14, s);
        p.getInventory().setItem(15, s);
        p.getInventory().setItem(16, s);
        p.getInventory().setItem(17, iw);
        p.getInventory().setItem(18, s);
        p.getInventory().setItem(19, s);
        p.getInventory().setItem(20, s);
        p.getInventory().setItem(21, s);
        p.getInventory().setItem(22, s);
        p.getInventory().setItem(23, s);
        p.getInventory().setItem(24, s);
        p.getInventory().setItem(25, s);
        p.getInventory().setItem(26, iw);
        p.getInventory().setItem(27, s);
        p.getInventory().setItem(28, s);
        p.getInventory().setItem(29, s);
        p.getInventory().setItem(30, s);
        p.getInventory().setItem(31, s);
        p.getInventory().setItem(32, s);
        p.getInventory().setItem(33, s);
        p.getInventory().setItem(34, s);
        p.getInventory().setItem(35, iw);

    }
}

