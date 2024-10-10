package de.inconnu.mpsa.commands;

import de.inconnu.mpsa.MPSA;
import de.inconnu.mpsa.manager.ConfigManager;
import de.inconnu.mpsa.manager.HomesManager;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HomeCommand implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(MPSA.getPrefix() + ConfigManager.getOnlyByPlayer());
            return true;
        }
        Player player = (Player) sender;

        if (!ConfigManager.isHomesEnabled()) {
            player.sendMessage(MPSA.getPrefix() + ConfigManager.getAddonDisabled());
            return false;
        }

        if (ConfigManager.isPermissionsEnabled()) {
            if (!player.hasPermission("phase.home.use")) {
                player.sendMessage(MPSA.getPrefix() + ConfigManager.getNoPermission());
                return false;
            }
        }

        if (args.length != 1) {
            player.sendMessage(MPSA.getPrefix() + "§7/home <name>");
            return false;
        }

        Location location = HomesManager.getHomeLocation(player, args[0].toLowerCase());

        if (location == null) {
            player.sendMessage(MPSA.getPrefix() + ConfigManager.getHomeNotExists());
            return false;
        }

        player.teleport(location);
        player.sendMessage(MPSA.getPrefix() + ConfigManager.getTeleportToHome());

        return false;
    }
}
