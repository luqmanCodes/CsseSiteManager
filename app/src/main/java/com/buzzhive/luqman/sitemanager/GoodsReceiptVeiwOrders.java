package com.buzzhive.luqman.sitemanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.buzzhive.luqman.definedClases.VeiwOrderHelper;

public class GoodsReceiptVeiwOrders extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_receipt_veiw_orders);
        this.setTitle("Goods Receipts Approval");
        ListView lvGROrders = (ListView) findViewById(R.id.lvGROrders);
        VeiwOrderHelper.setOrdersToBeViewedByType(getApplicationContext(),lvGROrders,"APPROVED");
        lvGROrders.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent toGoodsListApproval = new Intent(getApplicationContext(),GoodsListApproval.class);
                toGoodsListApproval.putExtra("purchaseOrderId",VeiwOrderHelper.getOrderFromId(i).getOrderId());
                startActivity(toGoodsListApproval);
            }
        });
    }
}
