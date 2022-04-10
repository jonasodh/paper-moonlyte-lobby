package moonlyte.moonlyte.events;

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

            switch (event.getCurrentItem().getType()){
                case COMPASS:
                    player.closeInventory();
                    player.sendMessage("Teleporting to Lobby");
                    Bukkit.dispatchCommand(player, "server lobby");
                    break;
                case OAK_SAPLING:
                    player.closeInventory();
                    player.sendMessage("Teleporting to Survival");
                    player.chat("/server survival");
                    break;
            }
        }

    }
}
