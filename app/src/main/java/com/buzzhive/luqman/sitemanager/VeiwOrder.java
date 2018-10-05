package com.buzzhive.luqman.sitemanager;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.buzzhive.luqman.definedClases.ChangeActivityIntentHelper;
import com.buzzhive.luqman.definedClases.Item;
import com.buzzhive.luqman.definedClases.PurchaseOrder;
import com.buzzhive.luqman.definedClases.SiteManager;
import com.buzzhive.luqman.definedClases.VeiwOrderHelper;
import com.buzzhive.luqman.listAdapters.VeiwOrderItemAdapter;

import org.json.JSONArray;

import java.util.ArrayList;

public class VeiwOrder extends AppCompatActivity {

    private ListView lvVOItems;
    private TextView txtVOOrderId;
    private TextView txtVODate;
    private TextView txtVOTotalAmount;
    private PurchaseOrder orderToDisplay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_veiw_order);

        if(!getIntent().hasExtra("purchaseOrderId"))
            ChangeActivityIntentHelper.redirectToActivity(this,Dashboard.class);

        this.setTitle("Order Details");
        this.lvVOItems = (ListView) findViewById(R.id.lvVOItems);
        this.txtVOOrderId = (TextView) findViewById(R.id.txtVOOrderId);
        this.txtVODate = (TextView) findViewById(R.id.txtVODate);
        //this.txtVOTotalAmount = (TextView) findViewById(R.id.txtVOTotalAmount);

        int selectedPurchaseOrderId = getIntent().getIntExtra("purchaseOrderId",0);
        this.orderToDisplay = VeiwOrderHelper.getOrderFromId(selectedPurchaseOrderId);
        this.txtVOOrderId.setText(String.format("%d", orderToDisplay.getOrderId()));
        this.txtVODate.setText(orderToDisplay.getInitialDate().toString());
        final ArrayList<Item> list = new ArrayList<>();
        AndroidNetworking.get(SiteManager.baseURL.concat("/purchaseOrders/{id}/items"))
                .addPathParameter("id",orderToDisplay.getOrderId()+"")
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i = 0; i < response.length(); i++) {
                            JSONArray jsonArr = response.optJSONArray(i);
                            list.add(new Item(jsonArr.optString(1),jsonArr.optInt(0),jsonArr.optInt(2)));
                        }
                        VeiwOrderItemAdapter veiwOrderItemAdapter = new VeiwOrderItemAdapter(getApplicationContext(),list);
                        lvVOItems.setAdapter(veiwOrderItemAdapter);
                    }
                    @Override
                    public void onError(ANError anError) {

                    }
                });
    }
}
