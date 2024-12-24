package Util;

import Item.MagicStaffItem;
import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class UpgradeLore {
    private final JavaPlugin plugin;
    public UpgradeLore(JavaPlugin plugin){
        this.plugin = plugin;
    }
    public static void updateLore(ItemStack item, JavaPlugin plugin) {
        ItemMeta meta = item.getItemMeta();
        if (meta == null) return;

        List<String> lore = meta.getLore();
        if (lore == null) return;

        int charge = MagicStaffItem.getCharges(item);
        int maxCharges = MagicStaffItem.getMaxCharge(item);
        int upgradeLevel = MagicStaffItem.getUpgradeLvl(item);
        for (int i = 0; i < lore.size(); i++) {
            if (lore.get(i).contains("§lЗаряды:")) {
                lore.set(i, ChatColor.GREEN + "§lЗаряды: " + ChatColor.YELLOW + charge + "/" + maxCharges);
            }
            if (lore.get(i).contains("§lУлучшения:")) {
                lore.set(i, ChatColor.GREEN + "§lУлучшения: " + ChatColor.YELLOW + upgradeLevel + "/3");
            }
        }
            meta.setLore(lore);
            item.setItemMeta(meta);
        }
    }

