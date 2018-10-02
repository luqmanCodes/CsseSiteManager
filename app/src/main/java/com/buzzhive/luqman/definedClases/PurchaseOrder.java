package com.buzzhive.luqman.definedClases;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;

public class PurchaseOrder implements Serializable {
    private String siteManagerId;
    private ArrayList<Item> items;
    private String status;
    private Date initialDate;
    private Date expectedDate;
    public PurchaseOrder(String siteManagerId, ArrayList<Item> items, String status, Date initialDate, Date expectedDate) {
        this.siteManagerId = siteManagerId;
        this.items = items;
        this.status = status;
        this.initialDate = initialDate;
        this.expectedDate = expectedDate;
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
        return initialDate;
    }
}
