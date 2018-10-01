package com.buzzhive.luqman.listAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.buzzhive.luqman.definedClases.Item;
import com.buzzhive.luqman.definedClases.SiteManager;
import com.buzzhive.luqman.sitemanager.R;

import java.util.ArrayList;

public class AddOrderItemAdapter extends BaseAdapter {

    private ArrayList<Item> items;
    LayoutInflater mInflater;

    public AddOrderItemAdapter(Context context, ArrayList<Item> items) {
        this.items = items;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {

        View v = mInflater.inflate(R.layout.add_order_list_item,null);
        TextView addOrderItemName = (TextView) v.findViewById(R.id.addOrderItemName);
        TextView addOrderItemQuantity = (TextView) v.findViewById(R.id.addOrderItemQuantity);
        ImageButton btnAORemove = (ImageButton) v.findViewById(R.id.btnAORemove);
        btnAORemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SiteManager.getOrderBuilder().removeItem(i);
                notifyDataSetChanged();
            }
        });
        addOrderItemName.setText(items.get(i).getItemName());
        addOrderItemQuantity.setText(items.get(i).getQuantity()+"");
        return v;
    }
}
