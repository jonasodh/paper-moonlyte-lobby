package moonlyte.moonlyte.events;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class PlayerRespawn implements Listener {
    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event) {
        Player player = event.getPlayer();
            giveServerSelector(player);
    }

    public void giveServerSelector(Player player) {
        ItemStack compass = new ItemStack(Material.COMPASS);
        ItemMeta compassMeta = compass.getItemMeta();
        ArrayList<String> compassLore = new ArrayList<>();
        compassLore.add("Select a server to teleport to");
        assert compassMeta != null;
        compassMeta.setDisplayName("Server selector");
        compassMeta.setLore(compassLore);
        compass.setItemMeta(compassMeta);
        ItemStack[] giveSelectedItems = {compass};
        player.getPlayer().getInventory().setContents(giveSelectedItems);
    }

}
