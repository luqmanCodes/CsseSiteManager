package com.buzzhive.luqman.sitemanager;

import com.buzzhive.luqman.definedClases.DateHelper;
import com.buzzhive.luqman.definedClases.Item;
import com.buzzhive.luqman.definedClases.OrderBuilder;
import com.buzzhive.luqman.definedClases.PurchaseOrder;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;


public class TestOrderBuilder {

     ArrayList<Item> testItems;
     OrderBuilder testOrderB;
     Item testItem;

    @Before
    public void iniatializeOrderBuilder(){
        testItem = new Item("Cement",1,5);
        testOrderB = new OrderBuilder();
    }
    @Test
    public void testAddNewItemToEmptyList() {

        testOrderB = testOrderB.addItem(testItem);
        assertNotEquals(null,testOrderB);
        assertEquals(testItem.getItemName(),testOrderB.getArrayList().get(0).getItemName());
        assertEquals(1,testOrderB.getArrayList().size());
    }
    @Test
    public void testAddSameItemToList(){
        testOrderB = testOrderB.addItem(testItem);
        testOrderB = testOrderB.addItem(testItem);
        assertNotEquals(null,testOrderB);
        assertEquals(10,testOrderB.getArrayList().get(0).getQuantity());
        assertEquals(1,testOrderB.getArrayList().size());
    }
    @Test(expected = IllegalStateException.class)
    public void testBuildAsObjectCheckException(){
        testOrderB = testOrderB.addItem(testItem);
        testOrderB = testOrderB.addItem(testItem);
        PurchaseOrder po = testOrderB.buildOrderAsObject();
    }
    @Test(expected = IllegalStateException.class)
    public void testBuildAsJSONCheckException(){
        testOrderB = testOrderB.addItem(testItem);
        testOrderB = testOrderB.addItem(testItem);
        PurchaseOrder po = testOrderB.buildOrderAsObject();
    }
    @Test
    public void testSuccesssBuildObject(){

        PurchaseOrder po = testOrderB.addItem(testItem)
                .addItem(testItem)
                .setStatus("PENDING")
                .setExpectedDate(new java.sql.Date(new java.util.Date().getTime()))
                .setInitialDate(DateHelper.getNowSQLDate())
                .setSiteManagerId("SM1")
                .buildOrderAsObject();
        assertNotEquals(null,po);
    }
    @Test(expected = RuntimeException.class)
    public void testSuccesssBuildJSON(){

        JSONObject po = testOrderB.addItem(testItem)
                .addItem(testItem)
                .setStatus("PENDING")
                .setExpectedDate(new java.sql.Date(new java.util.Date().getTime()))
                .setInitialDate(DateHelper.getNowSQLDate())
                .setSiteManagerId("SM1")
                .buildOrderAsJSONObject();
        assertNotEquals(null,po);
    }
}
