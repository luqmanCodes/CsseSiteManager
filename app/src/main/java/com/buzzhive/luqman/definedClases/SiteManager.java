package com.buzzhive.luqman.definedClases;

import android.content.Context;

import java.util.ArrayList;
import java.util.Arrays;

public class SiteManager {
    private String siteManagerId;
    private static SiteManager singleInstance = null;
    private static boolean isLogedIn = false;

    private SiteManager(String siteManagerId) {
        this.siteManagerId = siteManagerId;
    }
    public static boolean logIn(String id,String password) {
        if(singleInstance != null)
            return false;
        singleInstance = new SiteManager(id);
        isLogedIn = true;
        return true;
    }
    public static boolean logOut() {
        singleInstance = null;
        isLogedIn = false;
        return true;
    }
    public static boolean isLogedIn() {
        return isLogedIn;
    }
    public static void automaticRedirectToLogout(Context context) {
        if(!isLogedIn)
            ChangeActivityIntentHelper.redirectToLogin(context);
    }

    public static SiteManager getInstance() {
        return (isLogedIn) ? singleInstance : null;
    }
    public ArrayList<Item> getItems(){
        return new ArrayList<Item>(Arrays.asList(new Item[]{
                new Item("Cement",0),
                new Item("Steel",1),
                new Item("Wood",3),
                new Item("Rocks",4),
                new Item("Marble",5),
                new Item("Pipes",6)
        }));
    };
}
