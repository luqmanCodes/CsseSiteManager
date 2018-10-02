

package com.buzzhive.luqman.sitemanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.buzzhive.luqman.definedClases.Item;
import com.buzzhive.luqman.definedClases.PurchaseOrder;

import java.util.ArrayList;
import java.util.Arrays;

public class VeiwOrders extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_veiw_orders);

        Intent toActivity = new Intent(this,VeiwOrder.class);
        toActivity.putExtra("VO.purchaseOrder",new PurchaseOrder(
                "SM001",
                new ArrayList<Item>(Arrays.asList(
                        new Item("Cement",1,500)
                )),
                "PENDING",new java.sql.Date(new java.util.Date().getTime())
        ));
        startActivity(toActivity);
    }
}
