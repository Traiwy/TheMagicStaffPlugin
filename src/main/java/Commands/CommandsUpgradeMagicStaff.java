package Commands;

import Item.UpgradeSystem;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CommandsUpgradeMagicStaff implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(!(sender instanceof Player)){
            sender.sendMessage(ChatColor.RED + "Эту команду можно использовать только в игре!");
            return true;
        }
        Player player = (Player) sender;
        boolean success = UpgradeSystem.upgradeStaff(player);
        if (!success) {
            player.sendMessage(ChatColor.RED + "Не удалось улучшить посох.");
        }

        return true;
    }
}
