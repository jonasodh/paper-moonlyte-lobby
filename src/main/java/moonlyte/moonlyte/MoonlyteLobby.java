package moonlyte.moonlyte;

import moonlyte.moonlyte.commands.ServerSelector;
import moonlyte.moonlyte.events.PlayerDie;
import moonlyte.moonlyte.events.PlayerJoin;
import moonlyte.moonlyte.events.PlayerLeave;
import moonlyte.moonlyte.events.ServerSelectorInteract;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class MoonlyteLobby extends JavaPlugin {
private static MoonlyteLobby plugin;
    @Override
    public void onEnable() {
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
    }
    public static MoonlyteLobby getPlugin() {
        return plugin;
    }
}
