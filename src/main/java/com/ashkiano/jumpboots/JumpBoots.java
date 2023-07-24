package com.ashkiano.jumpboots;

import org.bukkit.plugin.java.JavaPlugin;

public class JumpBoots extends JavaPlugin {

    private String jumpBootsUsePermission;

    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        this.jumpBootsUsePermission = this.getConfig().getString("permissions.use", "jumpboots.use");

        getCommand("jumpboots").setExecutor(new JumpBootsCommand(jumpBootsUsePermission));
        getServer().getPluginManager().registerEvents(new JumpBootsListener(this), this);

        Metrics metrics = new Metrics(this, 19168);
    }
}