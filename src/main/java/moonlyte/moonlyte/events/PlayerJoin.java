package moonlyte.moonlyte.events;

import moonlyte.moonlyte.MoonlyteLobby;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Objects;

public class PlayerJoin implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        MoonlyteLobby plugin = MoonlyteLobby.getPlugin();

        if (!(event.getPlayer().hasPlayedBefore())) {
            event.setJoinMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("playerJoinedFirstTimeMessage")).replace("[player]", event.getPlayer().getDisplayName())));
        } else {
            event.setJoinMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("playerJoinMessage")).replace("[player]", event.getPlayer().getDisplayName())));
        }
        giveServerSelector(event);
    }
    public void giveServerSelector(PlayerJoinEvent e) {
        ItemStack compass = new ItemStack(Material.COMPASS);
        ItemMeta compassMeta = compass.getItemMeta();
        ArrayList<String> compassLore = new ArrayList<>();
        compassLore.add("Select a server to teleport to");
        assert compassMeta != null;
        compassMeta.setDisplayName("Server selector");
        compassMeta.setLore(compassLore);
        compass.setItemMeta(compassMeta);
        ItemStack[] giveSelectedItems = {compass};
        e.getPlayer().getInventory().setContents(giveSelectedItems);
    }
}
