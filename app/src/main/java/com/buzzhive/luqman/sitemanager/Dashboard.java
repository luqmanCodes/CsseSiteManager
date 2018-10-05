package com.buzzhive.luqman.sitemanager;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.androidnetworking.AndroidNetworking;
import com.buzzhive.luqman.definedClases.ChangeActivityIntentHelper;
import com.buzzhive.luqman.definedClases.SiteManager;

public class Dashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        this.setTitle("Site Manager Dashboard");
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitNetwork().build();
        StrictMode.setThreadPolicy(policy);
        SiteManager.automaticRedirectToLogout(this);
        AndroidNetworking.initialize(getApplicationContext());

        findViewById(R.id.btnAddOrder).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChangeActivityIntentHelper.redirectToActivity(getApplicationContext(),AddOrderActivity.class);
            }
        });
        findViewById(R.id.btnVeiwOrders).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChangeActivityIntentHelper.redirectToVeiwOrderSetType(getApplicationContext(),"All");
            }
        });
        findViewById(R.id.btnApprovedOrders).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChangeActivityIntentHelper.redirectToVeiwOrderSetType(getApplicationContext(),"Approved");
            }
        });
        findViewById(R.id.btnRejectedOrders).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChangeActivityIntentHelper.redirectToVeiwOrderSetType(getApplicationContext(),"Rejected");
            }
        });
        findViewById(R.id.btnPendingOrders).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChangeActivityIntentHelper.redirectToVeiwOrderSetType(getApplicationContext(),"Pending");
            }
        });
        findViewById(R.id.btnApproveGoodsList).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChangeActivityIntentHelper.redirectToActivity(getApplicationContext(),GoodsReceiptVeiwOrders.class);
            }
        });
        findViewById(R.id.btnLogOut).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SiteManager.getInstance().loggOut();
                ChangeActivityIntentHelper.redirectToLogin(getApplicationContext());
            }
        });


    }
}
