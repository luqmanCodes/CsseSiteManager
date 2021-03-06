package com.buzzhive.luqman.sitemanager;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.buzzhive.luqman.definedClases.ChangeActivityIntentHelper;
import com.buzzhive.luqman.definedClases.DateHelper;
import com.buzzhive.luqman.definedClases.Item;
import com.buzzhive.luqman.definedClases.PurchaseOrder;
import com.buzzhive.luqman.definedClases.SiteManager;
import com.buzzhive.luqman.listAdapters.AddOrderItemAdapter;

import org.json.JSONObject;

import java.sql.Date;
import java.text.ParseException;
import java.util.ArrayList;

public class AddOrderActivity extends AppCompatActivity {

    private ListView lvAddedOrders;
    private Spinner spnAoItems;
    private TextView txtAoQty;
    private EditText txtAOExpArivalDate;
    private ArrayList<Item> itemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_add_order);
        this.setTitle("Add Order");
        final Context thisClass = this;
        SiteManager.automaticRedirectToLogout(this);

        this.itemList = SiteManager.getInstance().getItems(getApplicationContext());
        this.lvAddedOrders = (ListView) findViewById(R.id.lvAddedOrders);
        this.spnAoItems = (Spinner) findViewById(R.id.spnAoItems);
        this.txtAoQty = (TextView) findViewById(R.id.txtAoQty);
        this.txtAOExpArivalDate = (EditText) findViewById(R.id.txtAOExpArivalDate);
        Button btnAoAdd = (Button) findViewById(R.id.btnAoAdd);
        Button btnAOAddOrder = (Button) findViewById(R.id.btnAOAddOrder);

        LayoutInflater lf = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View headerView = lf.inflate(R.layout.add_order_list_header,null);
        this.lvAddedOrders.addHeaderView(headerView);
        ArrayAdapter<String> itemsSpinnerAdapter = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,SiteManager.getInstance().getOnlyItemNames(itemList));
        this.spnAoItems.setAdapter(itemsSpinnerAdapter);

        this.reInstateAdapter();

        btnAoAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedItemPos = spnAoItems.getSelectedItemPosition();
                if(txtAoQty.getText().length() != 0 && selectedItemPos != 0) {
                    Item toBeAdded = itemList.get(selectedItemPos-1);
                    toBeAdded.setQuantity(Integer.parseInt(txtAoQty.getText().toString()));
                    SiteManager.getInstance().getOrderBuilder().addItem(toBeAdded);
                    reInstateAdapter();
                } else {
                    Toast addItemErrToast = Toast.makeText(thisClass,"Fill Quantity and select an item",Toast.LENGTH_LONG);
                    addItemErrToast.show();
                }
            }
        });
        btnAOAddOrder.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                try {
                    Date expArrivalDate = DateHelper.getDateFromString(txtAOExpArivalDate.getText().toString());
                    if(DateHelper.validateDate(expArrivalDate)) {
                        PurchaseOrder po = SiteManager.getInstance().getOrderBuilder()
                                .setSiteManagerId(SiteManager.getInstance().getSiteManagerId())
                                .setStatus("PENDING")
                                .setInitialDate(DateHelper.getNowSQLDate())
                                .setExpectedDate(expArrivalDate)
                                .buildOrderAsObject();
                        AndroidNetworking.post(SiteManager.baseURL.concat("/purchaseOrders"))
                                .addApplicationJsonBody(po)
                                .setContentType("application/json;charset=UTF-8")
                                .build()
                                .getAsJSONObject(new JSONObjectRequestListener() {
                                    @Override
                                    public void onResponse(JSONObject response) {
                                        Toast success = Toast.makeText(getApplicationContext(),"Added a Purchase Order Successfully",Toast.LENGTH_LONG);
                                        success.show();
                                        ChangeActivityIntentHelper.redirectToActivity(getApplicationContext(),Dashboard.class);
                                    }

                                    @Override
                                    public void onError(ANError anError) {
                                        Toast t1 = Toast.makeText(getApplicationContext(),anError.getErrorBody(),Toast.LENGTH_LONG);
                                        t1.show();
                                    }
                                });

                    } else {
                        Toast dateErrToast = Toast.makeText(thisClass,"Invalid Date",Toast.LENGTH_LONG);
                        dateErrToast.show();
                    }
                }catch(ParseException ex) {
                     Toast dateErrToast = Toast.makeText(thisClass,"Invalid Date",Toast.LENGTH_LONG);
                     dateErrToast.show();
                }catch(IllegalStateException ise) {
                    Toast dateErrToast = Toast.makeText(thisClass,ise.getMessage(),Toast.LENGTH_LONG);
                    dateErrToast.show();
                }

            }
        });

    }
    public void reInstateAdapter() {
        AddOrderItemAdapter addOrderItemAdapter = new AddOrderItemAdapter(this,SiteManager.getInstance().getOrderBuilder().getItems());
        lvAddedOrders.setAdapter(addOrderItemAdapter);
    }
}
