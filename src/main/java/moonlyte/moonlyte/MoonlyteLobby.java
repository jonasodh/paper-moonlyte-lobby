package moonlyte.moonlyte;

import moonlyte.moonlyte.commands.ServerSelector;
import moonlyte.moonlyte.events.PlayerDie;
import moonlyte.moonlyte.events.PlayerJoin;
import moonlyte.moonlyte.events.PlayerLeave;
import moonlyte.moonlyte.events.ServerSelectorInteract;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;

import java.util.logging.Logger;

public final class MoonlyteLobby extends JavaPlugin implements PluginMessageListener {
private static MoonlyteLobby plugin;
    @Override
    public void onEnable() {
        this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        this.getServer().getMessenger().registerIncomingPluginChannel(this, "BungeeCord", this);
        plugin = this;
        Logger logger = getLogger();
        logger.info("MoonlyteLobby: Lift off!");
        getConfig().options().copyDefaults(true);
        saveDefaultConfig();
        PluginManager pm = getServer().getPluginManager();

        //register Events
        pm.registerEvents(new PlayerJoin(), this);
        pm.registerEvents(new PlayerLeave(), this);
        pm.registerEvents(new ServerSelectorInteract(), this);
        pm.registerEvents(new PlayerDie(), this);

        //register commands
        getCommand("serverSelector").setExecutor(new ServerSelector());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        this.getServer().getMessenger().unregisterOutgoingPluginChannel(this);
        this.getServer().getMessenger().unregisterIncomingPluginChannel(this);
    }
    public static MoonlyteLobby getPlugin() {
        return plugin;
    }

    @Override
    public void onPluginMessageReceived(String channel, Player player, byte[] message) {

    }
}
