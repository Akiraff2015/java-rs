package com.akiraff.gui;

import com.akiraff.api.Item;

import java.util.HashMap;
import java.util.Map;

public class BarrowsLog {
    private HashMap<Item, Integer> itemList = new HashMap<>();

    private static BarrowsLog ourInstance = new BarrowsLog();

    public static BarrowsLog getInstance() {

        return ourInstance;
    }

    private BarrowsLog() {
    }

    public void addItem(Item item, int amount) {
        itemList.put(item, amount);
    }

    public void emptyItem() {
        itemList.clear();
    }

    public void getItem() {
        for (Map.Entry<Item, Integer> entry : itemList.entrySet()) {
            System.out.println(entry.getValue() + "x " + entry.getKey().getName());
        }
    }
}
