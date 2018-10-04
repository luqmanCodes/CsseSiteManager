package com.buzzhive.luqman.definedClases;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;

public class PurchaseOrder implements Serializable {

    private int id;
    private String siteManagerId;
    private ArrayList<Item> items;
    private String status;
    private Date initiatedDate;
    private Date expectedDate;
    private double totalAmount;

    public PurchaseOrder(String siteManagerId, ArrayList<Item> items, String status, Date initiatedDate, Date expectedDate) {
        this.siteManagerId = siteManagerId;
        this.items = items;
        this.status = status;
        this.initiatedDate = initiatedDate;
        this.expectedDate = expectedDate;
    }
    public PurchaseOrder(int id,String siteManagerId, ArrayList<Item> items, String status, Date initiatedDate, Date expectedDate) {
        this.id = id;
        this.siteManagerId = siteManagerId;
        this.items = items;
        this.status = status;
        this.initiatedDate = initiatedDate;
        this.expectedDate = expectedDate;
    }
    public PurchaseOrder(int id,Date initiatedDate) {
        this.id = id;
        this.initiatedDate = initiatedDate;
    }
    public String getSiteManagerId() {
        return siteManagerId;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public String getStatus() {
        return status;
    }

    public Date getInitialDate() {
        return initiatedDate;
    }

    public int getOrderId() {
        return id;
    }

    public Date getExpectedDate() {
        return expectedDate;
    }
}
