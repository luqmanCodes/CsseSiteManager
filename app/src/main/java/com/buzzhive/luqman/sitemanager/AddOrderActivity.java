package com.buzzhive.luqman.sitemanager;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.buzzhive.luqman.definedClases.SiteManager;
import com.buzzhive.luqman.listAdapters.AddOrderItemAdapter;

public class AddOrderActivity extends AppCompatActivity {

    private ListView lvAddedOrders;
    private Spinner spnAoItems;
    private TextView txtAoQty;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_order);
        this.setTitle("Add Order");
        SiteManager.automaticRedirectToLogout(this);

        SiteManager sm = SiteManager.getInstance();

        lvAddedOrders = (ListView) findViewById(R.id.lvAddedOrders);
        spnAoItems = (Spinner) findViewById(R.id.spnAoItems);
        txtAoQty = (TextView) findViewById(R.id.txtAoQty);

        ArrayAdapter<String> itemsSpinnerAdapter = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,sm.getOnlyItemNames());
        spnAoItems.setAdapter(itemsSpinnerAdapter);

        AddOrderItemAdapter addOrderItemAdapter = new AddOrderItemAdapter(this,sm.getOrderBuilder().getItems());
        lvAddedOrders.setAdapter(addOrderItemAdapter);
    }
}
