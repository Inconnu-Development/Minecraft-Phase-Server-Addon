package de.inconnu.mpsa.commands;

import de.inconnu.mpsa.MPSA;
import de.inconnu.mpsa.manager.ConfigManager;
import de.inconnu.mpsa.manager.HomesManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DelteHomeCommand implements CommandExecutor {

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
            if (!player.hasPermission("phase.home.delete")) {
                player.sendMessage(MPSA.getPrefix() + ConfigManager.getNoPermission());
                return false;
            }
        }

        if (args.length != 1) {
            player.sendMessage(MPSA.getPrefix() + "§7/delhome <name>");
            return false;
        }

        HomesManager.deleteHome(player, args[0].toLowerCase());
        return false;
    }
}
