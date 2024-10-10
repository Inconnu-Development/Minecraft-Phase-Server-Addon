package de.inconnu.mpsa.commands;

import de.inconnu.mpsa.MPSA;
import de.inconnu.mpsa.manager.BackPackManager;
import de.inconnu.mpsa.manager.ConfigManager;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.UUID;

public class BackpackCommand implements CommandExecutor {

    @Getter @Setter
    private static boolean backapIsOpen = false;

    @Getter @Setter
    private static UUID openPlayerUUID;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(MPSA.getPrefix() + ConfigManager.getOnlyByPlayer());
            return true;
        }
        Player player = (Player) sender;

        if (!ConfigManager.isWarpsEnabled()) {
            player.sendMessage(MPSA.getPrefix() + ConfigManager.getAddonDisabled());
            return false;
        }

        if (ConfigManager.isPermissionsEnabled()) {
            if (!player.hasPermission("phase.backpack.use")) {
                player.sendMessage(MPSA.getPrefix() + ConfigManager.getNoPermission());
                return false;
            }
        }

        if (args.length != 0) {
            player.sendMessage(MPSA.getPrefix() + "§7/backpack");
            return false;
        }

        if (backapIsOpen) {
            player.sendMessage(MPSA.getPrefix() + ConfigManager.getBackpackIsInUse());
            return false;
        }

        backapIsOpen = true;
        openPlayerUUID = player.getUniqueId();

        Inventory inventory = BackPackManager.getBackPack();

        if (inventory != null) {
            player.openInventory(inventory);
        }


        return false;
    }
}
