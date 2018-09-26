package com.buzzhive.luqman.definedClases;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.buzzhive.luqman.sitemanager.LoginActivity;

public class ChangeActivityIntentHelper extends BaseAdapter {
    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return null;
    }

    public static void redirectToLogin(Context context) {
        Intent toLogin = new Intent(context,LoginActivity.class);
        context.startActivity(toLogin);
    }
    public static void redirectToActivity(Context context,Class activity) {
        Intent toActivity = new Intent(context,activity);
        context.startActivity(toActivity);
    }
}
