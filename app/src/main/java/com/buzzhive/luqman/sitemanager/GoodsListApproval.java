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
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.buzzhive.luqman.definedClases.ChangeActivityIntentHelper;
import com.buzzhive.luqman.definedClases.GoodsReceipt;
import com.buzzhive.luqman.definedClases.SiteManager;

import java.util.ArrayList;
import java.util.List;

public class GoodsListApproval extends AppCompatActivity {

    ArrayList<GoodsReceipt> goodsListByOrder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_list_approval);

        int purchaseOrderId = 0;
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

                }
            }
        });
    }
}
