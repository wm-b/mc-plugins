package com.inceris.invcopy;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;

public class Serialisation {
	/**
	 * Converts the player inventory to a String array of Base64 strings. First
	 * string is the content and second string is the armor.
	 * 
	 * @param playerInventory to turn into an array of strings.
	 * @return Array of strings: [ main content, armor content ]
	 * @throws IllegalStateException
	 */
	public static byte[][] playerInventoryToBase64(PlayerInventory playerInventory) throws IllegalStateException {
		// get the main content part, this doesn't return the armor
		byte[] content = inventoryToBase64(playerInventory.getStorageContents());
		byte[] armor = itemStackArrayToBase64(playerInventory.getArmorContents());
		byte[] offHand = itemStackToBase64(playerInventory.getItemInOffHand());

		return new byte[][] { content, armor, offHand };
	}

	/**
	 * 
	 * A method to serialize an {@link ItemStack} array to Base64 String.
	 * 
	 * <p />
	 * 
	 * Based off of {@link #toBase64(Inventory)}.
	 * 
	 * @param items to turn into a Base64 String.
	 * @return Base64 string of the items.
	 * @throws IllegalStateException
	 */
	public static PlayerInventory playerInventoryFromBase64(byte[] contents, byte[] armor, byte[] offHand, PlayerInventory inv) {
		try {
			inv.setStorageContents(inventoryFromBase64(contents).getContents());
			inv.setArmorContents(itemStackArrayFromBase64(armor));
			inv.setItemInOffHand(itemStackFromBase64(offHand));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return inv;
	}
	
	public static byte[] itemStackArrayToBase64(ItemStack[] items) throws IllegalStateException {
		try {
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			BukkitObjectOutputStream dataOutput = new BukkitObjectOutputStream(outputStream);

			// Write the size of the inventory
			dataOutput.writeInt(items.length);

			// Save every element in the list
			for (int i = 0; i < items.length; i++) {
				dataOutput.writeObject(items[i]);
			}

			// Serialize that array
			dataOutput.close();
			return outputStream.toByteArray();
		} catch (Exception e) {
			throw new IllegalStateException("Unable to save item stacks.", e);
		}
	}
	
	public static byte[] itemStackToBase64(ItemStack item) throws IllegalStateException {
		try {
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			BukkitObjectOutputStream dataOutput = new BukkitObjectOutputStream(outputStream);

			// Save every element in the list
			dataOutput.writeObject(item);

			// Serialize that array
			dataOutput.close();
			return outputStream.toByteArray();
		} catch (Exception e) {
			throw new IllegalStateException("Unable to save item stacks.", e);
		}
	}

	/**
	 * A method to serialize an inventory to Base64 string.
	 * 
	 * <p />
	 * 
	 * Special thanks to Comphenix in the Bukkit forums or also known as aadnk on
	 * GitHub.
	 * 
	 * <a href="https://gist.github.com/aadnk/8138186">Original Source</a>
	 * 
	 * @param itemStacks to serialize
	 * @return Base64 string of the provided inventory
	 * @throws IllegalStateException
	 */
	public static byte[] inventoryToBase64(ItemStack[] itemStacks) throws IllegalStateException {
		try {
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			BukkitObjectOutputStream dataOutput = new BukkitObjectOutputStream(outputStream);

			// Write the size of the inventory
			dataOutput.writeInt(itemStacks.length);

			// Save every element in the list
			for (int i = 0; i < itemStacks.length; i++) {
				dataOutput.writeObject(itemStacks[i]);
			}

			// Serialize that array
			dataOutput.close();
			return outputStream.toByteArray();
		} catch (Exception e) {
			throw new IllegalStateException("Unable to save item stacks.", e);
		}
	}

	/**
	 * 
	 * A method to get an {@link Inventory} from an encoded, Base64, string.
	 * 
	 * <p />
	 * 
	 * Special thanks to Comphenix in the Bukkit forums or also known as aadnk on
	 * GitHub.
	 * 
	 * <a href="https://gist.github.com/aadnk/8138186">Original Source</a>
	 * 
	 * @param data Base64 string of data containing an inventory.
	 * @return Inventory created from the Base64 string.
	 * @throws IOException
	 */
	public static Inventory inventoryFromBase64(byte[] data) throws IOException {
		try {
			ByteArrayInputStream inputStream = new ByteArrayInputStream(data);
			BukkitObjectInputStream dataInput = new BukkitObjectInputStream(inputStream);
			Inventory inventory = Bukkit.getServer().createInventory(null, dataInput.readInt());

			// Read the serialized inventory
			for (int i = 0; i < inventory.getSize(); i++) {
				inventory.setItem(i, (ItemStack) dataInput.readObject());
			}

			dataInput.close();
			return inventory;
		} catch (ClassNotFoundException e) {
			throw new IOException("Unable to decode class type.", e);
		}
	}

	/**
	 * Gets an array of ItemStacks from Base64 string.
	 * 
	 * <p />
	 * 
	 * Base off of {@link #fromBase64(String)}.
	 * 
	 * @param data Base64 string to convert to ItemStack array.
	 * @return ItemStack array created from the Base64 string.
	 * @throws IOException
	 */
	public static ItemStack[] itemStackArrayFromBase64(byte[] data) throws IOException {
		try {
			ByteArrayInputStream inputStream = new ByteArrayInputStream(data);
			BukkitObjectInputStream dataInput = new BukkitObjectInputStream(inputStream);
			ItemStack[] items = new ItemStack[dataInput.readInt()];

			// Read the serialized inventory
			for (int i = 0; i < items.length; i++) {
				items[i] = (ItemStack) dataInput.readObject();
			}

			dataInput.close();
			return items;
		} catch (ClassNotFoundException e) {
			throw new IOException("Unable to decode class type.", e);
		}
	}

	public static ItemStack itemStackFromBase64(byte[] data) throws IOException {
		try {
			ByteArrayInputStream inputStream = new ByteArrayInputStream(data);
			BukkitObjectInputStream dataInput = new BukkitObjectInputStream(inputStream);
			ItemStack item = (ItemStack) dataInput.readObject();

			dataInput.close();
			return item;
		} catch (ClassNotFoundException e) {
			throw new IOException("Unable to decode class type.", e);
		}
	}
}
