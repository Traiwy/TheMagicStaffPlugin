package Commands;


import Item.MagicStaffItem;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class CommandsGiveTheMagicStaff implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Эту команду можно использовать только в игре!");
            return true;
        }

        Player player = (Player) sender;
        ItemStack magicStaff = MagicStaffItem.createMagicStaff();

        if (player.getInventory().firstEmpty() != -1) {
            player.getInventory().addItem(magicStaff);
            player.sendMessage(ChatColor.GREEN + "Вы получили Посох Арканов!");
        } else {
            player.sendMessage(ChatColor.RED + "Ваш инвентарь полон!");
        }

        return true;
    }
}
