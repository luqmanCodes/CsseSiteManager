package com.buzzhive.luqman.definedClases;

import java.sql.Date;
import java.util.ArrayList;

public class PurchaseOrder {
    private String siteManagerId;
    private ArrayList<Item> items;
    private String status;
    private Date initialDate;

    public PurchaseOrder(String siteManagerId, ArrayList<Item> items, String status, Date initialDate) {
        this.siteManagerId = siteManagerId;
        this.items = items;
        this.status = status;
        this.initialDate = initialDate;
    }
}
