package de.inconnu.mpsa.manager;

import de.inconnu.mpsa.MPSA;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class ConfigManager {

    private static boolean isDebugMode = false;
    private static boolean isPermissionsEnabled = true;
    private static boolean isWarpsEnabled = true;
    private static boolean isHomesEnabled = true;
    private static boolean isBackpackEnabled = true;
    private static int warpNumber = 0;
    private static int homeNumber = 0;
    private static int backpackSize = 0;

    @Getter
    private static int configVersion;

    @Getter
    private static boolean isTpaEnabled;

    @Getter
    private static String noPermission;
    @Getter
    private static String onlyByPlayer;
    @Getter
    private static String addonDisabled;

    @Getter
    private static String backpackInvTitle;
    @Getter
    private static String playerBackpackInvTitle;
    @Getter
    private static String backpackIsInUse;

    @Getter
    private static String yourHomes;
    @Getter
    private static String toManyHomes;
    @Getter
    private static String homeAlreadyExists;
    @Getter
    private static String homeAreDisabled;
    @Getter
    private static String homeNotExists;
    @Getter
    private static String teleportToHome;
    @Getter
    private static String newHomeCreated;
    @Getter
    private static String homeDeleted;

    @Getter
    private static String warps;
    @Getter
    private static String toManyWarps;
    @Getter
    private static String warpAlreadyExists;
    @Getter
    private static String warpsAreDisabled;
    @Getter
    private static String warpNotExists;
    @Getter
    private static String teleportToWarp;
    @Getter
    private static String newWarpCreated;
    @Getter
    private static String warpDeleted;

    @Getter
    private static String playerNotFound;
    @Getter
    private static String cantTpToYourself;
    @Getter
    private static String tpaRequest;
    @Getter
    private static String tpaRequestAccept;
    @Getter
    private static String sendRequest;
    @Getter
    private static String noOpenRequests;
    @Getter
    private static String playerNotOnline;
    @Getter
    private static String teleportTarget;
    @Getter
    private static String teleportCommandUser;

    public static void loadConfig() {
        try {
            MPSA.getPlugin().getConfig().options().copyDefaults(true);
            MPSA.getPlugin().saveConfig();

            configVersion = MPSA.getPlugin().getConfig().getInt("configVersion");
            isDebugMode = MPSA.getPlugin().getConfig().getBoolean("settings.enableDebugMode");
            isPermissionsEnabled = MPSA.getPlugin().getConfig().getBoolean("settings.enablePermissions");

            warpNumber = MPSA.getPlugin().getConfig().getInt("warps.maxWarpsPerPlayer");
            isWarpsEnabled = MPSA.getPlugin().getConfig().getBoolean("warps.enable");

            homeNumber = MPSA.getPlugin().getConfig().getInt("homes.maxHomesPerPlayer");
            isHomesEnabled = MPSA.getPlugin().getConfig().getBoolean("homes.enable");

            backpackSize = MPSA.getPlugin().getConfig().getInt("backpack.size");
            isBackpackEnabled = MPSA.getPlugin().getConfig().getBoolean("backpack.enable");

            isTpaEnabled = MPSA.getPlugin().getConfig().getBoolean("tpa.enable");

            MPSA.setPrefix(ChatColor.translateAlternateColorCodes('&', MPSA.getPlugin().getConfig().getString("messages.general.Prefix")));
            noPermission = ChatColor.translateAlternateColorCodes('&', MPSA.getPlugin().getConfig().getString("messages.general.noPermission"));
            onlyByPlayer = ChatColor.translateAlternateColorCodes('&', MPSA.getPlugin().getConfig().getString("messages.general.onlyByPlayer"));
            addonDisabled = ChatColor.translateAlternateColorCodes('&', MPSA.getPlugin().getConfig().getString("messages.general.addonDisabled"));

            playerBackpackInvTitle = ChatColor.translateAlternateColorCodes('&', MPSA.getPlugin().getConfig().getString("messages.backpack.playerBackpackInvTitle"));
            backpackInvTitle = ChatColor.translateAlternateColorCodes('&', MPSA.getPlugin().getConfig().getString("messages.backpack.publicBackpackInvTitle"));
            backpackIsInUse = ChatColor.translateAlternateColorCodes('&', MPSA.getPlugin().getConfig().getString("messages.backpack.backpackIsInUse"));

            yourHomes = ChatColor.translateAlternateColorCodes('&', MPSA.getPlugin().getConfig().getString("messages.homes.yourHomes"));
            toManyHomes = ChatColor.translateAlternateColorCodes('&', MPSA.getPlugin().getConfig().getString("messages.homes.toManyHomes"));
            homeAlreadyExists = ChatColor.translateAlternateColorCodes('&', MPSA.getPlugin().getConfig().getString("messages.homes.homeAlreadyExists"));
            homeAreDisabled = ChatColor.translateAlternateColorCodes('&', MPSA.getPlugin().getConfig().getString("messages.homes.homeAreDisabled"));
            homeNotExists = ChatColor.translateAlternateColorCodes('&', MPSA.getPlugin().getConfig().getString("messages.homes.homeNotExists"));
            teleportToHome = ChatColor.translateAlternateColorCodes('&', MPSA.getPlugin().getConfig().getString("messages.homes.teleportToHome"));
            newHomeCreated = ChatColor.translateAlternateColorCodes('&', MPSA.getPlugin().getConfig().getString("messages.homes.newHomeCreated"));
            homeDeleted = ChatColor.translateAlternateColorCodes('&', MPSA.getPlugin().getConfig().getString("messages.homes.homeDeleted"));

            warps = ChatColor.translateAlternateColorCodes('&', MPSA.getPlugin().getConfig().getString("messages.warps.warps"));
            toManyWarps = ChatColor.translateAlternateColorCodes('&', MPSA.getPlugin().getConfig().getString("messages.warps.toManyWarps"));
            warpAlreadyExists = ChatColor.translateAlternateColorCodes('&', MPSA.getPlugin().getConfig().getString("messages.warps.warpAlreadyExists"));
            warpsAreDisabled = ChatColor.translateAlternateColorCodes('&', MPSA.getPlugin().getConfig().getString("messages.warps.warpsAreDisabled"));
            warpNotExists = ChatColor.translateAlternateColorCodes('&', MPSA.getPlugin().getConfig().getString("messages.warps.warpNotExists"));
            teleportToWarp = ChatColor.translateAlternateColorCodes('&', MPSA.getPlugin().getConfig().getString("messages.warps.teleportToWarp"));
            newWarpCreated = ChatColor.translateAlternateColorCodes('&', MPSA.getPlugin().getConfig().getString("messages.warps.newWarpCreated"));
            warpDeleted = ChatColor.translateAlternateColorCodes('&', MPSA.getPlugin().getConfig().getString("messages.warps.warpDeleted"));

            playerNotFound = ChatColor.translateAlternateColorCodes('&', MPSA.getPlugin().getConfig().getString("messages.tpa.playerNotFound"));
            cantTpToYourself = ChatColor.translateAlternateColorCodes('&', MPSA.getPlugin().getConfig().getString("messages.tpa.cantTpToYourself"));
            tpaRequest = ChatColor.translateAlternateColorCodes('&', MPSA.getPlugin().getConfig().getString("messages.tpa.tpaRequest"));
            tpaRequestAccept = ChatColor.translateAlternateColorCodes('&', MPSA.getPlugin().getConfig().getString("messages.tpa.tpaRequestAccept"));
            sendRequest = ChatColor.translateAlternateColorCodes('&', MPSA.getPlugin().getConfig().getString("messages.tpa.sendRequest"));
            noOpenRequests = ChatColor.translateAlternateColorCodes('&', MPSA.getPlugin().getConfig().getString("messages.tpa.noOpenRequests"));
            playerNotOnline = ChatColor.translateAlternateColorCodes('&', MPSA.getPlugin().getConfig().getString("messages.tpa.playerNotOnline"));
            teleportTarget = ChatColor.translateAlternateColorCodes('&', MPSA.getPlugin().getConfig().getString("messages.tpa.teleportTarget"));
            teleportCommandUser = ChatColor.translateAlternateColorCodes('&', MPSA.getPlugin().getConfig().getString("messages.tpa.teleportCommandUser"));

        }catch(Exception error) {
            Bukkit.getLogger().warning(error.getMessage());
        }
    }

    public static boolean isDebugMode() {
        return isDebugMode;
    }

    public static boolean isPermissionsEnabled() {
        return isPermissionsEnabled;
    }

    public static boolean isHomesEnabled() {
        return isHomesEnabled;
    }
    public static int getHomeNumber() {
        return homeNumber;
    }

    public static boolean isBackpackEnabled() {
        return isBackpackEnabled;
    }
    public static int getBackpackSize() {
        return backpackSize;
    }

    public static boolean isWarpsEnabled() {
        return isWarpsEnabled;
    }
    public static int getWarpNumber() {
        return warpNumber;
    }
}


