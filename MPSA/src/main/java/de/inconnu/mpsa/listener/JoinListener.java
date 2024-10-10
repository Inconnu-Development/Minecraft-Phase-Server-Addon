package de.inconnu.mpsa.listener;

import de.inconnu.mpsa.MPSA;
import de.inconnu.mpsa.manager.ConfigManager;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

public class JoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerLoginEvent event) {
        Player player = event.getPlayer();
        String uuid = player.getUniqueId().toString();
        String name = player.getName();

        if (player.hasPermission("phase.admin.update")) {
            if (MPSA.isUpdateAvailable()) {

                TextComponent updateMessage = new TextComponent("§7[§aDownload latest Version§7]");
                updateMessage.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://builtbybit.com/resources/minecraft-phase-server-addon.52980/"));

                TextComponent message = new TextComponent("§7(https://builtbybit.com/resources/minecraft-phase-server-addon.52980/)");

                updateMessage.addExtra(message);

                player.sendMessage("");
                player.sendMessage(MPSA.prefix + "A new version of Minecraft-Phase-Server-Addon (MPSA) is available! Latest version: §a" + MPSA.getLatestVersion());
                player.sendMessage(MPSA.prefix + "You are on version: §c" + MPSA.getPluginVersion());
                player.sendMessage(MPSA.prefix + updateMessage);
                player.sendMessage(MPSA.prefix + "This message will only be displayed for Administrator.");
                player.sendMessage("");
            }
        }
    }

}
