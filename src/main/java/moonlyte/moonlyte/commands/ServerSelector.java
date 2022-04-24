package moonlyte.moonlyte.commands;

import moonlyte.moonlyte.MoonlyteLobby;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

public class ServerSelector implements CommandExecutor {
    MoonlyteLobby plugin = MoonlyteLobby.getPlugin();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            Inventory serverSelector = Bukkit.createInventory(player, 45, "Choose a gamemode");
            ConfigurationSection items = Objects.requireNonNull(plugin.getConfig().getConfigurationSection("lobby_menu"));
            for (String item : items.getKeys(false)) {

                String itemMaterial = (String) Objects.requireNonNull(plugin.getConfig().getConfigurationSection("lobby_menu." + item)).get("material");
                Integer itemIndex = (Integer) Objects.requireNonNull(plugin.getConfig().getConfigurationSection("lobby_menu." + item)).get("inventory_index");
                String itemName = (String) Objects.requireNonNull(plugin.getConfig().getConfigurationSection("lobby_menu." + item)).get("name");
                String itemLore = (String) Objects.requireNonNull(plugin.getConfig().getConfigurationSection("lobby_menu." + item)).get("lore");
                if (itemMaterial != null && itemIndex != null && itemName != null && itemLore != null) {
                    ItemStack itemSlot = new ItemStack(Material.valueOf(itemMaterial));
                    ItemMeta itemSlotMeta = itemSlot.getItemMeta();
                    ArrayList<String> itemSlotLore = new ArrayList<>();
                    if (itemSlotMeta != null) {
                        itemSlotLore.add(itemLore);
                        itemSlotMeta.setDisplayName(itemName);
                        itemSlotMeta.setLore(itemSlotLore);
                    }
                    itemSlot.setItemMeta(itemSlotMeta);
                    if (itemIndex != null) {
                        serverSelector.setItem(itemIndex, itemSlot);
                    }
                }
            }
            player.openInventory(serverSelector);
        }
        return true;
    }
}
