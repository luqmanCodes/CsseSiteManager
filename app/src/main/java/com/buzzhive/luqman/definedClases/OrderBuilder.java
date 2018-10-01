package com.buzzhive.luqman.definedClases;

import android.util.Log;

import java.sql.Date;
import java.util.ArrayList;

public class OrderBuilder {

    private ArrayList<Item> items;
    private String siteManagerId;
    private String status;
    private Date initialDate;

    public OrderBuilder() {
        this.items = new ArrayList<Item>();
    }
    public ArrayList<Item> getItems() {
        return items;
    }

    public OrderBuilder addItem(Item item) {

        this.items.add(item);
        for(Item tempItem : this.items) {
            if(tempItem.getItemId() == item.getItemId()) {
                Log.d("item",tempItem.getItemName());
            }
        }
        return this;
    }
    public OrderBuilder removeItem(int id) {
        this.items.remove(id);
        return this;
    }
    public OrderBuilder setItems(ArrayList<Item> items) {
        this.items = items;
        return this;
    }
    public String getSiteManagerId() {
        return siteManagerId;
    }
    public OrderBuilder setSiteManagerId(String siteManagerId) {
        this.siteManagerId = siteManagerId;
        return this;
    }
    public String getStatus() {
        return status;
    }
    public OrderBuilder setStatus(String status) {
        this.status = status;
        return this;
    }
    public Date getInitialDate() {
        return initialDate;
    }
    public OrderBuilder setInitialDate(Date initialDate) {
        this.initialDate = initialDate;
        return this;
    }
    public PurchaseOrder buildOrderAsObject() {
        return new PurchaseOrder(
            this.siteManagerId,
            this.items,
            this.status,
            this.initialDate
        );
    }
}
