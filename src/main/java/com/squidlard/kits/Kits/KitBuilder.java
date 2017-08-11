package com.squidlard.kits.Kits;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
/**
 * Created by Dalton on 4/17/2017.
 */
public class KitBuilder {

    public static void giveItems(Player p) {
        ItemStack ih = new ItemStack(Material.IRON_HELMET);
        ItemMeta ihmeta = ih.getItemMeta();
        ih.setItemMeta(ihmeta);

        ItemStack ic = new ItemStack(Material.IRON_CHESTPLATE);
        ItemMeta icmeta = ic.getItemMeta();
        ic.setItemMeta(icmeta);

        ItemStack il = new ItemStack(Material.IRON_LEGGINGS);
        ItemMeta ilmeta = il.getItemMeta();
        il.setItemMeta(ilmeta);

        ItemStack ib = new ItemStack(Material.IRON_BOOTS);
        ItemMeta ibmeta = ib.getItemMeta();
        ib.setItemMeta(ibmeta);

        ItemStack arrow = new ItemStack(Material.REDSTONE_BLOCK, 64);
        ItemMeta arrowmeta = arrow.getItemMeta();
        arrow.setItemMeta(arrowmeta);

        ItemStack is = new ItemStack(Material.LOG_2, 64);
        ItemMeta ismeta = is.getItemMeta();
        is.setItemMeta(ismeta);

        ItemStack a = new ItemStack(Material.DIRT, 64);
        ItemMeta ameta = a.getItemMeta();
        is.setItemMeta(ameta);

        ItemStack u = new ItemStack(Material.LAPIS_BLOCK, 64);
        ItemMeta umeta = u.getItemMeta();
        is.setItemMeta(umeta);

        ItemStack c = new ItemStack(Material.QUARTZ_BLOCK, 64);
        ItemMeta cmeta = c.getItemMeta();
        is.setItemMeta(cmeta);

        ItemStack d = new ItemStack(Material.STONE, 64);
        ItemMeta dmeta = d.getItemMeta();
        is.setItemMeta(dmeta);

        ItemStack e = new ItemStack(Material.GLOWSTONE, 64);
        ItemMeta emeta = e.getItemMeta();
        is.setItemMeta(emeta);

        ItemStack g = new ItemStack(Material.COBBLESTONE_STAIRS, 64);
        ItemMeta gmeta = g.getItemMeta();
        is.setItemMeta(gmeta);

        ItemStack b = new ItemStack(Material.WATER_BUCKET);
        ItemMeta bmeta = b.getItemMeta();
        b.setItemMeta(bmeta);

        ItemStack ir = new ItemStack(Material.FENCE_GATE, 16);
        ItemMeta irmeta = ir.getItemMeta();
        ir.setItemMeta(irmeta);

        ItemStack s = new ItemStack(Material.COOKED_BEEF, 64);
        ItemMeta smeta = s.getItemMeta();
        s.setItemMeta(smeta);

        ItemStack f = new ItemStack(Material.DIAMOND_SWORD);
        f.addEnchantment(Enchantment.DAMAGE_ALL, 1);
        f.addEnchantment(Enchantment.DURABILITY, 3);
        ItemMeta fmeta = f.getItemMeta();
        f.setItemMeta(fmeta);

        ItemStack gd = new ItemStack(Material.DIAMOND_AXE);
        gd.addEnchantment(Enchantment.DIG_SPEED, 5);
        gd.addEnchantment(Enchantment.DURABILITY, 3);
        ItemMeta gdmeta = gd.getItemMeta();
        gd.setItemMeta(gdmeta);

        ItemStack fg = new ItemStack(Material.DIAMOND_SPADE);
        fg.addEnchantment(Enchantment.DIG_SPEED, 5);
        fg.addEnchantment(Enchantment.DURABILITY, 3);
        ItemMeta fgmeta = fg.getItemMeta();
        fg.setItemMeta(fgmeta);

        ItemStack gf = new ItemStack(Material.DIAMOND_PICKAXE);
        gf.addEnchantment(Enchantment.DIG_SPEED, 5);
        gf.addEnchantment(Enchantment.DURABILITY, 3);
        ItemMeta gfmeta = gf.getItemMeta();
        gf.setItemMeta(gfmeta);

        p.getInventory().setHelmet(ih);
        p.getInventory().setChestplate(ic);
        p.getInventory().setLeggings(il);
        p.getInventory().setBoots(ib);

        p.getInventory().setItem(0, f);
        p.getInventory().addItem(gf);
        p.getInventory().addItem(is);
        p.getInventory().addItem(a);
        p.getInventory().addItem(u);
        p.getInventory().addItem(b);
        p.getInventory().addItem(c);
        p.getInventory().addItem(gd);
        p.getInventory().addItem(fg);
        p.getInventory().addItem(arrow);
        p.getInventory().addItem(d);
        p.getInventory().addItem(e);
        p.getInventory().addItem(a);
    }
}

