package moonlyte.moonlyte.events;

import moonlyte.moonlyte.MoonlyteLobby;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

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
    }
}
