package com.buzzhive.luqman.definedClases;

import android.content.Context;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SiteManager {

    private String siteManagerId;
    private static SiteManager singleInstance = null;
    private static boolean isLoggedIn = false;
    private OrderBuilder orderBuilder = new OrderBuilder();

    private SiteManager(String siteManagerId) {
        this.siteManagerId = siteManagerId;
    }
    public static boolean logIn(String id,String password) {
        if(singleInstance != null)
            return false;
        singleInstance = new SiteManager(id);
        isLoggedIn = true;
        return true;
    }
    public boolean loggOut() {
        singleInstance = null;
        isLoggedIn = false;
        return true;
    }
    public static boolean isLoggedIn() {
        return isLoggedIn;
    }

    public static void automaticRedirectToLogout(Context context) {
        if(!isLoggedIn)
            ChangeActivityIntentHelper.redirectToLogin(context);
    }
    public OrderBuilder getOrderBuilder() {
        return orderBuilder;
    }
    public static SiteManager getInstance() {
        return (isLoggedIn) ? singleInstance : null;
    }

    public ArrayList<Item> getItems(){
         return new ArrayList<>(Arrays.asList(
                 new Item("Cement",0),
                 new Item("Steel",1),
                 new Item("Wood",3),
                 new Item("Rocks",4),
                 new Item("Marble",5),
                 new Item("Pipes",6))
         );
    }

    public ArrayList<PurchaseOrder> getAllPurchaseOrders() {
        return new ArrayList<PurchaseOrder>(Arrays.asList(
           new PurchaseOrder(
                   3,
             getSiteManagerId(),
             new ArrayList<Item>(Arrays.asList(
                     new Item("Cement",0,5)
             )),
             "PENDING",
             DateHelper.getNowSQLDate(),
             DateHelper.getNowSQLDate()
           )
        ));
    }
    public ArrayList<PurchaseOrder> getAllRejectedPurchaseOrders() {
        return new ArrayList<PurchaseOrder>(Arrays.asList(
                new PurchaseOrder(
                        1,
                        getSiteManagerId(),
                        new ArrayList<Item>(Arrays.asList(
                                new Item("Cement",0,5)
                        )),
                        "REJECTED",
                        DateHelper.getNowSQLDate(),
                        DateHelper.getNowSQLDate()
                )
        ));
    }
    public ArrayList<PurchaseOrder> getAllApprovedPurchaseOrders() {
        return new ArrayList<PurchaseOrder>(Arrays.asList(
                new PurchaseOrder(
                        4,
                        getSiteManagerId(),
                        new ArrayList<Item>(Arrays.asList(
                                new Item("Cement",0,5)
                        )),
                        "SUPPLIER_APPROVED",
                        DateHelper.getNowSQLDate(),
                        DateHelper.getNowSQLDate()
                )
        ));
    }
    public ArrayList<PurchaseOrder> getAllPendingPurchaseOrders() {
        return new ArrayList<PurchaseOrder>(Arrays.asList(
                new PurchaseOrder(
                        7,
                        getSiteManagerId(),
                        new ArrayList<Item>(Arrays.asList(
                                new Item("Cement",0,5)
                        )),
                        "PENDING",
                        DateHelper.getNowSQLDate(),
                        DateHelper.getNowSQLDate()
                )
        ));
    }
    public String getSiteManagerId() {
        return this.siteManagerId;
    }
    public List<String> getOnlyItemNames(ArrayList<Item> tItems) {
        List<String> itemNames = new ArrayList<>();
        itemNames.add("Select An Item");
        for( Item i : tItems)
            itemNames.add(i.getItemName());
        return itemNames;
    }


}
