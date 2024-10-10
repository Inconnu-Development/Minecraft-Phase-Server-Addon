package de.inconnu.mpsa.commands;

import de.inconnu.mpsa.MPSA;
import de.inconnu.mpsa.manager.ConfigManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class TpaAcceptCommand implements CommandExecutor {


    private final TpaCommand tpaCommand;

    public TpaAcceptCommand(TpaCommand tpaCommand) {
        this.tpaCommand = tpaCommand;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(MPSA.getPrefix() + ConfigManager.getOnlyByPlayer());
            return true;
        }

        Player player = (Player) sender;

        UUID requesterId = tpaCommand.getTpaRequest(player.getUniqueId());

        if (!ConfigManager.isTpaEnabled()) {
            player.sendMessage(MPSA.getPrefix() + ConfigManager.getAddonDisabled());
            return false;
        }

        if (ConfigManager.isPermissionsEnabled()) {
            if (!player.hasPermission("phase.tpa.use")) {
                player.sendMessage(MPSA.getPrefix() + ConfigManager.getNoPermission());
                return false;
            }
        }

        if (requesterId == null) {
            player.sendMessage(MPSA.getPrefix() + ConfigManager.getNoOpenRequests());
            return true;
        }

        Player requester = player.getServer().getPlayer(requesterId);

        if (requester == null) {
            player.sendMessage(MPSA.getPrefix() + ConfigManager.getPlayerNotOnline());
            tpaCommand.removeTpaRequest(player.getUniqueId());
            return true;
        }

        requester.teleport(player.getLocation());
        requester.sendMessage(MPSA.getPrefix() + ConfigManager.getTeleportTarget().replace("%player%", player.getDisplayName()));
        player.sendMessage(MPSA.getPrefix() + ConfigManager.getTeleportCommandUser().replace("%player%", requester.getDisplayName()));

        tpaCommand.removeTpaRequest(player.getUniqueId());

        return true;
    }
}
