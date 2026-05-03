package com.library.app.services;

import java.util.ArrayList;
import java.util.List;
import java.io.*;
import com.library.app.models.LibraryItem;

public class ItemManager {
    private List<LibraryItem> inventory;
    private static final String ROOT_FILE = "inventory.txt";
    private static final String DATA_FILE = "data/inventory.txt";
    private String activeFilePath;

    public ItemManager() {
        this.inventory = new ArrayList<>();
        this.activeFilePath = determineActiveFilePath();
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

    public boolean updateItem(String id, String newName, String newCreator, String newCategory, String newLocation) {
        LibraryItem item = findItemById(id);
        if (item != null) {
            item.setName(newName);
            item.setCreator(newCreator);
            item.setCategory(newCategory);
            item.setLocation(newLocation);
            saveToFile(); // Update file after modification
            return true;
        }
        return false;
    }

    public LibraryItem findItemById(String id) {
        String searchId = normalizeId(id);
        for (LibraryItem item : inventory) {
            if (item.getId() != null && item.getId().trim().equalsIgnoreCase(searchId)) return item;
        }
        return null;
    }

    public List<LibraryItem> getInventory() {
        return inventory;
    }

    private String normalizeId(String id) {
        return id == null ? "" : id.trim();
    }

    // --- FILE PERSISTENCE LOGIC ---

    private void saveToFile() {
        File file = new File(activeFilePath);
        File parent = file.getParentFile();
        if (parent != null && !parent.exists()) {
            parent.mkdirs();
        }

        try (PrintWriter writer = new PrintWriter(new FileWriter(file))) {
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
        File file = new File(activeFilePath);
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

    private String determineActiveFilePath() {
        File rootFile = new File(ROOT_FILE);
        File dataFile = new File(DATA_FILE);
        if (rootFile.exists()) {
            return ROOT_FILE;
        }
        if (dataFile.exists()) {
            return DATA_FILE;
        }
        // Default to root inventory file when neither exists.
        return ROOT_FILE;
    }
}

