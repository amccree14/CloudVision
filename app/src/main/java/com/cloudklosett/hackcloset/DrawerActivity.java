package com.cloudklosett.hackcloset;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.mindorks.placeholderview.PlaceHolderView;

import java.util.LinkedList;
import java.util.List;

public class DrawerActivity extends AppCompatActivity {

    private PlaceHolderView mDrawerView;
    private DrawerLayout mDrawer;
    private Toolbar mToolbar;
    private PlaceHolderView mGalleryView;
    String email = "";
    String name = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);

        mDrawer = (DrawerLayout) findViewById(R.id.drawerLayout);
        mDrawerView = (PlaceHolderView) findViewById(R.id.drawerView);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mGalleryView = (PlaceHolderView) findViewById(R.id.galleryView);
        //String[] fields = {
        //        ContactsContract.Profile.DISPLAY_NAME
        //};
        //ContentResolver cr = this.getContentResolver();
        //Cursor cursor = cr.query(ContactsContract.Profile.CONTENT_URI, fields,
        //        null,
        //        null,
        //        null);
        //if (cursor.getCount() > 0) {
        //    cursor.moveToNext();
        //    name = cursor.getString(0);
        //    String a = cursor.getString(1);
        //    String b = cursor.getString(2);
        //    String c = cursor.getString(3);
        //    String d = cursor.getString(4);
        //}
        //email = getUserEmail(this);
        setupDrawer();
    }

    private void goToSchedule() {
        Intent i = new Intent(getApplicationContext(), SchedulerActivity.class);
        startActivity(i);
    }

    private void setupDrawer(){
        mDrawerView
                .addView(new DrawerHeader(email, name))
                .addView(new DrawerMenuItem(this.getApplicationContext(), DrawerMenuItem.DRAWER_MENU_ITEM_PROFILE))
                .addView(new DrawerMenuItem(this.getApplicationContext(), DrawerMenuItem.DRAWER_MENU_ITEM_REQUESTS))
                .addView(new DrawerMenuItem(this.getApplicationContext(), DrawerMenuItem.DRAWER_MENU_ITEM_MESSAGE))
                .addView(new DrawerMenuItem(this.getApplicationContext(), DrawerMenuItem.DRAWER_MENU_ITEM_GROUPS));

        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, mDrawer, mToolbar, R.string.open_drawer, R.string.close_drawer){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };

        mDrawer.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
    }

    public String getUserEmail(Context context) {
        AccountManager manager = AccountManager.get(context);
        if (ContextCompat.checkSelfPermission(context, android.Manifest.permission.GET_ACCOUNTS)
                == PackageManager.PERMISSION_GRANTED) {
            Account[] accounts = manager.getAccountsByType("com.google");
            List<String> possibleEmails = new LinkedList<String>();

            for (Account account : accounts) {
                possibleEmails.add(account.name);
            }

            if (!possibleEmails.isEmpty() && possibleEmails.get(0) != null) {
                return possibleEmails.get(0);
            }
        }
        return null;
    }

}
