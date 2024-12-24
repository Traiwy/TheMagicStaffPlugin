package Item;

import Util.UpgradeLore;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;
import ru.traiwy.themagicstaffplugin.TheMagicStaffPlugin;

import java.util.ArrayList;
import java.util.List;

public class MagicStaffItem {
    private static NamespacedKey CHARGES_KEY;
    private static NamespacedKey MAX_CHARGES_KEY;
    private static NamespacedKey UPGRADE_LEVEL_KEY;
    private static JavaPlugin plugin;
    public MagicStaffItem(TheMagicStaffPlugin plugin){
        this.plugin = plugin;
        CHARGES_KEY = new NamespacedKey(plugin, "magic_staff_charges");
        MAX_CHARGES_KEY = new NamespacedKey(plugin, "magic_staff_max_charges");
        UPGRADE_LEVEL_KEY = new NamespacedKey(plugin, "magic_staff_upgrade_level");

    }

    public static ItemStack createMagicStaff(){
        ItemStack MagicStaff = new ItemStack(new ItemStack(Material.REDSTONE_TORCH));
        ItemMeta meta = MagicStaff.getItemMeta();
        meta.setDisplayName(ChatColor.RED + "§lПосох Арканов");
        final List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "Древний артефакт, объединяющий силу");
        lore.add(ChatColor.GRAY + "телепортации, защиты и разрушительной магии.");
        lore.add(ChatColor.GRAY + "Используй его с умом — заряды восстанавливаются медленно.");
        lore.add(ChatColor.GRAY + "Улучшай посох, чтобы раскрыть его истинный потенциал.");
        lore.add("");
        lore.add(ChatColor.GREEN + "§lЗаряды: " + ChatColor.YELLOW + "5/10"); // Строка с зарядами
        lore.add(ChatColor.GREEN + "§lУлучшения: " + ChatColor.YELLOW + "2/3");
        lore.add(ChatColor.GREEN + "§lВремя перезарядки: " + ChatColor.YELLOW + "1.5 секунды");
        lore.add(ChatColor.GREEN + "§lРадиус телепортации: " + ChatColor.YELLOW + "6 блоков");
        lore.add(ChatColor.GREEN + "§lРадиус атаки: " + ChatColor.YELLOW + "3 блока");

        meta.setLore(lore);
        PersistentDataContainer data = meta.getPersistentDataContainer();
        data.set(CHARGES_KEY, PersistentDataType.INTEGER, 10);
        data.set(MAX_CHARGES_KEY, PersistentDataType.INTEGER, 10);
        data.set(UPGRADE_LEVEL_KEY, PersistentDataType.INTEGER, 1);
        MagicStaff.setItemMeta(meta);
        return MagicStaff;

    }
    public static void setCharges(ItemStack item, int charge) {
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            PersistentDataContainer data = meta.getPersistentDataContainer();
            data.set(CHARGES_KEY, PersistentDataType.INTEGER, charge);
            item.setItemMeta(meta);
        }
    }
    public static int getCharges(ItemStack item) {
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            PersistentDataContainer data = meta.getPersistentDataContainer();
            return data.getOrDefault(CHARGES_KEY, PersistentDataType.INTEGER, 0);
        }
        return 0;
    }

    public static void setMaxCharge(ItemStack item, int maxCharge){
        ItemMeta meta = item.getItemMeta();
        if(meta != null){
            PersistentDataContainer data = meta.getPersistentDataContainer();
            data.set(MAX_CHARGES_KEY, PersistentDataType.INTEGER, 10);
            item.setItemMeta(meta);
        }
    }
    public static int getMaxCharge(ItemStack item) {
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            PersistentDataContainer data = meta.getPersistentDataContainer();
            return data.getOrDefault(MAX_CHARGES_KEY, PersistentDataType.INTEGER, 10);
        }
        return 10;
    }
    public static void setUpgradeLvl(ItemStack item, int upgradeLvl){
        ItemMeta meta = item.getItemMeta();
        if(meta != null){
            PersistentDataContainer data = meta.getPersistentDataContainer();
            data.set(UPGRADE_LEVEL_KEY, PersistentDataType.INTEGER, 3);
            item.setItemMeta(meta);
        }
    }
    public static int getUpgradeLvl(ItemStack item) {
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            PersistentDataContainer data = meta.getPersistentDataContainer();
            return data.getOrDefault(UPGRADE_LEVEL_KEY, PersistentDataType.INTEGER, 0);
        }
        return 0;
    }

    public static void decreaseCharges(ItemStack item, Player player) {
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            PersistentDataContainer data = meta.getPersistentDataContainer();
            int charges = data.getOrDefault(CHARGES_KEY, PersistentDataType.INTEGER, 0);
            if (charges > 0) {
                data.set(CHARGES_KEY, PersistentDataType.INTEGER, charges - 1);
                item.setItemMeta(meta);
            }
            UpgradeLore.updateLore(item, plugin);
        }
    }


}
