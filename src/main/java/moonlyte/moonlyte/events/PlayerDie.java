package moonlyte.moonlyte.events;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDie implements Listener {
    @EventHandler
    public void onPlayerDie(PlayerDeathEvent event) {
        if (event.getEntityType() == EntityType.PLAYER) {
            event.setKeepInventory(true);
            event.getDrops().clear();
        }
    }
}
