package de.inconnu.mpsa.manager;

import de.inconnu.mpsa.MPSA;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Base64;

public class BackPackManager {

    public static File invfile = new File("plugins//MPSA//Backpack//BackpackInv.yml");

    public static YamlConfiguration invData = YamlConfiguration.loadConfiguration(invfile);

    public static boolean saveBackpack(Inventory inventory)
    {
        String data = inventoryToBase64(inventory);
        invData.set("backpackInv", data);

        try {
            invData.save(invfile);
        } catch (IOException e) {
            Bukkit.getLogger().warning(e.getMessage());
            return false;
        }

        return true;
    }

    public static Inventory getBackPack()
    {
        return getInventoryFromBase64(invData.getString("backpackInv"));
    }

    private static String inventoryToBase64(Inventory inventory) throws IllegalStateException {
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            BukkitObjectOutputStream dataOutput = new BukkitObjectOutputStream(outputStream);

            dataOutput.writeInt(inventory.getSize());
            for (int i = 0; i < inventory.getSize(); i++) {
                dataOutput.writeObject(inventory.getItem(i));
            }

            return Base64.getEncoder().encodeToString(outputStream.toByteArray());

        } catch (Exception e) {
            throw new IllegalStateException("Unable to save item stacks.", e);
        }
    }

    private static Inventory getInventoryFromBase64(String data) {
        if (data == null) {
            return Bukkit.createInventory(null, ConfigManager.getBackpackSize(), ConfigManager.getBackpackInvTitle());
        }

        try {
            byte[] decodedData = Base64.getDecoder().decode(data);
            try (ByteArrayInputStream inputStream = new ByteArrayInputStream(decodedData);
                 BukkitObjectInputStream dataInput = new BukkitObjectInputStream(inputStream)) {

                int size = dataInput.readInt();
                Inventory inventory = Bukkit.createInventory(null, ConfigManager.getBackpackSize(), ConfigManager.getBackpackInvTitle());

                for (int i = 0; i < size; i++) {
                    inventory.setItem(i, (ItemStack) dataInput.readObject());
                }

                return inventory;

            }
        } catch (IllegalArgumentException | IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            MPSA.getPlugin().getLogger().warning("The Backpack changed to a smaller size in the config.yml. Please change it back to the bigger size or delete the Backpack folder in the MPSA folder.");
            return null;
        }
    }
}
