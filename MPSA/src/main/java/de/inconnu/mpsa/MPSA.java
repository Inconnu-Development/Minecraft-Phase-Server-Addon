package de.inconnu.mpsa;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import de.inconnu.mpsa.commands.*;
import de.inconnu.mpsa.listener.BackpackListener;
import de.inconnu.mpsa.manager.ConfigManager;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public final class MPSA extends JavaPlugin {

    @Getter
    private static String pluginVersion;
    @Getter
    private static String latestVersion;

    @Getter
    public static Plugin plugin;

    @Getter @Setter
    public static String prefix = "§8[§9MPSA§8] §7";

    @Override
    public void onEnable() {
        // Plugin startup logic
        plugin = this;

        ConfigManager.loadConfig();
        registerCommands();
        registerListener();

        pluginVersion = getDescription().getVersion();
        if (isUpdateAvailable()) {
            getLogger().warning("");
            getLogger().warning("A new version of Minecraft-Phase-Server-Addon (MPSA) is available! Latest version: " + latestVersion);
            getLogger().warning("You are on version: " + pluginVersion);
            getLogger().warning("Download latest Version: https://builtbybit.com/resources/minecraft-phase-server-addon.52980/");
            getLogger().warning("");
        } else {
            getLogger().info("Minecraft-Phase-Server-Addon (MPSA) is up to date.");
        }

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void registerCommands() {
        this.getCommand("home").setExecutor(new HomeCommand());
        this.getCommand("sethome").setExecutor(new SetHomeCommand());
        this.getCommand("delhome").setExecutor(new DelteHomeCommand());
        this.getCommand("homes").setExecutor(new HomesCommand());

        this.getCommand("warp").setExecutor(new WarpCommand());
        this.getCommand("setwarp").setExecutor(new SetWarpCommand());
        this.getCommand("delwarp").setExecutor(new DelteWarpCommand());
        this.getCommand("warps").setExecutor(new WarpsCommand());

        this.getCommand("backpack").setExecutor(new BackpackCommand());

        TpaCommand tpaCommand = new TpaCommand();
        this.getCommand("tpa").setExecutor(tpaCommand);
        this.getCommand("tpaaccept").setExecutor(new TpaAcceptCommand(tpaCommand));

    }

    private void registerListener() {
        PluginManager pluginManager = Bukkit.getPluginManager();

        pluginManager.registerEvents(new BackpackListener(), this);
    }

    public static boolean isUpdateAvailable() {

        try {
            URL url = new URL("https://api.github.com/repos/Inconnu-Development/Minecraft-Phase-Server-Addon/releases/latest");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("Accept", "application/vnd.github.v3+json");

            InputStreamReader reader = new InputStreamReader(connection.getInputStream());

            JsonParser jsonParser = new JsonParser();
            JsonObject jsonObject = jsonParser.parse(reader).getAsJsonObject();

            if (jsonObject != null && jsonObject.has("tag_name")) {
                latestVersion = jsonObject.get("tag_name").getAsString().replace("v", "");

                return !pluginVersion.equals(latestVersion);
            } else {
                Bukkit.getLogger().warning("Unexpected GitHub API response format. At getting latest plugin Version");
                return false;
            }
        } catch (Exception e) {
            if (ConfigManager.isDebugMode()) {
                Bukkit.getLogger().warning("Error checking for plugin updates: " + e.getMessage());
            } else {
                Bukkit.getLogger().warning("Error checking for plugin updates, please check for updates manually");
            }
            return false;
        }

    }

}
