package com.ashkiano.jumpboots;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class JumpBootsListener implements Listener {

    private final JavaPlugin plugin;

    public JumpBootsListener(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getWhoClicked() instanceof Player) {
            Player player = (Player) event.getWhoClicked();
            Bukkit.getScheduler().runTaskLater(this.plugin, () -> checkForJumpBoots(player), 1L);
        }
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Bukkit.getScheduler().runTaskLater(this.plugin, () -> checkForJumpBoots(player), 1L);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        checkForJumpBoots(player);
    }

    private void checkForJumpBoots(Player player) {
        ItemStack boots = player.getInventory().getBoots();

        if (boots != null && boots.getType() == Material.LEATHER_BOOTS) {
            ItemMeta meta = boots.getItemMeta();

            if (meta == null) return;
            if (meta.hasLore() && meta.getLore().contains(JumpBootsCommand.JUMP_BOOTS_LORE)) {
                if (!player.hasPotionEffect(PotionEffectType.JUMP)) {
                    player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, Integer.MAX_VALUE, 1, false, false, false));
                }
                return;
            }
        }

        if (player.hasPotionEffect(PotionEffectType.JUMP)) {
            player.removePotionEffect(PotionEffectType.JUMP);
        }
    }
}
