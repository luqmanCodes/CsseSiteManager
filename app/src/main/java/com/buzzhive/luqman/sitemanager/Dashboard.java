package com.buzzhive.luqman.sitemanager;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.buzzhive.luqman.definedClases.ChangeActivityIntentHelper;
import com.buzzhive.luqman.definedClases.SiteManager;

public class Dashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        this.setTitle("Site Manager Dashboard");
        final Context thisClass = this;
        SiteManager.logIn("SM101","");
        SiteManager.automaticRedirectToLogout(this);

        findViewById(R.id.btnAddOrder).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChangeActivityIntentHelper.redirectToActivity(thisClass,AddOrderActivity.class);
            }
        });
    }
}
