package com.buzzhive.luqman.definedClases;

public class Item {
    private String name;
    private int id;
    private int quantity;

    public Item(String itemName, int itemId, int quantity) {
        this.name = itemName;
        this.id = itemId;
        this.quantity = quantity;
    }

    public Item(String itemName, int itemId) {
        this.name = itemName;
        this.id = itemId;
    }
    public String toString(){
        return String.format("{'itemId':%d,'quantity':%d}",this.id,this.quantity);
    }
    public String getItemName() {
        return name;
    }

    public void setItemName(String itemName) {
        this.name = itemName;
    }

    public int getItemId() {
        return id;
    }

    public void setItemId(int itemId) {
        this.id = itemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
