package com.buzzhive.luqman.sitemanager;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.buzzhive.luqman.definedClases.Item;
import com.buzzhive.luqman.definedClases.SiteManager;
import com.buzzhive.luqman.listAdapters.AddOrderItemAdapter;

import java.util.ArrayList;

public class AddOrderActivity extends AppCompatActivity {

    private ListView lvAddedOrders;
    private Spinner spnAoItems;
    private TextView txtAoQty;
    private Button btnAoAdd;
    private SiteManager sm;
    private Item selectedItem = null;
    private ArrayList<Item> itemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_add_order);
        this.setTitle("Add Order");
        final Context thisClass = this;
        SiteManager.automaticRedirectToLogout(this);
        sm = SiteManager.getInstance();
        itemList = sm.getItems();
        lvAddedOrders = (ListView) findViewById(R.id.lvAddedOrders);
        spnAoItems = (Spinner) findViewById(R.id.spnAoItems);
        txtAoQty = (TextView) findViewById(R.id.txtAoQty);
        btnAoAdd = (Button) findViewById(R.id.btnAoAdd);


        ArrayAdapter<String> itemsSpinnerAdapter = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,sm.getOnlyItemNames());
        spnAoItems.setAdapter(itemsSpinnerAdapter);

        this.reInstateAdapter();
        spnAoItems.setOnItemSelectedListener(new ListView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i != 0)
                    selectedItem = itemList.get(i-1);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });
        btnAoAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selectedItem != null && txtAoQty.getText().length() != 0) {
                    selectedItem.setQuantity(Integer.parseInt(txtAoQty.getText().toString()));
                    sm.getOrderBuilder().addItem(selectedItem);
                    reInstateAdapter();
                } else {
                    Toast addItemErrToast = Toast.makeText(thisClass,"Fill Quantity and select an item",Toast.LENGTH_LONG);
                    addItemErrToast.show();
                }
            }
        });
    }
    public void reInstateAdapter() {
        AddOrderItemAdapter addOrderItemAdapter = new AddOrderItemAdapter(this,sm.getOrderBuilder().getItems());
        lvAddedOrders.setAdapter(addOrderItemAdapter);
    }
}
