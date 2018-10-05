package com.buzzhive.luqman.sitemanager;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.buzzhive.luqman.definedClases.ChangeActivityIntentHelper;
import com.buzzhive.luqman.definedClases.GoodsReceipt;
import com.buzzhive.luqman.definedClases.Item;
import com.buzzhive.luqman.definedClases.SiteManager;
import com.buzzhive.luqman.listAdapters.VeiwOrderItemAdapter;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class GoodsListApproval extends AppCompatActivity {

    ArrayList<GoodsReceipt> goodsListByOrder;
    boolean didClick = false;
    int purchaseOrderId = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_list_approval);


        if (!getIntent().hasExtra("purchaseOrderId"))
            ChangeActivityIntentHelper.redirectToActivity(getApplicationContext(),Dashboard.class);
        purchaseOrderId = getIntent().getIntExtra("purchaseOrderId",0);

        final Spinner spnGRGoodsRecipts = (Spinner) findViewById(R.id.spnGRGoodsRecipts);
        final ListView lvGRItems = (ListView) findViewById(R.id.lvGRItems);


        this.setTitle("Goods List Approval :"+purchaseOrderId);
        AndroidNetworking.get(SiteManager.baseURL.concat("/purchaseOrders/{id}/goodsRecipts"))
                .addQueryParameter("status","PENDING")
                .addPathParameter("id",purchaseOrderId+"")
                .build()
                .getAsObjectList(GoodsReceipt.class, new ParsedRequestListener<List<GoodsReceipt>>() {
                    @Override
                    public void onResponse(List<GoodsReceipt> response) {
                        goodsListByOrder = (ArrayList) response;
                        List<String> orderIdList = new ArrayList<>();
                        orderIdList.add("Select Goods Receipt Id");
                        for (GoodsReceipt gr:response)
                            orderIdList.add(gr.getId()+"");
                        spnGRGoodsRecipts.setAdapter(new ArrayAdapter<String>(getApplicationContext(),R.layout.support_simple_spinner_dropdown_item,orderIdList));
                    }
                    @Override
                    public void onError(ANError anError) {
                        Toast t = Toast.makeText(getApplicationContext(),anError.getErrorDetail(),Toast.LENGTH_LONG);
                        t.show();
                    }
                });
        findViewById(R.id.btnGRGetItems).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(spnGRGoodsRecipts.getSelectedItemPosition() == 0){
                    Toast t = Toast.makeText(getApplicationContext(),"Select A Goods Receipt Id",Toast.LENGTH_LONG);
                    t.show();
                }else {
                    final ArrayList<Item> list = new ArrayList<>();
                    AndroidNetworking.get(SiteManager.baseURL.concat("/purchaseOrders/{prId}/goodsRecipts/{grId}/items"))
                            .addPathParameter("prId",purchaseOrderId+"")
                            .addPathParameter("grId",spnGRGoodsRecipts.getSelectedItem().toString())
                            .build()
                            .getAsJSONArray(new JSONArrayRequestListener() {
                                @Override
                                public void onResponse(JSONArray response) {
                                    for (int i = 0; i < response.length(); i++) {
                                        JSONArray jsonArr = response.optJSONArray(i);
                                        list.add(new Item(jsonArr.optString(1),jsonArr.optInt(0),jsonArr.optInt(2)));
                                    }
                                    VeiwOrderItemAdapter veiwOrderItemAdapter = new VeiwOrderItemAdapter(getApplicationContext(),list);
                                    lvGRItems.setAdapter(veiwOrderItemAdapter);
                                    didClick = true;
                                }

                                @Override
                                public void onError(ANError anError) {

                                }
                            });
                }
            }
        });
        findViewById(R.id.btnGRApprove).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(spnGRGoodsRecipts.getSelectedItemPosition() == 0 || !didClick){
                    Toast t = Toast.makeText(getApplicationContext(),"Select A Goods Receipt Id And Pull Items",Toast.LENGTH_LONG);
                    t.show();
                }else {
                    AndroidNetworking.put(SiteManager.baseURL.concat("/goodsReceipts/{goodsReceiptId}/statusOnly"))
                            .addPathParameter("goodsReceiptId",spnGRGoodsRecipts.getSelectedItem().toString())
                            .addQueryParameter("status","APPROVED")
                            .build()
                            .getAsJSONObject(new JSONObjectRequestListener() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    lvGRItems.setAdapter(null);
                                    didClick = false;
                                }

                                @Override
                                public void onError(ANError anError) {
                                    Toast t1 = Toast.makeText(getApplicationContext(),anError.getErrorDetail(),Toast.LENGTH_LONG);
                                    t1.show();
                                }
                            });
                }
            }
        });
        findViewById(R.id.btnGRDecline).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(spnGRGoodsRecipts.getSelectedItemPosition() == 0 || !didClick){
                    Toast t = Toast.makeText(getApplicationContext(),"Select A Goods Receipt Id And Pull Items",Toast.LENGTH_LONG);
                    t.show();
                }else {
                    AndroidNetworking.put(SiteManager.baseURL.concat("/goodsReceipts/{goodsReceiptId}/statusOnly"))
                            .addPathParameter("goodsReceiptId",spnGRGoodsRecipts.getSelectedItem().toString())
                            .addQueryParameter("status","REJECTED")
                            .build()
                            .getAsJSONObject(new JSONObjectRequestListener() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    lvGRItems.setAdapter(null);
                                    didClick = false;
                                }

                                @Override
                                public void onError(ANError anError) {
                                    Toast t1 = Toast.makeText(getApplicationContext(),anError.getErrorDetail(),Toast.LENGTH_LONG);
                                    t1.show();
                                }
                            });
                }
            }
        });
    }
}
