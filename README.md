# Minecraft Phase Server Addon 🎮🛠️

Minecraft Phase Server Addon is a plugin for Spigot and Paper (version 1.21.1) that adds useful features like Warps, Homes, Backpacks, and more to enhance your server's gameplay experience.

You dont need any permissions System if you just play with some friends in this two weeks minecraft phase.
Plug and Play.

### 📥 Download Plugin
🔗 [GitHub Releases](https://github.com/Inconnu-Development/Minecraft-Phase-Server-Addon/releases)

### ✨ Features

The Features can be disbaled one by one if you dont need/want them.

- 🌍 **Warps**: Set warp points to allow players to quickly teleport to specific locations.
- 🏠 **Homes**: Allow players to set personal home points for easy teleportation back to their base.
- 🤝 **TPA**: Teleport to your friends.
- 🎒 **Backpack**: Provides players with additional inventory space through a portable backpack. *(coming soon)*
- 🚀 More useful addons and features coming in future updates!

### ⚙️ Installation

1. **Spigot or Paper 1.21.1**: Ensure your server is running on Minecraft version 1.21.1.
2. Download the latest version of the plugin from [GitHub Releases](https://github.com/Inconnu-Development/Minecraft-Phase-Server-Addon/releases).
3. Place the `.jar` file into your server's plugins folder.
4. Restart the server or use `/reload` to load the plugin.

### 🔧 Configuration

After installation, you will find a configuration file in the plugins folder. You can adjust various settings such as the maximum number of warps, homes, or the size of the backpack.

**Example Configuration (config.yml):**

```
yaml
settings:
  enableDebugMode: false
  enablePermissions: true
warps:
  enable: true
  maxWarpsPerPlayer: 5
homes:
  enable: true
  maxHomesPerPlayer: 5
backpack:
  enable: true
  size: 27
tpa:
  enable: true
messages:
  general:
    Prefix: "&8[&9MPSA§8] &7"
    noPermission: "§cYou do not have permission for this."
    onlyByPlayer: "§cThis command can only be executed by a player."
    addonDisabled: "§cThis addon has been disabled."
  homes:
    yourHomes: "§7These are your homes:"
    toManyHomes: "§7You cannot create any more homes."
    homeAlreadyExists: "§7This home already exists."
    homeAreDisabled: "§7Homes are disabled."
    homeNotExists: "§7This home does not exist."
    teleportToHome: "§7You have teleported."
    newHomeCreated: "§7You have created a new home."
    homeDeleted: "§7You deleted this home."
  warps:
    warps: "§7These are all the warps:"
    toManyWarps: "§7You cannot create any more warps."
    warpAlreadyExists: "§7This warp already exists."
    warpsAreDisabled: "§7Warps are disabled."
    warpNotExists: "§7This warp does not exist."
    teleportToWarp: "§7You have teleported."
    newWarpCreated: "§7You have created a new warp."
    warpDeleted: "§7You deleted this warp."
  tpa:
    playerNotFound: "§7Player not found."
    cantTpToYourself: "§7You cannot teleport to yourself."
    tpaRequest: "§7%player% wants to teleport to you."
    tpaRequestAccept: "§7[§aAccept§7]"
    sendRequest: "§7Request sent to %player%."
    noOpenRequests: "§7There are no pending TPA requests."
    playerNotOnline: "The player who sent the request is no longer online."
    teleportTarget: "You have been teleported to %player%."
    teleportCommandUser: "You accepted the teleport request from %player%."
  backpack:
    backpackInvTitle: "§7Backpack"
    backpackIsInUse: "§cThe backpack is currently being used by another player."
```

### 🛠️ Commands

- `/setwarp <name>` – Sets a warp point with the specified name.
- `/warp <name>` – Teleports you to a warp point.
- `/delwarp <name>` – Deletes a warp point.
- `/sethome <name>` – Sets a home point with the specified name.
- `/home <name>` – Teleports you to your home point.
- `/delhome <name>` – Deletes a home point.
- `/tpa <name>` – Teleport Request.
- `/tpaaccept` – Accept Teleport.
- `/backpack` – Opens your backpack.

### 🔑 Permissions

To manage access to specific commands, you can use a permissions management plugin like LuckPerms.
Can be disabled in the config.yml

- `phase.warp.use` – Allows the use of the `/warp` command.
- `phase.warp.set` – Allows players to set warps using `/setwarp`.
- `phase.warp.delete` – Allows the deletion of warp points with `/delwarp`.
- `phase.home.use` – Allows the use of the `/home` command.
- `phase.home.set` – Allows players to set home points using `/sethome`.
- `phase.home.delete` – Allows the deletion of home points with `/delhome`.
- `phase.tpa.use` – TPA requests and accept those.
- `phase.backpack.use` – Allows players to use the backpack.

### 📋 Requirements

- Minecraft Server 1.21.1
- Spigot or Paper

### 🚧 Planned Features

- Additional backpack customization (e.g., expandable sizes, upgrades).
- Warp categories or groups for better organization.

### 👥 Contributors

- **Ente_3** – Developer and Maintainer
