package ru.traiwy.themagicstaffplugin;

import Commands.CommandsGiveTheMagicStaff;
import Commands.CommandsUpgradeMagicStaff;
import Event.MagicStaffListener;
import Item.ChargeRegenerationTask;
import Item.MagicStaffItem;
import Item.UpgradeSystem;
import Util.CreateMagicalBarier;
import Util.MagicStaffTeleportPlayer;
import Util.UpgradeLore;
import org.bukkit.plugin.java.JavaPlugin;

public final class TheMagicStaffPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        new MagicStaffTeleportPlayer(this);
        new UpgradeLore(this);
        new UpgradeSystem(this);
        new MagicStaffItem(this);

        new ChargeRegenerationTask(this).runTaskTimer(this, 0L, 60L);
        this.getCommand("givemagicstaff").setExecutor(new CommandsGiveTheMagicStaff());
        this.getCommand("upgradestaff").setExecutor(new CommandsUpgradeMagicStaff());
        getServer().getPluginManager().registerEvents(new MagicStaffListener(this), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
