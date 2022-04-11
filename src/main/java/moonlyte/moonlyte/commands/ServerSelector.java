package moonlyte.moonlyte.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class ServerSelector implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            Inventory serverSelector = Bukkit.createInventory(player, 45, "Choose a gamemode");

            ItemStack lobby = new ItemStack(Material.COMPASS);
            ItemMeta lobbyMeta = lobby.getItemMeta();
            ArrayList<String> lobbyLore = new ArrayList<>();
            lobbyLore.add("Teleport to the lobby");
            if (lobbyMeta != null) {
                lobbyMeta.setDisplayName("Lobby");
                lobbyMeta.setLore(lobbyLore);
            }
            lobby.setItemMeta(lobbyMeta);

            ItemStack survival = new ItemStack(Material.OAK_SAPLING);
            ItemMeta survivalMeta = survival.getItemMeta();
            if (survivalMeta != null) {
                survivalMeta.setDisplayName("Survival");
            }

//            ItemStack[] serverOptions = {lobby, survival};
//            serverSelector.setContents(serverOptions);
            serverSelector.setItem(21, lobby);
            serverSelector.setItem(23, survival);
            player.openInventory(serverSelector);

        }
        return true;
    }

    public void goToLobbyServer() {

    }
}
