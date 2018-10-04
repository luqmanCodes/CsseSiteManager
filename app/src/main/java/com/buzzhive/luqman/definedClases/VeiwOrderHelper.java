package com.buzzhive.luqman.definedClases;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.buzzhive.luqman.sitemanager.R;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class VeiwOrderHelper {

    private static ArrayList<PurchaseOrder> ordersToBeViewed = null;
    private static String currentInStoreOrderType = null;


    private VeiwOrderHelper(){}
    public static void setOrdersToBeViewedByType(final Context context, final ListView listView, String type) {

        currentInStoreOrderType = type;
        switch (type.toUpperCase()) {
            case "ALL" :

                AndroidNetworking.get(SiteManager.baseURL.concat("/purchaseOrders/bySiteManagerId"))
                        .addQueryParameter("siteManagerId",SiteManager.getInstance().getSiteManagerId())
                        .addQueryParameter("status","ALL")
                        .build()
                        .getAsJSONArray(new PurchaseOrderJSONListener(context,listView));
            break;
            case "REJECTED":
                AndroidNetworking.get(SiteManager.baseURL.concat("/purchaseOrders/bySiteManagerId"))
                        .addQueryParameter("siteManagerId",SiteManager.getInstance().getSiteManagerId())
                        .addQueryParameter("status","REJECTED")
                        .build()
                        .getAsJSONArray(new PurchaseOrderJSONListener(context,listView));
            break;
            case "APPROVED":
                AndroidNetworking.get(SiteManager.baseURL.concat("/purchaseOrders/bySiteManagerId"))
                        .addQueryParameter("siteManagerId",SiteManager.getInstance().getSiteManagerId())
                        .addQueryParameter("status","SUPPLIER_APPROVED")
                        .build()
                        .getAsJSONArray(new PurchaseOrderJSONListener(context,listView));
            break;
            case "PENDING":
                AndroidNetworking.get(SiteManager.baseURL.concat("/purchaseOrders/bySiteManagerId"))
                        .addQueryParameter("siteManagerId",SiteManager.getInstance().getSiteManagerId())
                        .addQueryParameter("status","PENDING")
                        .build()
                        .getAsJSONArray(new PurchaseOrderJSONListener(context,listView));
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

    private static class PurchaseOrderJSONListener implements JSONArrayRequestListener {
        Context context;
        ListView listView;
        PurchaseOrderJSONListener(Context icontext,ListView ilistView) {
            context = icontext;
            listView = ilistView;
            listView.setAdapter(null);
            ordersToBeViewed = new ArrayList<>();
        }
        @Override
        public void onResponse(JSONArray response) {
            for (int i = 0; i < response.length() ; i++) {
                ordersToBeViewed.add(new PurchaseOrder(response.optJSONObject(i).optInt("id"),
                        DateHelper.getDateFromJSONString(response.optJSONObject(i).optString("initiatedDate"))));
            }
            listView.setAdapter(new ArrayAdapter<Integer>(context, R.layout.support_simple_spinner_dropdown_item,
                    VeiwOrderHelper.getOnlyPurchaseOrderIds(ordersToBeViewed)));
        }
        @Override
        public void onError(ANError anError) {
            Toast t = Toast.makeText(context,anError.getErrorDetail(),Toast.LENGTH_LONG);
            t.show();
        }
    }
}
