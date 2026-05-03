package com.library.app.services;

import java.util.ArrayList;
import java.util.List;
import java.io.*;
import com.library.app.models.LibraryItem;

public class ItemManager {
    private List<LibraryItem> inventory;
    private final String FILE_NAME = "inventory.txt";

    public ItemManager() {
        this.inventory = new ArrayList<>();
        loadFromFile(); // Load existing data as soon as the manager is created
    }

    public void registerItem(LibraryItem item) {
        if (item != null) {
            inventory.add(item);
            saveToFile(); // Save to file immediately
            System.out.println("Saved to file: " + item.getName());
        }
    }

    public boolean deleteItemById(String id) {
        LibraryItem toRemove = findItemById(id);
        if (toRemove != null) {
            inventory.remove(toRemove);
            saveToFile(); // Update file after deletion
            return true;
        }
        return false;
    }

    public LibraryItem findItemById(String id) {
        for (LibraryItem item : inventory) {
            if (item.getId().equalsIgnoreCase(id)) return item;
        }
        return null;
    }

    public List<LibraryItem> getInventory() {
        return inventory;
    }

    // --- FILE PERSISTENCE LOGIC ---

    private void saveToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (LibraryItem item : inventory) {
                // Save data separated by a delimiter like "|"
                writer.println(item.getName() + "|" + 
                               item.getCreator() + "|" + 
                               item.getId() + "|" + 
                               item.getCategory() + "|" + 
                               item.getLocation());
            }
        } catch (IOException e) {
            System.err.println("Error saving to file: " + e.getMessage());
        }
    }

    private void loadFromFile() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return; // Nothing to load yet

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split("\\|");
                if (data.length == 5) {
                    inventory.add(new LibraryItem(data[0], data[1], data[2], data[3], data[4]));
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading file: " + e.getMessage());
        }
    }
}
