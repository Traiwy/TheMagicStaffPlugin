package Item;

import Util.UpgradeLore;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

public class UpgradeSystem {
    private final JavaPlugin plugin;
    public UpgradeSystem(JavaPlugin plugin){
        this.plugin = plugin;
    }
    public static boolean upgradeStaff(Player player) {
        ItemStack staff = null;
        for (ItemStack item : player.getInventory().getContents()) {
            if (item != null && item.hasItemMeta() && item.getItemMeta().hasDisplayName() && item.getItemMeta().getDisplayName().equals(ChatColor.RED + "§lПосох Арканов")) {
                staff = item;
                break;
            }
        }
        if(staff == null){
            player.sendMessage("У вас нет посоха в инвентаре");
            return false;
        }
        int upgradeLevel = MagicStaffItem.getUpgradeLvl(staff);
        if (upgradeLevel >= 3) {
            player.sendMessage(ChatColor.RED + "Посох уже максимального уровня");
            return false;
        }
        ItemStack requiredResource = new ItemStack(Material.DIAMOND, 5);
        if(!player.getInventory().containsAtLeast(requiredResource , requiredResource .getAmount())){
            player.sendMessage("У вас недостаточно камней для улучшения посоха ( 5 алмазов )");
            return false;
        }
        player.getInventory().removeItem(requiredResource);
        MagicStaffItem.setUpgradeLvl(staff, upgradeLevel + 1);
        int maxCharges = MagicStaffItem.getMaxCharge(staff);
        MagicStaffItem.setMaxCharge(staff, maxCharges + 2);

        player.sendMessage(ChatColor.GREEN + "Посох Арканов улучшен! Уровень улучшения: " + (upgradeLevel + 1));
        return true;
    }
}

