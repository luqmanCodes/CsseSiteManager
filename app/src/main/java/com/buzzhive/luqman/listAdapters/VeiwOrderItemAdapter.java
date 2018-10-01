package com.buzzhive.luqman.listAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.buzzhive.luqman.definedClases.Item;
import com.buzzhive.luqman.sitemanager.R;

import java.util.ArrayList;

public class VeiwOrderItemAdapter extends BaseAdapter {

    ArrayList<Item> listItems;
    LayoutInflater mInflator;

    public VeiwOrderItemAdapter(Context context,ArrayList<Item> items) {
        this.listItems = items;
        this.mInflator = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return listItems.size();
    }

    @Override
    public Object getItem(int i) {
        return listItems.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = mInflator.inflate(R.layout.veiw_order_list_item,null);
        TextView txtVOItemName = (TextView) v.findViewById(R.id.txtVOItemName);
        TextView txtVOItemQuantity = (TextView) v.findViewById(R.id.txtVOItemQuantity);

        txtVOItemName.setText(listItems.get(i).getItemName());
        txtVOItemQuantity.setText(listItems.get(i).getQuantity());
        return v;
    }
}
