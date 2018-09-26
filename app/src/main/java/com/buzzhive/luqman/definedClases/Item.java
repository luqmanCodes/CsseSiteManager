package com.buzzhive.luqman.definedClases;

public class Item {
    private String itemName;
    private int itemId;
    private int quantity;

    public Item(String itemName, int itemId, int quantity) {
        this.itemName = itemName;
        this.itemId = itemId;
        this.quantity = quantity;
    }

    public Item(String itemName, int itemId) {
        this.itemName = itemName;
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
