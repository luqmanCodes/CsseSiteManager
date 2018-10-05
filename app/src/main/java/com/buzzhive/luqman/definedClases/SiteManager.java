package com.buzzhive.luqman.definedClases;

import android.content.Context;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.ANRequest;
import com.androidnetworking.common.ANResponse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class  SiteManager {

    private String siteManagerId;
    private static SiteManager singleInstance = null;
    private static boolean isLoggedIn = false;
    public static final String baseURL = "http://192.168.43.46:9000";
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

    public ArrayList<Item> getItems(Context con){
        String uri = baseURL.concat("/items");
        ANRequest request = AndroidNetworking.get(uri).build();
        ANResponse<List<Item>> response = request.executeForObjectList(Item.class);
        if(response.isSuccess()) {
            return (ArrayList<Item>) response.getResult();
        } else {
            Toast t = Toast.makeText(con,response.getError().getErrorDetail(),Toast.LENGTH_LONG);
            t.show();
        }
        return null;
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
        final List<String> itemNames = new ArrayList<>();
        itemNames.add("Select An Item");
        for( Item i : tItems)
            itemNames.add(i.getItemName());
        return itemNames;
    }


}
