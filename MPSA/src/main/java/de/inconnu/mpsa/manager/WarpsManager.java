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

public class WarpsManager {

    public static File locfile = new File("plugins//MPSA//Warps//warpsLocations.yml");
    public static File playerfile = new File("plugins//MPSA//Warps//warpsPlayerInformation.yml");

    public static YamlConfiguration locs = YamlConfiguration.loadConfiguration(locfile);
    public static YamlConfiguration playerInfo = YamlConfiguration.loadConfiguration(playerfile);

    public static ArrayList<String> getWarps() {

        ArrayList<String> WarpsNameList = (ArrayList<String>) playerInfo.getList("warps");

        if (WarpsNameList == null || WarpsNameList.isEmpty()) {
            WarpsNameList = new ArrayList<String>();
        }

        return WarpsNameList;

    }

    public static void addNewWarp(Player player, String warpName) {

        ArrayList<String> warps = getWarps();
        int currentWarps = warps.size();
        int maxWarps = ConfigManager.getWarpNumber();

        if (currentWarps >= maxWarps) {
            player.sendMessage(MPSA.getPrefix() + ConfigManager.getToManyWarps());
            return;
        }

        if (warps.contains(warpName)) {
            player.sendMessage(MPSA.getPrefix() + ConfigManager.getWarpAlreadyExists());
            return;
        }

        Location warpLocation = player.getLocation();

        addLocationIDToConfig(warpName, "warps");
        addLocationToConfig(warpLocation, warpName);

        player.sendMessage(MPSA.getPrefix() + ConfigManager.getNewWarpCreated());
    }

    public static void deleteWarp(Player player, String warpName) {

        ArrayList<String> warps = getWarps();

        if (!warps.contains(warpName)) {
            player.sendMessage(MPSA.getPrefix() + ConfigManager.getWarpNotExists());
            return;
        }

        removeLocationIDToConfig(warpName);
        removeLocationFromConfig(warpName);

        player.sendMessage(MPSA.getPrefix() + ConfigManager.getWarpDeleted());
    }


    public static Location getWarpLocation(String LocationName) {

        String world = locs.getString(LocationName + ".World");
        double x = locs.getDouble(LocationName + ".X");
        double y = locs.getDouble(LocationName + ".Y");
        double z = locs.getDouble(LocationName + ".Z");
        float yaw = (float) locs.getDouble(LocationName + ".Yaw");
        float pitch = (float) locs.getDouble(LocationName + ".Pitch");

        if (world == null) {
            return null;
        }

        World warpWorld = Bukkit.getWorld(world);
        Location warpLocation = new Location(warpWorld, x, y, z, yaw, pitch);

        return warpLocation;
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

        if (locs.contains(path)) {
            locs.set(path, null);

            try {
                locs.save(locfile);
            } catch (IOException e) {
                Bukkit.getLogger().warning(e.getMessage());
            }

            return true;
        } else {
            Bukkit.getLogger().warning("Location path '" + path + "' does not exist in config.");
            return false;
        }
    }


    private static boolean addLocationIDToConfig(String warpID, String path) {
        ArrayList<String> playerLocationsIDList = (ArrayList<String>) playerInfo.getList(path);
        if (playerLocationsIDList == null || playerLocationsIDList.isEmpty()) {
            playerLocationsIDList = new ArrayList<String>();
        }
        playerLocationsIDList.add(warpID);

        playerInfo.set(path, playerLocationsIDList);

        try {
            playerInfo.save(playerfile);
        } catch (IOException e) {
            Bukkit.getLogger().warning(e.getMessage());
        }

        return true;
    }

    private static boolean removeLocationIDToConfig(String warpID) {
        ArrayList<String> playerLocationsIDList = (ArrayList<String>) playerInfo.getList("warps");
        if (playerLocationsIDList == null || playerLocationsIDList.isEmpty()) {
            return false;
        }
        playerLocationsIDList.remove(warpID);

        playerInfo.set("warps", playerLocationsIDList);

        try {
            playerInfo.save(playerfile);
        } catch (IOException e) {
            Bukkit.getLogger().warning(e.getMessage());
        }

        return true;
    }

}
