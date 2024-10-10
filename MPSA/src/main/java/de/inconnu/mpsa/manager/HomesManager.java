package de.inconnu.mpsa.manager;

import de.inconnu.mpsa.MPSA;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

public class HomesManager {

    public static File locfile = new File("plugins//MPSA//Homes//homesLocations.yml");
    public static File playerfile = new File("plugins//MPSA//Homes//homesPlayerInformation.yml");

    public static YamlConfiguration locs = YamlConfiguration.loadConfiguration(locfile);
    public static YamlConfiguration playerInfo = YamlConfiguration.loadConfiguration(playerfile);

    public static ArrayList<String> getPlayerHomes(UUID playerUUID) {

        ArrayList<String> playerLocationsIDList = (ArrayList<String>) playerInfo.getList(playerUUID.toString());

        if (playerLocationsIDList == null || playerLocationsIDList.isEmpty()) {
            playerLocationsIDList = new ArrayList<String>();
        }

        return playerLocationsIDList;

    }

    public static void addNewHome(Player player, String homeName) {

        ArrayList<String> homes = getPlayerHomes(player.getUniqueId());
        int currentHomes = homes.size();
        int maxHomes = ConfigManager.getHomeNumber();

        if (currentHomes >= maxHomes) {
            player.sendMessage(MPSA.getPrefix() + ConfigManager.getToManyHomes());
            return;
        }

        if (homes.contains(homeName)) {
            player.sendMessage(MPSA.getPrefix() + ConfigManager.getHomeAlreadyExists());
            return;
        }

        Location homeLocation = player.getLocation();

        addLocationIDToConfig(homeName, player.getUniqueId().toString());
        addLocationToConfig(homeLocation, player.getUniqueId().toString() + "." + homeName);

        player.sendMessage(MPSA.getPrefix() + ConfigManager.getNewHomeCreated());
    }

    public static void deleteHome(Player player, String homeName) {

        ArrayList<String> homes = getPlayerHomes(player.getUniqueId());

        if (!homes.contains(homeName)) {
            player.sendMessage(MPSA.getPrefix() + ConfigManager.getHomeNotExists());
            return;
        }

        Location homeLocation = player.getLocation();

        removeLocationIDToConfig(homeName, player.getUniqueId().toString());
        removeLocationFromConfig(player.getUniqueId().toString() + "." + homeName);

        player.sendMessage(MPSA.getPrefix() + ConfigManager.getHomeDeleted());
    }


    public static Location getHomeLocation(Player player, String LocationName) {

        String world = locs.getString(player.getUniqueId().toString() + "." + LocationName + ".World");
        double x = locs.getDouble(player.getUniqueId().toString() + "." + LocationName + ".X");
        double y = locs.getDouble(player.getUniqueId().toString() + "." + LocationName + ".Y");
        double z = locs.getDouble(player.getUniqueId().toString() + "." + LocationName + ".Z");
        float yaw = (float) locs.getDouble(player.getUniqueId().toString() + "." + LocationName + ".Yaw");
        float pitch = (float) locs.getDouble(player.getUniqueId().toString() + "." + LocationName + ".Pitch");

        if (world == null) {
            return null;
        }

        World homeWorld = Bukkit.getWorld(world);
        Location homeLocation = new Location(homeWorld, x, y, z, yaw, pitch);

        return homeLocation;
    }

    private static boolean addLocationToConfig(Location loc, String path) {
        locs.set(path+".World", loc.getWorld().getName());
        locs.set(path+".X", loc.getX());
        locs.set(path+".Y", loc.getY());
        locs.set(path+".Z", loc.getZ());
        locs.set(path+".Yaw", loc.getYaw());
        locs.set(path+".Pitch", loc.getPitch());

        try {
            locs.save(locfile);
        } catch (IOException e) {
            Bukkit.getLogger().warning(e.getMessage());
        }

        return true;
    }

    private static boolean removeLocationFromConfig(String path) {
        // Überprüfen, ob der Pfad existiert
        if (locs.contains(path)) {
            // Entfernen der Location aus der Config
            locs.set(path, null);

            try {
                // Speichern der aktualisierten Datei
                locs.save(locfile);
            } catch (IOException e) {
                Bukkit.getLogger().warning(e.getMessage());
            }

            return true;
        } else {
            // Wenn der Pfad nicht existiert
            Bukkit.getLogger().warning("Location path '" + path + "' does not exist in config.");
            return false;
        }
    }


    private static boolean addLocationIDToConfig(String homeID, String path) {
        ArrayList<String> playerLocationsIDList = (ArrayList<String>) playerInfo.getList(path);
        if (playerLocationsIDList == null || playerLocationsIDList.isEmpty()) {
            playerLocationsIDList = new ArrayList<String>();
        }
        playerLocationsIDList.add(homeID);

        playerInfo.set(path, playerLocationsIDList);

        try {
            playerInfo.save(playerfile);
        } catch (IOException e) {
            Bukkit.getLogger().warning(e.getMessage());
        }

        return true;
    }

    private static boolean removeLocationIDToConfig(String homeID, String path) {
        ArrayList<String> playerLocationsIDList = (ArrayList<String>) playerInfo.getList(path);
        if (playerLocationsIDList == null || playerLocationsIDList.isEmpty()) {
            return false;
        }
        playerLocationsIDList.remove(homeID);

        playerInfo.set(path, playerLocationsIDList);

        try {
            playerInfo.save(playerfile);
        } catch (IOException e) {
            Bukkit.getLogger().warning(e.getMessage());
        }

        return true;
    }

}
