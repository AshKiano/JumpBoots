package com.ashkiano.jumpboots;

import org.bukkit.plugin.java.JavaPlugin;

public class JumpBoots extends JavaPlugin {

    private String jumpBootsUsePermission;

    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        this.jumpBootsUsePermission = this.getConfig().getString("permissions.use", "jumpboots.use");

        getCommand("jumpboots").setExecutor(new JumpBootsCommand(jumpBootsUsePermission, this));
        getServer().getPluginManager().registerEvents(new JumpBootsListener(this), this);

        System.out.println("Thank you for using the JumpBoots plugin! If you enjoy using this plugin, please consider making a donation to support the development. You can donate at: https://paypal.me/josefvyskocil");

        Metrics metrics = new Metrics(this, 19168);
    }
}