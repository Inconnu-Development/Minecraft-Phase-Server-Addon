package de.inconnu.mpsa.commands;

import de.inconnu.mpsa.MPSA;
import de.inconnu.mpsa.manager.ConfigManager;
import de.inconnu.mpsa.manager.HomesManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetHomeCommand implements CommandExecutor {

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
            if (!player.hasPermission("phase.home.set")) {
                player.sendMessage(MPSA.getPrefix() + ConfigManager.getNoPermission());
                return false;
            }
        }

        if (args.length != 1) {
            player.sendMessage(MPSA.getPrefix() + "§7/sethome <name>");
            return false;
        }

        HomesManager.addNewHome(player, args[0].toLowerCase());
        return false;
    }
}
