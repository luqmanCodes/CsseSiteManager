package com.buzzhive.luqman.sitemanager;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.buzzhive.luqman.definedClases.SiteManager;
import com.buzzhive.luqman.listAdapters.AddOrderItemAdapter;

public class AddOrderActivity extends AppCompatActivity {

    ListView lvAddedOrders;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_order);
        this.setTitle("Add Order");
        SiteManager sm = SiteManager.getInstance();


        lvAddedOrders = (ListView) findViewById(R.id.lvAddedOrders);
        AddOrderItemAdapter addOrderItemAdapter = new AddOrderItemAdapter(this,sm.getItems());
        lvAddedOrders.setAdapter(addOrderItemAdapter);
    }
}
