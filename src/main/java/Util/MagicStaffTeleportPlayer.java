package Util;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class MagicStaffTeleportPlayer {
    private final JavaPlugin plugin;
    public MagicStaffTeleportPlayer(JavaPlugin plugin){
        this.plugin = plugin;
    }
    public static void teleportPlayer(Player player){
        Location currentLocation = player.getLocation();
        Location teleportLocation = currentLocation.add(currentLocation.getDirection().multiply(10));
        teleportLocation.setY(teleportLocation.getY() + 1);
        if (teleportLocation.getBlock().getType().isSolid()) {
            player.sendMessage(ChatColor.RED + "Путь заблокирован!");
            return;
        }
        player.teleport(teleportLocation);
        player.getWorld().spawnParticle(Particle.SPELL_INSTANT, currentLocation, 30);
        player.getWorld().playSound(currentLocation, Sound.ENTITY_ENDERMAN_TELEPORT, 1.0f, 1.0f);

    }
}
