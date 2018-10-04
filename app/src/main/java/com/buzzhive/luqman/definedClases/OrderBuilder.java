package com.buzzhive.luqman.definedClases;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Date;
import java.util.ArrayList;

public class OrderBuilder {

    private ArrayList<Item> items;
    private String siteManagerId;
    private String status;
    private Date initialDate;
    private Date expectedDate;

    public OrderBuilder() {
        this.items = new ArrayList<Item>();
    }
    public ArrayList<Item> getItems() {
        return items;
    }

    public OrderBuilder addItem(Item item) {

        for(int i = 0;i<this.items.size();i++) {
            if(this.items.get(i).getItemId() == item.getItemId()) {
                Item toBeAdded = new Item(item.getItemName(),item.getItemId(),this.items.get(i).getQuantity()+item.getQuantity());
                this.items.set(i,toBeAdded);
                return this;
            }
        }
        this.items.add(item);
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

    public Date getExpectedDate() {
        return expectedDate;
    }

    public OrderBuilder setExpectedDate(Date expectedDate) {
        this.expectedDate = expectedDate;
        return this;
    }

    public PurchaseOrder buildOrderAsObject() throws IllegalStateException {
        if(items.size() == 0) {
            throw new IllegalStateException("No items in Order");
        }
        else if (this.siteManagerId.isEmpty() || this.status.isEmpty()
                || this.getInitialDate() == null || this.expectedDate == null){
            throw new IllegalStateException("Purchase Object fields missing");
        }
        else {
            PurchaseOrder temp = new PurchaseOrder(
                    this.siteManagerId,
                    this.items,
                    this.status,
                    this.initialDate,
                    this.expectedDate
            );
            this.items = new ArrayList<>();
            return temp;
        }
    }
    public JSONObject buildOrderAsJSONObject() throws IllegalStateException {
        if(items.size() == 0) {
            throw new IllegalStateException("No items in Order");
        }
        else if (this.siteManagerId.isEmpty() || this.status.isEmpty()
                || this.getInitialDate() == null || this.expectedDate == null){
            throw new IllegalStateException("Purchase Object fields missing");
        }
        else {
            Log.d("dint","please");
            JSONObject retobject = new JSONObject();
            try {
                retobject.put("siteManagerId",getSiteManagerId());
                retobject.put("items",getItems());
                retobject.put("status",getStatus());
                retobject.put("initiatedDate",getInitialDate().toString());
                retobject.put("expectedDate",getExpectedDate().toString());
            }catch(JSONException jsex) {
                Log.d("happends",jsex.getMessage());
            }
            Log.d("obj",retobject.toString());
            return retobject;
        }
    }
}
