package com.buzzhive.luqman.sitemanager;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.buzzhive.luqman.definedClases.Item;
import com.buzzhive.luqman.definedClases.PurchaseOrder;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;

public class VeiwOrder extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_veiw_order);


        ArrayList<PurchaseOrder> mockPurchaseOrder = new ArrayList<PurchaseOrder>();
        mockPurchaseOrder.add(new PurchaseOrder("SM001",new ArrayList<Item>(Arrays.asList(new Item[]{
                new Item("Cement",5,50),
                new Item("Steel",6,50)
        })),"Approved",new Date(2018,4,25)));
    }
}
