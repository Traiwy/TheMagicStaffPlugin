package Util;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class CreateMagicalBarier {
    private static final int DAMAGE_RADIUS = 3;
    private static final double DAMAGE_AMOUNT = 5.0;
    public static void createBarrier(Player player) {
        Location playerLocation = player.getLocation();
        World world = player.getWorld();
        for (int x = -3; x <= 3; x++) {
            for (int y = -3; y <= 3; y++) {
                for (int z = -3; z <= 3; z++) {
                    if (x * x + y * y + z * z <= 9) {
                        Location particleLocation = playerLocation.clone().add(x, y, z);
                        world.spawnParticle(Particle.CRIT_MAGIC, particleLocation, 1);
                    }
                }
            }
        }
        for (Entity entity : world.getNearbyEntities(playerLocation, DAMAGE_RADIUS, DAMAGE_RADIUS, DAMAGE_RADIUS)) {
            if (entity instanceof LivingEntity && entity != player) {
                LivingEntity livingEntity = (LivingEntity) entity;
                livingEntity.damage(DAMAGE_AMOUNT, player);
            }
        }
        world.playSound(playerLocation, Sound.BLOCK_BEACON_ACTIVATE, 1.0f, 1.0f);
    }
}
