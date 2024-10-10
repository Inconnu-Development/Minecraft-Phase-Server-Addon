package de.inconnu.mpsa.commands;

import de.inconnu.mpsa.MPSA;
import de.inconnu.mpsa.manager.ConfigManager;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class TpaCommand implements CommandExecutor {

    private HashMap<UUID, UUID> tpaRequests = new HashMap<>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(MPSA.getPrefix() + ConfigManager.getOnlyByPlayer());
            return true;
        }

        Player player = (Player) sender;

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

        if (args.length != 1) {
            player.sendMessage( MPSA.getPrefix() + "§7/tpa <Spielername>");
            return true;
        }

        Player target = Bukkit.getPlayer(args[0]);

        if (target == null) {
            player.sendMessage(MPSA.getPrefix() + ConfigManager.getPlayerNotFound());
            return true;
        }

        if (target.equals(player)) {
            player.sendMessage(MPSA.getPrefix() + ConfigManager.getCantTpToYourself());
            return true;
        }

        tpaRequests.put(target.getUniqueId(), player.getUniqueId());

        TextComponent message = new TextComponent(MPSA.getPrefix() + ConfigManager.getTpaRequest().replace("%player%", player.getDisplayName()));
        TextComponent accept = new TextComponent(ConfigManager.getTpaRequestAccept());
        accept.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tpaaccept"));

        // accept.setColor(net.md_5.bungee.api.ChatColor.GREEN); // Optional: grüne Farbe für den Akzeptieren-Button

        message.addExtra(accept);
        target.spigot().sendMessage(message);

        player.sendMessage(MPSA.getPrefix() + ConfigManager.getSendRequest().replace("%player%", target.getDisplayName()));

        return true;
    }

    public UUID getTpaRequest(UUID targetId) {
        return tpaRequests.get(targetId);
    }

    public void removeTpaRequest(UUID targetId) {
        tpaRequests.remove(targetId);
    }
}
