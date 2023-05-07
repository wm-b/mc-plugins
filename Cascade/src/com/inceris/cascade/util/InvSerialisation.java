package com.inceris.cascade.util;

import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static org.bukkit.Bukkit.getServer;

public class InvSerialisation {

    public static String toBase64(ItemStack[] paramArrayOfItemStack) {
        if (paramArrayOfItemStack == null)
            return null;
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            BukkitObjectOutputStream bukkitObjectOutputStream = new BukkitObjectOutputStream(byteArrayOutputStream);

            bukkitObjectOutputStream.writeInt(paramArrayOfItemStack.length);

            for (ItemStack itemStack : paramArrayOfItemStack) {
                bukkitObjectOutputStream.writeObject(itemStack);
            }

            bukkitObjectOutputStream.close();
            return Base64Coder.encodeLines(byteArrayOutputStream.toByteArray());
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    public static String toBase64(ItemStack paramItemStack) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            BukkitObjectOutputStream bukkitObjectOutputStream = new BukkitObjectOutputStream(byteArrayOutputStream);

            bukkitObjectOutputStream.writeObject(paramItemStack);

            bukkitObjectOutputStream.close();
            return Base64Coder.encodeLines(byteArrayOutputStream.toByteArray());
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    public static String toBase64(Object obj) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            BukkitObjectOutputStream bukkitObjectOutputStream = new BukkitObjectOutputStream(byteArrayOutputStream);

            bukkitObjectOutputStream.writeObject(obj);

            bukkitObjectOutputStream.close();
            return Base64Coder.encodeLines(byteArrayOutputStream.toByteArray());
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    public static boolean isCustom(ItemStack i) {
        if (i.getType() == Material.FILLED_MAP || i.getType() == Material.WRITTEN_BOOK
                || i.getType() == Material.WRITABLE_BOOK) {
            return true;
        }
        if (i.hasItemMeta()) {
            return true;
        }
        if (i.getEnchantments().size() > 0) {
            return true;
        }
        return false;
    }

    public static String toBase64(Inventory paramInventory) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            BukkitObjectOutputStream bukkitObjectOutputStream = new BukkitObjectOutputStream(byteArrayOutputStream);

            bukkitObjectOutputStream.writeInt(paramInventory.getSize());

            for (byte b = 0; b < paramInventory.getSize(); b++) {
                bukkitObjectOutputStream.writeObject(paramInventory.getItem(b));
            }

            bukkitObjectOutputStream.close();
            return Base64Coder.encodeLines(byteArrayOutputStream.toByteArray());
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    public static Inventory toInventory(String paramString, InventoryHolder holder, String title) {
        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(Base64Coder.decodeLines(paramString));
            BukkitObjectInputStream bukkitObjectInputStream = new BukkitObjectInputStream(byteArrayInputStream);
            int filesize = bukkitObjectInputStream.readInt();
            int size = (int) Math.ceil(filesize / 9.0) * 9;
            Inventory inventory = getServer().createInventory(holder, size, title);

            for (byte b = 0; b < filesize; b++) {
                inventory.setItem(b, (ItemStack) bukkitObjectInputStream.readObject());
            }

            bukkitObjectInputStream.close();
            return inventory;
        } catch (Exception exc) {
            exc.printStackTrace();
        }
        return null;
    }

    public static Inventory toInventory(String paramString) {
        return toInventory(paramString, null, "Inventory");
    }

    public static ItemStack[] toItemStackArray(String paramString) {
        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(Base64Coder.decodeLines(paramString));
            BukkitObjectInputStream bukkitObjectInputStream = new BukkitObjectInputStream(byteArrayInputStream);
            ItemStack[] arrayOfItemStack = new ItemStack[bukkitObjectInputStream.readInt()];

            for (byte b = 0; b < arrayOfItemStack.length; b++) {
                arrayOfItemStack[b] = (ItemStack) bukkitObjectInputStream.readObject();
            }

            bukkitObjectInputStream.close();
            return arrayOfItemStack;
        } catch (Exception exc) {
            exc.printStackTrace();
        }
        return null;
    }

    public static ItemStack toItemStack(String paramString) {
        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(Base64Coder.decodeLines(paramString));
            BukkitObjectInputStream bukkitObjectInputStream = new BukkitObjectInputStream(byteArrayInputStream);

            ItemStack itemStack = (ItemStack) bukkitObjectInputStream.readObject();

            bukkitObjectInputStream.close();
            return itemStack;
        } catch (Exception exc) {
            exc.printStackTrace();
        }
        return null;
    }

    public static Object toObject(String paramString) {
        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(Base64Coder.decodeLines(paramString));
            BukkitObjectInputStream bukkitObjectInputStream = new BukkitObjectInputStream(byteArrayInputStream);

            Object obj = bukkitObjectInputStream.readObject();

            bukkitObjectInputStream.close();
            return obj;
        } catch (Exception exc) {
            exc.printStackTrace();
        }
        return null;
    }
}
