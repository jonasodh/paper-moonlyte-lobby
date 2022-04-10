package moonlyte.moonlyte.events;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import moonlyte.moonlyte.MoonlyteLobby;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
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
        //cancel ability to move items in inventory
        if (event.getView().getTitle().equalsIgnoreCase(ChatColor.RED + "Choose a gamemode")){
            event.setCancelled(true);
            ByteArrayDataOutput out = ByteStreams.newDataOutput();
            switch (event.getCurrentItem().getType()){
                case COMPASS:
                    player.closeInventory();
                    player.sendMessage("Teleporting to Lobby");
                    out.writeUTF("Connect");
                    out.writeUTF("lobby");  //server's name, set in the  velocity config
                    player.sendPluginMessage(plugin, "BungeeCord", out.toByteArray());
                    break;
                case OAK_SAPLING:
                    player.closeInventory();
                    player.sendMessage("Teleporting to Survival");
                    out.writeUTF("Connect");
                    out.writeUTF("survival");
                    player.sendPluginMessage(plugin, "BungeeCord", out.toByteArray());
                    break;
            }
        }

    }
}
