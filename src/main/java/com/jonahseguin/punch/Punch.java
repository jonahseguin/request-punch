package com.jonahseguin.punch;

import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by Jonah on 5/5/2018.
 * Project: Punch
 *
 * @ 1:24 PM
 */
public class Punch extends JavaPlugin {

    @Override
    public void onEnable() {
        saveDefaultConfig();
        this.getCommand("punch").setExecutor(new PunchCommand(this));
    }

    @Override
    public void onDisable() {

    }

}
