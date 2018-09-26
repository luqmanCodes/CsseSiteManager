package com.buzzhive.luqman.definedClases;

import java.util.ArrayList;

public class PurchaseOrder {
    private String siteManagerId;
    private ArrayList<Item> items;

    public String getSiteManagerId() {
        return siteManagerId;
    }

    public void setSiteManagerId(String siteManagerId) {
        this.siteManagerId = siteManagerId;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public void addItem(Item item) {
        this.items.add(item);
    }
    public void removeItem(int arrayId) {
        this.items.remove(arrayId);
    }
}
