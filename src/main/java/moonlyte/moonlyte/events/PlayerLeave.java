package moonlyte.moonlyte.events;

import moonlyte.moonlyte.MoonlyteLobby;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.Objects;

public class PlayerLeave implements Listener {
    MoonlyteLobby plugin = MoonlyteLobby.getPlugin();
    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {
        event.setQuitMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("playerQuitMessage")).replace("[player]", event.getPlayer().getDisplayName())));
    }
}
