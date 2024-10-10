package de.inconnu.mpsa.commands;

import de.inconnu.mpsa.MPSA;
import de.inconnu.mpsa.manager.ConfigManager;
import de.inconnu.mpsa.manager.HomesManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class HomesCommand  implements CommandExecutor {

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

        if (args.length != 0) {
            player.sendMessage(MPSA.getPrefix() + "§7/homes");
            return false;
        }

        player.sendMessage(MPSA.getPrefix() + ConfigManager.getYourHomes());
        ArrayList<String> homesList = HomesManager.getPlayerHomes(player.getUniqueId());
        if (!homesList.isEmpty()) {
            for (String home : homesList) {
                player.sendMessage("§7 - " + home);
            }
        }

        return false;
    }
}
