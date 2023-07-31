package com.ashkiano.jumpboots;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;

public class JumpBootsCommand implements CommandExecutor {
    public static String JUMP_BOOTS_LORE;

    private final JavaPlugin plugin;

    private final String jumpBootsUsePermission;

    public JumpBootsCommand(String permission, JavaPlugin plugin) {
        this.jumpBootsUsePermission = permission;
        this.plugin = plugin;
        JUMP_BOOTS_LORE = ChatColor.GRAY + plugin.getConfig().getString("jumpboots-lore", "Enjoy each of your jumps!");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("This command can only be used by a player!");
            return true;
        }

        Player player = (Player) sender;

        if (!player.hasPermission(jumpBootsUsePermission)) {
            player.sendMessage(ChatColor.RED + "You do not have permission to use this command!");
            return true;
        }

        String jumpBootsName = plugin.getConfig().getString("jumpboots-name", "Jump boots");
        ItemStack jumpBoots = new ItemStack(Material.LEATHER_BOOTS);
        LeatherArmorMeta meta = (LeatherArmorMeta) jumpBoots.getItemMeta();
        meta.setColor(Color.RED);
        meta.setLore(Arrays.asList(JUMP_BOOTS_LORE));
        meta.setDisplayName(jumpBootsName);
        jumpBoots.setItemMeta(meta);

        player.getInventory().addItem(jumpBoots);

        return true;
    }
}
