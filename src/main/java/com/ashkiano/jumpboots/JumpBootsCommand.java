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

import java.util.Arrays;

public class JumpBootsCommand implements CommandExecutor {
    public static final String JUMP_BOOTS_LORE = ChatColor.GRAY + "Jump boots";

    private final String jumpBootsUsePermission;

    public JumpBootsCommand(String permission) {
        this.jumpBootsUsePermission = permission;
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

        ItemStack jumpBoots = new ItemStack(Material.LEATHER_BOOTS);
        LeatherArmorMeta meta = (LeatherArmorMeta) jumpBoots.getItemMeta();
        meta.setColor(Color.RED);
        meta.setLore(Arrays.asList(JUMP_BOOTS_LORE));
        jumpBoots.setItemMeta(meta);

        player.getInventory().addItem(jumpBoots);

        return true;
    }
}
