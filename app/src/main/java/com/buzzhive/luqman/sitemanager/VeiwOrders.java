

package com.buzzhive.luqman.sitemanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.buzzhive.luqman.definedClases.ChangeActivityIntentHelper;
import com.buzzhive.luqman.definedClases.VeiwOrderHelper;

public class VeiwOrders extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String ordersType;
        setContentView(R.layout.activity_veiw_orders);
        final VeiwOrders thisClass = this;
        if (!getIntent().hasExtra("orderType"))
            ChangeActivityIntentHelper.redirectToActivity(this, Dashboard.class);
        ordersType = getIntent().getStringExtra("orderType");
        this.setTitle(String.format("%s Orders", ordersType));
        ListView lvVOsPOlist = (ListView) findViewById(R.id.lvVOsPOList);
        VeiwOrderHelper.setOrdersToBeViewedByType(getApplicationContext(),lvVOsPOlist,ordersType);
        lvVOsPOlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent toVeiwOrder = new Intent(thisClass,VeiwOrder.class);
                toVeiwOrder.putExtra("purchaseOrderId",i);
                startActivity(toVeiwOrder);
            }
        });
    }

    public static class GoodReciptApprove extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_good_recipt_approve);
        }
    }
}
