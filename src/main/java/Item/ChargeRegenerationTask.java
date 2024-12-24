package Item;

import Util.UpgradeLore;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class ChargeRegenerationTask extends BukkitRunnable {
    private final JavaPlugin plugin;
    public ChargeRegenerationTask(JavaPlugin plugin){
        this.plugin = plugin;
    }
    @Override
    public void run() {
        for (Player player : plugin.getServer().getOnlinePlayers()) {
            for (ItemStack item : player.getInventory().getContents()) {
                if (item != null && item.hasItemMeta() && item.getItemMeta().hasDisplayName()
                        && item.getItemMeta().getDisplayName().equals(ChatColor.RED + "§lПосох Арканов")) {

                    int charges = MagicStaffItem.getCharges(item);
                    int maxCharges = MagicStaffItem.getMaxCharge(item);

                    if (charges < maxCharges) {
                        MagicStaffItem.setCharges(item, charges + 1);

                    }
                    if(charges == maxCharges){
                        player.sendMessage(ChatColor.GREEN + "Посох полностью восстановил свои заряды");

                    }
                    UpgradeLore.updateLore(item, plugin);
                }
            }
        }
    }
}
