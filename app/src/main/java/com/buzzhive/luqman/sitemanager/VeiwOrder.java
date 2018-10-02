package com.buzzhive.luqman.sitemanager;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;

import com.buzzhive.luqman.definedClases.ChangeActivityIntentHelper;
import com.buzzhive.luqman.definedClases.PurchaseOrder;
import com.buzzhive.luqman.listAdapters.VeiwOrderItemAdapter;

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

        if(!getIntent().hasExtra("VO.purchaseOrder"))
            ChangeActivityIntentHelper.redirectToActivity(this,Dashboard.class);

        this.setTitle("Order Details");
        this.lvVOItems = (ListView) findViewById(R.id.lvVOItems);
        this.txtVOOrderId = (TextView) findViewById(R.id.txtVOOrderId);
        this.txtVODate = (TextView) findViewById(R.id.txtVODate);
        this.txtVOTotalAmount = (TextView) findViewById(R.id.txtVOTotalAmount);
        this.orderToDisplay = (PurchaseOrder) getIntent().getSerializableExtra("VO.purchaseOrder");

        VeiwOrderItemAdapter veiwOrderItemAdapter = new VeiwOrderItemAdapter(this,this.orderToDisplay.getItems());
        this.lvVOItems.setAdapter(veiwOrderItemAdapter);

    }
}
