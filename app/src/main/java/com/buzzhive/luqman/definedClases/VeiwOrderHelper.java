package com.buzzhive.luqman.definedClases;

import java.util.ArrayList;
import java.util.List;

public class VeiwOrderHelper {

    private static ArrayList<PurchaseOrder> ordersToBeViewed = null;
    private static String currentInStoreOrderType = null;


    private VeiwOrderHelper(){}
    public static void setOrdersToBeViewedByType(String type) {
        currentInStoreOrderType = type;
        switch (type.toUpperCase()) {
            case "ALL" :
                ordersToBeViewed = SiteManager.getInstance().getAllPurchaseOrders();
            break;
            case "REJECTED":
                ordersToBeViewed = SiteManager.getInstance().getAllRejectedPurchaseOrders();
            break;
            case "APPROVED":
                ordersToBeViewed = SiteManager.getInstance().getAllApprovedPurchaseOrders();
            break;
            case "PENDING":
                ordersToBeViewed = SiteManager.getInstance().getAllPendingPurchaseOrders();
            break;
        }
    }
    public static List<Integer> getOnlyPurchaseOrderIds(ArrayList<PurchaseOrder> arPurOrders) {
        List<Integer> onlyOrderIdsArr = new ArrayList<Integer>();
        for (PurchaseOrder po: ordersToBeViewed)
            onlyOrderIdsArr.add(po.getOrderId());
        return onlyOrderIdsArr;
    }
    public static ArrayList<PurchaseOrder> getOrdersToBeVeiwed() {
        return ordersToBeViewed;
    }
    public static String getCurrentInStoreOrderType() {
        return currentInStoreOrderType;
    }
    public static PurchaseOrder getOrderFromId(int id) {
        return ordersToBeViewed.get(id);
    }
    public static ArrayList<PurchaseOrder> getOrdersToBeViewed() {
        return ordersToBeViewed;
    }
}
