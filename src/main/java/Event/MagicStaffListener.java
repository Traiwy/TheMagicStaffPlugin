package Event;

import Item.MagicStaffItem;
import Util.CreateMagicalBarier;
import Util.MagicStaffTeleportPlayer;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class MagicStaffListener implements Listener {
    private final JavaPlugin plugin;
    public MagicStaffListener(JavaPlugin plugin){
        this.plugin = plugin;
    }
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event){
        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();

        if (item != null && item.hasItemMeta() && item.getItemMeta().hasDisplayName()
                && item.getItemMeta().getDisplayName().equals(ChatColor.RED + "§lПосох Арканов")) {
            int charges = MagicStaffItem.getCharges(item);
            if (charges <= 0) {
                player.sendMessage(ChatColor.RED + "Посох разряжен! Подождите, пока он восстановит заряды.");
                return;
            }
            if (charges > 0 && event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK) {

                MagicStaffTeleportPlayer.teleportPlayer(player);
                MagicStaffItem.decreaseCharges(item, player);
            } else if (charges > 0 && event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                CreateMagicalBarier.createBarrier(player);
                MagicStaffItem.decreaseCharges(item, player);
            }
            event.setCancelled(true);
        }
    }
}
