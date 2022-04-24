package moonlyte.moonlyte.events;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import moonlyte.moonlyte.MoonlyteLobby;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.Objects;

public class ServerSelectorInteract implements Listener {
    MoonlyteLobby plugin = MoonlyteLobby.getPlugin();

    @EventHandler
    public void playerServerSelectorInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Action action = event.getAction();
        if (action.equals(Action.RIGHT_CLICK_AIR) || action.equals(Action.RIGHT_CLICK_BLOCK)) {
            if (Objects.requireNonNull(event.getItem()).getType() == Material.COMPASS) {
                player.performCommand("serverSelector");
            }
        }
    }

    @EventHandler
    public void selectMenuOption(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        ConfigurationSection items = Objects.requireNonNull(plugin.getConfig().getConfigurationSection("lobby_menu"));
        //cancel ability to move items in inventory
        if (event.getView().getTitle().equalsIgnoreCase("Choose a gamemode")) {
            event.setCancelled(true);
            ByteArrayDataOutput out = ByteStreams.newDataOutput();
            if (event.getCurrentItem() == null) {
                return;
            } else {
                for (String item : items.getKeys(false)) {
                    String itemName = (String) Objects.requireNonNull(plugin.getConfig().getConfigurationSection("lobby_menu." + item)).get("name");
                    String serverName = (String) Objects.requireNonNull(plugin.getConfig().getConfigurationSection("lobby_menu." + item)).get("server_name");
                    if (Objects.requireNonNull(event.getCurrentItem().getItemMeta()).getDisplayName().equals(itemName)) {
                        player.closeInventory();
                        if (serverName != null) {
                            out.writeUTF("Connect");
                            out.writeUTF(serverName);  //server's name, set in the  velocity config
                        }
                        player.sendPluginMessage(plugin, "BungeeCord", out.toByteArray());
                    }
                }
            }
        }
    }
}
